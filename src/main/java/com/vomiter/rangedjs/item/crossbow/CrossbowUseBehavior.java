package com.vomiter.rangedjs.item.crossbow;


import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public class CrossbowUseBehavior implements UseBehavior<CrossbowUseContext, Void> {
    protected Consumer<CrossbowUseContext> useCallback = (t)->{};
    protected Consumer<CrossbowUseContext> useTickCallback = (t)->{};
    protected Consumer<CrossbowUseContext> shootCallback = (t)->{};
    protected CrossbowUseBehavior(){}

    @HideFromJS
    public Consumer<CrossbowUseContext> getUseTickCallback() {
        return useTickCallback;
    }

    @Override
    public Consumer<Void> getReleaseCallback() {
        return null;
    }

    @HideFromJS
    public Consumer<CrossbowUseContext> getShootCallback() {
        return shootCallback;
    }

    @HideFromJS
    public Consumer<CrossbowUseContext> getUseCallback() {
        return useCallback;
    }

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

    @Info("The event fires when the player shoot the crossbow.")
    @SuppressWarnings("unused")
    public CrossbowUseBehavior shoot(Consumer<CrossbowUseContext> c){
        shootCallback = c;
        return this;
    }


}
