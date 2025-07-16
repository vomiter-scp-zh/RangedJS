package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.kubejs.typings.Info;

import java.util.function.Consumer;

public class BowProperties {
    protected final BowAttributes bowAttributes = new BowAttributes();
    protected final HitBehavior hitBehavior = new HitBehavior();
    protected BowUseBehavior bowUseBehavior = new BowUseBehavior();

    public BowProperties(){}

    @Info("To modify some default attributes of the bow. E.g. ticks to full charge, base arrow damage, native enchantment-like capabilities.")
    @SuppressWarnings("unused")
    public BowProperties modifyBow(Consumer<BowAttributes> b){
        b.accept(bowAttributes);
        return this;
    }

    @Info("To add stuffs that will happen when the shot arrows hit entity/block")
    @SuppressWarnings("unused")
    public BowProperties onArrowHit(Consumer<HitBehavior> b){
        b.accept(hitBehavior);
        return this;
    }

    @Info("To modify how the bow behaves when pull/release")
    @SuppressWarnings("unused")
    public BowProperties onUse(Consumer<BowUseBehavior> b){
        b.accept(bowUseBehavior);
        return this;
    }
}
