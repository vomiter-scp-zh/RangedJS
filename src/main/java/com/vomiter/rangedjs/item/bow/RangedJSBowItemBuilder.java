package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.RangedJS;
import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import org.apache.logging.log4j.LogManager;

import java.util.function.Consumer;

public class RangedJSBowItemBuilder extends ItemBuilder {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(RangedJSBowItemBuilder.class);
    transient BowProperties bowProperties = new BowProperties();

    public RangedJSBowItemBuilder(ResourceLocation i) {
        super(i);
        log.debug("RangedJS register a bow:{}", i.toString());
    }

    @Info("To customize the bow.")
    @SuppressWarnings("unused")
    public RangedJSBowItemBuilder bow(Consumer<BowProperties> b){
        b.accept(bowProperties);
        return this;
    }

    @Override
    public Item createObject() {
        BowItem newBow = new BowItem(createItemProperties());
        ((BowItemInterface)newBow).rjs$setBowProperties(bowProperties);

        RangedJS.customizedBows.add(newBow);
        ItemProperties.register(newBow, BowUtils.PULL, BowUtils.PULL_PROVIDER);
        ItemProperties.register(newBow, BowUtils.PULLING, BowUtils.PULLING_PROVIDER);
        if(anim == null){
            anim = UseAnim.BOW;
        }
        return newBow;
    }
}


