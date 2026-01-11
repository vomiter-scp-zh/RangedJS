package com.vomiter.rangedjs.item;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.Ingredient;

public interface ItemInterface {
    @HideFromJS
    default void rjs$setRepairIngredient(Ingredient ingredient) {}

    @HideFromJS
    default Ingredient rjs$getRepairIngredient() { return Ingredient.EMPTY; }

    @HideFromJS
    default void rjs$setShootSound(SoundEvent soundEvent) {}

    @HideFromJS
    default SoundEvent rjs$getShootSound() { return null; }

}
