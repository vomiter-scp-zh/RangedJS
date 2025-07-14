package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.RangedJSBowItemBuilder;
import com.vomiter.rangedjs.item.callbacks.RangedJSBowUseContext;
import com.vomiter.rangedjs.item.callbacks.UseContext;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BowItem.class)
public abstract class BowItemMixin implements BowItemInterface {
    @Unique
    private RangedJSBowItemBuilder.BuilderHelper rangedjs$builderHelper = new RangedJSBowItemBuilder.BuilderHelper();

    @Override
    @Unique
    public RangedJSBowItemBuilder.BuilderHelper rjs$getBowProperties(){return this.rangedjs$builderHelper;}

    @RemapForJS("setBowProperties")
    @Override
    public void rjs$setBowProperties(RangedJSBowItemBuilder.BuilderHelper builderHelper){this.rangedjs$builderHelper = builderHelper;}

    //TODO refactor this with mixin extra someday.
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void beforePull(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir){
        RangedJSBowUseContext ctx = new RangedJSBowUseContext(level, player, hand);
        ItemStack item = player.getItemInHand(hand);
        this.getUseCallback().accept(ctx);
        if(ctx.getResult().equals(UseContext.Result.DENY)) cir.setReturnValue(InteractionResultHolder.fail(item));
        else if(ctx.getResult().equals(UseContext.Result.ALLOW)) cir.setReturnValue(InteractionResultHolder.consume(item));
    }

}
