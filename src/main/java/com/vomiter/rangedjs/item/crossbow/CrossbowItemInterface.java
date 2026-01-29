package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.context.CrossbowUseContext;
import dev.latvian.mods.rhino.util.HideFromJS;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.function.Consumer;

public interface CrossbowItemInterface extends ArrowShootingInterface<
        CrossbowProperties,
        CrossbowAttributes,
        CrossbowUseBehavior,
        CrossbowUseContext,
        Void
        > {
    @HideFromJS
    default Consumer<CrossbowUseContext> rjs$getCrossbowShootCallback() {
        return rjs$getUseBehavior().getShootCallback();
    }

    @HideFromJS
    default int rjs$getQuickChargeFactor() {
        return rjs$getAttributes().getQuickChargeFactor();
    }

    @RemapForJS("crossbow")
    default CrossbowItemInterface rjs$crossbow(Consumer<CrossbowProperties> consumer) {
        CrossbowProperties props = rjs$getProperties();
        consumer.accept(props);
        rjs$setProperties(props);
        if(FMLEnvironment.dist.isClient())CrossbowRenderRegister.register((Item) this);
        return this;
    }
}
