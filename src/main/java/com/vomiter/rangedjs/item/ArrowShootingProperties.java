package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;

public interface ArrowShootingProperties {
    @HideFromJS
    UseBehavior getUseBehavior();

    @HideFromJS
    Attributes getAttributes();

    @HideFromJS
    HitBehavior getHitBehavior();
}
