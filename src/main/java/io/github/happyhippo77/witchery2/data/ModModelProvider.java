package io.github.happyhippo77.witchery2.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WITCHS_OVEN);

        blockStateModelGenerator.registerCrop(ModBlocks.BELLADONNA, ModBlocks.BELLADONNA.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerCrop(ModBlocks.MANDRAKE, ModBlocks.MANDRAKE.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerCrop(ModBlocks.WATER_ARTICHOKE, ModBlocks.WATER_ARTICHOKE.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.SNOWBELL, BlockStateModelGenerator.TintType.NOT_TINTED, ModBlocks.SNOWBELL.getAgeProperty(), 0, 1, 2, 3, 4);
        blockStateModelGenerator.registerTintableCrossBlockStateWithStages(ModBlocks.WOLFSBANE, BlockStateModelGenerator.TintType.NOT_TINTED, ModBlocks.WOLFSBANE.getAgeProperty(), 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.registerCrop(ModBlocks.GARLIC, ModBlocks.GARLIC.getAgeProperty(), 0, 1, 2, 3, 4, 5);

        blockStateModelGenerator.registerTintableCross(ModBlocks.EMBER_MOSS, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.GLINT_WEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerWallPlant(ModBlocks.SPANISH_MOSS);

        blockStateModelGenerator.registerLog(ModBlocks.ROWAN_LOG).log(ModBlocks.ROWAN_LOG).wood(ModBlocks.ROWAN_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ROWAN_LOG).log(ModBlocks.STRIPPED_ROWAN_LOG).wood(ModBlocks.STRIPPED_ROWAN_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROWAN_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.ROWAN_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ROWAN_SAPLING, ModBlocks.POTTED_ROWAN_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerStairs(blockStateModelGenerator, ModBlocks.ROWAN_STAIRS, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        registerSlab(blockStateModelGenerator, ModBlocks.ROWAN_SLAB, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        registerFence(blockStateModelGenerator, ModBlocks.ROWAN_FENCE, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        registerFenceGate(blockStateModelGenerator, ModBlocks.ROWAN_FENCE_GATE, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        blockStateModelGenerator.registerDoor(ModBlocks.ROWAN_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.ROWAN_TRAPDOOR);
        registerPressurePlate(blockStateModelGenerator, ModBlocks.ROWAN_PRESSURE_PLATE, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        registerButton(blockStateModelGenerator, ModBlocks.ROWAN_BUTTON, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));
        registerSign(blockStateModelGenerator, ModBlocks.ROWAN_SIGN, ModBlocks.ROWAN_WALL_SIGN, new Identifier(Witchery2.MOD_ID, "block/rowan_planks"));

        blockStateModelGenerator.registerLog(ModBlocks.ALDER_LOG).log(ModBlocks.ALDER_LOG).wood(ModBlocks.ALDER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ALDER_LOG).log(ModBlocks.STRIPPED_ALDER_LOG).wood(ModBlocks.STRIPPED_ALDER_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ALDER_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.ALDER_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ALDER_SAPLING, ModBlocks.POTTED_ALDER_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerStairs(blockStateModelGenerator, ModBlocks.ALDER_STAIRS, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerSlab(blockStateModelGenerator, ModBlocks.ALDER_SLAB, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerFence(blockStateModelGenerator, ModBlocks.ALDER_FENCE, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerFenceGate(blockStateModelGenerator, ModBlocks.ALDER_FENCE_GATE, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerPressurePlate(blockStateModelGenerator, ModBlocks.ALDER_PRESSURE_PLATE, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerButton(blockStateModelGenerator, ModBlocks.ALDER_BUTTON, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));
        registerSign(blockStateModelGenerator, ModBlocks.ALDER_SIGN, ModBlocks.ALDER_WALL_SIGN, new Identifier(Witchery2.MOD_ID, "block/alder_planks"));

        blockStateModelGenerator.registerLog(ModBlocks.HAWTHORN_LOG).log(ModBlocks.HAWTHORN_LOG).wood(ModBlocks.HAWTHORN_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_HAWTHORN_LOG).log(ModBlocks.STRIPPED_HAWTHORN_LOG).wood(ModBlocks.STRIPPED_HAWTHORN_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.HAWTHORN_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.HAWTHORN_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HAWTHORN_SAPLING, ModBlocks.POTTED_HAWTHORN_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerStairs(blockStateModelGenerator, ModBlocks.HAWTHORN_STAIRS, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        registerSlab(blockStateModelGenerator, ModBlocks.HAWTHORN_SLAB, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        registerFence(blockStateModelGenerator, ModBlocks.HAWTHORN_FENCE, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        registerFenceGate(blockStateModelGenerator, ModBlocks.HAWTHORN_FENCE_GATE, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        blockStateModelGenerator.registerDoor(ModBlocks.HAWTHORN_DOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.HAWTHORN_TRAPDOOR);
        registerPressurePlate(blockStateModelGenerator, ModBlocks.HAWTHORN_PRESSURE_PLATE, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        registerButton(blockStateModelGenerator, ModBlocks.HAWTHORN_BUTTON, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
        registerSign(blockStateModelGenerator, ModBlocks.HAWTHORN_SIGN, ModBlocks.HAWTHORN_WALL_SIGN, new Identifier(Witchery2.MOD_ID, "block/hawthorn_planks"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.FUME_FILTER, Models.GENERATED);

        itemModelGenerator.register(ModItems.ANOINTING_PASTE, Models.GENERATED);

        itemModelGenerator.register(ModItems.BELLADONNA_FLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANDRAKE_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WATER_ARTICHOKE_GLOBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ICY_NEEDLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOLFSBANE, Models.GENERATED);

        itemModelGenerator.register(ModItems.FOUL_FUME, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXHALE_OF_THE_HORNED_ONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BREATH_OF_THE_GODDESS, Models.GENERATED);
        itemModelGenerator.register(ModItems.HINT_OF_REBIRTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHIFF_OF_MAGIC, Models.GENERATED);
        itemModelGenerator.register(ModItems.ODOUR_OF_PURITY, Models.GENERATED);
        itemModelGenerator.register(ModItems.REEK_OF_MISFORTUNE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUTANDIS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CLAY_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOFT_CLAY_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOOD_ASH, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUICKLIME, Models.GENERATED);
        itemModelGenerator.register(ModItems.EARMUFFS, Models.GENERATED);

        itemModelGenerator.register(ModItems.ROWAN_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROWAN_DOOR_KEY, Models.GENERATED);

        itemModelGenerator.register(ModItems.ATTUNED_STONE, Models.GENERATED);
    }

    private void registerStairs(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier texture) {
        TextureMap m = new TextureMap()
                .put(TextureKey.BOTTOM, texture)
                .put(TextureKey.SIDE, texture)
                .put(TextureKey.TOP, texture);

        Identifier identifier = Models.INNER_STAIRS.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.STAIRS.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.OUTER_STAIRS.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(block, identifier, identifier2, identifier3));
        blockStateModelGenerator.registerParentedItemModel(block, identifier2);
    }

    private void registerSlab(BlockStateModelGenerator blockStateModelGenerator, Block slabBlock, Identifier fullBlock) {
        TextureMap m = new TextureMap()
                .put(TextureKey.BOTTOM, fullBlock)
                .put(TextureKey.SIDE, fullBlock)
                .put(TextureKey.TOP, fullBlock);

        Identifier identifier = Models.SLAB.upload(slabBlock, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.SLAB_TOP.upload(slabBlock, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSlabBlockState(slabBlock, identifier, identifier2, fullBlock));
        blockStateModelGenerator.registerParentedItemModel(slabBlock, identifier);
    }

    private void registerFence(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier texture) {
        TextureMap m = new TextureMap().put(TextureKey.TEXTURE, texture);

        Identifier identifier = Models.FENCE_POST.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.FENCE_SIDE.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceBlockState(block, identifier, identifier2));
        Identifier identifier3 = Models.FENCE_INVENTORY.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(block, identifier3);
    }

    private void registerFenceGate(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier texture) {
        TextureMap m = new TextureMap().put(TextureKey.TEXTURE, texture);

        Identifier identifier = Models.TEMPLATE_FENCE_GATE_OPEN.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.TEMPLATE_FENCE_GATE.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier3 = Models.TEMPLATE_FENCE_GATE_WALL_OPEN.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier4 = Models.TEMPLATE_FENCE_GATE_WALL.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createFenceGateBlockState(block, identifier, identifier2, identifier3, identifier4, true));
    }

    private void registerButton(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier texture) {
        TextureMap m = new TextureMap().put(TextureKey.TEXTURE, texture);

        Identifier identifier = Models.BUTTON.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.BUTTON_PRESSED.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createButtonBlockState(block, identifier, identifier2));
        Identifier identifier3 = Models.BUTTON_INVENTORY.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.registerParentedItemModel(block, identifier3);
    }

    private void registerPressurePlate(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier texture) {
        TextureMap m = new TextureMap().put(TextureKey.TEXTURE, texture);

        Identifier identifier = Models.PRESSURE_PLATE_UP.upload(block, m, blockStateModelGenerator.modelCollector);
        Identifier identifier2 = Models.PRESSURE_PLATE_DOWN.upload(block, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createPressurePlateBlockState(block, identifier, identifier2));
    }

    private void registerSign(BlockStateModelGenerator blockStateModelGenerator, Block signBlock, Block wallSignBlock, Identifier texture) {
        TextureMap m = new TextureMap().put(TextureKey.TEXTURE, texture);

        Identifier identifier = Models.PARTICLE.upload(signBlock, m, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, identifier));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSignBlock, identifier));
        blockStateModelGenerator.registerItemModel(signBlock.asItem());
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallSignBlock);
    }
}
