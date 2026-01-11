package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.ProjectileWeaponItemBuilder;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.UseAnim;

import java.util.function.Consumer;

public class CrossbowItemBuilder extends ProjectileWeaponItemBuilder<CrossbowItemBuilder, CrossbowItem, CrossbowProperties> {

    public CrossbowItemBuilder(ResourceLocation id) {
        super(id, new CrossbowProperties());
    }

    @Override protected CrossbowItemBuilder self() { return this; }

    @Info("To customize the crossbow.")
    public CrossbowItemBuilder crossbow(Consumer<CrossbowProperties> b) {
        return config(b);
    }

    @HideFromJS
    @Override
    protected CrossbowItem createItem() {
        return new CrossbowItem(createItemProperties());
    }

    @HideFromJS
    @Override
    protected void attachProperties(CrossbowItem item, CrossbowProperties props) {
        ((CrossbowItemInterface)item).rjs$setProperties(props);
    }

    @HideFromJS
    @Override
    protected void registerClient(CrossbowItem item) {
        CrossbowRenderRegister.register(item);
    }

    @HideFromJS
    @Override
    protected UseAnim defaultUseAnim() {
        return UseAnim.CROSSBOW;
    }
}
