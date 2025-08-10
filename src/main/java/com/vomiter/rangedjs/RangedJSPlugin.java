package com.vomiter.rangedjs;

import com.mojang.logging.LogUtils;
import com.vomiter.rangedjs.item.bow.BowItemBuilder;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemBuilder;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import net.minecraft.core.registries.Registries;
import org.slf4j.Logger;


public class RangedJSPlugin implements KubeJSPlugin {
    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(Registries.ITEM, reg -> {
            reg.add("bow", BowItemBuilder.class, BowItemBuilder::new);
            reg.add("crossbow", CrossbowItemBuilder.class, CrossbowItemBuilder::new);
        });
    }

}
