package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.BowProperties;
import com.vomiter.rangedjs.item.context.BowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BowItem.class)
public abstract class BowItemMixin implements BowItemInterface {
    @Unique
    private BowProperties rangedjs$bowProperties = new BowProperties();

    @Override
    @Unique
    public BowProperties rjs$getBowProperties(){return this.rangedjs$bowProperties;}

    @Override
    public void rjs$setBowProperties(BowProperties bowProperties){this.rangedjs$bowProperties = bowProperties;}

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void beforePull(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        BowUseContext ctx = new BowUseContext(level, player, hand, cir);
        ItemStack item = player.getItemInHand(hand);
        this.getUseCallback().accept(ctx);
        if(ctx.getResult().equals(UseContext.Result.DENY)) cir.setReturnValue(InteractionResultHolder.fail(item));
        else if(ctx.getResult().equals(UseContext.Result.ALLOW)) {
            player.startUsingItem(hand);
            cir.setReturnValue(InteractionResultHolder.consume(item));
        }
    }
}
