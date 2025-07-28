package com.vomiter.rangedjs.item.crossbow;


import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.kubejs.typings.Info;

import java.util.function.Consumer;

public class CrossbowUseBehavior extends UseBehavior {
    protected Consumer<CrossbowUseContext> useCallback = (t)->{};
    protected Consumer<UseContext> useTickCallback = (t)->{};
    protected Consumer<CrossbowUseContext> shootCallback = (t)->{};
    protected CrossbowUseBehavior(){}

    @Info("The event fires when the player is gonna start to pull the bow. For the event during pulling process, use pullTick instead.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior pull(Consumer<CrossbowUseContext> c){
        useCallback = c;
        return this;
    }

    @Info("The event fires when the player is pulling the bow.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior pullTick(Consumer<UseContext> c){
        useTickCallback = c;
        return this;
    }

    @Info("The event fires when the player shoot the crossbow.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior shoot(Consumer<CrossbowUseContext> c){
        shootCallback = c;
        return this;
    }


}
