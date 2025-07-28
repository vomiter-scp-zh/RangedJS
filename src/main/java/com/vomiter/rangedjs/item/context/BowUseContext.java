package com.vomiter.rangedjs.item.context;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class BowUseContext extends UseContext {
    private final ItemStack bow;
    private final ItemStack arrow;


    public BowUseContext(Level level, Player player, InteractionHand hand, CallbackInfo ci) {
        super(level, player, hand, ci);
        bow = this.getPlayer().getItemInHand(hand);
        arrow = this.getPlayer().getProjectile(bow);
    }

    @SuppressWarnings("unused")
    public ItemStack getBow() {
        return bow;
    }


    @SuppressWarnings("unused")
    public ItemStack getArrowStack() {
        return arrow;
    }
}
