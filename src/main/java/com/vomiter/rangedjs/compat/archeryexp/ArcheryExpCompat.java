package com.vomiter.rangedjs.compat.archeryexp;

import com.vomiter.rangedjs.item.ProjectileWeaponItemBuilder;
import com.vomiter.rangedjs.item.bow.BowItemBuilder;
import com.vomiter.rangedjs.item.bow.BowItemInterface;
import net.minecraft.world.item.BowItem;

public final class ArcheryExpCompat {
    public static void init() {
        ProjectileWeaponItemBuilder.registerPostCreateHook((builder, item, props) -> {
            if (!(builder instanceof BowItemBuilder)) return;
            if (!(item instanceof BowItem bow)) return;
            var aexp = ((BowPropertiesInterface) ((BowItemInterface) bow).rjs$getProperties()).rjs$getAExpProperties();
            if (aexp != null) aexp.bowAccept(bow);
        });
    }
}
