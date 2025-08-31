package io.github.diiiaz.better_hammers.mixin;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/mixin/ServerPlayerInteractionManagerMixin.java"

import io.github.diiiaz.better_hammers.api.PlayerInteractionManagerExtension;
import io.github.diiiaz.better_hammers.api.HammerTool;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This class has a lower priority than default so it runs after claim mods/FAPI checks for block breaking validity.
@Mixin(value = ServerPlayerInteractionManager.class, priority = 1001)
public class ServerPlayerInteractionManagerMixin implements PlayerInteractionManagerExtension {

    @Final
    @Shadow
    protected ServerPlayerEntity player;
    @Shadow
    protected ServerWorld world;

    @Unique private boolean isMining = false;

    @Inject(
            method = "tryBreakBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;onBreak(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;)V"
            ),
            cancellable = true
    )
    private void tryBreak(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        ItemStack heldStack = player.getMainHandStack();

        if (heldStack.getItem() instanceof HammerTool) {
            // This is to avoid recursion, but the goal is to make sure every block it doesn't override cancelled block breaks using Fabric's callbacks. This was made to support claim mods.
            boolean v = isMining || ((HammerTool) heldStack.getItem()).attemptBreak(world, pos, player, ((HammerTool) heldStack.getItem()).getRadius(heldStack), ((HammerTool) heldStack.getItem()).getProcessor(world, player, pos, heldStack));

            // only cancel if the break was successful
            if(v) { cir.setReturnValue(true); }
        }
    }

    @Override
    public void betterHammers$setMining(boolean mining) {
        this.isMining = mining;
    }
}