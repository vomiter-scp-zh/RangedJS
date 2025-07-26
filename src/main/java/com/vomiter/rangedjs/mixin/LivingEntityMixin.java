package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.context.BowUseContext;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class)
public class LivingEntityMixin {
    @Unique
    LivingEntity rjs$this = (LivingEntity)(Object)this;

    @Inject(method = "updatingUsingItem", at = @At("HEAD"))
    private void pullTick(CallbackInfo ci){
        if(!(rjs$this instanceof Player)) return; //duct tape
        BowUseContext ctx = new BowUseContext(rjs$this.level(), (Player)rjs$this, rjs$this.getUsedItemHand(), ci);
        ItemStack itemStack = rjs$this.getUseItem();
        if(itemStack.getItem() instanceof BowItem){
            ((BowItemInterface)itemStack.getItem()).rjs$getUseTickCallback().accept(ctx);
        }
    }
}
