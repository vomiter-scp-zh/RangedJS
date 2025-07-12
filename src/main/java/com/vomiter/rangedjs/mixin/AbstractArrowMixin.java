package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.projectile.arrow.ArrowInterface;
import com.vomiter.rangedjs.projectile.arrow.HitConsumerContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AbstractArrow.class)
public abstract class AbstractArrowMixin extends Entity implements EntityAccess, ArrowInterface {
    private AbstractArrowMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Unique
    private AbstractArrow rangedJS$this(){
        return (AbstractArrow) (Object) this;
    }

    @Inject(method="doPostHurtEffects", at=@At("TAIL"))
    private void doPostHurtEffects(LivingEntity livingEntity, CallbackInfo ci) {
        HitConsumerContainer hitConsumerContainer = this.rangedjs$getHitConsumerContainer();
        if(hitConsumerContainer == null) return;
        var postHurtEffect = hitConsumerContainer.postHurtEffect;
        if(postHurtEffect == null) return;
        hitConsumerContainer.postHurtEffect.accept(livingEntity);
    }

    @Unique
    private HitConsumerContainer rangedjs$hitConsumerContainer;

    @Override
    public HitConsumerContainer rangedjs$getHitConsumerContainer(){
        return this.rangedjs$hitConsumerContainer;
    }

    @Override
    public void rangedjs$setHitConsumerContainer(HitConsumerContainer hitConsumerContainer){
        this.rangedjs$hitConsumerContainer = hitConsumerContainer;
    }
}
