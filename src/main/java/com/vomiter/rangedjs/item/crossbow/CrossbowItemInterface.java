package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public interface CrossbowItemInterface {

    @HideFromJS
    default void rjs$setCrossbowProperties(CrossbowProperties b){}

    @HideFromJS
    default CrossbowProperties rjs$getCrossbowProperties(){return new CrossbowProperties();}

    @HideFromJS
    default CrossbowUseBehavior rjs$getCrossbowUseBehavior(){return rjs$getCrossbowProperties().crossbowUseBehavior;}

    @HideFromJS
    default Consumer<CrossbowUseContext> rjs$getUseCallback(){return rjs$getCrossbowUseBehavior().useCallback;}

    @HideFromJS
    default Consumer<CrossbowUseContext> rjs$getUseTickCallback(){return rjs$getCrossbowUseBehavior().useTickCallback;}

    @HideFromJS
    default CrossbowAttributes rjs$getCrossbowAttributes(){return rjs$getCrossbowProperties().crossbowAttributes;}

    @HideFromJS
    default HitBehavior rjs$getHitBehavior(){return rjs$getCrossbowProperties().hitBehavior;}

    @SuppressWarnings("unused")
    default int rjs$getFullChargeTick() {
        return rjs$getCrossbowAttributes().getFullChargeTick();
    }

    @SuppressWarnings("unused")
    default double rjs$getArrowDamage() {
        return rjs$getCrossbowAttributes().getArrowDamage();
    }

    @SuppressWarnings("unused")
    default int rjs$getPower() {
        return rjs$getCrossbowAttributes().getPower();
    }

    @SuppressWarnings("unused")
    default int rjs$getKnockBack() {
        return rjs$getCrossbowAttributes().getKnockBack();
    }

    @SuppressWarnings("unused")
    default float rjs$getArrowSpeedScale() {
        return rjs$getCrossbowAttributes().getArrowSpeedScale();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isFlamingArrow() {
        return rjs$getCrossbowAttributes().isFlamingArrow();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isInfinity() {
        return rjs$getCrossbowAttributes().isInfinity();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isSpecialInfinity() {
        return rjs$getCrossbowAttributes().isSpecialInfinity();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isNoDamage() {
        return rjs$getCrossbowAttributes().isNoDamage();
    }

    @SuppressWarnings("unused")
    default CrossbowItemInterface crossbow(Consumer<CrossbowProperties> consumer){
        CrossbowProperties properties = this.rjs$getCrossbowProperties();
        consumer.accept(properties);
        this.rjs$setCrossbowProperties(properties);
        return this;
    }
}
