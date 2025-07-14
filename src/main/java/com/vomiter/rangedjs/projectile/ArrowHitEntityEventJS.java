package com.vomiter.rangedjs.projectile;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;

public class ArrowHitEntityEventJS extends ProjectileHitEntityEventJS {
    public ArrowHitEntityEventJS(EntityHitResult hitResult, Projectile projectile) {
        super(hitResult, projectile);
    }
}
