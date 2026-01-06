package com.vomiter.rangedjs.mixin;

import com.mojang.authlib.GameProfile;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player{
    @Unique
    private static final org.apache.logging.log4j.Logger rangedjs_forge_1_20_1$log = LogManager.getLogger(AbstractClientPlayerMixin.class);
    private AbstractClientPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Redirect(
            method = "getFieldOfViewModifier",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean skipVanillaBowFovShrink(ItemStack stack, Item item) {
        if (item == Items.BOW) {
            return false;
        }
        return stack.is(item);
    }

    @Unique
    @Inject(method="getFieldOfViewModifier", at=@At("TAIL"), cancellable = true)
    private void modifyFOV(CallbackInfoReturnable<Float> cir){
        float f = cir.getReturnValue();
        if(!this.isUsingItem()) return;
        Item useItem = this.useItem.getItem();
        if((useItem instanceof BowItem)){
            int fullChargeTicks = ((BowItemInterface)useItem).rjs$getBowAttributes().getFullChargeTick();
            int i = this.getTicksUsingItem();
            float f1 = (float)i / fullChargeTicks;
            if (f1 > 1.0F) {
                f1 = 1.0F;
            } else {
                f1 *= f1;
            }
            f *= 1.0F - f1 * 0.15F;
            cir.setReturnValue(f);
        }
    }


}
