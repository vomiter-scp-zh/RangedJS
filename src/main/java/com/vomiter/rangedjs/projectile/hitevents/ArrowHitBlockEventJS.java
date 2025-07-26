package com.vomiter.rangedjs.projectile.hitevents;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class ArrowHitBlockEventJS extends ProjectileHitBlockEventJS {
    private float damage;
    public ArrowHitBlockEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci) {
        super(hitResult, projectile, ci);
    }
}