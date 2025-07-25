package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.context.UseContext;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;

import java.util.function.Consumer;

public interface CrossbowItemInterface extends ArrowShootingInterface {

    @Override
    @HideFromJS
    default CrossbowProperties rjs$getBowProperties(){return new CrossbowProperties();}

    @Override
    @HideFromJS
    default CrossbowUseBehavior rjs$getUseBehavior(){return (CrossbowUseBehavior)rjs$getBowProperties().getUseBehavior();}

    @Override
    @HideFromJS
    default CrossbowAttributes rjs$getBowAttributes(){return rjs$getBowProperties().crossbowAttributes;}

    @HideFromJS
    default Consumer<UseContext> rjs$getCrossbowShootCallback(){return rjs$getUseBehavior().shootCallback;}

    @RemapForJS("crossbow")
    @SuppressWarnings("unused")
    default CrossbowItemInterface rjs$crossbow(Consumer<CrossbowProperties> consumer){
        CrossbowProperties properties = this.rjs$getBowProperties();
        consumer.accept(properties);
        this.rjs$setBowProperties(properties);
        return this;
    }
}
