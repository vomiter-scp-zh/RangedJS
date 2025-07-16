package com.vomiter.rangedjs.item.context;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ReleaseContext {
    public boolean canceled;
    private final ItemStack item;
    private final Level level;
    private final LivingEntity livingEntity;
    private final int remainTick;
    private final CallbackInfo ci;

    public ReleaseContext(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainTick, CallbackInfo ci){
        this.item = itemStack;
        this.level = level;
        this.livingEntity = livingEntity;
        this.remainTick = remainTick;
        this.ci = ci;
    }

    public ItemStack getItem() {
        return item;
    }

    public Level getLevel() {
        return level;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    @SuppressWarnings("unused")
    public int getRemainTick() {
        return remainTick;
    }

    @SuppressWarnings("unused")
    public void cancel(){
        canceled = true;
        ci.cancel();
    }
}
