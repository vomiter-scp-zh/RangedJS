package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.RangedJS;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import org.apache.logging.log4j.LogManager;

import java.util.function.Consumer;

public class CrossbowItemBuilder extends ItemBuilder {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(CrossbowItemBuilder.class);
    transient CrossbowProperties crossbowProperties = new CrossbowProperties();

    public CrossbowItemBuilder(ResourceLocation i) {
        super(i);
        log.debug("RangedJS register a crossbow:{}", i.toString());
    }

    @Info("To customize the bow.")
    @SuppressWarnings("unused")
    public CrossbowItemBuilder crossbow(Consumer<CrossbowProperties> b){
        b.accept(crossbowProperties);
        return this;
    }

    @Override
    public Item createObject() {
        CrossbowItem newCrossbow = new CrossbowItem(createItemProperties());
        ((CrossbowItemInterface)newCrossbow).rjs$setBowProperties(crossbowProperties);

        if(anim == null){
            anim = UseAnim.CROSSBOW;
        }
        return newCrossbow;
    }
}


