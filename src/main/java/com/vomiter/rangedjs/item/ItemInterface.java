package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.item.context.ReleaseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public interface ItemInterface {
    @HideFromJS
    UseBehavior rjs$getUseBehavior();

    @HideFromJS
    default Consumer<? extends UseContext> rjs$getUseCallback(){return rjs$getUseBehavior().getUseCallback();}

    @HideFromJS
    default Consumer<? extends UseContext> rjs$getUseTickCallback(){return rjs$getUseBehavior().getUseTickCallback();}

    @HideFromJS
    default Consumer<? extends ReleaseContext> rjs$getReleaseCallback(){return rjs$getUseBehavior().getReleaseCallback();}

}
