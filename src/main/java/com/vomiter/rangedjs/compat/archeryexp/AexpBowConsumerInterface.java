package com.vomiter.rangedjs.compat.archeryexp;

import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.world.item.BowItem;
import org.infernalstudios.archeryexp.util.mixinterfaces.IBowProperties;

import java.util.function.Consumer;

public interface AexpBowConsumerInterface extends Consumer<AexpBowPropertiesExt> {
    default void bowAccept(BowItem bow){
        IBowProperties bowProperties =((IBowProperties)bow);
        BowItemInterface rjsBowProperties = (BowItemInterface) bow;
        //set default values
        bowProperties.archeryexp$setSpecial(true);
        bowProperties.archeryexp$setBowCooldown(16);
        bowProperties.archeryexp$setChargeTime(rjsBowProperties.rjs$getFullChargeTick());
        bowProperties.archeryexp$setBreakResist(0f);
        bowProperties.archeryexp$setBreakChance(0.33f);
        bowProperties.archeryexp$setBaseDamage((float) ((BowItemInterface) bow).rjs$getBaseDamage());
        bowProperties.archeryexp$setRange((float)((BowItemInterface) bow).rjs$getArrowSpeedScale());
        bowProperties.archeryexp$setWalkSpeed(0.8f);
        accept((AexpBowPropertiesExt)bow);
    }
}
