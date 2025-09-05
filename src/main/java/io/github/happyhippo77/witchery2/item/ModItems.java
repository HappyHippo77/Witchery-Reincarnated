package io.github.happyhippo77.witchery2.item;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.items.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FUME_FILTER = new Item(new FabricItemSettings());

    public static final Arthana ARTHANA = new Arthana(ToolMaterials.GOLD, 3, -2.4f, new FabricItemSettings().maxDamage(ToolMaterials.IRON.getDurability()));
    public static final Pentacle PENTACLE = new Pentacle(new FabricItemSettings());

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

    public static final Mutandis MUTANDIS = new Mutandis(new FabricItemSettings());
    public static final Item CLAY_JAR = new Item(new FabricItemSettings());
    public static final Item SOFT_CLAY_JAR = new Item(new FabricItemSettings());
    public static final Item FOUL_FUME = new Item(new FabricItemSettings());
    public static final Item EXHALE_OF_THE_HORNED_ONE = new Item(new FabricItemSettings());
    public static final Item BREATH_OF_THE_GODDESS = new Item(new FabricItemSettings());
    public static final Item HINT_OF_REBIRTH = new Item(new FabricItemSettings());
    public static final Item WHIFF_OF_MAGIC = new Item(new FabricItemSettings());
    public static final Item ODOUR_OF_PURITY = new Item(new FabricItemSettings());
    public static final Item REEK_OF_MISFORTUNE = new Item(new FabricItemSettings());
    public static final Item WOOD_ASH = new Item(new FabricItemSettings());
    public static final Item QUICKLIME = new Item(new FabricItemSettings());
    public static final Earmuffs EARMUFFS = new Earmuffs(new FabricItemSettings());

    public static final Item GYPSUM = new Item(new FabricItemSettings());
    public static final Item OIL_OF_VITRIOL = new Item(new FabricItemSettings());
    public static final Item TEAR_OF_THE_GODDESS = new Item(new FabricItemSettings());
    public static final Item DIAMOND_VAPOR = new Item(new FabricItemSettings());
    public static final Item REFINED_EVIL = new Item(new FabricItemSettings());
    public static final Item ENDER_DEW = new Item(new FabricItemSettings());
    public static final Item DEMONIC_BLOOD = new Item(new FabricItemSettings());

    public static final Item KOBOLDITE_DUST = new Item(new FabricItemSettings());
    public static final Item KOBOLDITE_NUGGET = new Item(new FabricItemSettings());
    public static final Item KOBOLDITE_INGOT = new Item(new FabricItemSettings());
    public static final RedstoneSoup REDSTONE_SOUP = new RedstoneSoup(new FabricItemSettings());

    public static final Brew BREW = new Brew(new FabricItemSettings().maxCount(8));
    public static final ProjectileBrew PROJECTILE_BREW = new ProjectileBrew(new FabricItemSettings().maxCount(8));

    public static final Item ROWAN_BERRIES = new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(1).saturationModifier(0.3f).build()));
    public static final Item ROWAN_DOOR_KEY = new Item(new FabricItemSettings());
    public static final SignItem ROWAN_SIGN = new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.ROWAN_SIGN, ModBlocks.ROWAN_WALL_SIGN);
    public static final SignItem ALDER_SIGN = new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.ALDER_SIGN, ModBlocks.ALDER_WALL_SIGN);
    public static final SignItem HAWTHORN_SIGN = new SignItem(new FabricItemSettings().maxCount(16), ModBlocks.HAWTHORN_SIGN, ModBlocks.HAWTHORN_WALL_SIGN);

    public static final Item ATTUNED_STONE = new Item(new FabricItemSettings());
    public static final Item DEMON_HEART = new Item(new FabricItemSettings());

    public static final SpawnEggItem MANDRAKE_SPAWN_EGG = new SpawnEggItem(ModEntities.MANDRAKE, 0x7A54300, 0x4C070, new FabricItemSettings());

    public static void registerAllItems() {
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "fume_filter"), FUME_FILTER);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "arthana"), ARTHANA);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "pentacle"), PENTACLE);

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

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mutandis"), MUTANDIS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "clay_jar"), CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "soft_clay_jar"), SOFT_CLAY_JAR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "foul_fume"), FOUL_FUME);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "breath_of_the_goddess"), BREATH_OF_THE_GODDESS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hint_of_rebirth"), HINT_OF_REBIRTH);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "whiff_of_magic"), WHIFF_OF_MAGIC);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "odour_of_purity"), ODOUR_OF_PURITY);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "reek_of_misfortune"), REEK_OF_MISFORTUNE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "exhale_of_the_horned_one"), EXHALE_OF_THE_HORNED_ONE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "wood_ash"), WOOD_ASH);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "quicklime"), QUICKLIME);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "earmuffs"), EARMUFFS);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "gypsum"), GYPSUM);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "oil_of_vitriol"), OIL_OF_VITRIOL);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "tear_of_the_goddess"), TEAR_OF_THE_GODDESS);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "diamond_vapor"), DIAMOND_VAPOR);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "refined_evil"), REFINED_EVIL);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "ender_dew"), ENDER_DEW);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "demonic_blood"), DEMONIC_BLOOD);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "koboldite_dust"), KOBOLDITE_DUST);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "koboldite_nugget"), KOBOLDITE_NUGGET);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "koboldite_ingot"), KOBOLDITE_INGOT);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "redstone_soup"), REDSTONE_SOUP);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "brew"), BREW);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "projectile_brew"), PROJECTILE_BREW);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_berries"), ROWAN_BERRIES);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_door_key"), ROWAN_DOOR_KEY);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_sign"), ROWAN_SIGN);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "alder_sign"), ALDER_SIGN);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "hawthorn_sign"), HAWTHORN_SIGN);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "attuned_stone"), ATTUNED_STONE);
        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "demon_heart"), DEMON_HEART);

        Registry.register(Registries.ITEM, new Identifier(Witchery2.MOD_ID, "mandrake_spawn_egg"), MANDRAKE_SPAWN_EGG);
    }
}
