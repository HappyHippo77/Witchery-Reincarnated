package io.github.happyhippo77.witchery2.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final AnointingPaste ANOINTING_PASTE = new AnointingPaste(new FabricItemSettings());
    public static final BelladonnaSeeds BELLADONNA_SEEDS = new BelladonnaSeeds(new FabricItemSettings());
    public static final MandrakeSeeds MANDRAKE_SEEDS = new MandrakeSeeds(ModBlocks.MANDRAKE, new FabricItemSettings());
    public static final MandrakeRoot MANDRAKE_ROOT = new MandrakeRoot(new FabricItemSettings());
    public static final WaterArtichokeSeeds WATER_ARTICHOKE_SEEDS = new WaterArtichokeSeeds(new FabricItemSettings());
    public static final SnowbellSeeds SNOWBELL_SEEDS = new SnowbellSeeds(new FabricItemSettings());
    public static final WolfsbaneSeeds WOLFSBANE_SEEDS = new WolfsbaneSeeds(new FabricItemSettings());
    public static final Garlic GARLIC = new Garlic(new FabricItemSettings());
    public static final ExhaleOfTheHornedOne EXHALE_OF_THE_HORNED_ONE = new ExhaleOfTheHornedOne(new FabricItemSettings());
    public static final Mutandis MUTANDIS = new Mutandis(new FabricItemSettings());
    public static final ClayJar CLAY_JAR = new ClayJar(new FabricItemSettings());
    public static final SoftClayJar SOFT_CLAY_JAR = new SoftClayJar(new FabricItemSettings());
    public static final WoodAsh WOOD_ASH = new WoodAsh(new FabricItemSettings());
    public static final Earmuffs EARMUFFS = new Earmuffs(new FabricItemSettings());

    public static final Item MANDRAKE_SPAWN_EGG = new SpawnEggItem(ModEntities.MANDRAKE, 0x7A54300, 0x4C070, new FabricItemSettings());
    public static void registerAllItems() {
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "anointing_paste"), ANOINTING_PASTE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "belladonna_seeds"), BELLADONNA_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_seeds"), MANDRAKE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_root"), MANDRAKE_ROOT);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "water_artichoke_seeds"), WATER_ARTICHOKE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "snowbell_seeds"), SNOWBELL_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wolfsbane_seeds"), WOLFSBANE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "garlic"), GARLIC);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "exhale_of_the_horned_one"), EXHALE_OF_THE_HORNED_ONE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mutandis"), MUTANDIS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "clay_jar"), CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "soft_clay_jar"), SOFT_CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wood_ash"), WOOD_ASH);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "earmuffs"), EARMUFFS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_spawn_egg"), MANDRAKE_SPAWN_EGG);
    }
}
