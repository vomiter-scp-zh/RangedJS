package com.vomiter.rangedjs.mixin.compat.archeryexp;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.ArrowShootingInterface;
import net.minecraft.client.player.LocalPlayer;
import org.infernalstudios.archeryexp.client.ArrowHudThing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ArrowHudThing.class)
public class ArcheryExpBarMixin {
    @ModifyExpressionValue(
            method = "renderBowBar",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BowItem;getPowerForTime(I)F")
    )
    private static float modifyPowerForTime(float original, @Local LocalPlayer player){
        if(player == null) return original;
        if(player.getUseItem().getItem() instanceof ArrowShootingInterface bow){
            float f = (float)(player.getUseItem().getUseDuration() - player.getUseItemRemainingTicks()) / bow.rjs$getBowAttributes().getFullChargeTick();
            f = (f * f + f * 2.0F) / 3.0F;
            if (f > 1.0F) {
                f = 1.0F;
            }
            return f;
        }
        return original;
    }
}
