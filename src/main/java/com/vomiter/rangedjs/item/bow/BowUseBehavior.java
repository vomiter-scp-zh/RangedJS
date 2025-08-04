package com.vomiter.rangedjs.item.bow;


import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.item.context.BowUseContext;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public class BowUseBehavior extends UseBehavior {
    protected Consumer<BowReleaseContext> releaseCallback = (t) -> {};
    protected Consumer<BowUseContext> useCallback = (t)->{};
    protected Consumer<BowUseContext> useTickCallback = (t)->{};
    protected BowUseBehavior(){}

    @HideFromJS
    public Consumer<BowReleaseContext> getReleaseCallback() {
        return releaseCallback;
    }

    @HideFromJS
    public Consumer<BowUseContext> getUseCallback() {
        return useCallback;
    }

    @HideFromJS
    public Consumer<BowUseContext> getUseTickCallback() {
        return useTickCallback;
    }

    @Info("The event fires when the player is gonna start to pull the bow. For the event during pulling process, use pullTick instead.")
    @SuppressWarnings("unused")
    public BowUseBehavior pull(Consumer<BowUseContext> c){
        useCallback = c;
        return this;
    }

    @Info("The event fires when the player is pulling the bow.")
    @SuppressWarnings("unused")
    public BowUseBehavior pullTick(Consumer<BowUseContext> c){
        useTickCallback = c;
        return this;
    }

    @SuppressWarnings("unused")
    @Info("The event fires when the player release the bow.")
    public BowUseBehavior release(Consumer<BowReleaseContext> c){
        releaseCallback = c;
        return this;
    }



}
