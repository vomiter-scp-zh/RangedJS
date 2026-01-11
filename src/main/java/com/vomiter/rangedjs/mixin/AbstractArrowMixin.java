package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.projectile.*;
import com.vomiter.rangedjs.projectile.hitevents.ArrowHitEntityEventJS;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Mixin(value = AbstractArrow.class)
public abstract class AbstractArrowMixin implements EntityAccess, ProjectileInterface {

    @Shadow @Nullable private ItemStack firedFromWeapon;

    @Shadow @Nullable private List<Entity> piercedAndKilledEntities;

    @Shadow public abstract byte getPierceLevel();

    @Shadow protected abstract void setPierceLevel(byte pierceLevel);

    @Inject(method = "onHitEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;"), cancellable = true)
    private void doOnHitEntity(EntityHitResult hitResult, CallbackInfo ci, @Local LocalIntRef damage){
        ArrowHitEntityEventJS eventJS = new ArrowHitEntityEventJS(hitResult, (Projectile) (Object) this, ci);
        eventJS.setDamage(damage.get());
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        Optional.ofNullable(hitBehavior.getHitEntity()).orElse(t->{}).accept(eventJS);
        damage.set(Math.round(eventJS.getDamage()));
    }

    /*
    @Inject(method = "onHitBlock", at = @At(value = "HEAD"), cancellable = true)
    private void doOnHitBlock(BlockHitResult hitResult, CallbackInfo ci){
        ArrowHitBlockEventJS eventJS = new ArrowHitBlockEventJS(hitResult, (Projectile) (Object) this, ci);
        HitBehavior arrowhitBehavior = this.rangedjs$rjs$rjs$getHitBehavior();
        Optional.ofNullable(arrowhitBehavior.getHitBlock()).orElse(t->{}).accept(eventJS);
        if(eventJS.getEventResult() == ProjectileHitEventJS.Result.DENY) ci.cancel();
    }
     */

    @Unique
    @Inject(method="doPostHurtEffects", at=@At("HEAD"))
    private void doPostHurtEffects(LivingEntity livingEntity, CallbackInfo ci) {
        HitBehavior hitBehavior = this.rangedjs$getHitBehavior();
        if(hitBehavior == null) return;
        if(hitBehavior instanceof ArrowHitBehavior arrowHitBehavior){
            Optional.ofNullable(arrowHitBehavior.getPostHurtEffect()).orElse((t,u) -> {}).accept(livingEntity, (AbstractArrow) (Object)this);
        }
    }

    @ModifyVariable(method = "doKnockback", at = @At("STORE"))
    private double modifyKnockback(double value){
        if(this.firedFromWeapon == null) return value;
        if(this.firedFromWeapon.getItem() instanceof ArrowShootingInterface bowItem){
            return bowItem.rjs$getKnockBack() + value;
        }
        return value;
    }

    @Inject(method="onHitEntity", at=@At("HEAD"))
    private void modifyPierceLevel(EntityHitResult result, CallbackInfo ci){
        if(this.firedFromWeapon == null) return;
        if(this.piercedAndKilledEntities != null) return;
        if(this.firedFromWeapon.getItem() instanceof ArrowShootingInterface bowItem){
            this.setPierceLevel(
                    (byte)(bowItem.rjs$getAttributes().getPierce() + this.getPierceLevel())
            );
        }


    }

}
