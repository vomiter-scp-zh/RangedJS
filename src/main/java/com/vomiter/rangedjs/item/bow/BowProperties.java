package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.ArrowShootingProperties;
import com.vomiter.rangedjs.item.UseBehavior;
import com.vomiter.rangedjs.projectile.ArrowHitBehavior;
import com.vomiter.rangedjs.projectile.HitBehavior;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class BowProperties implements ArrowShootingProperties {
    protected final BowAttributes bowAttributes = new BowAttributes();
    protected final ArrowHitBehavior hitBehavior = new ArrowHitBehavior();
    protected BowUseBehavior bowUseBehavior = new BowUseBehavior();
    protected Item item;

    public BowProperties(){}

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
    public BowProperties modifyBow(Consumer<BowAttributes> b){
        b.accept(bowAttributes);
        return this;
    }

    @Info("To add stuffs that will happen when the shot arrows hit entity/block")
    @SuppressWarnings("unused")
    public BowProperties onArrowHit(Consumer<ArrowHitBehavior> b){
        b.accept(hitBehavior);
        return this;
    }

    @Info("To modify how the bow behaves when pull/release")
    @SuppressWarnings("unused")
    public BowProperties onUse(Consumer<BowUseBehavior> b){
        b.accept(bowUseBehavior);
        return this;
    }

    @Override
    public UseBehavior getUseBehavior() {
        return bowUseBehavior;
    }

    @Override
    public BowAttributes getAttributes() {
        return bowAttributes;
    }

    @Override
    public HitBehavior getHitBehavior() {
        return hitBehavior;
    }
}
