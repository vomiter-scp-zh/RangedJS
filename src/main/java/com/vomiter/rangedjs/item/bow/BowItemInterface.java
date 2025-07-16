package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.item.context.BowUseContext;
import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.BowItem;

import java.util.function.Consumer;

public interface BowItemInterface {

    @HideFromJS
    default void rjs$setBowProperties(BowProperties b){}

    @HideFromJS
    default BowProperties rjs$getBowProperties(){return new BowProperties();}

    @HideFromJS
    default BowUseBehavior getBowUseBehavior(){return rjs$getBowProperties().bowUseBehavior;}

    @HideFromJS
    default Consumer<BowUseContext> getUseCallback(){return getBowUseBehavior().useCallback;}

    @HideFromJS
    default Consumer<BowUseContext> getUseTickCallback(){return getBowUseBehavior().useTickCallback;}

    @HideFromJS
    default Consumer<BowReleaseContext> getReleaseCallback(){return getBowUseBehavior().releaseCallback;}

    @HideFromJS
    default BowAttributes getBowAttributes(){return rjs$getBowProperties().bowAttributes;}

    @HideFromJS
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
