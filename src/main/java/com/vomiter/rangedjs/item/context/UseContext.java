package com.vomiter.rangedjs.item.context;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class UseContext {
    public enum Result {DEFAULT, DENY, ALLOW}
    private final Level level;
    private final Player player;
    private final InteractionHand hand;
    private Result result = Result.DEFAULT;
    private final CallbackInfo ci;

    public UseContext(Level level, Player player, InteractionHand hand, CallbackInfo ci){
        this.level = level;
        this.player = player;
        this.hand = hand;
        this.ci = ci;
    }

    @SuppressWarnings("unused")
    public void setResult(Result result) {
        this.result = result;
    }

    @SuppressWarnings("unused")
    public void cancel(){
        setResult(Result.DENY);
        if(ci instanceof CallbackInfoReturnable){
            ((CallbackInfoReturnable<InteractionResultHolder>) ci)
                    .setReturnValue(InteractionResultHolder.fail(player.getItemInHand(hand)));
        }
        else{
            ci.cancel();
        }
    }

    @SuppressWarnings("unused")
    public Result getResult() {
        return result;
    }

    @SuppressWarnings("unused")
    public Level getLevel() {
        return level;
    }

    @SuppressWarnings("unused")
    public Player getPlayer() {
        return player;
    }

    @SuppressWarnings("unused")
    public InteractionHand getHand() {
        return hand;
    }
}