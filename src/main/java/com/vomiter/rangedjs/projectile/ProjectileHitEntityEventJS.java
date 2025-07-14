package com.vomiter.rangedjs.projectile;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ProjectileHitEntityEventJS {
    public static Int2BooleanMap eventResultMap = new Int2BooleanOpenHashMap();
    private final EntityHitResult hitResult;
    private final Projectile projectile;
    public enum Result {DEFAULT, ALLOW, DENY}
    private Result eventResult = Result.DEFAULT;

    public ProjectileHitEntityEventJS(EntityHitResult hitResult, Projectile projectile){
        this.hitResult = hitResult;
        this.projectile = projectile;
    }

    public Entity getEntity(){
        return this.hitResult.getEntity();
    }

    public Vec3 getPos(){
        return this.hitResult.getLocation();
    }

    public Level getLevel(){
        return this.getEntity().level();
    }

    public MinecraftServer getServer(){
        return this.getLevel().getServer();
    }

    public Projectile getProjectile(){
        return this.projectile;
    }

    @Info("""
            If the event is canceled, the projectile will go through the entity and cause no effect or damage.
            """)
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
