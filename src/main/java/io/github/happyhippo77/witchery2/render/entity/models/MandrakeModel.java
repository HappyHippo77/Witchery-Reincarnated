package io.github.happyhippo77.witchery2.render.entity.models;

import io.github.happyhippo77.witchery2.entity.entities.MandrakeEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 4.6.5
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class MandrakeModel extends EntityModel<MandrakeEntity> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public MandrakeModel(ModelPart root) {
		this.body = root.getChild("body");
		this.head = body.getChild("head");
		this.rightArm = body.getChild("rightArm");
		this.leftArm = body.getChild("leftArm");
		this.rightLeg = body.getChild("rightLeg");
		this.leftLeg = body.getChild("leftLeg");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(21, 0).cuboid(-2.5F, -8.0F, -2.5F, 5.0F, 2.0F, 5.0F, new Dilation(0.0F))
				.uv(17, 7).cuboid(-3.5F, -6.0F, -3.5F, 7.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 8).cuboid(-2.0F, -12.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-4.0F, -20.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rightArm = body.addChild("rightArm", ModelPartBuilder.create().uv(37, 0).cuboid(0.15F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -8.0F, 0.0F, 0.0F, 0.0F, 1.0472F));

		ModelPartData leftArm = body.addChild("leftArm", ModelPartBuilder.create().uv(37, 0).cuboid(-1.15F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, -8.0F, 0.0F, 0.0F, 0.0F, -1.0472F));

		ModelPartData rightLeg = body.addChild("rightLeg", ModelPartBuilder.create().uv(27, 18).cuboid(0.5F, 5.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -8.0F, 0.0F));

		ModelPartData leftLeg = body.addChild("leftLeg", ModelPartBuilder.create().uv(27, 18).cuboid(2.5F, 5.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.5F, -8.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(MandrakeEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}

	@Override
	public void animateModel(MandrakeEntity entity, float limbAngle, float limbDistance, float tickDelta) {
		this.rightArm.pitch = this.func_78172_a(limbAngle, 13.0F) * limbDistance;
		this.leftArm.pitch = -this.func_78172_a(limbAngle, 13.0F) * limbDistance;
		this.body.roll = (float) (this.func_78172_a(limbAngle, 13.0F) * (limbDistance * 0.2));
	}

	private float func_78172_a(float par1, float par2) {
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}
}