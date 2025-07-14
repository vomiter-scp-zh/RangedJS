package com.vomiter.rangedjs.item.callbacks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ReleaseContext {
    public boolean canceled;
    private final ItemStack item;
    private final Level level;
    private final LivingEntity livingEntity;
    private final int remainTick;

    public ReleaseContext(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainTick){
        this.item = itemStack;
        this.level = level;
        this.livingEntity = livingEntity;
        this.remainTick = remainTick;
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

    public int getRemainTick() {
        return remainTick;
    }
}
