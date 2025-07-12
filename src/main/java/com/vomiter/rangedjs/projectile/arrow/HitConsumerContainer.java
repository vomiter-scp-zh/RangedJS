package com.vomiter.rangedjs.projectile.arrow;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class HitConsumerContainer {
    public static Map<UUID, HitConsumerContainer> containerMap = new HashMap<>();
    public HitConsumerContainer() {}

    public Consumer<LivingEntity> postHurtEffect;
    public Consumer<EntityHitResult> hitEntity;
    public Consumer<BlockHitResult> hitBlock;

    protected void postHurtEffect(Consumer<LivingEntity> postHurtEffect) {this.postHurtEffect = postHurtEffect;}
    protected void hitEntity(Consumer<EntityHitResult> hitEntity) {this.hitEntity = hitEntity;}
    protected void hitBlock(Consumer<BlockHitResult> hitBlock) {this.hitBlock = hitBlock;}
}
