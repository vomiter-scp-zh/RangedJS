package com.vomiter.rangedjs.item.callbacks;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BowUseContext extends UseContext {
    private final ItemStack bow;
    private final ItemStack ammo;

    public BowUseContext(Level level, Player player, InteractionHand hand) {
        super(level, player, hand);
        bow = this.getPlayer().getUseItem();
        ammo = this.getPlayer().getProjectile(bow);
    }

    @SuppressWarnings("unused")
    public ItemStack getBow() {
        return bow;
    }
    @SuppressWarnings("unused")
    public ItemStack getAmmo() {
        return ammo;
    }
}
