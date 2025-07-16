package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.level.BlockContainerJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class ProjectileHitBlockEventJS extends ProjectileHitEventJS{
    public ProjectileHitBlockEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci) {
        super(hitResult, projectile, ci);
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
        this.ci.cancel();
    }
}
