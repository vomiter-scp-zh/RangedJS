package com.vomiter.rangedjs.compat.archeryexp;

import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.effect.MobEffect;
import org.infernalstudios.archeryexp.util.BowProperties;

@SuppressWarnings("unused")
public interface AexpBowPropertiesExt extends BowProperties {
    @RemapForJS("addEffect")
    void rjs$addEffect(MobEffect e1, MobEffect e2, Integer lv, Integer length, Boolean particles);
}
