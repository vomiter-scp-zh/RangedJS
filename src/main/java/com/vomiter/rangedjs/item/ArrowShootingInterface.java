package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public interface ArrowShootingInterface<
        P extends ArrowShootingProperties<A, UB>,
        A extends ArrowShootingAttributes,
        UB extends UseBehavior<UC, RC>,
        UC,
        RC
        > extends ItemInterface {

    // 由 mixin 實作：塞/取 properties
    @HideFromJS
    void rjs$setProperties(P props);

    @HideFromJS
    P rjs$getProperties();

    // 下面開始全部都可以共用，不用各種 cast
    @HideFromJS
    default UB rjs$getUseBehavior() {
        return rjs$getProperties().getUseBehavior();
    }

    @HideFromJS
    default A rjs$getAttributes() {
        return rjs$getProperties().getAttributes();
    }

    @HideFromJS
    default HitBehavior rjs$getHitBehavior() {
        return rjs$getProperties().getHitBehavior();
    }

    @HideFromJS
    default Consumer<UC> rjs$getUseCallback() {
        return rjs$getUseBehavior().getUseCallback();
    }

    @HideFromJS
    default Consumer<UC> rjs$getUseTickCallback() {
        return rjs$getUseBehavior().getUseTickCallback();
    }

    @HideFromJS
    default Consumer<RC> rjs$getReleaseCallback() {
        return rjs$getUseBehavior().getReleaseCallback();
    }

    default int rjs$getFullChargeTick() { return rjs$getAttributes().getFullChargeTick(); }
    default double rjs$getBaseDamage() { return rjs$getAttributes().getBaseDamage(); }
    default int rjs$getPower() { return rjs$getAttributes().getPower(); }
    default int rjs$getKnockBack() { return rjs$getAttributes().getKnockBack(); }
    default float rjs$getArrowSpeedScale() { return rjs$getAttributes().getArrowSpeedScale(); }
    default boolean rjs$isFlamingArrow() { return rjs$getAttributes().isFlamingArrow(); }
    default boolean rjs$isInfinity() { return rjs$getAttributes().isInfinity(); }
    default boolean rjs$isSpecialInfinity() { return rjs$getAttributes().isSpecialInfinity(); }
    default boolean rjs$isNoDamage() { return rjs$getAttributes().isNoDamage(); }
}
