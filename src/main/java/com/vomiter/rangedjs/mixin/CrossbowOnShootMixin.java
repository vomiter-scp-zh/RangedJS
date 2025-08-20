package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.ArrowShootingProperties;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import com.vomiter.rangedjs.item.crossbow.CrossbowProperties;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;getShootingPower(Lnet/minecraft/world/item/component/ChargedProjectiles;)F"))
    private static float modifySpeed(float original, @Local ChargedProjectiles chargedProjectiles,
                                     @Local ItemStack itemStack
    ){
        return chargedProjectiles.contains(Items.FIREWORK_ROCKET) ? 1.6F : ((CrossbowItemInterface)itemStack.getItem()).rjs$getArrowSpeedScale();
    }


    @ModifyExpressionValue(method = "createProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;createProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;"))
    private static Projectile modifyShootArrow(
            Projectile original, @Local(argsOnly = true, ordinal = 0) ItemStack crossbow
            ){
                var crossbowItem = (CrossbowItemInterface)crossbow.getItem();
                if(original instanceof AbstractArrow arrow){
                    arrow.setBaseDamage(crossbowItem.rjs$getBaseDamage());
                    if(crossbowItem.rjs$getPower() > 0){
                        arrow.setBaseDamage(arrow.getBaseDamage() + 0.5 + 0.5 * crossbowItem.rjs$getPower());
                    }
                    if(crossbowItem.rjs$isFlamingArrow()){
                        arrow.setRemainingFireTicks(20 * 100);
                    }
                    if(crossbowItem.rjs$isNoDamage()){
                        arrow.setBaseDamage(0);
                    }
                    if(crossbowItem.rjs$isInfinity()){
                        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    /*
                    arrow.setKnockback(arrow.getKnockback() + crossbowItem.rjs$getKnockBack());
                    if(crossbowItem.rjs$getBowAttributes().getPierce() > 0) {
                        arrow.setPierceLevel(
                                (byte)(crossbowItem.rjs$getBowAttributes().getPierce() + arrow.getPierceLevel())
                        );
                    }
                     */
                    ((ProjectileInterface)arrow).rangedjs$setHitBehavior(crossbowItem.rjs$getHitBehavior());

                }
                return original;
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
