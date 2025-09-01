package io.github.diiiaz.better_hammers.mixin;

// Copied and/or modified from: "https://github.com/Draylar/magna/blob/1.20.1/src/main/java/dev/draylar/magna/mixin/WorldRendererMixin.java"

import io.github.diiiaz.better_hammers.api.ToolRadiusCallback;
import io.github.diiiaz.better_hammers.api.HammerTool;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldRenderer.class)
@Environment(EnvType.CLIENT)
public class WorldRendererMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private ClientWorld world;

    @Inject(at = @At("HEAD"), method = "drawBlockOutline", cancellable = true)
    private void drawBlockOutline(MatrixStack stack, VertexConsumer vertexConsumer, Entity entity, double d, double e, double f, BlockPos blockPos, BlockState blockState, CallbackInfo ci) {

        if (this.client.player == null || this.client.world == null) { return; }

        ItemStack heldStack = this.client.player.getInventory().getMainHandStack();
        if (!(heldStack.getItem() instanceof HammerTool tool)) { return; }
        if (!(client.crosshairTarget instanceof BlockHitResult crosshairTarget)) { return; }

        BlockPos crosshairPos = crosshairTarget.getBlockPos();
        BlockState crosshairState = client.world.getBlockState(crosshairPos);

        if (crosshairState.isAir() || !client.world.getWorldBorder().contains(crosshairPos) || !tool.isBlockValidForBreaking(world, crosshairPos, heldStack)) { return; }

        int radius = ToolRadiusCallback.EVENT.invoker().getRadius(heldStack, tool.getRadius(world, heldStack));
        List<BlockPos> positions = tool.getBlockFinder().findPositions(world, client.player, radius, tool.getDepth(world, heldStack));
        List<VoxelShape> outlineShapes = new ArrayList<>();
        outlineShapes.add(VoxelShapes.empty());

        for (BlockPos position : positions) {
            if (!tool.isBlockValidForBreaking(world, position, heldStack)) { continue; }

            BlockPos diffPos = position.subtract(crosshairPos);
            BlockState offsetShape = world.getBlockState(position);

            if (!offsetShape.isAir()) {
                outlineShapes.add(VoxelShapes.fullCube().offset(diffPos.getX(), diffPos.getY(), diffPos.getZ()));
            }
        }

        outlineShapes.forEach(shape -> drawCuboidShapeOutline(
                stack,
                vertexConsumer,
                shape,
                (double) crosshairPos.getX() - d,
                (double) crosshairPos.getY() - e,
                (double) crosshairPos.getZ() - f,
                0.0F,
                0.0F,
                0.0F,
                0.4F));
        ci.cancel();
    }

    @Invoker("drawCuboidShapeOutline")
    public static void drawCuboidShapeOutline(MatrixStack matrices, VertexConsumer vertexConsumer, VoxelShape shape, double offsetX, double offsetY, double offsetZ, float red, float green, float blue, float alpha) {
        throw new AssertionError();
    }
}