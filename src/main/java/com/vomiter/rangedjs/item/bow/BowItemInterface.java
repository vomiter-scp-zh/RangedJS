package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.callbacks.BowUseContext;
import com.vomiter.rangedjs.projectile.HitBehavior;

import java.util.function.Consumer;

public interface BowItemInterface {
    default void rjs$setBowProperties(BowProperties b){}
    default BowProperties rjs$getBowProperties(){return new BowProperties();}
    default Consumer<BowUseContext> getUseCallback(){return rjs$getBowProperties().useCallback;}
    default BowAttributes getBowAttributes(){return rjs$getBowProperties().bowAttributes;}
    default HitBehavior getHitBehavior(){return rjs$getBowProperties().hitBehavior;}
}
