package com.vomiter.rangedjs.item.crossbow;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class CrossbowUtils {
    public static int defaultFullChargeTick = 25;
    public static double defaultArrowDamage = 2;
    public static float defaultSpeedScale = 3.15F;

    public static ResourceLocation PULL = ResourceLocation.tryBuild("minecraft", "pull");
    public static ResourceLocation PULLING = ResourceLocation.tryBuild("minecraft","pulling");

    public static int getPullingTicks(LivingEntity entity, ItemStack stack){
        return stack.getUseDuration() - entity.getUseItemRemainingTicks();
    }
}
