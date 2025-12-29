package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
    @ModifyConstant(method = "getEnchantmentValue", constant = @Constant(intValue = 1))
    private int setEnchantmentValue(int constant){
        var self = (ProjectileWeaponItem)(Object)this;
        if(self instanceof ArrowShootingInterface arrowShooting) return arrowShooting.rjs$getBowAttributes().getEnchantmentValue();
        return constant;
    }

    @Inject(method = "getSupportedHeldProjectiles", at = @At("HEAD"), cancellable = true)
    private void overrideAllSupportedProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir){
        var self = (ProjectileWeaponItem)(Object)this;
        if(!(self instanceof ArrowShootingInterface arrowShooting)) return;
        Predicate<ItemStack> predicate = arrowShooting.rjs$getBowAttributes().getSupportedHeldProjectiles();
        if(predicate == null) return;
        cir.setReturnValue(predicate);
    }

}
