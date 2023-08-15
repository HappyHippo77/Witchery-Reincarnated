package io.github.happyhippo77.witchery2.block.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockItems {
    public static void registerAllBlocks() {
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), new BlockItem(ModBlocks.WITCHS_CAULDRON, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "witchs_oven"), new BlockItem(ModBlocks.WITCHS_OVEN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "fume_funnel"), new BlockItem(ModBlocks.FUME_FUNNEL, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "filtered_fume_funnel"), new BlockItem(ModBlocks.FILTERED_FUME_FUNNEL, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "altar"), new BlockItem(ModBlocks.ALTAR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "candelabra"), new BlockItem(ModBlocks.CANDELABRA, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "chalice"), new BlockItem(ModBlocks.CHALICE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "filled_chalice"), new BlockItem(ModBlocks.FILLED_CHALICE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "pentacle"), new BlockItem(ModBlocks.PENTACLE, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "ember_moss"), new BlockItem(ModBlocks.EMBER_MOSS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "glint_weed"), new BlockItem(ModBlocks.GLINT_WEED, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "spanish_moss"), new BlockItem(ModBlocks.SPANISH_MOSS, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_log"), new BlockItem(ModBlocks.ROWAN_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_wood"), new BlockItem(ModBlocks.ROWAN_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_rowan_log"), new BlockItem(ModBlocks.STRIPPED_ROWAN_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_rowan_wood"), new BlockItem(ModBlocks.STRIPPED_ROWAN_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_planks"), new BlockItem(ModBlocks.ROWAN_PLANKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_leaves"), new BlockItem(ModBlocks.ROWAN_LEAVES, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_sapling"), new BlockItem(ModBlocks.ROWAN_SAPLING, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_stairs"), new BlockItem(ModBlocks.ROWAN_STAIRS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_slab"), new BlockItem(ModBlocks.ROWAN_SLAB, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_fence"), new BlockItem(ModBlocks.ROWAN_FENCE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_fence_gate"), new BlockItem(ModBlocks.ROWAN_FENCE_GATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_door"), new BlockItem(ModBlocks.ROWAN_DOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_trapdoor"), new BlockItem(ModBlocks.ROWAN_TRAPDOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_pressure_plate"), new BlockItem(ModBlocks.ROWAN_PRESSURE_PLATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_button"), new BlockItem(ModBlocks.ROWAN_BUTTON, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_log"), new BlockItem(ModBlocks.ALDER_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_wood"), new BlockItem(ModBlocks.ALDER_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_alder_log"), new BlockItem(ModBlocks.STRIPPED_ALDER_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_alder_wood"), new BlockItem(ModBlocks.STRIPPED_ALDER_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_planks"), new BlockItem(ModBlocks.ALDER_PLANKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_leaves"), new BlockItem(ModBlocks.ALDER_LEAVES, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_sapling"), new BlockItem(ModBlocks.ALDER_SAPLING, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_stairs"), new BlockItem(ModBlocks.ALDER_STAIRS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_slab"), new BlockItem(ModBlocks.ALDER_SLAB, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_fence"), new BlockItem(ModBlocks.ALDER_FENCE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_fence_gate"), new BlockItem(ModBlocks.ALDER_FENCE_GATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_door"), new BlockItem(ModBlocks.ALDER_DOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_trapdoor"), new BlockItem(ModBlocks.ALDER_TRAPDOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_pressure_plate"), new BlockItem(ModBlocks.ALDER_PRESSURE_PLATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_button"), new BlockItem(ModBlocks.ALDER_BUTTON, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_log"), new BlockItem(ModBlocks.HAWTHORN_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_wood"), new BlockItem(ModBlocks.HAWTHORN_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_hawthorn_log"), new BlockItem(ModBlocks.STRIPPED_HAWTHORN_LOG, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "stripped_hawthorn_wood"), new BlockItem(ModBlocks.STRIPPED_HAWTHORN_WOOD, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_planks"), new BlockItem(ModBlocks.HAWTHORN_PLANKS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_leaves"), new BlockItem(ModBlocks.HAWTHORN_LEAVES, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_sapling"), new BlockItem(ModBlocks.HAWTHORN_SAPLING, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_stairs"), new BlockItem(ModBlocks.HAWTHORN_STAIRS, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_slab"), new BlockItem(ModBlocks.HAWTHORN_SLAB, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_fence"), new BlockItem(ModBlocks.HAWTHORN_FENCE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_fence_gate"), new BlockItem(ModBlocks.HAWTHORN_FENCE_GATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_door"), new BlockItem(ModBlocks.HAWTHORN_DOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_trapdoor"), new BlockItem(ModBlocks.HAWTHORN_TRAPDOOR, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_pressure_plate"), new BlockItem(ModBlocks.HAWTHORN_PRESSURE_PLATE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_button"), new BlockItem(ModBlocks.HAWTHORN_BUTTON, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "oak_stockade"), new BlockItem(ModBlocks.OAK_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "spruce_stockade"), new BlockItem(ModBlocks.SPRUCE_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "birch_stockade"), new BlockItem(ModBlocks.BIRCH_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "jungle_stockade"), new BlockItem(ModBlocks.JUNGLE_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "acacia_stockade"), new BlockItem(ModBlocks.ACACIA_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "dark_oak_stockade"), new BlockItem(ModBlocks.DARK_OAK_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mangrove_stockade"), new BlockItem(ModBlocks.MANGROVE_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "ice_stockade"), new BlockItem(ModBlocks.ICE_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_stockade"), new BlockItem(ModBlocks.ROWAN_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_stockade"), new BlockItem(ModBlocks.ALDER_STOCKADE, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_stockade"), new BlockItem(ModBlocks.HAWTHORN_STOCKADE, new FabricItemSettings()));
    }
}
