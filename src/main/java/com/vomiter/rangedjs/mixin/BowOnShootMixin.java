package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = BowItem.class)
public abstract class BowOnShootMixin implements BowItemInterface {
    @Unique
    private SoundEvent SHOOT_SOUND;
    public void rjs$setShootSound(SoundEvent soundEvent) {SHOOT_SOUND = soundEvent;}
    public SoundEvent rjs$getShootSound() { return SHOOT_SOUND; }
    @WrapOperation(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
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
            @Local(argsOnly = true) int remainTick,
            @Local(argsOnly = true) LivingEntity entity
    ){
        float f = (float)(itemStackLocalRef.getUseDuration(entity) - remainTick) / this.rjs$getFullChargeTick();
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
        return this.rjs$getArrowSpeedScale();
    }
}
