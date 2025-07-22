package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.context.BowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import com.vomiter.rangedjs.item.crossbow.CrossbowProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CrossbowItem.class)
public abstract class CrossbowItemMixin implements CrossbowItemInterface {
    @Unique
    private CrossbowProperties rjs$bowProperties = new CrossbowProperties();

    @Override
    @Unique
    public CrossbowProperties rjs$getCrossbowProperties(){return this.rjs$bowProperties;}

    @Override
    public void rjs$setCrossbowProperties(CrossbowProperties bowProperties){this.rjs$bowProperties = bowProperties;}

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void beforePull(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        BowUseContext ctx = new BowUseContext(level, player, hand, cir);
        ItemStack item = player.getItemInHand(hand);
        //this.rjs$getUseCallback().accept(ctx);
        if(ctx.getResult().equals(UseContext.Result.DENY)) cir.setReturnValue(InteractionResultHolder.fail(item));
        else if(ctx.getResult().equals(UseContext.Result.ALLOW)) {
            player.startUsingItem(hand);
            cir.setReturnValue(InteractionResultHolder.consume(item));
        }
    }
}
