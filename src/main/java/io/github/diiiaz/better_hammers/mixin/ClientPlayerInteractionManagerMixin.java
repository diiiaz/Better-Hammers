package io.github.diiiaz.better_hammers.mixin;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/mixin/ClientPlayerInteractionManagerMixin.java"

import io.github.diiiaz.better_hammers.item.HammerItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(
            method = "breakBlock",
            at = @At("HEAD"),
            cancellable = true)
    private void onBreakBlockClient(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (client.player == null || !(client.player.getMainHandStack().getItem() instanceof HammerItem)) { return; }

        cir.cancel();
        World world = this.client.world;

        if (world == null) { return; }

        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        block.onBreak(world, pos, blockState, this.client.player);
    }
}