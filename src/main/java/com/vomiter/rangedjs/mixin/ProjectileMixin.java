package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.projectile.arrow.HitConsumerContainer;
import com.vomiter.rangedjs.projectile.arrow.ProjectileInterface;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = Projectile.class)
public class ProjectileMixin implements ProjectileInterface {
    @Unique
    private HitConsumerContainer rangedjs$hitConsumerContainer;
    @RemapForJS("getHitConsumerContainer")
    @Override
    public HitConsumerContainer rangedjs$getHitConsumerContainer(){
        return this.rangedjs$hitConsumerContainer;
    }

    @RemapForJS("setHitConsumerContainer")
    @Override
    public void rangedjs$setHitConsumerContainer(HitConsumerContainer hitConsumerContainer){
        this.rangedjs$hitConsumerContainer = hitConsumerContainer;
    }
}
