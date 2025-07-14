package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.callbacks.RangedJSBowUseContext;
import com.vomiter.rangedjs.item.callbacks.UseContext;
import com.vomiter.rangedjs.projectile.arrow.ProjectileInterface;
import com.vomiter.rangedjs.projectile.arrow.HitBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class RangedJSBowItem extends BowItem implements BowItemInterface {
    public final RangedJSBowItemBuilder.BuilderHelper builderHelper;
    
    public final BowAttributes bowAttributes;
    public final HitBehavior hitBehavior;

    public RangedJSBowItem(Properties itemProperties, RangedJSBowItemBuilder.BuilderHelper builderHelper) {
        super(itemProperties);
        this.builderHelper = builderHelper;
        bowAttributes =  builderHelper.bowAttributes;
        hitBehavior = builderHelper.hitBehavior;
    }


    //@Override
    public void legacy$releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int remainTick) {if (entity instanceof Player player) {
        boolean isInfinity =  bowAttributes.infinity || stack.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
        boolean canAlwaysShoot =
                player.getAbilities().instabuild
                        || isInfinity;

        ItemStack ammoStack = player.getProjectile(stack);

        int pullTick = this.getUseDuration(stack) - remainTick;
        pullTick = ForgeEventFactory.onArrowLoose(stack, level, player, pullTick, !ammoStack.isEmpty() || canAlwaysShoot);
        if (pullTick < 0) return;
        if(ammoStack.isEmpty() && !canAlwaysShoot) return;

        if (ammoStack.isEmpty()) {ammoStack = new ItemStack(Items.ARROW);}

        float pullPower = getPowerForPullTick(pullTick);
        if((double) pullPower < 0.10) return;

        boolean isAmmoInfinite =
                 bowAttributes.specialInfinity
                        || (ammoStack.getItem() instanceof ArrowItem
                        && ((ArrowItem) ammoStack.getItem()).isInfinite(ammoStack, stack, player));

        boolean shouldNotShrinkAmmo = isAmmoInfinite || player.getAbilities().instabuild;

        if (!level.isClientSide) {

            ArrowItem arrowitem = (ArrowItem)(ammoStack.getItem() instanceof ArrowItem ? ammoStack.getItem() : Items.ARROW);
            AbstractArrow abstractarrow = arrowitem.createArrow(level, ammoStack, player);
            abstractarrow = customArrow(abstractarrow);
            abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, pullPower * 3.0F *  bowAttributes.arrowSpeedScale, 1.0F);
            if (pullPower == 1.0F) {abstractarrow.setCritArrow(true);}
            abstractarrow.setBaseDamage( bowAttributes.baseDamage);

            stack.hurtAndBreak(1, player, (player_) -> player_.broadcastBreakEvent(player.getUsedItemHand()));
            if (shouldNotShrinkAmmo || player.getAbilities().instabuild && (ammoStack.is(Items.SPECTRAL_ARROW) || ammoStack.is(Items.TIPPED_ARROW))) {
                abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }

            abstractarrow = addEnchantEffectsToArrow(stack, abstractarrow);
            if( bowAttributes.noDamage) abstractarrow.setBaseDamage(0);
            ((ProjectileInterface)abstractarrow).rangedjs$setHitConsumerContainer(hitBehavior.hitConsumers);
            level.addFreshEntity(abstractarrow);
        }

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + pullPower * 0.5F
        );
        if (!shouldNotShrinkAmmo && !player.getAbilities().instabuild) {
            ammoStack.shrink(1);
            if (ammoStack.isEmpty()) {
                player.getInventory().removeItem(ammoStack);
            }
        }

        player.awardStat(Stats.ITEM_USED.get(this));

    }}

    @Override
    public RangedJSBowItemBuilder.BuilderHelper rjs$getBowProperties() {
        return this.builderHelper;
    }

    public float getPowerForPullTick(int pullTick) {
        float f = (float)pullTick /  bowAttributes.fullChargeTick;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public AbstractArrow addEnchantEffectsToArrow(@NotNull ItemStack stack, AbstractArrow arrow){
        int enchantPower =  bowAttributes.power + stack.getEnchantmentLevel(Enchantments.POWER_ARROWS);
        if (enchantPower > 0) {
            arrow.setBaseDamage(arrow.getBaseDamage() + (double) enchantPower * 0.5D + 0.5D);
        }

        int enchantKnockback =  bowAttributes.knockBack + stack.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
        if (enchantKnockback > 0) {
            arrow.setKnockback(enchantKnockback);
        }

        if ( bowAttributes.flamingArrow || stack.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
            arrow.setSecondsOnFire(100);
        }

        return arrow;
    }


    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public @NotNull AbstractArrow customArrow(@NotNull AbstractArrow arrow) {
        return arrow;
    }

}
