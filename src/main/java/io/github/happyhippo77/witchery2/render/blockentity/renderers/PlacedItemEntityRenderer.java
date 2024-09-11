package io.github.happyhippo77.witchery2.render.blockentity.renderers;

import io.github.happyhippo77.witchery2.block.blocks.PlacedItem;
import io.github.happyhippo77.witchery2.block.entity.entities.PlacedItemEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class PlacedItemEntityRenderer implements BlockEntityRenderer<PlacedItemEntity> {
    public PlacedItemEntityRenderer(BlockEntityRendererFactory.Context ctx) {}

    private void renderItem(PlacedItemEntity entity, MatrixStack matrices, VertexConsumerProvider vertexConsumers, float translateX, float translateY, float translateZ) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getItemStack();
        matrices.push();
        matrices.translate(0.5f, 0.015, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));

        switch (entity.getCachedState().get(PlacedItem.FACING)) {
            case NORTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(0));
            case EAST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(270));
            case SOUTH -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
            case WEST -> matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));
        }

        matrices.translate(translateX, translateY, translateZ);

        itemRenderer.renderItem(itemStack, ModelTransformationMode.GUI, getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
        matrices.pop();
    }

    @Override
    public void render(PlacedItemEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        renderItem(entity, matrices, vertexConsumers, 0, 1 / 16f, 0);
        if (entity.getItemStack().getCount() > 1) {
            renderItem(entity, matrices, vertexConsumers, 0, 1 / 16f, 1.35f / 16f);
        }
        if (entity.getItemStack().getCount() > 2) {
            renderItem(entity, matrices, vertexConsumers, 0, 1 / 16f, 2.7f / 16f);
        }
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
