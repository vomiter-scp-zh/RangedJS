package com.vomiter.rangedjs.item.model;

import dev.latvian.mods.kubejs.client.GenerateClientAssetsEventJS;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Map;

public class EasyCrossbowModel extends EasyBowLikeModel{
    @Override
    public String getParent(){
        return "minecraft:item/crossbow";
    }

    @Override
    public Int2FloatMap getPullMap(){
        return new Int2FloatOpenHashMap(
                Map.of(
                        0, 0F,
                        1, 0.58F,
                        2, 1.0F
                )
        );
    }

    @Override
    public Map<String, Object> createMap(ResourceLocation id, String mainTexture){
        var map = super.createMap(id, createLocation(id, "_standby", false).toString());
        map.computeIfPresent("overrides", (k, overrides) -> {
            ModelOverride chargedOverride = new ModelOverride();
            chargedOverride.setModel(createLocation(id, "_arrow", false).toString());
            chargedOverride.predicate.put("charged", 1.0F);
            ((ArrayList<ModelOverride>)overrides).add(chargedOverride);

            ModelOverride fireworkOverride = new ModelOverride();
            fireworkOverride.setModel(createLocation(id, "_firework", false).toString());
            fireworkOverride.predicate.put("charged", 1.0F);
            fireworkOverride.predicate.put("firework", 1.0F);
            ((ArrayList<ModelOverride>)overrides).add(fireworkOverride);

            return overrides;
        });
        return map;
    }

    @Override
    public void add(ResourceLocation id, GenerateClientAssetsEventJS eventJS){
        super.add(id, eventJS);
        addChild(id, eventJS, "_arrow");
        addChild(id, eventJS, "_firework");
    }
}
