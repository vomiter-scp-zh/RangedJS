package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.model.EasyBowLikeModel;
import com.vomiter.rangedjs.item.model.EasyCrossbowModel;
import com.vomiter.rangedjs.kubejs.GenerateClientAssetsEventJSInterface;

import dev.latvian.mods.kubejs.generator.KubeAssetGenerator;
import dev.latvian.mods.kubejs.script.data.VirtualAssetPack;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = VirtualAssetPack.class)
public abstract class KubeAssetGeneratorMixin implements GenerateClientAssetsEventJSInterface {

    @Unique
    KubeAssetGenerator rjs$this = (KubeAssetGenerator)(Object)this;

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
