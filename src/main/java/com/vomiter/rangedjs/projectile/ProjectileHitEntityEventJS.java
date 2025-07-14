package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;

public class ProjectileHitEntityEventJS extends ProjectileHitEventJS {
    public ProjectileHitEntityEventJS(EntityHitResult hitResult, Projectile projectile){
        super(hitResult, projectile);
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
    }
}
