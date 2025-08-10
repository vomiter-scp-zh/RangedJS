package com.vomiter.rangedjs;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;



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
}
