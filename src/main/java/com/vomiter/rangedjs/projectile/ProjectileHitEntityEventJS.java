package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ProjectileHitEntityEventJS extends ProjectileHitEventJS {

    public ProjectileHitEntityEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci) {
        super(hitResult, projectile, ci);
    }

    public Entity getEntity(){
        return ((EntityHitResult)hitResult).getEntity();
    }

    @Override
    @Info("""
            If the event is canceled, the projectile will go through the entity and cause no effect or damage.
            """)
    public void cancel(){
        this.setEventResult(Result.DENY);
        this.ci.cancel();
    }

    public void setDamage(float damage) {}

    public float getDamage() {return 0;}

}
