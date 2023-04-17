package io.github.happyhippo77.witchery2.block;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.blocks.*;
import io.github.happyhippo77.witchery2.world.trees.RowanSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final WitchsCauldron WITCHS_CAULDRON = new WitchsCauldron(FabricBlockSettings.of(Material.METAL).strength(2.0f, 2.0f).requiresTool().nonOpaque());
    public static final WitchsOven WITCHS_OVEN = new WitchsOven(FabricBlockSettings.of(Material.METAL).strength(3.5f, 2.0f).requiresTool().nonOpaque());

    public static final MandrakeCrop MANDRAKE = new MandrakeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WaterArtichokeCrop WATER_ARTICHOKE = new WaterArtichokeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final BelladonnaCrop BELLADONNA = new BelladonnaCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final SnowbellCrop SNOWBELL = new SnowbellCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WolfsbaneCrop WOLFSBANE = new WolfsbaneCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final GarlicCrop GARLIC = new GarlicCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final Block EMBER_MOSS = new EmberMoss(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block GLINT_WEED = new GlintWeed(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance(Math.round(0.9375F * 15)));
    public static final Block SPANISH_MOSS = new SpanishMoss(FabricBlockSettings.copyOf(Blocks.VINE));

    public static final Block ROWAN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final Block ROWAN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final Block STRIPPED_ROWAN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_ROWAN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
    public static final Block ROWAN_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final Block ROWAN_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final Block ROWAN_SAPLING = new SaplingBlock(new RowanSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final Block ROWAN_STAIRS = new StairsBlock(ROWAN_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));

    public static void registerAllBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), WITCHS_CAULDRON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_oven"), WITCHS_OVEN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "mandrake"), MANDRAKE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "water_artichoke"), WATER_ARTICHOKE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "belladonna"), BELLADONNA);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "snowbell"), SNOWBELL);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "wolfsbane"), WOLFSBANE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "garlic"), GARLIC);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "ember_moss"), EMBER_MOSS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "glint_weed"), GLINT_WEED);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "spanish_moss"), SPANISH_MOSS);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_log"), ROWAN_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_wood"), ROWAN_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_rowan_log"), STRIPPED_ROWAN_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_rowan_wood"), STRIPPED_ROWAN_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_planks"), ROWAN_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_leaves"), ROWAN_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_sapling"), ROWAN_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_stairs"), ROWAN_STAIRS);

        StrippableBlockRegistry.register(ROWAN_LOG, STRIPPED_ROWAN_LOG);
        StrippableBlockRegistry.register(ROWAN_WOOD, STRIPPED_ROWAN_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ROWAN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ROWAN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_LEAVES, 30, 60);
        //FlammableBlockRegistry.getDefaultInstance().add(ROWAN_STAIRS, 30, 60);

        FuelRegistry.INSTANCE.add(ROWAN_LOG, 300);
        FuelRegistry.INSTANCE.add(ROWAN_WOOD, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ROWAN_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ROWAN_WOOD, 300);
        FuelRegistry.INSTANCE.add(ROWAN_PLANKS, 300);
        FuelRegistry.INSTANCE.add(ROWAN_SAPLING, 100);
        //FuelRegistry.INSTANCE.add(ROWAN_STAIRS, 300);

    }
}
