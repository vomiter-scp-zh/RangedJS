package com.vomiter.rangedjs.item.callbacks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BowReleaseContext extends ReleaseContext{
    public BowReleaseContext(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainTick) {
        super(itemStack, level, livingEntity, remainTick);
    }
}
