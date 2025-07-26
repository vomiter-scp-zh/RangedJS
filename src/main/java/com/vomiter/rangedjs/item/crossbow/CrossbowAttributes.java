package com.vomiter.rangedjs.item.crossbow;

import com.vomiter.rangedjs.item.Attributes;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;

public class CrossbowAttributes extends Attributes {
    protected boolean specialInfinity = false;
    protected boolean infinity = false;
    protected boolean flamingArrow = false;
    protected int knockBack = 0;
    protected int power = 0;

    protected int fullChargeTick = CrossbowUtils.defaultFullChargeTick;
    protected double arrowDamage = CrossbowUtils.defaultArrowDamage;
    protected float arrowSpeedScale = CrossbowUtils.defaultSpeedScale;

    protected boolean noDamage = false;

    @HideFromJS
    public int getFullChargeTick() {
        return fullChargeTick;
    }

    @HideFromJS
    public double getArrowDamage() {
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

    public CrossbowAttributes(){}

    @Info("The damage caused by arrows shot by this bow will be 0.")
    @SuppressWarnings("unused")
    public CrossbowAttributes noDamage(boolean b){
        this.noDamage = b;
        return this;
    }


    @SuppressWarnings("unused")
    public CrossbowAttributes arrowSpeed(float f){
        this.arrowSpeedScale = f;
        return this;
    }

    @Info("Modify the base damage of shot arrows. The default value is 2.00.")
    @SuppressWarnings("unused")
    public CrossbowAttributes arrowDamage(double d){
        this.arrowDamage = d;
        return this;
    }

    @Info("Modify the tick counts taken to fully charge this crossbow. The default value is 25 ticks")
    @SuppressWarnings("unused")
    public CrossbowAttributes fullChargeTick(int t){
        this.fullChargeTick = t;
        return this;
    }

    @SuppressWarnings("unused")
    public CrossbowAttributes specialInfinity(){
        this.infinity = true;
        this.specialInfinity = true;
        return this;
    }

    @SuppressWarnings("unused")
    public CrossbowAttributes infinity(){
        this.infinity = true;
        return this;
    }

    @SuppressWarnings("unused")
    public CrossbowAttributes flamingArrow(){
        this.flamingArrow = true;
        return this;
    }

    @SuppressWarnings("unused")
    public CrossbowAttributes knockBack(int i){
        this.knockBack = i;
        return this;
    }

    @Info("Add native power enchantment-like buff to the bow.")
    @SuppressWarnings("unused")
    public CrossbowAttributes power(int i){
        this.power = i;
        return this;
    }

}
