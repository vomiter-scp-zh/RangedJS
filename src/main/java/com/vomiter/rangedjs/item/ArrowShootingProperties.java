package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;

public interface ArrowShootingProperties {
    UseBehavior getUseBehavior();
    Attributes getAttributes();
    HitBehavior getHitBehavior();
}
