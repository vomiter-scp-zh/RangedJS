package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.callbacks.RangedJSBowUseContext;
import com.vomiter.rangedjs.projectile.arrow.HitConsumerContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public interface BowItemInterface {
    default void rjs$setBowProperties(RangedJSBowItemBuilder.BuilderHelper b){}
    default RangedJSBowItemBuilder.BuilderHelper rjs$getBowProperties(){return new RangedJSBowItemBuilder.BuilderHelper();}
    default Consumer<RangedJSBowUseContext> getUseCallback(){return rjs$getBowProperties().useCallback;}
    default BowAttributes getBowAttributes(){return rjs$getBowProperties().bowAttributes;}
    default HitConsumerContainer getHitConsumers(){return rjs$getBowProperties().hitBehavior.hitConsumers;}
    default float getPowerForTimeModified(ItemStack stack, Player player){
        BowItem bowItem = (BowItem) stack.getItem();
        int fullChargeTick = ((BowItemInterface) bowItem).getBowAttributes().getFullChargeTick();
        int pullTick = bowItem.getUseDuration(stack) - player.getUseItemRemainingTicks();
        float f = (float)pullTick / fullChargeTick;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
}
