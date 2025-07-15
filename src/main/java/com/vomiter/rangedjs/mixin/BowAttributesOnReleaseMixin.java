package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.BowUtils;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BowItem.class)
public abstract class BowAttributesOnReleaseMixin implements BowItemInterface {
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
        float f = (float)(itemStackLocalRef.getUseDuration() - remainTick) / this.getBowAttributes().getFullChargeTick();
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
        return this.getBowAttributes().getArrowSpeedScale();
    }



    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 0
    )
    private boolean setInfinity(boolean bl){
        return bl || this.getBowAttributes().isInfinity();
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
        boolean returnValue = bl || this.getBowAttributes().isSpecialInfinity();
        if(!(player instanceof Player)) return returnValue;
        if(!returnValue && this.getBowAttributes().isInfinity()){
            ItemStack simulatorBow = bow.copy();
            simulatorBow.enchant(Enchantments.INFINITY_ARROWS, 1);
            returnValue = (arrow.getItem() instanceof ArrowItem
                    && ((ArrowItem)arrow.getItem()).isInfinite(arrow, simulatorBow, (Player)player));
        }
        return returnValue;
    }

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 2
    )
    private int setBowPower(int i){
        return i + this.getBowAttributes().getPower();
    }

    @ModifyVariable(
            method = "releaseUsing",
            at = @At("STORE"),
            ordinal = 3
    )
    private int setKnockback(int i){
        return i + this.getBowAttributes().getKnockBack();
    }

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
    }
}
