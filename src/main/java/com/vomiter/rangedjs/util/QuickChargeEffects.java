package com.vomiter.rangedjs.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.item.enchantment.LevelBasedValue;

import java.util.Optional;

public final class QuickChargeEffects {

    /**
     * 只在 Quick Charge 的 crossbow_charge_time
     * 仍然是 minecraft:add + linear 時回傳 AddValue
     */
    public static Optional<AddValue> getQuickChargeAddEffectIfLinear(RegistryAccess access) {
        Holder<Enchantment> qc =
                access.registryOrThrow(Registries.ENCHANTMENT)
                        .getHolderOrThrow(Enchantments.QUICK_CHARGE);

        Object raw = qc.value()
                .effects()
                .get(EnchantmentEffectComponents.CROSSBOW_CHARGE_TIME);

        if (!(raw instanceof EnchantmentValueEffect effect)) {
            return Optional.empty();
        }

        if (!(effect instanceof AddValue add)) {
            return Optional.empty();
        }

        if (!(add.value() instanceof LevelBasedValue.Linear)) {
            return Optional.empty();
        }

        return Optional.of(add);
    }
}
