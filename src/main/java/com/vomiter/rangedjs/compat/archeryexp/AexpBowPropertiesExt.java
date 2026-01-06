package com.vomiter.rangedjs.compat.archeryexp;

import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.effect.MobEffect;
import org.infernalstudios.archeryexp.util.mixinterfaces.IBowProperties;

@SuppressWarnings("unused")
public interface AexpBowPropertiesExt extends IBowProperties {
    @RemapForJS("addEffect")
    void rjs$addEffect(MobEffect e1, MobEffect e2, Integer lv, Integer length, Boolean particles, Boolean tooltips);
}
