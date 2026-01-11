package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.ItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemRepairMixin implements ItemInterface {

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


    @Inject(method = "isValidRepairItem", at = @At("HEAD"), cancellable = true)
    private void rangedjs$overrideRepair(ItemStack toRepair, ItemStack material,
                                         CallbackInfoReturnable<Boolean> cir) {
        Ingredient ing = rjs$getRepairIngredient();
        if (ing != null && !ing.isEmpty()) {
            cir.setReturnValue(ing.test(material));
        }
    }
}
