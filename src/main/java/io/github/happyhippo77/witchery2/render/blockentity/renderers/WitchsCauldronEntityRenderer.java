package io.github.happyhippo77.witchery2.render.blockentity.renderers;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import io.github.happyhippo77.witchery2.util.brewing.CauldronLevel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.screen.PlayerScreenHandler;

import java.awt.*;
import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class WitchsCauldronEntityRenderer implements BlockEntityRenderer<WitchsCauldronEntity> {

    public WitchsCauldronEntityRenderer(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(WitchsCauldronEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        CauldronLevel level = entity.getLevel();
        Color color = entity.getColor();

        // Adjust color to match original mod
        float desaturationValue = 0.25f;
        float darkeningValue = 0.475f;
        float[] array = new float[3];
        array = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), array);
        array[1] = (float) (array[1] * (1.0 - desaturationValue));
        array[2] = (float) (array[2] * (1.0 - darkeningValue));
        color = new Color(Color.HSBtoRGB(array[0], array[1], array[2]));

        //ModelCauldron model = new ModelCauldron(ModelCauldron.getTexturedModelData().createModel());
        //model.render(matrices, vertexConsumers.getBuffer(model.getLayer(new Identifier(Witchery2.MOD_ID, "textures/block/witchs_cauldron.png"))), light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);

        ArrayList<Item> ingredients = entity.getIngredients();

        float waterHeight = 0;
        boolean filled = true;

        switch (level) {
            case EMPTY -> filled = false;
            case LOW -> waterHeight = 0.375f;
            case MEDIUM -> waterHeight = 0.53125f;
            case FULL -> waterHeight = 0.6875f;
        }

        matrices.push();

        if (filled) {
            SpriteAtlasTexture atlas = MinecraftClient.getInstance()
                    .getBakedModelManager()
                    .getAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);

            Sprite waterTexture = atlas.getSprite(SimpleFluidRenderHandler.WATER_STILL);

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayers.getFluidLayer(Fluids.WATER.getDefaultState()));
            vertexConsumer = waterTexture.getTextureSpecificVertexConsumer(vertexConsumer);

            // COLOR TINTS THE TEXTURE
            vertexConsumer.vertex(matrices.peek().getPositionMatrix(), 0.125F, waterHeight, 0.125F)
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .texture(waterTexture.getMinU(), waterTexture.getMinV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.125F, waterHeight, 0.875F)
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .texture(waterTexture.getMinU(), waterTexture.getMaxV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.875F, waterHeight, 0.875F)
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .texture(waterTexture.getMaxU(), waterTexture.getMaxV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
            vertexConsumer
                    .vertex(matrices.peek().getPositionMatrix(), 0.875F, waterHeight, 0.125F)
                    .color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())
                    .texture(waterTexture.getMaxU(), waterTexture.getMinV())
                    .light(light)
                    .normal(0, 1, 0)
                    .next();
        }

        // Mandatory call after GL calls
        matrices.pop();
    }
}
