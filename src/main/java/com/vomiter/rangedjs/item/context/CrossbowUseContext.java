package com.vomiter.rangedjs.item.context;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class CrossbowUseContext extends UseContext {
    private final ItemStack crossbow;
    private final ItemStack arrowStack;
    private AbstractArrow arrow = null;


    public CrossbowUseContext(Level level, LivingEntity shooter, InteractionHand hand, CallbackInfo ci) {
        super(level, shooter, hand, ci);
        crossbow = this.getShooter().getItemInHand(hand);
        ItemStack ammoStack = this.getShooter().getProjectile(crossbow);
        if(ammoStack.getItem() instanceof ArrowItem) {
            arrowStack = ammoStack;
        } else {
            arrowStack = ItemStack.EMPTY;
        }
    }

    @SuppressWarnings("unused")
    public ItemStack getCrossbow() {
        return crossbow;
    }


    @SuppressWarnings("unused")
    public ItemStack getArrowStack() {
        return arrowStack;
    }

    @HideFromJS
    public void setArrow(AbstractArrow arrow){
        this.arrow = arrow;
    }

    public AbstractArrow getArrow() {
        return arrow;
    }
}
