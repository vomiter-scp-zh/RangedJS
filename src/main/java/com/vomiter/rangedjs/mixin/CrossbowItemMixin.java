package com.vomiter.rangedjs.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import com.vomiter.rangedjs.item.context.UseContext;
import com.vomiter.rangedjs.item.crossbow.CrossbowItemInterface;
import com.vomiter.rangedjs.item.crossbow.CrossbowProperties;
import com.vomiter.rangedjs.util.CrossbowChargeTimeInverse;
import com.vomiter.rangedjs.util.QuickChargeEffects;
import com.vomiter.rangedjs.util.QuickChargeInspector;
import com.vomiter.rangedjs.util.RJSEnchantmentUtil;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(value = CrossbowItem.class)
public class CrossbowItemMixin implements CrossbowItemInterface {
    @Unique
    private CrossbowProperties rangedjs$props = new CrossbowProperties();

    @Override
    public void rjs$setProperties(CrossbowProperties props) {
        this.rangedjs$props = (props == null) ? new CrossbowProperties() : props;
    }

    @Override
    public CrossbowProperties rjs$getProperties() {
        return this.rangedjs$props;
    }


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

    @ModifyConstant(method = "getChargeDuration", constant = @Constant(floatValue = 1.25F))
    private static float modifyCharge(float chargeDuration, @Local(argsOnly = true) ItemStack stack){
        if(stack.getItem() instanceof CrossbowItemInterface crossbow){
            return (float)(crossbow.rjs$getFullChargeTick() / 20);
        }
        return chargeDuration;
    }

    @Inject(method = "getChargeDuration", at = @At("RETURN"), cancellable = true)
    private static void modifyQuickDraw(ItemStack stack, LivingEntity shooter, CallbackInfoReturnable<Integer> cir){
        if(stack.getItem() instanceof CrossbowItemInterface crossbow){
            if(crossbow.rjs$getQuickChargeFactor() == null) return;
            RegistryAccess access = shooter.registryAccess();
            int qcLv = stack.getEnchantmentLevel(new RJSEnchantmentUtil(access).get(Enchantments.QUICK_CHARGE));
            if(qcLv < 1) return;
            QuickChargeInspector.QuickChargeChargeTimeConfig config = QuickChargeInspector.read(access);
            if(!config.isAdd() || !config.isLinear()) return;
            Optional<AddValue> opt = QuickChargeEffects.getQuickChargeAddEffectIfLinear(access);
            if(opt.isEmpty()) return;
            int nonQCFullChargeTicks = CrossbowChargeTimeInverse.invertBaseFullChargeTicksPickMin_AssumingAddLinear(
                    cir.getReturnValue() * 20,
                    qcLv,
                    opt.get()
            );
            int finalTicks = nonQCFullChargeTicks - crossbow.rjs$getQuickChargeFactor() * qcLv;
            cir.setReturnValue(finalTicks);
        }
    }

    @Inject(method = "getAllSupportedProjectiles", at = @At("HEAD"), cancellable = true)
    private void overrideAllSupportedProjectiles(CallbackInfoReturnable<Predicate<ItemStack>> cir){
        Predicate<ItemStack> predicate = this.rjs$getAttributes().getAllSupportedProjectiles();
        if(predicate == null) return;
        cir.setReturnValue(predicate);
    }

}
