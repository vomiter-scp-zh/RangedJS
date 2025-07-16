package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.item.context.BowUseContext;
import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.kubejs.typings.Info;

import java.util.function.Consumer;

public class BowProperties {
    protected final BowAttributes bowAttributes = new BowAttributes();
    protected final HitBehavior hitBehavior = new HitBehavior();
    protected Consumer<BowReleaseContext> releaseCallback = (t) -> {};
    protected Consumer<BowUseContext> useCallback = (t)->{};
    protected Consumer<BowUseContext> useTickCallback = (t)->{};

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

    @Info("The event fires when the player is gonna start to pull the bow. For the event during pulling process, use pullTick instead.")
    @SuppressWarnings("unused")
    public BowProperties pull(Consumer<BowUseContext> c){
        useCallback = c;
        return this;
    }

    @Info("The event fires when the player is pulling the bow.")
    @SuppressWarnings("unused")
    public BowProperties pullTick(Consumer<BowUseContext> c){
        useTickCallback = c;
        return this;
    }

    @SuppressWarnings("unused")
    @Info("The event fires when the player release the bow.")
    public BowProperties release(Consumer<BowReleaseContext> c){
        releaseCallback = c;
        return this;
    }
}
