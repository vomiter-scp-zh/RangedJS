package com.vomiter.rangedjs.mixin.compat.archeryexp;

import com.vomiter.rangedjs.compat.archeryexp.BowPropertiesInterface;
import com.vomiter.rangedjs.item.bow.BowItemBuilder;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BowItemBuilder.class, remap = false)
public class BowItemBuilderMixin {
    @Inject(method = "createObject()Lnet/minecraft/world/item/Item;", at = @At("TAIL"))
    void applyAExpProp(CallbackInfoReturnable<Item> cir){
        BowItem item = (BowItem) cir.getReturnValue();
        var aexpbowproperties = ((BowPropertiesInterface)((BowItemInterface)item).rjs$getBowProperties()).rjs$getAExpProperties();
        if(aexpbowproperties == null) return;
        aexpbowproperties.bowAccept(item);
    }
}
