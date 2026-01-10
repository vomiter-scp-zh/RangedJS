package com.vomiter.rangedjs;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;



//TODO compat with archery expansion
//TODO make skeleton use custom bow


//TODO beforeShoot/afterShoot
//TODO make special ammo/arrow(projectile)
//TODO greater scoping

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RangedJS.MODID)
public class RangedJS
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "rangedjs";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static List<Item> customizedBows = new ArrayList<>();

    // You can use SubscribeEvent and let the Event Bus discover methods to call
}
