package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

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

    @HideFromJS
    default int rjs$getQuickChargeFactor(){return rjs$getBowAttributes().getQuickChargeFactor();}


    @RemapForJS("crossbow")
    @SuppressWarnings("unused")
    default CrossbowItemInterface rjs$crossbow(Consumer<CrossbowProperties> consumer){
        CrossbowProperties properties = this.rjs$getBowProperties();
        consumer.accept(properties);
        this.rjs$setBowProperties(properties);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> CrossbowRenderRegister.register((Item)this));
        return this;
    }
}
