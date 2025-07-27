package com.vomiter.rangedjs.item.bow;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.apache.logging.log4j.LogManager;

import java.util.function.Consumer;

public class BowItemBuilder extends ItemBuilder {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(BowItemBuilder.class);
    transient BowProperties bowProperties = new BowProperties();

    public BowItemBuilder(ResourceLocation i) {
        super(i);
        log.debug("RangedJS register a bow:{}", i.toString());
    }

    @Info("To customize the bow.")
    @SuppressWarnings("unused")
    public BowItemBuilder bow(Consumer<BowProperties> b){
        b.accept(bowProperties);
        return this;
    }

    @Override
    public Item createObject() {
        BowItem newBow = new BowItem(createItemProperties());
        ((BowItemInterface)newBow).rjs$setBowProperties(bowProperties);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> BowRenderRegister.register(newBow));
        if(anim == null){
            anim = UseAnim.BOW;
        }
        return newBow;
    }
}


