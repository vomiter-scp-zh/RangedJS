package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.level.BlockContainerJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ProjectileHitBlockEventJS extends ProjectileHitEventJS{
    public ProjectileHitBlockEventJS(HitResult hitResult, Projectile projectile) {
        super(hitResult, projectile);
    }

    public BlockContainerJS getBlock(){
        Vec3 pos = getPos();
        return new BlockContainerJS(getLevel(),
                new BlockPos(
                    (int)Math.floor(pos.x),
                    (int)Math.floor(pos.y),
                    (int)Math.floor(pos.z)
                )
        );
    }

    @Override
    @Info("""
            If the event is canceled, the projectile will not trigger the block's onProjectileHit method.
            """)
    public void cancel(){
        this.setEventResult(Result.DENY);
    }
}
