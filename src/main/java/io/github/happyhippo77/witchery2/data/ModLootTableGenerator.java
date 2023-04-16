package io.github.happyhippo77.witchery2.data;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootPoolEntryTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    private void addBasicDrop(Block block, ItemConvertible item) {
        addDrop(block,
                new LootTable.Builder().pool(new LootPool.Builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(item))
                        .conditionally(SurvivesExplosionLootCondition.builder().build())
                )
        );
    }

    private void addLeafDrop(Block block, ItemConvertible leaf, ItemConvertible sapling, @Nullable ItemConvertible fruit) {
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

        addDrop(block, builder);
    }

    private void addTreeDrops() {
        addBasicDrop(ModBlocks.ROWAN_LOG, ModBlocks.ROWAN_LOG);
        addBasicDrop(ModBlocks.ROWAN_WOOD, ModBlocks.ROWAN_WOOD);
        addBasicDrop(ModBlocks.STRIPPED_ROWAN_LOG, ModBlocks.STRIPPED_ROWAN_LOG);
        addBasicDrop(ModBlocks.STRIPPED_ROWAN_WOOD, ModBlocks.STRIPPED_ROWAN_WOOD);
        addBasicDrop(ModBlocks.ROWAN_PLANKS, ModBlocks.ROWAN_PLANKS);
        addBasicDrop(ModBlocks.ROWAN_SAPLING, ModBlocks.ROWAN_SAPLING);
        addLeafDrop(ModBlocks.ROWAN_LEAVES, ModBlocks.ROWAN_LEAVES, ModBlocks.ROWAN_SAPLING, ModItems.ROWAN_BERRIES);
    }

    @Override
    public void generate() {
        addTreeDrops();
    }
}
