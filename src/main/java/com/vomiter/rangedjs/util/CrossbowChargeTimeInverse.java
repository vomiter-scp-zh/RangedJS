package com.vomiter.rangedjs.util;

import net.minecraft.util.Mth;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public final class CrossbowChargeTimeInverse {

    /** 回傳 [min, max]，表示「可能的原始 base full charge ticks」區間（含頭含尾） */
    public static int[] invertBaseFullChargeTicks_AssumingAddLinear(
            int finalFullChargeTicks,
            int quickChargeLevel,
            AddValue addEffect
    ) {
        LevelBasedValue v = addEffect.value();
        if (!(v instanceof LevelBasedValue.Linear linear)) {
            throw new IllegalStateException("Expected LevelBasedValue.Linear, got: " + v.getClass().getName());
        }

        // Quick Charge 的秒數加成（原版會是負數：-0.25, -0.5, -0.75）
        float deltaSeconds = linear.calculate(quickChargeLevel);

        // 轉成「ticks 空間」的連續區間邊界
        float A = finalFullChargeTicks - deltaSeconds * 20.0f;
        float B = (finalFullChargeTicks + 1) - deltaSeconds * 20.0f;

        // baseTicks 的可行整數範圍
        int min = Mth.floor(A);
        int max = Mth.floor(Math.nextDown(B)); // 確保嚴格 < B

        return new int[]{min, max};
    }

    /** 便利版：若可行解不是唯一，選「最小」那個 */
    public static int invertBaseFullChargeTicksPickMin_AssumingAddLinear(
            int finalFullChargeTicks,
            int quickChargeLevel,
            AddValue addEffect
    ) {
        int[] r = invertBaseFullChargeTicks_AssumingAddLinear(finalFullChargeTicks, quickChargeLevel, addEffect);
        return r[0];
    }

    /** 便利版：若不是唯一解就丟例外 */
    public static int invertBaseFullChargeTicksRequireUnique_AssumingAddLinear(
            int finalFullChargeTicks,
            int quickChargeLevel,
            AddValue addEffect
    ) {
        int[] r = invertBaseFullChargeTicks_AssumingAddLinear(finalFullChargeTicks, quickChargeLevel, addEffect);
        if (r[0] != r[1]) {
            throw new IllegalStateException("Ambiguous baseTicks range: [" + r[0] + "," + r[1] + "]");
        }
        return r[0];
    }
}
