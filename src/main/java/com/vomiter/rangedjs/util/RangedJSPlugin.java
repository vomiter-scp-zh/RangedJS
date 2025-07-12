package com.vomiter.rangedjs.util;

import com.vomiter.rangedjs.item.bow.RjsBowItemBuilder;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;


public class RangedJSPlugin extends KubeJSPlugin{
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("bow", RjsBowItemBuilder.class, i -> new RjsBowItemBuilder(i));
    }
}
