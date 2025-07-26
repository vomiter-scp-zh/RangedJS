package com.vomiter.rangedjs.projectile.hitevents;

import dev.latvian.mods.kubejs.level.BlockContainerJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class ProjectileHitBlockEventJS extends ProjectileHitEventJS{
    public ProjectileHitBlockEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci) {
        super(hitResult, projectile, ci);
    }

    public BlockContainerJS getBlock(){
        BlockPos pos = ((BlockHitResult)hitResult).getBlockPos();
        return new BlockContainerJS(getLevel(), pos
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
