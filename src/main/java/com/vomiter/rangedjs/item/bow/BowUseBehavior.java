package com.vomiter.rangedjs.item.bow;


import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.item.context.ReleaseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.kubejs.typings.Info;

import java.util.function.Consumer;

public class BowUseBehavior extends UseBehavior {
    protected Consumer<ReleaseContext> releaseCallback = (t) -> {};
    protected Consumer<UseContext> useCallback = (t)->{};
    protected Consumer<UseContext> useTickCallback = (t)->{};
    protected BowUseBehavior(){}

    @Info("The event fires when the player is gonna start to pull the bow. For the event during pulling process, use pullTick instead.")
    @SuppressWarnings("unused")
    public BowUseBehavior pull(Consumer<UseContext> c){
        useCallback = c;
        return this;
    }

    @Info("The event fires when the player is pulling the bow.")
    @SuppressWarnings("unused")
    public BowUseBehavior pullTick(Consumer<UseContext> c){
        useTickCallback = c;
        return this;
    }

    @SuppressWarnings("unused")
    @Info("The event fires when the player release the bow.")
    public BowUseBehavior release(Consumer<ReleaseContext> c){
        releaseCallback = c;
        return this;
    }



}
