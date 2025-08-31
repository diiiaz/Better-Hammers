package io.github.diiiaz.better_hammers.mixin;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/mixin/WorldMixin.java"

import io.github.diiiaz.better_hammers.item.HammerItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin implements WorldAccess, AutoCloseable {

    @Unique private Entity cachedEntity = null;

    @Inject(
            method = "breakBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;"
            )
    )
    private void storeContext(BlockPos pos, boolean drop, Entity breakingEntity, int maxUpdateDepth, CallbackInfoReturnable<Boolean> cir) {
        cachedEntity = breakingEntity;
    }

    @Redirect(
            method = "breakBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;syncWorldEvent(ILnet/minecraft/util/math/BlockPos;I)V"
            )
    )
    private void cancelSyncWorldEvent(World world, int eventId, BlockPos pos, int data) {
        BlockState blockState = this.getBlockState(pos);

        if(cachedEntity instanceof PlayerEntity player) {
            ItemStack heldStack = player.getMainHandStack();

            if(heldStack.getItem() instanceof HammerItem) { return; }
        }

        this.syncWorldEvent(2001, pos, Block.getRawIdFromState(blockState));
    }
}