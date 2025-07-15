package com.vomiter.rangedjs.projectile;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;

public class ArrowHitEntityEventJS extends ProjectileHitEntityEventJS {
    private float damage;
    public ArrowHitEntityEventJS(EntityHitResult hitResult, Projectile projectile) {
        super(hitResult, projectile);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }
}
