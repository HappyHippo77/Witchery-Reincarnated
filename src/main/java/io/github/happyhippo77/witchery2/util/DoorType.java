package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.StringIdentifiable;

import java.util.Arrays;
import java.util.List;

public enum DoorType implements StringIdentifiable {
    OAK,
    SPRUCE,
    BIRCH,
    JUNGLE,
    ACACIA,
    DARK_OAK,
    MANGROVE,
    CRIMSON,
    WARPED,
    ROWAN,
    ALDER,
    HAWTHORN;

    public static Block toBlock(DoorType type) {
        return switch (type) {
            case OAK -> Blocks.OAK_PLANKS;
            case SPRUCE -> Blocks.SPRUCE_PLANKS;
            case BIRCH -> Blocks.BIRCH_PLANKS;
            case JUNGLE -> Blocks.JUNGLE_PLANKS;
            case ACACIA -> Blocks.ACACIA_PLANKS;
            case DARK_OAK -> Blocks.DARK_OAK_PLANKS;
            case MANGROVE -> Blocks.MANGROVE_PLANKS;
            case CRIMSON -> Blocks.CRIMSON_PLANKS;
            case WARPED -> Blocks.WARPED_PLANKS;
            case ROWAN -> ModBlocks.ROWAN_PLANKS;
            case ALDER -> ModBlocks.ALDER_PLANKS;
            case HAWTHORN -> ModBlocks.HAWTHORN_PLANKS;
            default -> null;
        };
    }
    public static DoorType fromBlock(Block block) {
        if (block.equals(Blocks.OAK_PLANKS)) {
            return OAK;
        } else if (block.equals(Blocks.SPRUCE_PLANKS)) {
            return SPRUCE;
        } else if (block.equals(Blocks.BIRCH_PLANKS)) {
            return BIRCH;
        } else if (block.equals(Blocks.JUNGLE_PLANKS)) {
            return JUNGLE;
        } else if (block.equals(Blocks.ACACIA_PLANKS)) {
            return ACACIA;
        } else if (block.equals(Blocks.DARK_OAK_PLANKS)) {
            return DARK_OAK;
        } else if (block.equals(Blocks.MANGROVE_PLANKS)) {
            return MANGROVE;
        } else if (block.equals(Blocks.CRIMSON_PLANKS)) {
            return CRIMSON;
        } else if (block.equals(Blocks.WARPED_PLANKS)) {
            return WARPED;
        } else if (block.equals(ModBlocks.ROWAN_PLANKS)) {
            return ROWAN;
        } else if (block.equals(ModBlocks.ALDER_PLANKS)) {
            return ALDER;
        } else if (block.equals(ModBlocks.HAWTHORN_PLANKS)) {
            return HAWTHORN;
        }
        return ALDER;
    }

    @Override
    public String asString() {
        return switch (this) {
            case OAK -> "oak";
            case SPRUCE -> "spruce";
            case BIRCH -> "birch";
            case JUNGLE -> "jungle";
            case ACACIA -> "acacia";
            case DARK_OAK -> "dark_oak";
            case MANGROVE -> "mangrove";
            case CRIMSON -> "crimson";
            case WARPED -> "warped";
            case ROWAN -> "rowan";
            case ALDER -> "alder";
            case HAWTHORN -> "hawthorn";
        };
    }

    public static List<Block> getBlocks() {
        return Arrays.asList(
                Blocks.OAK_PLANKS,
                Blocks.SPRUCE_PLANKS,
                Blocks.BIRCH_PLANKS,
                Blocks.JUNGLE_PLANKS,
                Blocks.ACACIA_PLANKS,
                Blocks.DARK_OAK_PLANKS,
                Blocks.MANGROVE_PLANKS,
                Blocks.CRIMSON_PLANKS,
                Blocks.WARPED_PLANKS,
                ModBlocks.ROWAN_PLANKS,
                ModBlocks.ALDER_PLANKS,
                ModBlocks.HAWTHORN_PLANKS
                );
    }
}
