package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.model.EasyBowLikeModel;
import com.vomiter.rangedjs.item.model.EasyCrossbowModel;
import com.vomiter.rangedjs.kubejs.GenerateClientAssetsEventJSInterface;
import dev.latvian.mods.kubejs.client.GenerateClientAssetsEventJS;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = GenerateClientAssetsEventJS.class)
public abstract class GenerateClientAssetsEventJSMixin implements GenerateClientAssetsEventJSInterface {

    @Unique
    GenerateClientAssetsEventJS rjs$this = (GenerateClientAssetsEventJS)(Object)this;

    @Unique
    @Override
    public void rjs$easyBowModel(ResourceLocation id) {
        new EasyBowLikeModel().add(id, rjs$this);
    }

    @Unique
    @Override
    public void rjs$easyCrossbowModel(ResourceLocation id) {
        new EasyCrossbowModel().add(id, rjs$this);
    }

}
