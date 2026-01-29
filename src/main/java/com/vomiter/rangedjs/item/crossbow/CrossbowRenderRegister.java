package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.bow.BowRenderRegister;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CrossbowRenderRegister extends BowRenderRegister {
    public static ResourceLocation CHARGED = ResourceLocation.tryBuild("minecraft", "charged");
    public static ResourceLocation FIREWORK = ResourceLocation.tryBuild("minecraft","firework");

    static public void register(Item newCrossbow){
        BowRenderRegister.register(newCrossbow);
        ItemProperties.register(newCrossbow, CHARGED, (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);
        ItemProperties.register(newCrossbow, FIREWORK, (p_275887_, p_275888_, p_275889_, p_275890_) -> CrossbowItem.isCharged(p_275887_) && CrossbowItem.containsChargedProjectile(p_275887_, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F);
    }

}
