package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.Item;

public interface ArrowShootingProperties<A extends ArrowShootingAttributes, UB> {
    @HideFromJS
    UB getUseBehavior();

    @HideFromJS
    A getAttributes();

    @HideFromJS
    HitBehavior getHitBehavior();

    @HideFromJS
    Item getItem();
}
