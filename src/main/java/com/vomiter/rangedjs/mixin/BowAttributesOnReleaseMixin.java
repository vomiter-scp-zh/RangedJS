package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.BowUtils;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BowItem.class)
public abstract class BowAttributesOnReleaseMixin implements BowItemInterface {
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
        float f = (float)(itemStackLocalRef.getUseDuration() - remainTick) / this.getBowAttributes().getFullChargeTick();
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }



    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 0
    )
    private boolean setInfinity(boolean bl){
        return bl || this.getBowAttributes().isInfinity();
    };

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 1
    )
    private boolean setSpecialInfinity(boolean bl){
        return bl || this.getBowAttributes().isSpecialInfinity();
    };

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 2
    )
    private int setBowPower(int i){
        return i + this.getBowAttributes().getPower();
    };

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 3
    )
    private int setKnockback(int i){
        return i + this.getBowAttributes().getKnockBack();
    };

    //TODO refactor this with mixin extra someday.
    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 0
    )
    private AbstractArrow modifyArrowFinal(AbstractArrow arrow){
        double damageModifier = this.getBowAttributes().getBaseDamage() - BowUtils.defaultBaseDamage;
        arrow.setBaseDamage(arrow.getBaseDamage() + damageModifier);
        if(this.getBowAttributes().isFlamingArrow()) arrow.setSecondsOnFire(100);
        if(this.getBowAttributes().isNoDamage()) arrow.setBaseDamage(0);
        ((ProjectileInterface)arrow).rangedjs$setHitBehavior(getHitBehavior());
        return arrow;
    };
}
