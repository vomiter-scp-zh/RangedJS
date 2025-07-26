package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Consumer;

public class ArrowHitBehavior  extends HitBehavior{
    protected Consumer<LivingEntity> postHurtEffect;

    @HideFromJS
    public Consumer<LivingEntity> getPostHurtEffect() { return postHurtEffect; }

    @Info("This will only apply to the living entity hit by the arrow. Non-living entity will not trigger the effects.")
    @RemapForJS("postHurtEffect")
    public void setPostHurtEffect(Consumer<LivingEntity> postHurtEffect) {
        this.postHurtEffect = postHurtEffect;
    }

}
