package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.projectile.arrow.HitConsumerContainer;
import com.vomiter.rangedjs.projectile.arrow.ProjectileInterface;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.entity.EntityAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(value = AbstractArrow.class)
public abstract class AbstractArrowMixin implements EntityAccess, ProjectileInterface {

    @Unique
    @Inject(method="doPostHurtEffects", at=@At("TAIL"))
    private void doPostHurtEffects(LivingEntity livingEntity, CallbackInfo ci) {
        HitConsumerContainer hitConsumerContainer = this.rangedjs$getHitConsumerContainer();
        if(hitConsumerContainer == null) return;
        Optional.ofNullable(hitConsumerContainer.getPostHurtEffect()).orElse(t -> {}).accept(livingEntity);
    }
}
