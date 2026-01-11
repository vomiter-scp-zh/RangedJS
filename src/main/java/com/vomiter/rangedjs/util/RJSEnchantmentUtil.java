package com.vomiter.rangedjs.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class RJSEnchantmentUtil {
    final RegistryAccess access;
    public RJSEnchantmentUtil(RegistryAccess ra){
        access = ra;
    }
    public Holder<Enchantment> get(ResourceKey<Enchantment> key){
        return access.holderOrThrow(key);
    }


}
