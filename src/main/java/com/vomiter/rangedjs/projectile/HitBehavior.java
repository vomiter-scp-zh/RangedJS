package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Consumer;

public class HitBehavior {
    protected Consumer<LivingEntity> postHurtEffect;
    protected Consumer<ProjectileHitEntityEventJS> hitEntity;
    protected Consumer<BlockHitResult> hitBlock;
    protected Consumer<HitResult> hit;

    public Consumer<LivingEntity> getPostHurtEffect() { return postHurtEffect; }
    public Consumer<ProjectileHitEntityEventJS> getHitEntity() { return hitEntity; }
    public Consumer<BlockHitResult> getHitBlock() { return hitBlock;}
    public Consumer<HitResult> getHit() { return hit; }

    @RemapForJS("postHurtEffect")
    public void setPostHurtEffect(Consumer<LivingEntity> postHurtEffect) {
        this.postHurtEffect = postHurtEffect;
    }

    @RemapForJS("hitEntity")
    public void setHitEntity(Consumer<ProjectileHitEntityEventJS> hitEntity) {
        this.hitEntity = hitEntity;
    }

    @RemapForJS("hitBlock")
    public void setHitBlock(Consumer<BlockHitResult> hitBlock) {
        this.hitBlock = hitBlock;
    }

    @RemapForJS("hit")
    public void setHit(Consumer<HitResult> hit) {
        this.hit = hit;
    }

    public HitBehavior(){}
}
