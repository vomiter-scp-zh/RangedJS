package com.vomiter.rangedjs.item;

import dev.latvian.mods.rhino.util.HideFromJS;

import java.util.function.Consumer;

public interface UseBehavior<UC, RC> {
    @HideFromJS
    Consumer<UC> getUseCallback();

    @HideFromJS
    Consumer<UC> getUseTickCallback();

    @HideFromJS
    Consumer<RC> getReleaseCallback();
}
