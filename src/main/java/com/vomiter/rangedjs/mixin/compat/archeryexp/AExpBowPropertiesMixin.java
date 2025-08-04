package com.vomiter.rangedjs.mixin.compat.archeryexp;

import com.vomiter.rangedjs.compat.archeryexp.AexpBowPropertiesExt;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BowItem;
import org.infernalstudios.archeryexp.util.BowProperties;
import org.infernalstudios.archeryexp.util.PotionData;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Objects;

@Mixin(value = BowItem.class)
public abstract class AExpBowPropertiesMixin implements AexpBowPropertiesExt {
    @Override
    public void rjs$addEffect(MobEffect effect, MobEffect fallback, Integer lv, Integer length, Boolean particles) {
        String location1 = Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.getKey(effect)).toString();
        String location2 = Objects.requireNonNull(BuiltInRegistries.MOB_EFFECT.getKey(fallback)).toString();
        PotionData potionData = new PotionData(location1, location2, lv, length, particles);
        BowProperties $this =((BowProperties)this);
        List<PotionData> effects = $this.getEffects();
        effects.add(potionData);
        $this.setEffects(effects);
    }
}
