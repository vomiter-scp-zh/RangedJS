package com.vomiter.rangedjs.item.crossbow;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;

import java.util.function.Consumer;

public class CrossbowItemBuilder extends ItemBuilder {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(CrossbowItemBuilder.class);
    transient CrossbowProperties crossbowProperties = new CrossbowProperties();

    public CrossbowItemBuilder(ResourceLocation i) {
        super(i);
        log.debug("RangedJS register a crossbow:{}", i.toString());
    }

    @Info("To customize the crossbow.")
    @SuppressWarnings("unused")
    public CrossbowItemBuilder crossbow(Consumer<CrossbowProperties> b){
        b.accept(crossbowProperties);
        return this;
    }

    @Override
    public Item createObject() {
        CrossbowItem newCrossbow = new CrossbowItem(createItemProperties());
        ((CrossbowItemInterface)newCrossbow).rjs$setBowProperties(crossbowProperties);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> CrossbowRenderRegister.register(newCrossbow));
        if(anim == null){
            anim = UseAnim.CROSSBOW;
        }
        return newCrossbow;
    }
}


