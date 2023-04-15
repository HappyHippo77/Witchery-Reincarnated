package io.github.happyhippo77.witchery2.render.block.models;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

@Deprecated
public class ModelCauldron extends EntityModel<Entity> {
	private final ModelPart VoxelShapes;
	public ModelCauldron(ModelPart root) {
		this.VoxelShapes = root.getChild("VoxelShapes");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData VoxelShapes = modelPartData.addChild("VoxelShapes", ModelPartBuilder.create()
				.uv(0, 53).cuboid(-5.0F, 13.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
				.uv(0, 50).cuboid(-5.0F, 12.0F, -6.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 50).cuboid(-5.0F, 12.0F, 5.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 36).cuboid(5.0F, 12.0F, -6.0F, 1.0F, 1.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 36).cuboid(-6.0F, 12.0F, -6.0F, 1.0F, 1.0F, 12.0F, new Dilation(0.0F))
				.uv(27, 45).cuboid(-6.0F, 6.0F, -7.0F, 12.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(27, 45).cuboid(-6.0F, 6.0F, 6.0F, 12.0F, 6.0F, 1.0F, new Dilation(0.0F))
				.uv(27, 24).cuboid(6.0F, 6.0F, -7.0F, 1.0F, 6.0F, 14.0F, new Dilation(0.0F))
				.uv(27, 24).cuboid(-7.0F, 6.0F, -7.0F, 1.0F, 6.0F, 14.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-5.0F, 4.0F, -6.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 32).cuboid(-5.0F, 4.0F, 5.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 17).cuboid(5.0F, 4.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 17).cuboid(-6.0F, 4.0F, -6.0F, 1.0F, 2.0F, 12.0F, new Dilation(0.0F))
				.uv(27, 21).cuboid(-6.0F, 3.0F, -7.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(27, 21).cuboid(-6.0F, 3.0F, 6.0F, 12.0F, 1.0F, 1.0F, new Dilation(0.0F))
				.uv(27, 5).cuboid(6.0F, 3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new Dilation(0.0F))
				.uv(27, 5).cuboid(-7.0F, 3.0F, -7.0F, 1.0F, 1.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 16.0F, 8.0F, 0.0F, 0.0F, -3.1416F));

		ModelPartData legBR_r1 = VoxelShapes.addChild("legBR_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.5F, 14.5F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.3491F));

		ModelPartData legBL_r1 = VoxelShapes.addChild("legBL_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 14.5F, -1.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, -0.3491F));

		ModelPartData legFR_r1 = VoxelShapes.addChild("legFR_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.5F, 14.5F, 0.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.3491F));

		ModelPartData legFL_r1 = VoxelShapes.addChild("legFL_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, 14.5F, 0.75F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, -0.3491F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		VoxelShapes.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}