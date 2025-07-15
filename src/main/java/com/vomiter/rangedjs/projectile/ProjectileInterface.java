package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.rhino.util.HideFromJS;

public interface ProjectileInterface {

    @HideFromJS
    HitBehavior rangedjs$getHitBehavior();

    @HideFromJS
    void rangedjs$setHitBehavior(HitBehavior h);
}
