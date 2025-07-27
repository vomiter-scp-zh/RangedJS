package com.vomiter.rangedjs.item.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.vomiter.rangedjs.RangedJS;
import dev.latvian.mods.kubejs.client.GenerateClientAssetsEventJS;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EasyBowLikeModel {
    public String getParent() {
        return "minecraft:item/bow";
    }

    public Int2FloatMap getPullMap(){
        return new Int2FloatOpenHashMap(
                Map.of(
                        0, 0F,
                        1, 0.65F,
                        2, 0.9F
                )
        );
    }

    static public ResourceLocation createLocation(ResourceLocation id, String suffix, @Nullable Boolean withModel){
        if(!Boolean.TRUE.equals(withModel)){
            return ResourceLocation.tryBuild(id.getNamespace(), "item/%s%s".formatted(id.getPath(), suffix));
        }
        return ResourceLocation.tryBuild(id.getNamespace(), "models/item/%s%s".formatted(id.getPath(), suffix));
    }

    public Map<String, Object> createMap(ResourceLocation id, @Nullable String mainTexture){
        Map<String, Object> map = new HashMap<>();
        ArrayList<ModelOverride> overrides = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            float predicateFloat = getPullMap().get(i);
            ModelOverride override = new ModelOverride();
            override.model = createLocation(id, "_pulling_" + i, false).toString();
            override.predicate.put("pulling", 1F);
            if(predicateFloat > 0) override.predicate.put("pull", predicateFloat);
            overrides.add(override);
        }

        map.put("parent", getParent());
        map.put("textures", Map.of("layer0",
                mainTexture == null
                        ? createLocation(id, "", false).toString()
                        : mainTexture
        ));
        map.put("overrides", overrides);
        return map;
    }

    public JsonElement createMainJson(ResourceLocation id){
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(createMap(id, null));
        RangedJS.LOGGER.info(json.toString());
        return json;
    }

    public void addMain(ResourceLocation id, GenerateClientAssetsEventJS eventJS, String suffix){
        eventJS.add(
                createLocation(id, suffix, true),
                createMainJson(id)
        );
    }

    public void addChild(ResourceLocation id, GenerateClientAssetsEventJS eventJS, String suffix){
        Gson gson = new Gson();
        Map<String, Object> map = Map.of(
                "parent", createLocation(id, "", false).toString(),
                "textures", Map.of(
                        "layer0", createLocation(id, suffix, false).toString()
                )
        );
        eventJS.add(
                createLocation(id, suffix, true),
                gson.toJsonTree(map)
        );

    }

    public void add(ResourceLocation id, GenerateClientAssetsEventJS eventJS){
        addMain(id, eventJS, "");
        for (int i = 0; i < 3; i++) {
            addChild(id, eventJS, "_pulling_" + i);
        }

    }

}
