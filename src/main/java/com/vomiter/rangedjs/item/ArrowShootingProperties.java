package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;

public interface ArrowShootingProperties {
    @HideFromJS
    UseBehavior getUseBehavior();

    @HideFromJS
    ArrowShootingAttributes getAttributes();

    @HideFromJS
    HitBehavior getHitBehavior();
}
