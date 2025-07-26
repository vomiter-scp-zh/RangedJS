package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.item.bow.BowUseBehavior;
import com.vomiter.rangedjs.item.context.ReleaseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public interface ItemInterface {
    @HideFromJS
    UseBehavior rjs$getUseBehavior();

    @HideFromJS
    default Consumer<UseContext> rjs$getUseCallback(){return rjs$getUseBehavior().useCallback;}

    @HideFromJS
    default Consumer<UseContext> rjs$getUseTickCallback(){return rjs$getUseBehavior().useTickCallback;}

    @HideFromJS
    default Consumer<ReleaseContext> rjs$getReleaseCallback(){return rjs$getUseBehavior().releaseCallback;}

}
