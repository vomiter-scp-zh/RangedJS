package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.BowUtils;
import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BowItem.class)
public abstract class BowOnShootMixin implements BowItemInterface {
    @SuppressWarnings("unused")
    @Unique
    BowItem rjs$bowItem =(BowItem)(Object)this;

    @ModifyExpressionValue(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F"
            )
    )
    private float modifyPowerForTime(
            float original,
            @Local(argsOnly = true) ItemStack itemStackLocalRef,
            @Local(argsOnly = true) int remainTick
    ){
        float f = (float)(itemStackLocalRef.getUseDuration() - remainTick) / this.rjs$getBowAttributes().getFullChargeTick();
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    @ModifyConstant(
            method = "releaseUsing",
            constant = @Constant(floatValue = 3.0F)
    )
    private float setInitialSpeed(float constant){
        return this.rjs$getBowAttributes().getArrowSpeedScale();
    }



    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 0
    )
    private boolean setInfinity(boolean bl){
        return bl || this.rjs$getBowAttributes().isInfinity();
    }

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 1
    )
    private boolean setSpecialInfinity(
            boolean bl,
            @Local(argsOnly = true)
            ItemStack bow,
            @Local(ordinal = 1) ItemStack arrow,
            @Local(argsOnly = true) LivingEntity player
    ){
        boolean returnValue = bl || this.rjs$getBowAttributes().isSpecialInfinity();
        if(!(player instanceof Player)) return returnValue;
        if(!returnValue && this.rjs$getBowAttributes().isInfinity()){
            returnValue = (arrow.getItem() instanceof ArrowItem
                    && (arrow.getItem()).equals(Items.ARROW));
        }
        return returnValue;
    }

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 2
    )
    private int setBowPower(int i){
        return i + this.rjs$getBowAttributes().getPower();
    }

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 3
    )
    private int setKnockback(int i){
        return i + this.rjs$getBowAttributes().getKnockBack();
    }

    @Inject(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private void modifyArrowFinal(ItemStack bow, Level level, LivingEntity player, int remainTick, CallbackInfo ci,
                                  @Local LocalRef<AbstractArrow> arrowRef
    ){
        double damageModifier = this.rjs$getBowAttributes().getBaseDamage() - BowUtils.defaultBaseDamage;
        AbstractArrow arrow = arrowRef.get();
        arrow.setBaseDamage(arrow.getBaseDamage() + damageModifier);
        if(this.rjs$isFlamingArrow()) arrow.setSecondsOnFire(100);
        if(this.rjs$getBowAttributes().isNoDamage()) arrow.setBaseDamage(0);
        if(this.rjs$getBowAttributes().getPierce() > 0) arrow.setPierceLevel(rjs$getBowAttributes().getPierce());
        ((ProjectileInterface)arrow).rangedjs$setHitBehavior(rjs$getHitBehavior());
        arrowRef.set(arrow);
        BowReleaseContext bowReleaseContext = new BowReleaseContext(bow, level, player, remainTick, ci);
        bowReleaseContext.setArrow(arrow);
        this.rjs$getReleaseCallback().accept(bowReleaseContext);
        arrowRef.set(bowReleaseContext.getArrow());
    }
}
