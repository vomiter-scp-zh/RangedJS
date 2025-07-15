package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.callbacks.BowUseContext;
import com.vomiter.rangedjs.projectile.HitBehavior;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.BowItem;

import java.util.function.Consumer;

public interface BowItemInterface {
    default void rjs$setBowProperties(BowProperties b){}
    default BowProperties rjs$getBowProperties(){return new BowProperties();}
    default Consumer<BowUseContext> getUseCallback(){return rjs$getBowProperties().useCallback;}
    default BowAttributes getBowAttributes(){return rjs$getBowProperties().bowAttributes;}
    default HitBehavior getHitBehavior(){return rjs$getBowProperties().hitBehavior;}

    @SuppressWarnings("unused")
    default int getFullChargeTick() {
        return getBowAttributes().getFullChargeTick();
    }

    @SuppressWarnings("unused")
    default double getBaseDamage() {
        return getBowAttributes().getBaseDamage();
    }

    @SuppressWarnings("unused")
    default int getPower() {
        return getBowAttributes().getPower();
    }

    @SuppressWarnings("unused")
    default int getKnockBack() {
        return getBowAttributes().getKnockBack();
    }

    @SuppressWarnings("unused")
    default float getArrowSpeedScale() {
        return getBowAttributes().getArrowSpeedScale();
    }

    @SuppressWarnings("unused")
    default boolean isFlamingArrow() {
        return getBowAttributes().isFlamingArrow();
    }

    @SuppressWarnings("unused")
    default boolean isInfinity() {
        return getBowAttributes().isInfinity();
    }

    @SuppressWarnings("unused")
    default boolean isSpecialInfinity() {
        return getBowAttributes().isSpecialInfinity();
    }

    @SuppressWarnings("unused")
    default boolean isNoDamage() {
        return getBowAttributes().isNoDamage();
    }

    @SuppressWarnings("unused")
    default BowItemInterface bow(Consumer<BowProperties> consumer){
        BowProperties properties = this.rjs$getBowProperties();
        consumer.accept(properties);
        this.rjs$setBowProperties(properties);
        ItemProperties.register((BowItem)this, BowUtils.PULL, BowUtils.PULL_PROVIDER);
        return this;
    }
}
