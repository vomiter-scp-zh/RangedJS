package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.ArrowShootingInterface;
import com.vomiter.rangedjs.item.context.BowReleaseContext;
import com.vomiter.rangedjs.item.context.BowUseContext;
import dev.latvian.mods.rhino.util.RemapForJS;
import net.minecraft.world.item.BowItem;
import net.neoforged.fml.loading.FMLLoader;

import java.util.function.Consumer;

public interface BowItemInterface extends ArrowShootingInterface<
        BowProperties,
        BowAttributes,
        BowUseBehavior,
        BowUseContext,
        BowReleaseContext
        > {

    @RemapForJS("bow")
    default BowItemInterface rjs$bow(Consumer<BowProperties> consumer) {
        BowProperties props = rjs$getProperties();
        consumer.accept(props);
        rjs$setProperties(props);
        if(FMLLoader.getDist().isClient()) BowRenderRegister.register((BowItem) this);
        return this;
    }
}
