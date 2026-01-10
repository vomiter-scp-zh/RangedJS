package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.ItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public abstract class Item_RepairFieldMixin implements ItemInterface {

    @Unique
    private Ingredient rangedjs$repairIngredient = Ingredient.EMPTY;

    @Override
    public void rjs$setRepairIngredient(Ingredient ingredient) {
        this.rangedjs$repairIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
    }

    @Override
    public Ingredient rjs$getRepairIngredient() {
        return rangedjs$repairIngredient;
    }
}
