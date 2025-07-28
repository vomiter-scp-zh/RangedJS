package com.vomiter.rangedjs.item.context;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unused")
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

    public void setResult(Result result) {
        this.result = result;
        if(ci instanceof CallbackInfoReturnable){
            var cir = ((CallbackInfoReturnable<InteractionResultHolder>) ci);
            if(result == Result.ALLOW){
                cir.setReturnValue(InteractionResultHolder.consume(player.getItemInHand(hand)));
            }
            else if(result == Result.DENY){
                cir.setReturnValue(InteractionResultHolder.fail(player.getItemInHand(hand)));
            }
        }
    }

    public void deny(){
        setResult(Result.DENY);
        if(!(ci instanceof CallbackInfoReturnable)){
            ci.cancel();
        }
    }

    public void allow(){
        setResult(Result.ALLOW);
    }

    public void cancel(){
        deny();
    }

    public Result getResult() {
        return result;
    }

    public Level getLevel() {
        return level;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractionHand getHand() {
        return hand;
    }

}