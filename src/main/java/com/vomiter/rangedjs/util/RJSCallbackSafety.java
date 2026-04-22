package com.vomiter.rangedjs.util;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

import java.util.function.Consumer;

public final class RJSCallbackSafety {
    private static final Logger LOGGER = LogUtils.getLogger();

    private RJSCallbackSafety() {
    }

    public static <T> void safeAccept(
            String callbackName,
            Consumer<T> consumer,
            T ctx,
            ItemStack weapon,
            LivingEntity actor
    ) {
        if (consumer == null) {
            return;
        }

        try {
            consumer.accept(ctx);
        } catch (Throwable t) {
            LOGGER.error(
                    "[RangedJS] Callback '{}' crashed. weapon='{}', actor='{}', ctx='{}'",
                    callbackName,
                    weapon,
                    actor == null ? "null" : actor.getName().getString(),
                    ctx == null ? "null" : ctx.getClass().getName(),
                    t
            );
        }
    }
}