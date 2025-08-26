package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

import java.util.HashMap;
import java.util.Map;

public class PowerSources {
    private static Map<Block, PowerSource> sources = Map.<Block, PowerSource>ofEntries(
            Map.entry(Blocks.DIRT, new PowerSource(1, 80)),
            Map.entry(Blocks.PODZOL, new PowerSource(1, 80)),
            Map.entry(Blocks.FARMLAND, new PowerSource(1, 100)),
            Map.entry(Blocks.MYCELIUM, new PowerSource(1, 80)),
            Map.entry(Blocks.WATER, new PowerSource(1, 50)),
            Map.entry(Blocks.GRASS_BLOCK, new PowerSource(2, 80)),
            Map.entry(Blocks.OAK_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.BIRCH_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.SPRUCE_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.JUNGLE_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.ACACIA_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.DARK_OAK_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.MANGROVE_LOG, new PowerSource(2, 50)),
            Map.entry(Blocks.CRIMSON_STEM, new PowerSource(2, 50)),
            Map.entry(Blocks.WARPED_STEM, new PowerSource(2, 50)),
            Map.entry(Blocks.VINE, new PowerSource(2, 50)),
            // Blood poppy, 2, 10
            // Critter Snare, 2, 10
            Map.entry(ModBlocks.GLINT_WEED, new PowerSource(2, 20)),
            // Grassper, 2, 10
            Map.entry(Blocks.GRASS, new PowerSource(3, 50)),
            Map.entry(Blocks.FERN, new PowerSource(3, 50)),
            Map.entry(Blocks.OAK_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.BIRCH_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.SPRUCE_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.JUNGLE_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.ACACIA_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.DARK_OAK_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.MANGROVE_LEAVES, new PowerSource(3, 100)),
            Map.entry(Blocks.CRIMSON_HYPHAE, new PowerSource(3, 100)),
            Map.entry(Blocks.WARPED_HYPHAE, new PowerSource(3, 100)),
            Map.entry(Blocks.CACTUS, new PowerSource(3, 50)),
            Map.entry(Blocks.SUGAR_CANE, new PowerSource(3, 50)),
            Map.entry(Blocks.BROWN_MUSHROOM, new PowerSource(3, 20)),
            Map.entry(Blocks.RED_MUSHROOM, new PowerSource(3, 20)),
            Map.entry(Blocks.BROWN_MUSHROOM_BLOCK, new PowerSource(3, 20)),
            Map.entry(Blocks.RED_MUSHROOM_BLOCK, new PowerSource(3, 20)),
            Map.entry(ModBlocks.ROWAN_LOG, new PowerSource(3, 100)),
            Map.entry(ModBlocks.ALDER_LOG, new PowerSource(3, 100)),
            Map.entry(ModBlocks.HAWTHORN_LOG, new PowerSource(3, 100)),
            Map.entry(ModBlocks.SPANISH_MOSS, new PowerSource(3, 20)),
            // Wispy cotton, 3, 20
            Map.entry(Blocks.WHEAT, new PowerSource(4, 20)),
            Map.entry(Blocks.CARROTS, new PowerSource(4, 20)),
            Map.entry(Blocks.POTATOES, new PowerSource(4, 20)),
            Map.entry(Blocks.MELON, new PowerSource(4, 20)),
            Map.entry(Blocks.PUMPKIN, new PowerSource(4, 20)),
            Map.entry(Blocks.OAK_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.BIRCH_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.SPRUCE_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.JUNGLE_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.ACACIA_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.DARK_OAK_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.MANGROVE_PROPAGULE, new PowerSource(4, 20)),
            Map.entry(Blocks.CRIMSON_FUNGUS, new PowerSource(4, 20)),
            Map.entry(Blocks.WARPED_FUNGUS, new PowerSource(4, 20)),
            Map.entry(ModBlocks.ROWAN_SAPLING, new PowerSource(4, 20)),
            Map.entry(ModBlocks.ALDER_SAPLING, new PowerSource(4, 20)),
            Map.entry(ModBlocks.HAWTHORN_SAPLING, new PowerSource(4, 20)),
            Map.entry(Blocks.DANDELION, new PowerSource(4, 30)),
            Map.entry(Blocks.POPPY, new PowerSource(4, 30)),
            Map.entry(Blocks.BLUE_ORCHID, new PowerSource(4, 30)),
            Map.entry(Blocks.ALLIUM, new PowerSource(4, 30)),
            Map.entry(Blocks.AZURE_BLUET, new PowerSource(4, 30)),
            Map.entry(Blocks.RED_TULIP, new PowerSource(4, 30)),
            Map.entry(Blocks.ORANGE_TULIP, new PowerSource(4, 30)),
            Map.entry(Blocks.WHITE_TULIP, new PowerSource(4, 30)),
            Map.entry(Blocks.PINK_TULIP, new PowerSource(4, 30)),
            Map.entry(Blocks.OXEYE_DAISY, new PowerSource(4, 30)),
            Map.entry(Blocks.CORNFLOWER, new PowerSource(4, 30)),
            Map.entry(Blocks.LILY_OF_THE_VALLEY, new PowerSource(4, 30)),
            Map.entry(ModBlocks.ROWAN_LEAVES, new PowerSource(4, 50)),
            Map.entry(ModBlocks.ALDER_LEAVES, new PowerSource(4, 50)),
            Map.entry(ModBlocks.HAWTHORN_LEAVES, new PowerSource(4, 50)),
            Map.entry(ModBlocks.BELLADONNA, new PowerSource(4, 20)),
            Map.entry(ModBlocks.EMBER_MOSS, new PowerSource(4, 20)),
            Map.entry(ModBlocks.MANDRAKE, new PowerSource(4, 20)),
            Map.entry(ModBlocks.SNOWBELL, new PowerSource(4, 20)),
            Map.entry(ModBlocks.WATER_ARTICHOKE, new PowerSource(4, 20)),
            // Demon Heart, 100, 2
            Map.entry(Blocks.DRAGON_EGG, new PowerSource(250, 1))
            // Infinity egg, 6000, 1
    );

    private Map<Block, PowerSource> currentSources;

    public PowerSources() {
        this.currentSources = new HashMap<>();
    }

    public void addBlock(Block block) {
        if (sources.containsKey(block)) {
            if (currentSources.containsKey(block)) {
                currentSources.get(block).count += 1;
            } else {
                currentSources.put(block, sources.get(block));
                currentSources.get(block).count = 1;
            }
        }
    }

    public int getPower() {
        int power = 0;
        for (Block block : currentSources.keySet()) {
            power += currentSources.get(block).getPower();
        }
        return power;
    }
}
