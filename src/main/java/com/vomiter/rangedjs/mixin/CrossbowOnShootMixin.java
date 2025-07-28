package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.vomiter.rangedjs.item.ArrowShootingProperties;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import com.vomiter.rangedjs.item.crossbow.CrossbowProperties;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CrossbowItem.class)
public abstract class CrossbowOnShootMixin implements CrossbowItemInterface {
    @Unique
    private CrossbowProperties rjs$bowProperties = new CrossbowProperties();

    @Override
    @Unique
    public CrossbowProperties rjs$getBowProperties(){return this.rjs$bowProperties;}

    @Override
    @Unique
    public void rjs$setBowProperties(ArrowShootingProperties bowProperties){this.rjs$bowProperties = (CrossbowProperties) bowProperties;}

    @Inject(method = "getChargeDuration", at = @At("TAIL"), cancellable = true)
    private static void modifyCharge(ItemStack crossbow, CallbackInfoReturnable<Integer> cir, @Local int quickChargeLevel){
        var crossbowItem = (CrossbowItemInterface)crossbow.getItem();
        cir.setReturnValue(
                crossbowItem.rjs$getFullChargeTick() - quickChargeLevel * 5
        );
    }

    @Inject(method = "getShootingPower", at = @At("TAIL"), cancellable = true)
    private static void modifySpeed(ItemStack crossbow, CallbackInfoReturnable<Float> cir){
        var crossbowItem = (CrossbowItemInterface)crossbow.getItem();
        cir.setReturnValue(
                CrossbowItem.containsChargedProjectile(crossbow, Items.FIREWORK_ROCKET) ?
                        1.6F
                        : crossbowItem.rjs$getArrowSpeedScale()
        );
    }

    @Inject(method = "getArrow", at = @At("TAIL"))
    private static void modifyShootArrow(
            Level level, LivingEntity livingEntity, ItemStack crossbow, ItemStack ammoItem,
            CallbackInfoReturnable<AbstractArrow> cir,
            @Local AbstractArrow arrow
            ){
        if(livingEntity instanceof Player player){

        }
        var crossbowItem = (CrossbowItemInterface)crossbow.getItem();
        arrow.setBaseDamage(crossbowItem.rjs$getBaseDamage());
        if(crossbowItem.rjs$getPower() > 0){
            arrow.setBaseDamage(arrow.getBaseDamage() + 0.5 + 0.5 * crossbowItem.rjs$getPower());
        }
        arrow.setKnockback(arrow.getKnockback() + crossbowItem.rjs$getKnockBack());
        if(crossbowItem.rjs$isFlamingArrow()){
            arrow.setSecondsOnFire(100);
        }
        if(crossbowItem.rjs$isNoDamage()){
            arrow.setBaseDamage(0);
        }
        if(crossbowItem.rjs$isInfinity()){
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
        if(crossbowItem.rjs$getBowAttributes().getPierce() > 0) {
            arrow.setPierceLevel(
                    (byte)(crossbowItem.rjs$getBowAttributes().getPierce() + arrow.getPierceLevel())
            );
        }

        ((ProjectileInterface)arrow).rangedjs$setHitBehavior(crossbowItem.rjs$getHitBehavior());
    }

    @ModifyVariable(method="tryLoadProjectiles", at = @At("STORE"))
    private static boolean infinity(boolean bl, @Local(argsOnly = true) ItemStack crossbow){
        CrossbowItemInterface crossbowItem = (CrossbowItemInterface)crossbow.getItem();
        return crossbowItem.rjs$isInfinity()||bl;
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onShoot(
            Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        CrossbowUseContext ctx = new CrossbowUseContext(level, player, hand, cir);
        ItemStack item = player.getItemInHand(hand);
        if(!CrossbowItem.isCharged(item)) return;
        this.rjs$getCrossbowShootCallback().accept(ctx);
        if(ctx.getResult().equals(UseContext.Result.DENY)) cir.setReturnValue(InteractionResultHolder.fail(item));
    }
}
