package com.vomiter.rangedjs.item.bow;

import com.vomiter.rangedjs.item.ProjectileWeaponItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.UseAnim;

import java.util.function.Consumer;

public class BowItemBuilder extends ProjectileWeaponItemBuilder<BowItemBuilder, BowItem, BowProperties> {

    public BowItemBuilder(ResourceLocation id) {
        super(id, new BowProperties());
    }

    @Override protected BowItemBuilder self() { return this; }

    @Info("To customize the bow.")
    public BowItemBuilder bow(Consumer<BowProperties> b) {
        return config(b);
    }

    @Override
    protected BowItem createItem() {
        return new BowItem(createItemProperties());
    }

    @Override
    protected void attachProperties(BowItem item, BowProperties props) {
        ((BowItemInterface) item).rjs$setProperties(props);
    }

    @Override
    protected void onItemCreated(BowItem item, BowProperties props) {
        props.setItem(item);
    }

    @Override
    protected void registerClient(BowItem item) {
        BowRenderRegister.register(item);
    }

    @Override
    protected UseAnim defaultUseAnim() {
        return UseAnim.BOW;
    }
}

