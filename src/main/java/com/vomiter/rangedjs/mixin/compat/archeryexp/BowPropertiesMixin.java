package com.vomiter.rangedjs.mixin.compat.archeryexp;

import com.vomiter.rangedjs.compat.archeryexp.AexpBowConsumerInterface;
import com.vomiter.rangedjs.compat.archeryexp.BowPropertiesInterface;
import com.vomiter.rangedjs.item.bow.BowProperties;
import dev.latvian.mods.rhino.util.RemapForJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = BowProperties.class, remap = false)
public class BowPropertiesMixin implements BowPropertiesInterface {

    @Unique
    AexpBowConsumerInterface rjs$AExpProperties;

    @RemapForJS("AExpBow")
    @Override
    public BowProperties rjs$AExpBow(AexpBowConsumerInterface c) {
        BowProperties rjs$this = (BowProperties)(Object)this;
        this.rjs$AExpProperties = c;
        return rjs$this;
    }

    @Override
    public AexpBowConsumerInterface rjs$getAExpProperties() {
        return rjs$AExpProperties;
    }
}
