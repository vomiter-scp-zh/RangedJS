package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
    private SoundEvent SHOOT_SOUND;
    public void rjs$setShootSound(SoundEvent soundEvent) {SHOOT_SOUND = soundEvent;}
    public SoundEvent rjs$getShootSound() { return SHOOT_SOUND; }
    @WrapOperation(
            method = "shootProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"
            )
    )
    private void changeSoundEvent(
            Level level,
            Player player,
            double x, double y, double z,
            SoundEvent sound,
            SoundSource source,
            float volume,
            float pitch,
            Operation<Void> original
    ) {
        SoundEvent out = sound;

        SoundEvent custom = rjs$getShootSound();
        if (custom != null) out = custom;

        original.call(level, player, x, y, z, out, source, volume, pitch);
    }


    @ModifyExpressionValue(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem;getShootingPower(Lnet/minecraft/world/item/component/ChargedProjectiles;)F"))
    private static float modifySpeed(float original, @Local ChargedProjectiles chargedProjectiles,
                                     @Local ItemStack itemStack
    ){
        if(itemStack.getItem() instanceof CrossbowItemInterface crossbow){
            return chargedProjectiles.contains(Items.FIREWORK_ROCKET) ? 1.6F : crossbow.rjs$getArrowSpeedScale();
        }
        return original;
    }


    @ModifyExpressionValue(method = "createProjectile", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;createProjectile(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/entity/projectile/Projectile;"))
    private static Projectile modifyShootArrow(
            Projectile original, @Local(argsOnly = true, ordinal = 0) ItemStack crossbow
            ){
                if(!(crossbow.getItem() instanceof CrossbowItemInterface crossbowItem)) return original;
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
