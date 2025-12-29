package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(value = CrossbowItem.class)
public class CrossbowItemMixin implements CrossbowItemInterface {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onPull(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        CrossbowUseContext ctx = new CrossbowUseContext(level, player, hand, cir);
        ItemStack item = player.getItemInHand(hand);
        if(CrossbowItem.isCharged(item)) return;
        this.rjs$getUseCallback().accept(ctx);
        if(ctx.getResult().equals(UseContext.Result.DENY)) cir.setReturnValue(InteractionResultHolder.fail(item));
        else if(ctx.getResult().equals(UseContext.Result.ALLOW)) {
            player.startUsingItem(hand);
            cir.setReturnValue(InteractionResultHolder.consume(item));
        }

    }

    @Inject(method = "getAllSupportedProjectiles", at = @At("HEAD"), cancellable = true)
    private void overrideAllSupportedProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir){
        Predicate<ItemStack> predicate = this.rjs$getBowAttributes().getAllSupportedProjectiles();
        if(predicate == null) return;
        cir.setReturnValue(predicate);
    }

}
