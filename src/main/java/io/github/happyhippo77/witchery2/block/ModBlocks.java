package io.github.happyhippo77.witchery2.block;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.blocks.*;
import io.github.happyhippo77.witchery2.world.trees.AlderSaplingGenerator;
import io.github.happyhippo77.witchery2.world.trees.HawthornSaplingGenerator;
import io.github.happyhippo77.witchery2.world.trees.RowanSaplingGenerator;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final BlockSetType ROWAN_BLOCK_SET = BlockSetTypeRegistry.registerWood(new Identifier(Witchery2.MOD_ID, "rowan"));
    public static final WoodType ROWAN_WOOD_TYPE = WoodTypeRegistry.register(new Identifier(Witchery2.MOD_ID, "rowan"), ROWAN_BLOCK_SET);
    public static final BlockSetType ALDER_BLOCK_SET = BlockSetTypeRegistry.registerWood(new Identifier(Witchery2.MOD_ID, "alder"));
    public static final WoodType ALDER_WOOD_TYPE = WoodTypeRegistry.register(new Identifier(Witchery2.MOD_ID, "alder"), ALDER_BLOCK_SET);
    public static final BlockSetType HAWTHORN_BLOCK_SET = BlockSetTypeRegistry.registerWood(new Identifier(Witchery2.MOD_ID, "hawthorn"));
    public static final WoodType HAWTHORN_WOOD_TYPE = WoodTypeRegistry.register(new Identifier(Witchery2.MOD_ID, "hawthorn"), HAWTHORN_BLOCK_SET);


    public static final PlacedItem PLACED_ITEM = new PlacedItem(FabricBlockSettings.of(Material.METAL).hardness(0).nonOpaque());

    public static final WitchsCauldron WITCHS_CAULDRON = new WitchsCauldron(FabricBlockSettings.of(Material.METAL).strength(2.0f, 2.0f).requiresTool().nonOpaque());
    public static final WitchsOven WITCHS_OVEN = new WitchsOven(FabricBlockSettings.of(Material.METAL).strength(3.5f, 2.0f).requiresTool().nonOpaque());
    public static final FumeFunnel FUME_FUNNEL = new FumeFunnel(false, FabricBlockSettings.of(Material.METAL).strength(3.5f, 2.0f).requiresTool().nonOpaque());
    public static final FumeFunnel FILTERED_FUME_FUNNEL = new FumeFunnel(true, FabricBlockSettings.of(Material.METAL).strength(3.5f, 2.0f).requiresTool().nonOpaque());
    public static final Altar ALTAR = new Altar(FabricBlockSettings.of(Material.STONE).hardness(2.0f).requiresTool());
    public static final Candelabra CANDELABRA = new Candelabra(FabricBlockSettings.of(Material.METAL).luminance(15).hardness(2.0f).nonOpaque().requiresTool());
    public static final Chalice CHALICE = new Chalice(FabricBlockSettings.of(Material.METAL).hardness(3.0f).nonOpaque().requiresTool(), false);
    public static final Chalice FILLED_CHALICE = new Chalice(FabricBlockSettings.of(Material.METAL).hardness(3.0f).nonOpaque().requiresTool(), true);
    public static final InfinityEgg INFINITY_EGG = new InfinityEgg(FabricBlockSettings.copyOf(Blocks.DRAGON_EGG));

    public static final MandrakeCrop MANDRAKE = new MandrakeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WaterArtichokeCrop WATER_ARTICHOKE = new WaterArtichokeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final BelladonnaCrop BELLADONNA = new BelladonnaCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final SnowbellCrop SNOWBELL = new SnowbellCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WolfsbaneCrop WOLFSBANE = new WolfsbaneCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final GarlicCrop GARLIC = new GarlicCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final EmberMoss EMBER_MOSS = new EmberMoss(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final GlintWeed GLINT_WEED = new GlintWeed(FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).luminance(Math.round(0.9375F * 15)));
    public static final SpanishMoss SPANISH_MOSS = new SpanishMoss(FabricBlockSettings.copyOf(Blocks.VINE));

    public static final PillarBlock ROWAN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final PillarBlock ROWAN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final PillarBlock STRIPPED_ROWAN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final PillarBlock STRIPPED_ROWAN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
    public static final Block ROWAN_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final LeavesBlock ROWAN_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final SaplingBlock ROWAN_SAPLING = new SaplingBlock(new RowanSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
    public static final FlowerPotBlock POTTED_ROWAN_SAPLING = new FlowerPotBlock(ROWAN_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
    public static final StairsBlock ROWAN_STAIRS = new StairsBlock(ModBlocks.ROWAN_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ROWAN_PLANKS));
    public static final SlabBlock ROWAN_SLAB = new SlabBlock(FabricBlockSettings.copyOf(ROWAN_PLANKS));
    public static final FenceBlock ROWAN_FENCE = new FenceBlock(FabricBlockSettings.copyOf(ROWAN_PLANKS));
    public static final FenceGateBlock ROWAN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(ROWAN_PLANKS), ROWAN_WOOD_TYPE);
    public static final RowanDoor ROWAN_DOOR = new RowanDoor(FabricBlockSettings.copyOf(Blocks.OAK_DOOR));
    public static final RowanTrapdoor ROWAN_TRAPDOOR = new RowanTrapdoor(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock ROWAN_BUTTON = new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), ROWAN_BLOCK_SET, 30, true);
    public static final PressurePlateBlock ROWAN_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), ROWAN_BLOCK_SET);
    public static final SignBlock ROWAN_SIGN = new SignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN), ROWAN_WOOD_TYPE);
    public static final WallSignBlock ROWAN_WALL_SIGN = new WallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), ROWAN_WOOD_TYPE);

    public static final PillarBlock ALDER_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final PillarBlock ALDER_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final PillarBlock STRIPPED_ALDER_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final PillarBlock STRIPPED_ALDER_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
    public static final Block ALDER_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final LeavesBlock ALDER_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final SaplingBlock ALDER_SAPLING = new SaplingBlock(new AlderSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
    public static final FlowerPotBlock POTTED_ALDER_SAPLING = new FlowerPotBlock(ALDER_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
    public static final StairsBlock ALDER_STAIRS = new StairsBlock(ModBlocks.ALDER_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ALDER_PLANKS));
    public static final SlabBlock ALDER_SLAB = new SlabBlock(FabricBlockSettings.copyOf(ALDER_PLANKS));
    public static final FenceBlock ALDER_FENCE = new FenceBlock(FabricBlockSettings.copyOf(ALDER_PLANKS));
    public static final FenceGateBlock ALDER_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(ALDER_PLANKS), ALDER_WOOD_TYPE);
    public static final AlderDoor ALDER_DOOR = new AlderDoor(FabricBlockSettings.copyOf(Blocks.OAK_DOOR));
    public static final AlderTrapdoor ALDER_TRAPDOOR = new AlderTrapdoor(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR));
    public static final ButtonBlock ALDER_BUTTON = new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), ALDER_BLOCK_SET, 30, true);
    public static final PressurePlateBlock ALDER_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), ALDER_BLOCK_SET);
    public static final SignBlock ALDER_SIGN = new SignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN), ALDER_WOOD_TYPE);
    public static final WallSignBlock ALDER_WALL_SIGN = new WallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), ALDER_WOOD_TYPE);

    public static final PillarBlock HAWTHORN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final PillarBlock HAWTHORN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final PillarBlock STRIPPED_HAWTHORN_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final PillarBlock STRIPPED_HAWTHORN_WOOD = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD));
    public static final Block HAWTHORN_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final LeavesBlock HAWTHORN_LEAVES = new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES));
    public static final SaplingBlock HAWTHORN_SAPLING = new SaplingBlock(new HawthornSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));
    public static final FlowerPotBlock POTTED_HAWTHORN_SAPLING = new FlowerPotBlock(HAWTHORN_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING));
    public static final StairsBlock HAWTHORN_STAIRS = new StairsBlock(ModBlocks.HAWTHORN_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(HAWTHORN_PLANKS));
    public static final SlabBlock HAWTHORN_SLAB = new SlabBlock(FabricBlockSettings.copyOf(HAWTHORN_PLANKS));
    public static final FenceBlock HAWTHORN_FENCE = new FenceBlock(FabricBlockSettings.copyOf(HAWTHORN_PLANKS));
    public static final FenceGateBlock HAWTHORN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(HAWTHORN_PLANKS), HAWTHORN_WOOD_TYPE);
    public static final DoorBlock HAWTHORN_DOOR = new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), HAWTHORN_BLOCK_SET);
    public static final TrapdoorBlock HAWTHORN_TRAPDOOR = new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), HAWTHORN_BLOCK_SET);
    public static final ButtonBlock HAWTHORN_BUTTON = new ButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), HAWTHORN_BLOCK_SET, 30, true);
    public static final PressurePlateBlock HAWTHORN_PRESSURE_PLATE = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), HAWTHORN_BLOCK_SET);
    public static final SignBlock HAWTHORN_SIGN = new SignBlock(FabricBlockSettings.copyOf(Blocks.OAK_SIGN), HAWTHORN_WOOD_TYPE);
    public static final WallSignBlock HAWTHORN_WALL_SIGN = new WallSignBlock(FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN), HAWTHORN_WOOD_TYPE);

    public static final Stockade OAK_STOCKADE = new Stockade();
    public static final Stockade SPRUCE_STOCKADE = new Stockade();
    public static final Stockade BIRCH_STOCKADE = new Stockade();
    public static final Stockade JUNGLE_STOCKADE = new Stockade();
    public static final Stockade ACACIA_STOCKADE = new Stockade();
    public static final Stockade DARK_OAK_STOCKADE = new Stockade();
    public static final Stockade MANGROVE_STOCKADE = new Stockade();
    public static final Stockade ICE_STOCKADE = new Stockade(FabricBlockSettings.of(Material.ICE).hardness(25).resistance(20));
    public static final Stockade ROWAN_STOCKADE = new Stockade();
    public static final Stockade ALDER_STOCKADE = new Stockade();
    public static final Stockade HAWTHORN_STOCKADE = new Stockade();

    public static void registerAllBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "placed_item"), PLACED_ITEM);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), WITCHS_CAULDRON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_oven"), WITCHS_OVEN);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "fume_funnel"), FUME_FUNNEL);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "filtered_fume_funnel"), FILTERED_FUME_FUNNEL);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "altar"), ALTAR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "candelabra"), CANDELABRA);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "chalice"), CHALICE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "filled_chalice"), FILLED_CHALICE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "infinity_egg"), INFINITY_EGG);

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
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "potted_rowan_sapling"), POTTED_ROWAN_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_stairs"), ROWAN_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_slab"), ROWAN_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_fence"), ROWAN_FENCE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_fence_gate"), ROWAN_FENCE_GATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_door"), ROWAN_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_trapdoor"), ROWAN_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_pressure_plate"), ROWAN_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_button"), ROWAN_BUTTON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_sign"), ROWAN_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_wall_sign"), ROWAN_WALL_SIGN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_log"), ALDER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_wood"), ALDER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_alder_log"), STRIPPED_ALDER_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_alder_wood"), STRIPPED_ALDER_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_planks"), ALDER_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_leaves"), ALDER_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_sapling"), ALDER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "potted_alder_sapling"), POTTED_ALDER_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_stairs"), ALDER_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_slab"), ALDER_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_fence"), ALDER_FENCE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_fence_gate"), ALDER_FENCE_GATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_door"), ALDER_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_trapdoor"), ALDER_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_pressure_plate"), ALDER_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_button"), ALDER_BUTTON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_sign"), ALDER_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_wall_sign"), ALDER_WALL_SIGN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_log"), HAWTHORN_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_wood"), HAWTHORN_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_hawthorn_log"), STRIPPED_HAWTHORN_LOG);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "stripped_hawthorn_wood"), STRIPPED_HAWTHORN_WOOD);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_planks"), HAWTHORN_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_leaves"), HAWTHORN_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_sapling"), HAWTHORN_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "potted_hawthorn_sapling"), POTTED_HAWTHORN_SAPLING);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_stairs"), HAWTHORN_STAIRS);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_slab"), HAWTHORN_SLAB);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_fence"), HAWTHORN_FENCE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_fence_gate"), HAWTHORN_FENCE_GATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_door"), HAWTHORN_DOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_trapdoor"), HAWTHORN_TRAPDOOR);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_pressure_plate"), HAWTHORN_PRESSURE_PLATE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_button"), HAWTHORN_BUTTON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_sign"), HAWTHORN_SIGN);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_wall_sign"), HAWTHORN_WALL_SIGN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "oak_stockade"), OAK_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "spruce_stockade"), SPRUCE_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "birch_stockade"), BIRCH_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "jungle_stockade"), JUNGLE_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "acacia_stockade"), ACACIA_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "dark_oak_stockade"), DARK_OAK_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "mangrove_stockade"), MANGROVE_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "ice_stockade"), ICE_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "rowan_stockade"), ROWAN_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "alder_stockade"), ALDER_STOCKADE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "hawthorn_stockade"), HAWTHORN_STOCKADE);

        StrippableBlockRegistry.register(ROWAN_LOG, STRIPPED_ROWAN_LOG);
        StrippableBlockRegistry.register(ROWAN_WOOD, STRIPPED_ROWAN_WOOD);

        StrippableBlockRegistry.register(ALDER_LOG, STRIPPED_ALDER_LOG);
        StrippableBlockRegistry.register(ALDER_WOOD, STRIPPED_ALDER_WOOD);

        StrippableBlockRegistry.register(HAWTHORN_LOG, STRIPPED_HAWTHORN_LOG);
        StrippableBlockRegistry.register(HAWTHORN_WOOD, STRIPPED_HAWTHORN_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ROWAN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ROWAN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ROWAN_FENCE_GATE, 5, 20);

        FlammableBlockRegistry.getDefaultInstance().add(ALDER_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ALDER_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ALDER_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ALDER_FENCE_GATE, 5, 20);

        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_HAWTHORN_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_HAWTHORN_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(HAWTHORN_FENCE_GATE, 5, 20);

        FuelRegistry.INSTANCE.add(ROWAN_LOG, 300);
        FuelRegistry.INSTANCE.add(ROWAN_WOOD, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ROWAN_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ROWAN_WOOD, 300);
        FuelRegistry.INSTANCE.add(ROWAN_PLANKS, 300);
        FuelRegistry.INSTANCE.add(ROWAN_SAPLING, 100);
        FuelRegistry.INSTANCE.add(ROWAN_STAIRS, 300);
        FuelRegistry.INSTANCE.add(ROWAN_SLAB, 150);
        FuelRegistry.INSTANCE.add(ROWAN_FENCE, 300);
        FuelRegistry.INSTANCE.add(ROWAN_FENCE_GATE, 300);
        FuelRegistry.INSTANCE.add(ROWAN_DOOR, 200);
        FuelRegistry.INSTANCE.add(ROWAN_TRAPDOOR, 300);
        FuelRegistry.INSTANCE.add(ROWAN_PRESSURE_PLATE, 300);
        FuelRegistry.INSTANCE.add(ROWAN_BUTTON, 100);
        FuelRegistry.INSTANCE.add(ROWAN_SIGN, 200);

        FuelRegistry.INSTANCE.add(ALDER_LOG, 300);
        FuelRegistry.INSTANCE.add(ALDER_WOOD, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ALDER_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_ALDER_WOOD, 300);
        FuelRegistry.INSTANCE.add(ALDER_PLANKS, 300);
        FuelRegistry.INSTANCE.add(ALDER_SAPLING, 100);
        FuelRegistry.INSTANCE.add(ALDER_STAIRS, 300);
        FuelRegistry.INSTANCE.add(ALDER_SLAB, 150);
        FuelRegistry.INSTANCE.add(ALDER_FENCE, 300);
        FuelRegistry.INSTANCE.add(ALDER_FENCE_GATE, 300);
        FuelRegistry.INSTANCE.add(ALDER_DOOR, 200);
        FuelRegistry.INSTANCE.add(ALDER_TRAPDOOR, 300);
        FuelRegistry.INSTANCE.add(ALDER_PRESSURE_PLATE, 300);
        FuelRegistry.INSTANCE.add(ALDER_BUTTON, 100);
        FuelRegistry.INSTANCE.add(ALDER_SIGN, 200);

        FuelRegistry.INSTANCE.add(HAWTHORN_LOG, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_WOOD, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_HAWTHORN_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_HAWTHORN_WOOD, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_PLANKS, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_SAPLING, 100);
        FuelRegistry.INSTANCE.add(HAWTHORN_STAIRS, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_SLAB, 150);
        FuelRegistry.INSTANCE.add(HAWTHORN_FENCE, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_FENCE_GATE, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_DOOR, 200);
        FuelRegistry.INSTANCE.add(HAWTHORN_TRAPDOOR, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_PRESSURE_PLATE, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_BUTTON, 100);
        FuelRegistry.INSTANCE.add(HAWTHORN_SIGN, 200);

        FuelRegistry.INSTANCE.add(OAK_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(SPRUCE_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(BIRCH_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(JUNGLE_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(ACACIA_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(DARK_OAK_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(MANGROVE_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(ROWAN_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(ALDER_STOCKADE, 300);
        FuelRegistry.INSTANCE.add(HAWTHORN_STOCKADE, 300);

    }
}
