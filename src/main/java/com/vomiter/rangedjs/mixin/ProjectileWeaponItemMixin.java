package com.vomiter.rangedjs.mixin;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
    @ModifyConstant(method = "getEnchantmentValue", constant = @Constant(intValue = 1))
    private int setEnchantmentValue(int constant){
        var self = (ProjectileWeaponItem)(Object)this;
        if(self instanceof ArrowShootingInterface arrowShooting) return arrowShooting.rjs$getBowAttributes().getEnchantmentValue();
        return constant;
    }
}
