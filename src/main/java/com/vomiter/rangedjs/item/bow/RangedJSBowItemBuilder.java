package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.RangedJS;
import com.vomiter.rangedjs.item.callbacks.RangedJSBowUseContext;
import com.vomiter.rangedjs.projectile.arrow.HitBehavior;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BowItem;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.world.item.UseAnim;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;

public class RangedJSBowItemBuilder extends ItemBuilder {
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(RangedJSBowItemBuilder.class);
    transient BuilderHelper builderHelper = new BuilderHelper();

    public RangedJSBowItemBuilder(ResourceLocation i) {
        super(i);
        log.debug("RangedJS register a bow:{}", i.toString());
    }


    public static class BuilderHelper {
        protected final BowAttributes bowAttributes = new BowAttributes();
        protected final HitBehavior hitBehavior = new HitBehavior();
        public Consumer<RangedJSBowUseContext> useCallback = (t)->{};

        public BuilderHelper(){}

        @Info("To modify some default attributes of the bow. E.g. ticks to full charge, base arrow damage, native enchantment-like capabilities.")
        @SuppressWarnings("unused")
        public BuilderHelper modifyBow(Consumer<BowAttributes> b){
            b.accept(bowAttributes);
            return this;
        }

        @Info("To add stuffs that will happen when the shot arrows hit entity/block")
        @SuppressWarnings("unused")
        public BuilderHelper onArrowHit(Consumer<HitBehavior> b){
            b.accept(hitBehavior);
            return this;
        }

        @Info("To add stuffs that will happen when the shot arrows hit entity/block")
        @SuppressWarnings("unused")
        public BuilderHelper pull(Consumer<RangedJSBowUseContext> c){
            useCallback = c;
            return this;
        }
    }

    @Info("To customize the bow.")
    @SuppressWarnings("unused")
    public RangedJSBowItemBuilder bow(Consumer<BuilderHelper> b){
        b.accept(builderHelper);
        return this;
    }

    @Override
    public Item createObject() {
        final int fullChargeTicks = builderHelper.bowAttributes.fullChargeTick;
        BowItem newBow = new RangedJSBowItem(createItemProperties(), builderHelper);

        RangedJS.customizedBows.add(newBow);
        ItemProperties.register(newBow, BowUtils.PULL, BowUtils.PULL_PROVIDER(fullChargeTicks));
        ItemProperties.register(newBow, BowUtils.PULLING, BowUtils.PULLING_PROVIDER);
        if(anim == null){
            anim = UseAnim.BOW;
        }
        return newBow;
    }
}


