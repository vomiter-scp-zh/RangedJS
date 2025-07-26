package com.vomiter.rangedjs.item;

import com.vomiter.rangedjs.item.context.ReleaseContext;
import com.vomiter.rangedjs.item.context.UseContext;

import java.util.function.Consumer;

public abstract class UseBehavior {
    protected Consumer<ReleaseContext> releaseCallback = (t) -> {};
    protected Consumer<UseContext> useCallback = (t)->{};
    protected Consumer<UseContext> useTickCallback = (t)->{};
}