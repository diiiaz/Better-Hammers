package io.github.diiiaz.better_hammers.api;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/api/BreakValidator.java"

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/**
 * Provides information on whether a {@link BlockState} can be broken.
 */
@FunctionalInterface
public interface BreakValidator {

    /**
     * Implementers should return whether the given {@link BlockState} can be broken.
     *
     * @param view  {@link BlockView} of the {@link BlockState}
     * @param pos  {@link BlockPos} of the {@link BlockState}
     * @return  whether the given {@link BlockState} can be broken
     */
    boolean canBreak(BlockView view, BlockPos pos);
}