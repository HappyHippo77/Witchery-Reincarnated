package io.github.happyhippo77.witchery2;

import com.mojang.datafixers.util.Either;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.networking.ClientPackets;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.particle.particles.BubbleParticle;
import io.github.happyhippo77.witchery2.particle.particles.PowerParticle;
import io.github.happyhippo77.witchery2.render.blockentity.renderers.WitchsCauldronEntityRenderer;
import io.github.happyhippo77.witchery2.render.entity.models.MandrakeModel;
import io.github.happyhippo77.witchery2.render.entity.renderers.MandrakeRenderer;
import io.github.happyhippo77.witchery2.screen.ModScreenHandlers;
import io.github.happyhippo77.witchery2.screen.WitchsOvenScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class Client implements ClientModInitializer {
//    public static final EntityModelLayer CAULDRON_LAYER = new EntityModelLayer(new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), "main");

    public static final EntityModelLayer MANDRAKE_LAYER = new EntityModelLayer(new Identifier(Witchery2.MOD_ID, "mandrake"), "main");

    @Override
    @SuppressWarnings("deprecation")
    public void onInitializeClient() {
        //EntityModelLayerRegistry.registerModelLayer(CAULDRON_LAYER, ModelCauldron::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANDRAKE, MandrakeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MANDRAKE_LAYER, MandrakeModel::getTexturedModelData);


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANDRAKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WATER_ARTICHOKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELLADONNA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOWBELL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOLFSBANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARLIC, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMBER_MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLINT_WEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPANISH_MOSS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_SAPLING, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getFoliageColor(world, pos), ModBlocks.ROWAN_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.ROWAN_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getFoliageColor(world, pos), ModBlocks.SPANISH_MOSS);
        //ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.SPANISH_MOSS);


        BlockEntityRendererRegistry.register(ModBlockEntities.WITCHS_CAULDRON_ENTITY, WitchsCauldronEntityRenderer::new);


        ParticleFactoryRegistry.getInstance().register(ModParticles.BUBBLE_PARTICLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.POWER_PARTICLE, PowerParticle.Factory::new);


        HandledScreens.register(ModScreenHandlers.WITCHS_OVEN_SCREEN_HANDLER, WitchsOvenScreen::new);


        ClientPackets.initialize();
    }
}
