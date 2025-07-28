package com.vomiter.rangedjs.item;
import dev.latvian.mods.rhino.util.HideFromJS;

public abstract class ArrowShootingAttributes {
    protected boolean specialInfinity = false;
    protected boolean infinity = false;
    protected boolean flamingArrow = false;
    protected int knockBack = 0;
    protected int power = 0;
    protected byte pierce = 0;

    protected int fullChargeTick;
    protected double arrowDamage;
    protected float arrowSpeedScale;

    protected boolean noDamage = false;

    @HideFromJS
    public int getFullChargeTick() {
        return fullChargeTick;
    }

    @HideFromJS
    public double getBaseDamage() {
        return arrowDamage;
    }

    @HideFromJS
    public int getPower() {
        return power;
    }

    @HideFromJS
    public int getKnockBack() {
        return knockBack;
    }

    @HideFromJS
    public float getArrowSpeedScale() {
        return arrowSpeedScale;
    }

    @HideFromJS
    public byte getPierce(){
        return pierce;
    }

    @HideFromJS
    public boolean isFlamingArrow() {
        return flamingArrow;
    }

    @HideFromJS
    public boolean isInfinity() {
        return infinity;
    }

    @HideFromJS
    public boolean isSpecialInfinity() {
        return specialInfinity;
    }

    @HideFromJS
    public boolean isNoDamage() {
        return noDamage;
    }
}
