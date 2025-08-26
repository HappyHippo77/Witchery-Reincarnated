package io.github.happyhippo77.witchery2;

import dev.felnull.specialmodelloader.api.event.SpecialModelLoaderEvents;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.networking.ClientPackets;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.particle.particles.BubbleParticle;
import io.github.happyhippo77.witchery2.particle.particles.PowerParticle;
import io.github.happyhippo77.witchery2.render.blockentity.renderers.PlacedItemEntityRenderer;
import io.github.happyhippo77.witchery2.render.blockentity.renderers.WitchsCauldronEntityRenderer;
import io.github.happyhippo77.witchery2.render.entity.models.MandrakeModel;
import io.github.happyhippo77.witchery2.render.entity.renderers.MandrakeRenderer;
import io.github.happyhippo77.witchery2.screen.AltarScreen;
import io.github.happyhippo77.witchery2.screen.ModScreenHandlers;
import io.github.happyhippo77.witchery2.screen.WitchsOvenScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class Client implements ClientModInitializer {
//    public static final EntityModelLayer CAULDRON_LAYER = new EntityModelLayer(new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), "main");

    public static final EntityModelLayer MANDRAKE_LAYER = new EntityModelLayer(new Identifier(Witchery2.MOD_ID, "mandrake"), "main");

    @Override
    @SuppressWarnings("deprecation")
    public void onInitializeClient() {
        SpecialModelLoaderEvents.LOAD_SCOPE.register(location -> Witchery2.MOD_ID.equals(location.getNamespace()));

        //EntityModelLayerRegistry.registerModelLayer(CAULDRON_LAYER, ModelCauldron::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MANDRAKE, MandrakeRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MANDRAKE_LAYER, MandrakeModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.BREW_ENTITY, FlyingItemEntityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FILTERED_FUME_FUNNEL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHALICE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FILLED_CHALICE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MANDRAKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WATER_ARTICHOKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELLADONNA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SNOWBELL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOLFSBANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GARLIC, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EMBER_MOSS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLINT_WEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPANISH_MOSS, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ROWAN_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ROWAN_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ALDER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ALDER_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HAWTHORN_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_HAWTHORN_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ICE_STOCKADE, RenderLayer.getTranslucent());

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getFoliageColor(world, pos), ModBlocks.ROWAN_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.ROWAN_LEAVES);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 3774771, ModBlocks.ALDER_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 3774771, ModBlocks.ALDER_LEAVES);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 6728294, ModBlocks.HAWTHORN_LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 6728294, ModBlocks.HAWTHORN_LEAVES);

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getFoliageColor(world, pos), ModBlocks.SPANISH_MOSS);
        //ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), ModBlocks.SPANISH_MOSS);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                tintIndex == 0?stack.getNbt() != null?stack.getNbt().contains("color")?stack.getNbt().getInt("color"):16777215:16777215:16777215,
                ModItems.BREW);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) ->
                        tintIndex == 0?stack.getNbt() != null?stack.getNbt().contains("color")?stack.getNbt().getInt("color"):16777215:16777215:16777215,
                ModItems.PROJECTILE_BREW);

        //tintIndex == 0?stack.getNbt() != null?stack.getNbt().contains("color")?stack.getNbt().getInt("color"):16777215:16777215:16777215


        BlockEntityRendererRegistry.register(ModBlockEntities.WITCHS_CAULDRON_ENTITY, WitchsCauldronEntityRenderer::new);
        BlockEntityRendererRegistry.register(ModBlockEntities.PLACED_ITEM_ENTITY, PlacedItemEntityRenderer::new);


        ParticleFactoryRegistry.getInstance().register(ModParticles.BUBBLE_PARTICLE, BubbleParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.POWER_PARTICLE, PowerParticle.Factory::new);


        HandledScreens.register(ModScreenHandlers.WITCHS_OVEN_SCREEN_HANDLER, WitchsOvenScreen::new);
        HandledScreens.register(ModScreenHandlers.ALTAR_SCREEN_HANDLER, AltarScreen::new);


        ClientPackets.initialize();
    }
}
