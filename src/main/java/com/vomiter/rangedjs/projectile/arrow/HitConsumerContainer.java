package com.vomiter.rangedjs.projectile.arrow;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class HitConsumerContainer {
    public static Map<UUID, HitConsumerContainer> containerMap = new HashMap<>();
    public HitConsumerContainer() {}

    protected Consumer<LivingEntity> postHurtEffect;
    protected Consumer<EntityHitResult> hitEntity;
    protected Consumer<BlockHitResult> hitBlock;
    protected Consumer<HitResult> hit;

    public Consumer<LivingEntity> getPostHurtEffect() {
        return postHurtEffect;
    }

    public Consumer<HitResult> getHit() {
        return hit;
    }

    public Consumer<EntityHitResult> getHitEntity() {
        return hitEntity;
    }

    public Consumer<BlockHitResult> getHitBlock() {
        return hitBlock;
    }

    protected void setPostHurtEffect(Consumer<LivingEntity> postHurtEffect) {
        this.postHurtEffect = postHurtEffect;
    }

    protected void setHitEntity(Consumer<EntityHitResult> hitEntity) {
        this.hitEntity = hitEntity;
    }

    protected void setHitBlock(Consumer<BlockHitResult> hitBlock) {
        this.hitBlock = hitBlock;
    }

    protected void setHit(Consumer<HitResult> hit) {
        this.hit = hit;
    }
}
