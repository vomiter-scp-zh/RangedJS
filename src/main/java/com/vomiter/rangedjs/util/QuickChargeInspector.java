package com.vomiter.rangedjs.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public final class QuickChargeInspector {

    private static final ResourceLocation ADD_ID = ResourceLocation.fromNamespaceAndPath("minecraft", "add");
    private static final ResourceLocation LINEAR_ID = ResourceLocation.fromNamespaceAndPath("minecraft", "linear");

    public static record QuickChargeChargeTimeConfig(
            boolean isAdd,
            boolean isLinear,
            Float linearBase,                 // 只有 isLinear=true 才有
            Float linearPerLevelAboveFirst,   // 只有 isLinear=true 才有
            LevelBasedValue value             // 若 isAdd=true 會有；可用 calculate(level)
    ) {}

    public static QuickChargeChargeTimeConfig read(RegistryAccess access) {
        Holder<Enchantment> qc = new RJSEnchantmentUtil(access).get(Enchantments.QUICK_CHARGE);
        Object raw = qc.value().effects().get(EnchantmentEffectComponents.CROSSBOW_CHARGE_TIME);
        if (!(raw instanceof EnchantmentValueEffect effect)) {
            return new QuickChargeChargeTimeConfig(false, false, null, null, null);
        }

        // 1) 判斷是不是 minecraft:add
        ResourceLocation effectType = BuiltInRegistries.ENCHANTMENT_VALUE_EFFECT_TYPE.getKey(effect.codec());
        if (!ADD_ID.equals(effectType)) {
            return new QuickChargeChargeTimeConfig(false, false, null, null, null);
        }

        AddValue add = (AddValue) effect;
        LevelBasedValue v = add.value();

        // 2) 判斷 LevelBasedValue 是不是 linear
        ResourceLocation vType = BuiltInRegistries.ENCHANTMENT_LEVEL_BASED_VALUE_TYPE.getKey(v.codec());

        if (v instanceof LevelBasedValue.Linear(float base, float perLevelAboveFirst) && LINEAR_ID.equals(vType)) {
            return new QuickChargeChargeTimeConfig(
                    true,
                    true,
                    base,
                    perLevelAboveFirst,
                    v
            );
        }

        // 不是 linear（可能 constant / lookup / clamped / fraction...）
        return new QuickChargeChargeTimeConfig(true, false, null, null, v);
    }

    /** 取得指定等級「直接寫進去的加成值」（秒）。Quick Charge 原版會是 -0.25/-0.5/-0.75 */
    public static float getDeltaSeconds(RegistryAccess access, int level) {
        QuickChargeChargeTimeConfig cfg = read(access);
        if (!cfg.isAdd() || cfg.value() == null) return 0f;
        return cfg.value().calculate(level);
    }

    /** 取得「每升一級多減多少秒」：對 linear 就是 per_level_above_first（原版 -0.25） */
    public static float getPerLevelSecondsIfLinear(RegistryAccess access) {
        QuickChargeChargeTimeConfig cfg = read(access);
        if (!cfg.isLinear() || cfg.linearPerLevelAboveFirst() == null) return 0f;
        return cfg.linearPerLevelAboveFirst();
    }
}
