package com.vomiter.rangedjs.item.crossbow;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrossbowUtils {
    public static int defaultFullChargeTick = 25;
    public static double defaultArrowDamage = 3.15;
    public static float defaultSpeedScale = 3;

    public static ResourceLocation PULL = ResourceLocation.tryBuild("minecraft", "pull");
    public static ResourceLocation PULLING = ResourceLocation.tryBuild("minecraft","pulling");

    public static int getPullingTicks(LivingEntity entity, ItemStack stack){
        return stack.getUseDuration() - entity.getUseItemRemainingTicks();
    }
}
