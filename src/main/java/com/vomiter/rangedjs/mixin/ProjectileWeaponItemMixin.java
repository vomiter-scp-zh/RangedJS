package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import com.vomiter.rangedjs.item.bow.BowUtils;
import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.projectile.ProjectileInterface;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {
    @ModifyConstant(method = "getEnchantmentValue", constant = @Constant(intValue = 1))
    private int setEnchantmentValue(int constant){
        var self = (ProjectileWeaponItem)(Object)this;
        if(self instanceof ArrowShootingInterface arrowShooting) return arrowShooting.rjs$getAttributes().getEnchantmentValue();
        return constant;
    }

    @ModifyExpressionValue(
            method = "useAmmo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;processAmmoUse(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;I)I"
            )
    )
    private static int modifyInfinity(
            int original,
            @Local(argsOnly = true, ordinal = 0) ItemStack weapon,
            @Local(argsOnly = true, ordinal = 1) ItemStack ammo
    ){
        if(weapon.getItem() instanceof ArrowShootingInterface bowItem){
            if(bowItem.rjs$isSpecialInfinity()){
                return 0;
            }
            if(bowItem.rjs$isInfinity() && ammo.is(Items.ARROW)){
                return 0;
            }
        }
        return original;
    }

    @Inject(
            method = "shoot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"
            )
    )
    private void modifyBowArrow(
            ServerLevel level, LivingEntity shooter, InteractionHand hand, ItemStack weapon, List<ItemStack> projectileItems, float velocity, float inaccuracy, boolean isCrit, LivingEntity target, CallbackInfo ci
            , @Local Projectile projectile
            ){
        if(shooter.level().isClientSide()) return;
        if(weapon.getItem() instanceof BowItemInterface bowItem){if(projectile instanceof AbstractArrow arrow){
            double rjsBaseDamage = bowItem.rjs$getBaseDamage();
            double modifiedBaseDamage = arrow.getBaseDamage() + 0;
            modifiedBaseDamage += rjsBaseDamage - BowUtils.defaultBaseDamage;
            int extraPower = bowItem.rjs$getPower();
            Holder<Enchantment> power = shooter.level().registryAccess().holderOrThrow(Enchantments.POWER);
            if(extraPower >= 1){
                if(!(weapon.getEnchantmentLevel(power) >= 1)){
                    modifiedBaseDamage += 0.5;
                }
                modifiedBaseDamage += extraPower * 0.5;
            }
            arrow.setBaseDamage(modifiedBaseDamage);
            if(bowItem.rjs$isFlamingArrow()){
                arrow.setRemainingFireTicks(20 * 100);
            }
            if(bowItem.rjs$isNoDamage()){
                arrow.setBaseDamage(0);
            }
            ((ProjectileInterface)arrow).rangedjs$setHitBehavior(bowItem.rjs$getHitBehavior());
            if(shooter instanceof Player player){
                BowReleaseContext bowReleaseContext = new BowReleaseContext(weapon, level, player, player.getUseItemRemainingTicks(), ci);
                bowReleaseContext.setArrow(arrow);
                bowItem.rjs$getReleaseCallback().accept(bowReleaseContext);
            }
        }}
    }

}
