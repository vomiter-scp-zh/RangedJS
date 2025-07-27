package com.vomiter.rangedjs.kubejs;

import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.resources.ResourceLocation;

public interface GenerateClientAssetsEventJSInterface {

    @RemapForJS("easyBowModel")
    @SuppressWarnings("unused")
    void rjs$easyBowModel(ResourceLocation id);

    @RemapForJS("easyCrossbowModel")
    @SuppressWarnings("unused")
    void rjs$easyCrossbowModel(ResourceLocation id);
}
