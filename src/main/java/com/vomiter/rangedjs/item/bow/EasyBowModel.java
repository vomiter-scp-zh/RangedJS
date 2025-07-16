package com.vomiter.rangedjs.item.bow;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EasyBowModel {
    static class ModelOverride {
        final Map<String, Float> predicate = new HashMap<>();
        String model;
        ModelOverride(){}
    }
    public static final Logger LOGGER = LogManager.getLogger(EasyBowModel.class);
    protected static Int2FloatMap pullMap = new Int2FloatOpenHashMap(
            Map.of(
                    0, 0F,
                    1, 0.65F,
                    2, 0.9F
            )
    );

    static public ResourceLocation createLocation(ResourceLocation id, String suffix){
        return ResourceLocation.tryBuild(id.getNamespace(), "models/item/%s%s".formatted(id.getPath(), suffix));
    }

    static public JsonElement createMainJson(ResourceLocation id){

        String resolvedRL = id.getNamespace() + ":item/" + id.getPath();
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        ArrayList<ModelOverride> overrides = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            float predicateFloat = pullMap.get(i);
            ModelOverride override = new ModelOverride();
            override.model = resolvedRL + "_pulling_" + i;
            override.predicate.put("pulling", 1F);
            if(predicateFloat > 0) override.predicate.put("pull", predicateFloat);
            overrides.add(override);
        }

        map.put("parent", "minecraft:item/bow");
        map.put("textures", Map.of("layer0", resolvedRL));
        map.put("overrides", overrides);
        JsonElement json = gson.toJsonTree(map);
        //LOGGER.info(json);
        return json;
    }

}
