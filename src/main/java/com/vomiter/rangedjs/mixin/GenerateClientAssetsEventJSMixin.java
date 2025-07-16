package com.vomiter.rangedjs.mixin;

import com.google.gson.Gson;
import com.vomiter.rangedjs.item.bow.EasyBowModel;
import com.vomiter.rangedjs.kubejs.GenerateClientAssetsEventJSInterface;
import dev.latvian.mods.kubejs.client.GenerateClientAssetsEventJS;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(value = GenerateClientAssetsEventJS.class)
public abstract class GenerateClientAssetsEventJSMixin implements GenerateClientAssetsEventJSInterface {

    @Unique
    GenerateClientAssetsEventJS rjs$this = (GenerateClientAssetsEventJS)(Object)this;

    @Unique
    @Override
    public void easyBowModel(ResourceLocation id) {
        Gson gson = new Gson();

        rjs$this.add(
                EasyBowModel.createLocation(id, ""),
                EasyBowModel.createMainJson(id)
        );
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = Map.of(
                    "parent", id.getNamespace() + ":item/" + id.getPath(),
                    "textures", Map.of(
                            "layer0", id.getNamespace() + ":item/" + id.getPath() + "_pulling_" + i
                    )
            );
            rjs$this.add(
                    EasyBowModel.createLocation(id, "_pulling_" + i),
                    gson.toJsonTree(map)
            );
        }
    }
}
