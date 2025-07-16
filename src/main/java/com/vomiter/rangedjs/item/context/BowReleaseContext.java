package com.vomiter.rangedjs.item.context;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class BowReleaseContext extends ReleaseContext{
    private AbstractArrow arrow = null;
    public BowReleaseContext(ItemStack itemStack, Level level, LivingEntity livingEntity, int remainTick, CallbackInfo ci) {
        super(itemStack, level, livingEntity, remainTick, ci);
    }

    public ItemStack getBow(){
        if(getItem().getItem() instanceof BowItem) return getItem();
        return null;
    }

    public Player getPlayer(){
        if(getLivingEntity() instanceof Player) return (Player)getLivingEntity();
        return null;
    }

    public void setArrow(AbstractArrow arrow) {
        this.arrow = arrow;
    }

    public AbstractArrow getArrow() {
        return arrow;
    }
}
