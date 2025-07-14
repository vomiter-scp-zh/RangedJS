package com.vomiter.rangedjs.mixin;

import com.mojang.authlib.GameProfile;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(value = AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player{
    @Unique
    private static final org.apache.logging.log4j.Logger rangedjs_forge_1_20_1$log = LogManager.getLogger(AbstractClientPlayerMixin.class);
    private AbstractClientPlayerMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    @Unique
    @ModifyConstant(method="getFieldOfViewModifier", constant = @Constant(floatValue = 20))
    private float modifyPullTickDivider(float constant){
        Item item = this.getUseItem().getItem();
        if(!(item instanceof BowItem)) return 20.0F;
        return ((BowItemInterface)item).getBowAttributes().getFullChargeTick();
    }

    @Unique
    @Inject(method="getFieldOfViewModifier", at=@At("TAIL"), cancellable = true)
    private void modifyFOV(CallbackInfoReturnable<Float> cir){
        float f = cir.getReturnValue();
        if(!this.isUsingItem()) return;
        Item useItem = this.useItem.getItem();
        if((useItem instanceof BowItem) && !useItem.equals(Items.BOW)){
            int fullChargeTicks = ((BowItemInterface)useItem).getBowAttributes().getFullChargeTick();
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
