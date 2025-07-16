package com.vomiter.rangedjs;

import com.vomiter.rangedjs.item.bow.BowItemBuilder;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.RegistryInfo;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;


public class RangedJSPlugin extends KubeJSPlugin{
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void init() {
        RegistryInfo.ITEM.addType("bow", BowItemBuilder.class, BowItemBuilder::new);
    }
}
