package com.vomiter.rangedjs.item;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ProjectileWeaponItemBuilder<
        SELF extends ProjectileWeaponItemBuilder<SELF, I, P>,
        I extends Item,
        P
        > extends CommonBuilder<I> {

    protected final transient P properties;

    protected ProjectileWeaponItemBuilder(ResourceLocation id, P properties) {
        super(id);
        this.properties = properties;
    }

    /** fluent self */
    protected abstract SELF self();

    /** 給腳本用的設定入口（bow()/crossbow() 都可以變成同一個） */
    public SELF config(Consumer<P> c) {
        c.accept(properties);
        return self();
    }

    /** 建立實體 Item（由子類決定 BowItem/CrossbowItem） */
    protected abstract I createItem();

    /** 把 properties 寫進 item（由子類決定 cast 到哪個介面） */
    protected abstract void attachProperties(I item, P properties);

    /** client renderer 註冊（子類決定怎麼註冊） */
    protected abstract void registerClient(I item);

    /** 預設動畫（子類回傳 BOW 或 CROSSBOW） */
    protected abstract UseAnim defaultUseAnim();

    /** 可選：若想要 repairIngredient 生效，這裡提供一個 hook */
    protected void applyRepairLogicIfNeeded(I item) {
    }

    @FunctionalInterface
    public interface TriConsumer<A, B, C> {
        void accept(A a, B b, C c);
    }

    private static final List<TriConsumer<ProjectileWeaponItemBuilder<?, ?, ?>, Item, Object>> POST_CREATE_HOOKS = new ArrayList<>();

    public static <B extends ProjectileWeaponItemBuilder<?, ?, ?>, I extends Item, P> void registerPostCreateHook(
            TriConsumer<B, I, P> hook
    ) {
        POST_CREATE_HOOKS.add((TriConsumer) hook);
    }

    @Override
    public I createObject() {
        I item = super.createObject();
        if(item instanceof ItemInterface itemInterface){
            itemInterface.rjs$setShootSound(shootSound);
        }

        attachProperties(item, properties);
        onItemCreated(item, properties);

        applyRepairLogicIfNeeded(item);
        if(FMLEnvironment.dist.isClient()) registerClient(item);
        if (anim == null) anim = defaultUseAnim();
        for (var h : POST_CREATE_HOOKS) {
            h.accept(this, item, properties);
        }
        return item;
    }

    /** 子類可覆寫：例如 bowProperties.setItem(item) */
    protected void onItemCreated(I item, P properties) {}

    @HideFromJS
    public SoundEvent shootSound;

    @Info("Change the sound event played when the arrow is shot.")
    public ProjectileWeaponItemBuilder<SELF, I, P> shootSound(SoundEvent soundEvent){
        this.shootSound = soundEvent;
        return this;
    }

}
