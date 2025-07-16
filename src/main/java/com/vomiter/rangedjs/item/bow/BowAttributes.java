package com.vomiter.rangedjs.item.bow;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;

public class BowAttributes {
    protected boolean specialInfinity = false;
    protected boolean infinity = false;
    protected boolean flamingArrow = false;
    protected int knockBack = 0;
    protected int power = 0;

    protected int fullChargeTick = BowUtils.defaultFullChargeTick;
    protected double baseDamage = BowUtils.defaultBaseDamage;
    protected float arrowSpeedScale = BowUtils.defaultSpeedScale;

    protected boolean noDamage = false;

    @HideFromJS
    public int getFullChargeTick() {
        return fullChargeTick;
    }

    @HideFromJS
    public double getBaseDamage() {
        return baseDamage;
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

    public BowAttributes(){}

    @Info("The damage caused by arrows shot by this bow will be 0.")
    @SuppressWarnings("unused")
    public BowAttributes noDamage(boolean b){
        this.noDamage = b;
        return this;
    }


    @SuppressWarnings("unused")
    public BowAttributes arrowSpeed(float f){
        this.arrowSpeedScale = f;
        return this;
    }

    @Info("Modify the base damage of shot arrows. The default value is 2.00.")
    @SuppressWarnings("unused")
    public BowAttributes baseDamage(double d){
        this.baseDamage = d;
        return this;
    }

    @Info("Modify the tick counts taken to fully charge this bow. The default value is 20 ticks (1 second).")
    @SuppressWarnings("unused")
    public BowAttributes fullChargeTick(int t){
        this.fullChargeTick = t;
        return this;
    }

    @Info("This is a typo. Please use .fullChargeTick in newer version. This method will be deprecated one day.")
    @SuppressWarnings("unused")
    public BowAttributes fullChargeTicks(int t){
        this.fullChargeTick = t;
        return this;
    }

    @SuppressWarnings("unused")
    public BowAttributes specialInfinity(){
        this.infinity = true;
        this.specialInfinity = true;
        return this;
    }

    @SuppressWarnings("unused")
    public BowAttributes infinity(){
        this.infinity = true;
        return this;
    }

    @SuppressWarnings("unused")
    public BowAttributes flamingArrow(){
        this.flamingArrow = true;
        return this;
    }

    @SuppressWarnings("unused")
    public BowAttributes knockBack(int i){
        this.knockBack = i;
        return this;
    }

    @Info("Add native power enchantment-like buff to the bow.")
    @SuppressWarnings("unused")
    public BowAttributes power(int i){
        this.power = i;
        return this;
    }

}
