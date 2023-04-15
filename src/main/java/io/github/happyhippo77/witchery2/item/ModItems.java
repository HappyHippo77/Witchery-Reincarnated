package io.github.happyhippo77.witchery2.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final AnointingPaste ANOINTING_PASTE = new AnointingPaste(new FabricItemSettings());
    public static final AliasedBlockItem BELLADONNA_SEEDS = new AliasedBlockItem(ModBlocks.BELLADONNA, new FabricItemSettings());
    public static final AliasedBlockItem MANDRAKE_SEEDS = new AliasedBlockItem(ModBlocks.MANDRAKE, new FabricItemSettings());
    public static final PlaceableOnWaterItem WATER_ARTICHOKE_SEEDS = new PlaceableOnWaterItem(ModBlocks.WATER_ARTICHOKE, new FabricItemSettings());
    public static final AliasedBlockItem SNOWBELL_SEEDS = new AliasedBlockItem(ModBlocks.SNOWBELL, new FabricItemSettings());
    public static final AliasedBlockItem WOLFSBANE_SEEDS = new AliasedBlockItem(ModBlocks.WOLFSBANE, new FabricItemSettings());
    public static final AliasedBlockItem GARLIC = new AliasedBlockItem(ModBlocks.GARLIC, new FabricItemSettings());

    public static final Item BELLADONNA_FLOWER = new Item(new FabricItemSettings());
    public static final Item MANDRAKE_ROOT = new Item(new FabricItemSettings());
    public static final Item WATER_ARTICHOKE_GLOBE = new Item(new FabricItemSettings());
    public static final Item ICY_NEEDLE = new Item(new FabricItemSettings());
    public static final Item WOLFSBANE = new Item(new FabricItemSettings());

    public static final Item EXHALE_OF_THE_HORNED_ONE = new Item(new FabricItemSettings());
    public static final Mutandis MUTANDIS = new Mutandis(new FabricItemSettings());
    public static final Item CLAY_JAR = new Item(new FabricItemSettings());
    public static final Item SOFT_CLAY_JAR = new Item(new FabricItemSettings());
    public static final Item WOOD_ASH = new Item(new FabricItemSettings());
    public static final Item QUICKLIME = new Item(new FabricItemSettings());
    public static final Earmuffs EARMUFFS = new Earmuffs(new FabricItemSettings());

    public static final Item MANDRAKE_SPAWN_EGG = new SpawnEggItem(ModEntities.MANDRAKE, 0x7A54300, 0x4C070, new FabricItemSettings());

    public static void registerAllItems() {
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "anointing_paste"), ANOINTING_PASTE);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "belladonna_seeds"), BELLADONNA_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_seeds"), MANDRAKE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "water_artichoke_seeds"), WATER_ARTICHOKE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "snowbell_seeds"), SNOWBELL_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wolfsbane_seeds"), WOLFSBANE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "garlic"), GARLIC);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "belladonna_flower"), BELLADONNA_FLOWER);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_root"), MANDRAKE_ROOT);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "water_artichoke_globe"), WATER_ARTICHOKE_GLOBE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "icy_needle"), ICY_NEEDLE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wolfsbane"), WOLFSBANE);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "exhale_of_the_horned_one"), EXHALE_OF_THE_HORNED_ONE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mutandis"), MUTANDIS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "clay_jar"), CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "soft_clay_jar"), SOFT_CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wood_ash"), WOOD_ASH);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "quicklime"), QUICKLIME);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "earmuffs"), EARMUFFS);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_spawn_egg"), MANDRAKE_SPAWN_EGG);
    }
}
