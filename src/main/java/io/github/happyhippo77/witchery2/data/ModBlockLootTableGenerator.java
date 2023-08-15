package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ExplosionDecayLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.state.property.Property;

import javax.annotation.Nullable;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.WITCHS_CAULDRON, basicDrop(ModBlocks.WITCHS_CAULDRON));
        addDrop(ModBlocks.WITCHS_OVEN, basicDrop(ModBlocks.WITCHS_OVEN));
        addDrop(ModBlocks.FUME_FUNNEL, basicDrop(ModBlocks.FUME_FUNNEL));
        addDrop(ModBlocks.FILTERED_FUME_FUNNEL, basicDrop(ModBlocks.FILTERED_FUME_FUNNEL));
        addDrop(ModBlocks.ALTAR, basicDrop(ModBlocks.ALTAR));
        addDrop(ModBlocks.CANDELABRA, basicDrop(ModBlocks.CANDELABRA));
        addDrop(ModBlocks.CHALICE, basicDrop(ModBlocks.CHALICE));
        addDrop(ModBlocks.FILLED_CHALICE, basicDrop(ModBlocks.FILLED_CHALICE));
        addDrop(ModBlocks.PENTACLE, basicDrop(ModBlocks.PENTACLE));

        addTreeDrops();

        addDrop(ModBlocks.BELLADONNA, cropDrop(ModBlocks.BELLADONNA, ModItems.BELLADONNA_FLOWER, ModItems.BELLADONNA_SEEDS, ModBlocks.BELLADONNA.getMaxAge(), null));
        addDrop(ModBlocks.WATER_ARTICHOKE, cropDrop(ModBlocks.WATER_ARTICHOKE, ModItems.WATER_ARTICHOKE_GLOBE, ModItems.WATER_ARTICHOKE_SEEDS, ModBlocks.WATER_ARTICHOKE.getMaxAge(), null));
        addDrop(ModBlocks.SNOWBELL, cropDrop(ModBlocks.SNOWBELL, Items.SNOWBALL, ModItems.SNOWBELL_SEEDS, ModBlocks.SNOWBELL.getMaxAge(), ModItems.ICY_NEEDLE));
        addDrop(ModBlocks.GARLIC, cropDrop(ModBlocks.GARLIC, ModItems.GARLIC, ModItems.GARLIC, ModBlocks.GARLIC.getMaxAge(), null));
        addDrop(ModBlocks.WOLFSBANE, cropDrop(ModBlocks.WOLFSBANE, ModItems.WOLFSBANE, ModItems.WOLFSBANE_SEEDS, ModBlocks.WOLFSBANE.getMaxAge(), null));
        addDrop(ModBlocks.EMBER_MOSS, dropsWithShears(ModBlocks.EMBER_MOSS));
        addDrop(ModBlocks.GLINT_WEED, basicDrop(ModBlocks.GLINT_WEED));
        addDrop(ModBlocks.SPANISH_MOSS, dropsWithShears(ModBlocks.SPANISH_MOSS));
    }

    private void addTreeDrops() {
        addDrop(ModBlocks.ROWAN_LOG, basicDrop(ModBlocks.ROWAN_LOG));
        addDrop(ModBlocks.ROWAN_WOOD, basicDrop(ModBlocks.ROWAN_WOOD));
        addDrop(ModBlocks.STRIPPED_ROWAN_LOG, basicDrop(ModBlocks.STRIPPED_ROWAN_LOG));
        addDrop(ModBlocks.STRIPPED_ROWAN_WOOD, basicDrop(ModBlocks.STRIPPED_ROWAN_WOOD));
        addDrop(ModBlocks.ROWAN_PLANKS, basicDrop(ModBlocks.ROWAN_PLANKS));
        addDrop(ModBlocks.ROWAN_SAPLING, basicDrop(ModBlocks.ROWAN_SAPLING));
        addDrop(ModBlocks.POTTED_ROWAN_SAPLING, pottedPlantDrop(ModBlocks.ROWAN_SAPLING));
        addDrop(ModBlocks.ROWAN_LEAVES, leafDrop(ModBlocks.ROWAN_LEAVES, ModBlocks.ROWAN_SAPLING, ModItems.ROWAN_BERRIES));
        addDrop(ModBlocks.ROWAN_STAIRS, basicDrop(ModBlocks.ROWAN_STAIRS));
        addDrop(ModBlocks.ROWAN_SLAB, slabDrop(ModBlocks.ROWAN_SLAB, ModBlocks.ROWAN_SLAB));
        addDrop(ModBlocks.ROWAN_FENCE, basicDrop(ModBlocks.ROWAN_FENCE));
        addDrop(ModBlocks.ROWAN_FENCE_GATE, basicDrop(ModBlocks.ROWAN_FENCE_GATE));
        addDrop(ModBlocks.ROWAN_PRESSURE_PLATE, basicDrop(ModBlocks.ROWAN_PRESSURE_PLATE));
        addDrop(ModBlocks.ROWAN_BUTTON, basicDrop(ModBlocks.ROWAN_BUTTON));
        addDrop(ModBlocks.ROWAN_SIGN, basicDrop(ModItems.ROWAN_SIGN));

        addDrop(ModBlocks.ALDER_LOG, basicDrop(ModBlocks.ALDER_LOG));
        addDrop(ModBlocks.ALDER_WOOD, basicDrop(ModBlocks.ALDER_WOOD));
        addDrop(ModBlocks.STRIPPED_ALDER_LOG, basicDrop(ModBlocks.STRIPPED_ALDER_LOG));
        addDrop(ModBlocks.STRIPPED_ALDER_WOOD, basicDrop(ModBlocks.STRIPPED_ALDER_WOOD));
        addDrop(ModBlocks.ALDER_PLANKS, basicDrop(ModBlocks.ALDER_PLANKS));
        addDrop(ModBlocks.ALDER_SAPLING, basicDrop(ModBlocks.ALDER_SAPLING));
        addDrop(ModBlocks.POTTED_ALDER_SAPLING, pottedPlantDrop(ModBlocks.ALDER_SAPLING));
        addDrop(ModBlocks.ALDER_LEAVES, leafDrop(ModBlocks.ALDER_LEAVES, ModBlocks.ALDER_SAPLING, null));
        addDrop(ModBlocks.ALDER_STAIRS, basicDrop(ModBlocks.ALDER_STAIRS));
        addDrop(ModBlocks.ALDER_SLAB, slabDrop(ModBlocks.ALDER_SLAB, ModBlocks.ALDER_SLAB));
        addDrop(ModBlocks.ALDER_FENCE, basicDrop(ModBlocks.ALDER_FENCE));
        addDrop(ModBlocks.ALDER_FENCE_GATE, basicDrop(ModBlocks.ALDER_FENCE_GATE));
        addDrop(ModBlocks.ALDER_DOOR, doorDrop(ModBlocks.ALDER_DOOR));
        addDrop(ModBlocks.ALDER_TRAPDOOR, basicDrop(ModBlocks.ALDER_TRAPDOOR));
        addDrop(ModBlocks.ALDER_PRESSURE_PLATE, basicDrop(ModBlocks.ALDER_PRESSURE_PLATE));
        addDrop(ModBlocks.ALDER_BUTTON, basicDrop(ModBlocks.ALDER_BUTTON));
        addDrop(ModBlocks.ALDER_SIGN, basicDrop(ModItems.ALDER_SIGN));

        addDrop(ModBlocks.HAWTHORN_LOG, basicDrop(ModBlocks.HAWTHORN_LOG));
        addDrop(ModBlocks.HAWTHORN_WOOD, basicDrop(ModBlocks.HAWTHORN_WOOD));
        addDrop(ModBlocks.STRIPPED_HAWTHORN_LOG, basicDrop(ModBlocks.STRIPPED_HAWTHORN_LOG));
        addDrop(ModBlocks.STRIPPED_HAWTHORN_WOOD, basicDrop(ModBlocks.STRIPPED_HAWTHORN_WOOD));
        addDrop(ModBlocks.HAWTHORN_PLANKS, basicDrop(ModBlocks.HAWTHORN_PLANKS));
        addDrop(ModBlocks.HAWTHORN_SAPLING, basicDrop(ModBlocks.HAWTHORN_SAPLING));
        addDrop(ModBlocks.POTTED_HAWTHORN_SAPLING, pottedPlantDrop(ModBlocks.HAWTHORN_SAPLING));
        addDrop(ModBlocks.HAWTHORN_LEAVES, leafDrop(ModBlocks.HAWTHORN_LEAVES, ModBlocks.HAWTHORN_SAPLING, null));
        addDrop(ModBlocks.HAWTHORN_STAIRS, basicDrop(ModBlocks.HAWTHORN_STAIRS));
        addDrop(ModBlocks.HAWTHORN_SLAB, slabDrop(ModBlocks.HAWTHORN_SLAB, ModBlocks.HAWTHORN_SLAB));
        addDrop(ModBlocks.HAWTHORN_FENCE, basicDrop(ModBlocks.HAWTHORN_FENCE));
        addDrop(ModBlocks.HAWTHORN_FENCE_GATE, basicDrop(ModBlocks.HAWTHORN_FENCE_GATE));
        addDrop(ModBlocks.HAWTHORN_DOOR, doorDrop(ModBlocks.HAWTHORN_DOOR));
        addDrop(ModBlocks.HAWTHORN_TRAPDOOR, basicDrop(ModBlocks.HAWTHORN_TRAPDOOR));
        addDrop(ModBlocks.HAWTHORN_PRESSURE_PLATE, basicDrop(ModBlocks.HAWTHORN_PRESSURE_PLATE));
        addDrop(ModBlocks.HAWTHORN_BUTTON, basicDrop(ModBlocks.HAWTHORN_BUTTON));
        addDrop(ModBlocks.HAWTHORN_SIGN, basicDrop(ModItems.HAWTHORN_SIGN));

        addDrop(ModBlocks.OAK_STOCKADE, basicDrop(ModBlocks.OAK_STOCKADE));
        addDrop(ModBlocks.SPRUCE_STOCKADE, basicDrop(ModBlocks.SPRUCE_STOCKADE));
        addDrop(ModBlocks.BIRCH_STOCKADE, basicDrop(ModBlocks.BIRCH_STOCKADE));
        addDrop(ModBlocks.JUNGLE_STOCKADE, basicDrop(ModBlocks.JUNGLE_STOCKADE));
        addDrop(ModBlocks.ACACIA_STOCKADE, basicDrop(ModBlocks.ACACIA_STOCKADE));
        addDrop(ModBlocks.DARK_OAK_STOCKADE, basicDrop(ModBlocks.DARK_OAK_STOCKADE));
        addDrop(ModBlocks.MANGROVE_STOCKADE, basicDrop(ModBlocks.MANGROVE_STOCKADE));
        addDrop(ModBlocks.ICE_STOCKADE, basicDrop(ModBlocks.ICE_STOCKADE));
        addDrop(ModBlocks.ROWAN_STOCKADE, basicDrop(ModBlocks.ROWAN_STOCKADE));
        addDrop(ModBlocks.ALDER_STOCKADE, basicDrop(ModBlocks.ALDER_STOCKADE));
        addDrop(ModBlocks.HAWTHORN_STOCKADE, basicDrop(ModBlocks.HAWTHORN_STOCKADE));
    }

    private LootTable.Builder basicDrop(ItemConvertible item) {
        return new LootTable.Builder().pool(new LootPool.Builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(item))
                .conditionally(SurvivesExplosionLootCondition.builder())
        );
    }

    private LootTable.Builder leafDrop(ItemConvertible leaf, ItemConvertible sapling, @Nullable ItemConvertible fruit) {
        LootTable.Builder builder = LootTable.builder().pool(
                LootPool.builder().with(
                        AlternativeEntry.builder(
                                ItemEntry.builder(leaf).conditionally(
                                        AlternativeLootCondition.builder(
                                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(Items.SHEARS)),
                                                MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))
                                        )
                                ),
                                ItemEntry.builder(sapling).conditionally(
                                        SurvivesExplosionLootCondition.builder()
                                ).conditionally(
                                        TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.05f, 0.0625f, 0.083333336f, 0.1f)
                                )
                        )
                )
        );

        if (fruit != null) {
            builder.pool(
                    LootPool.builder().with(
                            ItemEntry.builder(fruit).conditionally(
                                    SurvivesExplosionLootCondition.builder()
                            ).conditionally(
                                    TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f)
                            )
                    )
            );
        }

        return builder;
    }

    private LootTable.Builder cropDrop(Block cropBlock, ItemConvertible crop, ItemConvertible seeds, int maxAge, @Nullable ItemConvertible extra) {
        StatePredicate.Builder maxAgePredicate = StatePredicate.Builder.create().exactMatch((Property<Integer>) cropBlock.getStateManager().getProperty("age"), maxAge);
        LootTable.Builder builder = LootTable.builder().pool(
                LootPool.builder().with(
                        AlternativeEntry.builder().alternatively(
                                ItemEntry.builder(crop).conditionally(
                                        BlockStatePropertyLootCondition.builder(cropBlock).properties(
                                                maxAgePredicate
                                        )
                                )
                        ).alternatively(
                                ItemEntry.builder(seeds)
                        )
                )
        ).pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(3)).with(
                        ItemEntry.builder(seeds).conditionally(
                                RandomChanceLootCondition.builder(0.4667f)
                        )
                ).conditionally(
                        BlockStatePropertyLootCondition.builder(cropBlock).properties(
                                maxAgePredicate
                        )
                )
        );

        if (extra != null) {
            builder.pool(
                    LootPool.builder().with(
                            ItemEntry.builder(extra).conditionally(
                                    RandomChanceLootCondition.builder(0.2f)
                            ).conditionally(
                                    BlockStatePropertyLootCondition.builder(cropBlock).properties(
                                            maxAgePredicate
                                    )
                            )
                    )
            );
        }
        return builder;
    }

    private LootTable.Builder slabDrop(Block block, ItemConvertible drop) {
        return new LootTable.Builder().pool(
                LootPool.builder().with(
                        ItemEntry.builder(drop).apply(
                                SetCountLootFunction.builder(ConstantLootNumberProvider.create(2f)).conditionally(
                                        BlockStatePropertyLootCondition.builder(block).properties(
                                                StatePredicate.Builder.create().exactMatch(block.getStateManager().getProperty("type"), "double")
                                        )
                                )
                        ).apply(
                                ExplosionDecayLootFunction.builder()
                        )
                )
        );
    }

    private LootTable.Builder doorDrop(Block block) {
        return new LootTable.Builder().pool(
                LootPool.builder().with(
                        ItemEntry.builder(block).conditionally(
                                BlockStatePropertyLootCondition.builder(block).properties(
                                        StatePredicate.Builder.create().exactMatch(block.getStateManager().getProperty("half"), "lower")
                                )
                        )
                ).conditionally(
                        SurvivesExplosionLootCondition.builder()
                )
        );
    }

    private LootTable.Builder pottedPlantDrop(ItemConvertible plant) {
        return new LootTable.Builder().pool(
                new LootPool.Builder().with(
                        ItemEntry.builder(Items.FLOWER_POT)
                ).conditionally(
                        SurvivesExplosionLootCondition.builder()
                )
        ).pool(
                new LootPool.Builder().with(
                        ItemEntry.builder(plant)
                ).conditionally(
                        SurvivesExplosionLootCondition.builder()
                )
        );
    }
}
