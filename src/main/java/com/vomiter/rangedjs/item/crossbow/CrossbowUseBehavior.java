package com.vomiter.rangedjs.item.crossbow;


import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.item.context.BowUseContext;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import dev.latvian.mods.kubejs.typings.Info;

import java.util.function.Consumer;

public class CrossbowUseBehavior {
    protected Consumer<CrossbowUseContext> useCallback = (t)->{};
    protected Consumer<CrossbowUseContext> useTickCallback = (t)->{};
    protected CrossbowUseBehavior(){}

    @Info("The event fires when the player is gonna start to pull the bow. For the event during pulling process, use pullTick instead.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior pull(Consumer<CrossbowUseContext> c){
        useCallback = c;
        return this;
    }

    @Info("The event fires when the player is pulling the bow.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior pullTick(Consumer<CrossbowUseContext> c){
        useTickCallback = c;
        return this;
    }


}
