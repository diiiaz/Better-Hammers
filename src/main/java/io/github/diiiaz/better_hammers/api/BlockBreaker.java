package io.github.diiiaz.better_hammers.api;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/api/BlockBreaker.java"

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class BlockBreaker {

    public static void breakInRadius(World world, PlayerEntity player, int radius, int depth, BlockFinder finder, BreakValidator breakValidator, BlockProcessor smelter, boolean damageTool) {
        if (world.isClient) {
            return;
        }

        ServerPlayerInteractionManager interactionManager = ((ServerPlayerEntity) player).interactionManager;
        ((PlayerInteractionManagerExtension) interactionManager).betterHammers$setMining(true);

        List<BlockPos> brokenBlocks = finder.findPositions(world, player, radius, depth);
        for (BlockPos pos : brokenBlocks) {
            BlockState state = world.getBlockState(pos);
            BlockEntity blockEntity = world.getBlockState(pos).hasBlockEntity() ? world.getBlockEntity(pos) : null;

            if (!breakValidator.canBreak(world, pos) || state.isAir()) {
                continue;
            }

            state.getBlock().onBreak(world, pos, state, player);
            if (!interactionManager.tryBreakBlock(pos)) {
                continue;
            }

            boolean result = PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world, player, pos, state, world.getBlockEntity(pos));
            if (!result) {
                continue;
            }

            boolean bl = world.removeBlock(pos, false);
            if (bl) {
                state.getBlock().onBroken(world, pos, state);
            }

            if (player.isCreative()) {
                continue;
            }

            Vec3d offsetPos = new Vec3d(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5);

            List<ItemStack> droppedStacks = Block.getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, player, player.getMainHandStack());
            List<ItemStack> processed = new ArrayList<>();

            droppedStacks.forEach(stack -> processed.add(smelter.process(player.getInventory().getMainHandStack(), stack)));

            dropItems(player, world, processed, offsetPos);
            state.onStacksDropped((ServerWorld) world, pos, player.getMainHandStack(), true);

            if (!damageTool) {
                continue;
            }

            ItemStack itemStack = player.getMainHandStack();
            boolean usingEffectiveTool = player.canHarvest(state);
            itemStack.postMine(world, state, pos, player);

            if (!usingEffectiveTool) {
                continue;
            }

            player.incrementStat(Stats.MINED.getOrCreateStat(state.getBlock()));
            player.addExhaustion(0.005F);
        }
        ((PlayerInteractionManagerExtension) (interactionManager)).betterHammers$setMining(false);
    }

    private static void dropItems(PlayerEntity player, World world, List<ItemStack> stacks, Vec3d pos) {
        for (ItemStack stack : stacks) {
            if (stack.isEmpty()) {
                continue;
            }
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(itemEntity);
        }
    }
}