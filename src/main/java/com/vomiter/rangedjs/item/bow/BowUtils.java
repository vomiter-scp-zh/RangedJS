package com.vomiter.rangedjs.item.bow;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BowUtils {
    public static int defaultFullChargeTick = 20;
    public static double defaultBaseDamage = 2.00;
    public static float defaultSpeedScale = 3;

    public static ResourceLocation PULL = ResourceLocation.tryBuild("minecraft", "pull");
    public static ResourceLocation PULLING = ResourceLocation.tryBuild("minecraft","pulling");

    public static int getPullingTicks(LivingEntity entity, ItemStack stack){
        return stack.getUseDuration() - entity.getUseItemRemainingTicks();
    }

    public static ClampedItemPropertyFunction  PULL_PROVIDER =
            (@NotNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int i) -> {
                if (entity == null) return 0.0F;
                if (!entity.getUseItem().equals(stack)) return 0.0F;
                Item item = stack.getItem();
                return (float) getPullingTicks(entity, stack) / ((BowItemInterface)item).getBowAttributes().getFullChargeTick();
    };


    public static ClampedItemPropertyFunction PULLING_PROVIDER =
            (stack, level, entity, unused)
                    -> (
                    (entity != null) && entity.isUsingItem() && (entity.getUseItem().equals(stack))
                            ? 1.0F
                            : 0.0F
            );

}
