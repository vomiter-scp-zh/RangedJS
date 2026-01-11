package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.bow.BowRenderRegister;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Items;

public class CrossbowRenderRegister {
    public static ResourceLocation CHARGED = ResourceLocation.tryBuild("minecraft", "charged");
    public static ResourceLocation FIREWORK = ResourceLocation.tryBuild("minecraft","firework");

    static public void register(CrossbowItem item){
        ItemProperties.register(item, BowRenderRegister.PULL, BowRenderRegister.PULL_PROVIDER);
        ItemProperties.register(item, BowRenderRegister.PULLING, BowRenderRegister.PULLING_PROVIDER);
        ItemProperties.register(item, CHARGED, (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);
        ItemProperties.register(item, FIREWORK, (p_275887_, p_275888_, p_275889_, p_275890_) -> {
            return CrossbowItem.isCharged(p_275887_) && p_275887_.get(DataComponents.CHARGED_PROJECTILES).contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });

    }
}
