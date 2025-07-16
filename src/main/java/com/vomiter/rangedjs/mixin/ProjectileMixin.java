package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.projectile.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = Projectile.class)
public class ProjectileMixin implements ProjectileInterface {
    @Unique
    private HitBehavior rangedjs$hitBehavior;

    @Override
    public HitBehavior rangedjs$getHitBehavior(){
        return Optional.ofNullable(this.rangedjs$hitBehavior).orElse(new HitBehavior());
    }

    @Override
    public void rangedjs$setHitBehavior(HitBehavior h){
        this.rangedjs$hitBehavior = h;
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    private void doOnHitEntity(EntityHitResult hitResult, CallbackInfo ci){
        ProjectileHitEntityEventJS eventJS = new ProjectileHitEntityEventJS(hitResult, (Projectile) (Object) this, ci);
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        Optional.ofNullable(hitBehavior.getHitEntity()).orElse(t->{}).accept(eventJS);
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void doOnHitBlock(BlockHitResult hitResult, CallbackInfo ci){
        ProjectileHitBlockEventJS eventJS = new ProjectileHitBlockEventJS(hitResult, (Projectile) (Object) this, ci);
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        Optional.ofNullable(hitBehavior.getHitBlock()).orElse(t->{}).accept(eventJS);
    }
}
