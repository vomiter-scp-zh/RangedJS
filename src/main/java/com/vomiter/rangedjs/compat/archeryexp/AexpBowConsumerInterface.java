package com.vomiter.rangedjs.compat.archeryexp;

import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.world.item.BowItem;
import org.infernalstudios.archeryexp.util.BowProperties;

import java.util.function.Consumer;

public interface AexpBowConsumerInterface extends Consumer<AexpBowPropertiesExt> {
    default void bowAccept(BowItem bow){
        BowProperties bowProperties =((BowProperties)bow);
        BowItemInterface rjsBowProperties = (BowItemInterface) bow;
        //set default values
        bowProperties.setSpecialProperties(true);
        bowProperties.setBowCooldown(16);
        bowProperties.setChargeTime(rjsBowProperties.rjs$getFullChargeTick());
        bowProperties.setBreakingResistance(0f);
        bowProperties.setBreakingChance(0.33f);
        bowProperties.setBaseDamage((float) ((BowItemInterface) bow).rjs$getBaseDamage());
        bowProperties.setRange((float)((BowItemInterface) bow).rjs$getArrowSpeedScale());
        bowProperties.setMovementSpeedMultiplier(0.8f);
        accept((AexpBowPropertiesExt)bow);
    }
}
