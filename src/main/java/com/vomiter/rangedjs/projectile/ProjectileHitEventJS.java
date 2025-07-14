package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ProjectileHitEventJS {
    public static Int2BooleanMap eventResultMap = new Int2BooleanOpenHashMap();
    protected final HitResult hitResult;
    protected final Projectile projectile;
    public enum Result {DEFAULT, ALLOW, DENY}
    protected Result eventResult = Result.DEFAULT;

    public ProjectileHitEventJS(HitResult hitResult, Projectile projectile){
        this.hitResult = hitResult;
        this.projectile = projectile;
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

}
