package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;

public interface ArrowShootingInterface extends ItemInterface {

    @HideFromJS
    default void rjs$setBowProperties(ArrowShootingProperties b){}

    @HideFromJS
    ArrowShootingProperties rjs$getBowProperties();

    @Override
    @HideFromJS
    default UseBehavior rjs$getUseBehavior(){return rjs$getBowProperties().getUseBehavior();}

    @HideFromJS
    default ArrowShootingAttributes rjs$getBowAttributes(){return rjs$getBowProperties().getAttributes();}

    @HideFromJS
    default HitBehavior rjs$getHitBehavior(){return rjs$getBowProperties().getHitBehavior();}

    default int rjs$getFullChargeTick() {
        return rjs$getBowAttributes().getFullChargeTick();
    }

    @SuppressWarnings("unused")
    default double rjs$getBaseDamage() {
        return rjs$getBowAttributes().getBaseDamage();
    }

    @SuppressWarnings("unused")
    default int rjs$getPower() {
        return rjs$getBowAttributes().getPower();
    }

    @SuppressWarnings("unused")
    default int rjs$getKnockBack() {
        return rjs$getBowAttributes().getKnockBack();
    }

    @SuppressWarnings("unused")
    default float rjs$getArrowSpeedScale() {
        return rjs$getBowAttributes().getArrowSpeedScale();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isFlamingArrow() {
        return rjs$getBowAttributes().isFlamingArrow();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isInfinity() {
        return rjs$getBowAttributes().isInfinity();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isSpecialInfinity() {
        return rjs$getBowAttributes().isSpecialInfinity();
    }

    @SuppressWarnings("unused")
    default boolean rjs$isNoDamage() {
        return rjs$getBowAttributes().isNoDamage();
    }

}
