package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class HitBehavior {
    protected Consumer<LivingEntity> postHurtEffect;
    protected Consumer<ProjectileHitEntityEventJS> hitEntity;
    protected Consumer<ProjectileHitBlockEventJS> hitBlock;
    protected Consumer<ProjectileHitEventJS> hit;

    @HideFromJS
    public Consumer<LivingEntity> getPostHurtEffect() { return postHurtEffect; }

    @HideFromJS
    public Consumer<ProjectileHitEntityEventJS> getHitEntity() { return hitEntity; }

    @HideFromJS
    public Consumer<ProjectileHitBlockEventJS> getHitBlock() { return hitBlock;}

    @HideFromJS
    public Consumer<ProjectileHitEventJS> getHit() { return hit; }

    @Info("This will only apply to the living entity hit by the arrow. Non-living entity will not trigger the effects.")
    @RemapForJS("postHurtEffect")
    public void setPostHurtEffect(Consumer<LivingEntity> postHurtEffect) {
        this.postHurtEffect = postHurtEffect;
    }

    @RemapForJS("hitEntity")
    public void setHitEntity(Consumer<ProjectileHitEntityEventJS> hitEntity) {
        this.hitEntity = hitEntity;
    }

    @RemapForJS("hitBlock")
    public void setHitBlock(Consumer<ProjectileHitBlockEventJS> hitBlock) {
        this.hitBlock = hitBlock;
    }

    public HitBehavior(){}
}
