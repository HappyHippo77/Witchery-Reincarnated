package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.state.property.Property;

import javax.annotation.Nullable;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.WITCHS_CAULDRON, basicDrop(ModBlocks.WITCHS_CAULDRON));
        addDrop(ModBlocks.WITCHS_OVEN, basicDrop(ModBlocks.WITCHS_OVEN));

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

    private LootTable.Builder basicDrop(ItemConvertible item) {
        return new LootTable.Builder().pool(new LootPool.Builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .with(ItemEntry.builder(item))
                .conditionally(SurvivesExplosionLootCondition.builder())
        );
    }

    private void addTreeDrops() {
        addDrop(ModBlocks.ROWAN_LOG, basicDrop(ModBlocks.ROWAN_LOG));
        addDrop(ModBlocks.ROWAN_WOOD, basicDrop(ModBlocks.ROWAN_WOOD));
        addDrop(ModBlocks.STRIPPED_ROWAN_LOG, basicDrop(ModBlocks.STRIPPED_ROWAN_LOG));
        addDrop(ModBlocks.STRIPPED_ROWAN_WOOD, basicDrop(ModBlocks.STRIPPED_ROWAN_WOOD));
        addDrop(ModBlocks.ROWAN_PLANKS, basicDrop(ModBlocks.ROWAN_PLANKS));
        addDrop(ModBlocks.ROWAN_SAPLING, basicDrop(ModBlocks.ROWAN_SAPLING));
        addDrop(ModBlocks.ROWAN_LEAVES, leafDrop(ModBlocks.ROWAN_LEAVES, ModBlocks.ROWAN_SAPLING, ModItems.ROWAN_BERRIES));
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
}
