package com.vomiter.rangedjs.projectile;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class ArrowHitEntityEventJS extends ProjectileHitEntityEventJS {
    private float damage;

    public ArrowHitEntityEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci) {
        super(hitResult, projectile, ci);
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public float getDamage() {
        return damage;
    }
}
