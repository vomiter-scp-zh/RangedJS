package com.vomiter.rangedjs.item;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public abstract class CommonBuilder<I extends Item> extends ItemBuilder {
    public CommonBuilder(ResourceLocation i) {
        super(i);
    }

    public Ingredient repairIngredient = Ingredient.EMPTY;

    @Info("Ingredient to Repair")
    public CommonBuilder<I> repairWith(Ingredient ingredient){
        this.repairIngredient = ingredient;
        return this;
    }

    protected abstract I createItem();

    @Override
    public I createObject() {
        I item = createItem();
        if(item instanceof ItemInterface itemInterface){
            itemInterface.rjs$setRepairIngredient(repairIngredient);
        }
        return item;
    }

}
