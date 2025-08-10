package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.neoforged.fml.loading.FMLLoader;

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
    default CrossbowAttributes rjs$getBowAttributes(){return rjs$getBowProperties().getAttributes();}

    @HideFromJS
    default Consumer<CrossbowUseContext> rjs$getCrossbowShootCallback(){return rjs$getUseBehavior().getShootCallback();}

    @Override
    default Consumer<CrossbowUseContext> rjs$getUseCallback(){
        return rjs$getUseBehavior().useCallback;
    }

    @Override
    default Consumer<CrossbowUseContext> rjs$getUseTickCallback(){
        return rjs$getUseBehavior().useCallback;
    }


    @RemapForJS("crossbow")
    @SuppressWarnings("unused")
    default CrossbowItemInterface rjs$crossbow(Consumer<CrossbowProperties> consumer){
        CrossbowProperties properties = this.rjs$getBowProperties();
        consumer.accept(properties);
        this.rjs$setBowProperties(properties);
        if(FMLLoader.getDist().isClient()){
            CrossbowRenderRegister.innerRegister((CrossbowItem)this);
        }
        return this;
    }
}
