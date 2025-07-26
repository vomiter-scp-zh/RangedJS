package com.vomiter.rangedjs.projectile;

import com.vomiter.rangedjs.projectile.hitevents.ProjectileHitBlockEventJS;
import com.vomiter.rangedjs.projectile.hitevents.ProjectileHitEntityEventJS;
import com.vomiter.rangedjs.projectile.hitevents.ProjectileHitEventJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class HitBehavior {

    protected Consumer<ProjectileHitEntityEventJS> hitEntity;
    protected Consumer<ProjectileHitBlockEventJS> hitBlock;
    protected Consumer<ProjectileHitEventJS> hit;


    @HideFromJS
    public Consumer<ProjectileHitEntityEventJS> getHitEntity() { return hitEntity; }

    @HideFromJS
    public Consumer<ProjectileHitBlockEventJS> getHitBlock() { return hitBlock;}

    @HideFromJS
    public Consumer<ProjectileHitEventJS> getHit() { return hit; }

    @RemapForJS("hitEntity")
    public void setHitEntity(Consumer<ProjectileHitEntityEventJS> hitEntity) {
        this.hitEntity = hitEntity;
    }

    @RemapForJS("hitBlock")
    public void setHitBlock(Consumer<ProjectileHitBlockEventJS> hitBlock) {
        this.hitBlock = hitBlock;
    }

    public HitBehavior(){}
}
