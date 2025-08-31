package io.github.diiiaz.better_hammers.api;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/api/reach/ReachDistanceHelper.java"

import net.minecraft.entity.player.PlayerEntity;

public class ReachDistanceHelper {
    public static double getReachDistance(PlayerEntity playerEntity) {
        return playerEntity.isCreative() ? 5.0F : 4.5F;
    }
}