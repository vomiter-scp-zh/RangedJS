package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class ArrowHitBehavior  extends HitBehavior{
    protected BiConsumer<LivingEntity, @Nullable AbstractArrow> postHurtEffect;

    @HideFromJS
    public BiConsumer<LivingEntity, @Nullable AbstractArrow> getPostHurtEffect() { return postHurtEffect; }

    @Info("This will only apply to the living entity hit by the arrow. Non-living entity will not trigger the effects.")
    @RemapForJS("postHurtEffect")
    public void setPostHurtEffect(BiConsumer<LivingEntity, @Nullable AbstractArrow> postHurtEffect) {
        this.postHurtEffect = postHurtEffect;
    }

}
