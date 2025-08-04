package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ArrowShootingProperties;
import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.projectile.ArrowHitBehavior;
import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class CrossbowProperties implements ArrowShootingProperties {
    protected final CrossbowAttributes crossbowAttributes = new CrossbowAttributes();
    protected final ArrowHitBehavior arrowhitBehavior = new ArrowHitBehavior();
    protected CrossbowUseBehavior crossbowUseBehavior = new CrossbowUseBehavior();
    protected Item item;

    public CrossbowProperties(){}

    @Override
    @HideFromJS
    public Item getItem(){
        return item;
    }

    @HideFromJS
    public void setItem(Item i){
        item = i;
    }


    @Info("To modify some default attributes of the bow. E.g. ticks to full charge, base arrow damage, native enchantment-like capabilities.")
    @SuppressWarnings("unused")
    public CrossbowProperties modifyCrossbow(Consumer<CrossbowAttributes> b){
        b.accept(crossbowAttributes);
        return this;
    }

    @Info("To add stuffs that will happen when the shot arrows hit entity/block")
    @SuppressWarnings("unused")
    public CrossbowProperties onArrowHit(Consumer<ArrowHitBehavior> b){
        b.accept(arrowhitBehavior);
        return this;
    }

    @Info("To modify how the bow behaves when pull/release")
    @SuppressWarnings("unused")
    public CrossbowProperties onUse(Consumer<CrossbowUseBehavior> b){
        b.accept(crossbowUseBehavior);
        return this;
    }

    @Override
    public UseBehavior getUseBehavior() {
        return crossbowUseBehavior;
    }

    @Override
    public CrossbowAttributes getAttributes() {
        return crossbowAttributes;
    }

    @Override
    public HitBehavior getHitBehavior() {
        return arrowhitBehavior;
    }
}
