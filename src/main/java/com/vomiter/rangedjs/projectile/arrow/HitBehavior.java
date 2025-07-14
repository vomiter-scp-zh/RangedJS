package com.vomiter.rangedjs.projectile.arrow;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.function.Consumer;

public class HitBehavior {
    public final HitConsumerContainer hitConsumers;
    public HitBehavior(){
        this.hitConsumers = new HitConsumerContainer();
    }
    public HitBehavior postHurtEffect(Consumer<LivingEntity> postHurtEffect){
        this.hitConsumers.setPostHurtEffect(postHurtEffect);
        return this;
    }
    public HitBehavior hit(Consumer<HitResult> hit){
        this.hitConsumers.setHit(hit);
        return this;
    }

    public HitBehavior hitEntity(Consumer<EntityHitResult> hitEntity){
        this.hitConsumers.setHitEntity(hitEntity);
        return this;
    }
    public HitBehavior hitBlock(Consumer<BlockHitResult> hitBlock){
        this.hitConsumers.setHitBlock(hitBlock);
        return this;
    }
}
