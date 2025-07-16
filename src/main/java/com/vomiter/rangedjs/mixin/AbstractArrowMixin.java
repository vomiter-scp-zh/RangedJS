package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.vomiter.rangedjs.projectile.ArrowHitEntityEventJS;
import com.vomiter.rangedjs.projectile.HitBehavior;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = AbstractArrow.class)
public abstract class AbstractArrowMixin implements EntityAccess, ProjectileInterface {

    @Inject(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;"), cancellable = true)
    private void doOnHitEntity(EntityHitResult hitResult, CallbackInfo ci, @Local LocalIntRef damage){
        ArrowHitEntityEventJS eventJS = new ArrowHitEntityEventJS(hitResult, (Projectile) (Object) this, ci);
        eventJS.setDamage(damage.get());
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        Optional.ofNullable(hitBehavior.getHitEntity()).orElse(t->{}).accept(eventJS);
        damage.set(Math.round(eventJS.getDamage()));
    }

    @Unique
    @Inject(method="doPostHurtEffects", at=@At("HEAD"))
    private void doPostHurtEffects(LivingEntity livingEntity, CallbackInfo ci) {
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        if(hitBehavior == null) return;
        Optional.ofNullable(hitBehavior.getPostHurtEffect()).orElse(t -> {}).accept(livingEntity);
    }

}
