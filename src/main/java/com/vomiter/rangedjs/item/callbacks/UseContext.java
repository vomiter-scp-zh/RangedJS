package com.vomiter.rangedjs.item.callbacks;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class UseContext {
    public enum Result {DEFAULT, DENY, ALLOW}
    private final Level level;
    private final Player player;
    private final InteractionHand hand;
    private Result result = Result.DEFAULT;

    public UseContext(Level level, Player player, InteractionHand hand){
        this.level = level;
        this.player = player;
        this.hand = hand;
    }

    public void setResult(Result result) {
        this.result = result;
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