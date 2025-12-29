package com.vomiter.rangedjs.item;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

import java.util.function.Predicate;

public abstract class ArrowShootingAttributes<
        WEAPON extends ProjectileWeaponItem,
        SELF extends ArrowShootingAttributes
                <WEAPON, SELF>
        >
{
    @SuppressWarnings("unchecked")
    protected SELF self() {
        return (SELF) this;
    }


    protected Predicate<ItemStack> supportedHeldProjectiles = null;
    protected Predicate<ItemStack> allSupportedProjectiles = null;
    public SELF ammo(Predicate<ItemStack> ammoPredication) {
        this.allSupportedProjectiles = ammoPredication;
        return self();
    }
    public SELF ammoHeld(Predicate<ItemStack> ammoPredication) {
        this.supportedHeldProjectiles = ammoPredication;
        return self();
    }

    @HideFromJS
    public Predicate<ItemStack> getSupportedHeldProjectiles() {
        return supportedHeldProjectiles;
    }

    @HideFromJS
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return allSupportedProjectiles;
    }


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

    protected int enchantmentValue = 1;

    @SuppressWarnings("unused")
    public void enchantmentValue(int value){
        this.enchantmentValue = value;
    }

    @HideFromJS
    public int getEnchantmentValue(){
        return enchantmentValue;
    }

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
