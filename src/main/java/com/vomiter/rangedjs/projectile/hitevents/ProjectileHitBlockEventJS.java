package com.vomiter.rangedjs.projectile.hitevents;

import dev.latvian.mods.kubejs.level.CachedLevelBlock;
import dev.latvian.mods.kubejs.level.LevelBlock;
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

    public LevelBlock getBlock(){
        BlockPos pos = ((BlockHitResult)hitResult).getBlockPos();
        return new CachedLevelBlock(getLevel(), pos);
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
