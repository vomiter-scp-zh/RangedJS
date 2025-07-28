package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.item.context.ReleaseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public abstract class UseBehavior {
    @HideFromJS
    Consumer<? extends UseContext> getUseCallback(){return (t) -> {};}

    @HideFromJS
    Consumer<? extends UseContext> getUseTickCallback(){return (t) -> {};}

    @HideFromJS
    Consumer<? extends ReleaseContext> getReleaseCallback(){return (t) -> {};}

}