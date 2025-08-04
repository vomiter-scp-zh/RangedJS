package com.vomiter.rangedjs.compat.archeryexp;

import com.vomiter.rangedjs.item.bow.BowProperties;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;

public interface BowPropertiesInterface {
    @Info("Add/modify Archery Expansion bow Properties to the bow")
    BowProperties rjs$AExpBow(AexpBowConsumerInterface c);

    @HideFromJS
    AexpBowConsumerInterface rjs$getAExpProperties();
}
