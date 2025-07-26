package com.vomiter.rangedjs.projectile.hitevents;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
public class ProjectileHitEventJS {
    public static Int2BooleanMap eventResultMap = new Int2BooleanOpenHashMap();
    protected final HitResult hitResult;
    protected final Projectile projectile;
    public enum Result {DEFAULT, ALLOW, DENY}
    protected Result eventResult = Result.DEFAULT;
    protected CallbackInfo ci;

    public ProjectileHitEventJS(HitResult hitResult, Projectile projectile, CallbackInfo ci){
        this.hitResult = hitResult;
        this.projectile = projectile;
        this.ci = ci;
    }

    public Vec3 getPos(){
        return this.hitResult.getLocation();
    }

    public Level getLevel(){
        return this.projectile.level();
    }


    public MinecraftServer getServer(){
        return this.getLevel().getServer();
    }

    public Projectile getProjectile(){
        return this.projectile;
    }

    public void cancel(){
        this.setEventResult(Result.DENY);
        this.ci.cancel();
    }

    @HideFromJS
    @Info("""
            You can set it to "deny", "allow" or "default".
            With "allow", the arrow will be able to hit enderman.
            With "deny", the arrow will not cause any effect.
            """)
    public void setEventResult(Result eventResult){
        this.eventResult =eventResult;
    }

    @HideFromJS
    public Result getEventResult(){
        return this.eventResult;
    }

    public AbstractArrow getArrow(){
        if(projectile instanceof AbstractArrow) return (AbstractArrow)projectile;
        ci.cancel();
        return null;
    }

}
