package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.rhino.util.RemapForJS;

public interface ProjectileInterface {

    @RemapForJS("getHitBehavior")
    HitBehavior rangedjs$getHitBehavior();

    @RemapForJS("setHitBehavior")
    void rangedjs$setHitBehavior(HitBehavior h);
}
