package com.vomiter.rangedjs.item.callbacks;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RangedJSBowUseContext extends UseContext {
    private final ItemStack bow;
    private final int pullTick;
    private final ItemStack ammo;

    public RangedJSBowUseContext(Level level, Player player, InteractionHand hand) {
        super(level, player, hand);
        bow = this.getPlayer().getUseItem();
        pullTick =  bow.getUseDuration() - this.getPlayer().getUseItemRemainingTicks();
        ammo = this.getPlayer().getProjectile(bow);
    }

    public ItemStack getBow() {
        return bow;
    }

    public int getPullTick() {
        return pullTick;
    }

    public ItemStack getAmmo() {
        return ammo;
    }
}
