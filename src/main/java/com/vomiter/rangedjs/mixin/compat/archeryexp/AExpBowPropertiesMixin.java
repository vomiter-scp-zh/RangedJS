package com.vomiter.rangedjs.mixin.compat.archeryexp;

import com.vomiter.rangedjs.compat.archeryexp.AexpBowPropertiesExt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BowItem;
import net.minecraftforge.registries.ForgeRegistries;
import org.infernalstudios.archeryexp.util.json.data.BowEffectData;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Objects;

@Mixin(value = BowItem.class)
public abstract class AExpBowPropertiesMixin implements AexpBowPropertiesExt {
    @Override
    public void rjs$addEffect(MobEffect effect, MobEffect fallback, Integer lv, Integer length, Boolean particles, Boolean tooltips) {
        String location1 = Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(effect)).toString();
        String location2 = Objects.requireNonNull(ForgeRegistries.MOB_EFFECTS.getKey(fallback)).toString();
        BowEffectData potionData = new BowEffectData(location1, location2, lv, length, particles, tooltips);
        List<BowEffectData> effects = this.archeryexp$getEffects();
        effects.add(potionData);
        this.archeryexp$setEffects(effects);
    }
}
