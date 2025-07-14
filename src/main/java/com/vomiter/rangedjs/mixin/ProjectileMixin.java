package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.projectile.HitBehavior;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Projectile.class)
public class ProjectileMixin implements ProjectileInterface {
    @Unique
    private HitBehavior rangedjs$hitBehavior;

    @RemapForJS("getHitConsumerContainer")
    @Override
    public HitBehavior rangedjs$getHitBehavior(){
        return this.rangedjs$hitBehavior;
    }

    @RemapForJS("setHitConsumerContainer")
    @Override
    public void rangedjs$setHitBehavior(HitBehavior h){
        this.rangedjs$hitBehavior = h;
    }


}
