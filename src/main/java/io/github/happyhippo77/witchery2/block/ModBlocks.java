package io.github.happyhippo77.witchery2.block;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final WitchsCauldron WITCHS_CAULDRON = new WitchsCauldron(FabricBlockSettings.of(Material.METAL).strength(2.0f).nonOpaque());
    public static final WitchsOven WITCHS_OVEN = new WitchsOven(FabricBlockSettings.of(Material.METAL).strength(3.5f).nonOpaque());
    public static final MandrakeCrop MANDRAKE = new MandrakeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WaterArtichokeCrop WATER_ARTICHOKE = new WaterArtichokeCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final BelladonnaCrop BELLADONNA = new BelladonnaCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final SnowbellCrop SNOWBELL = new SnowbellCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final WolfsbaneCrop WOLFSBANE = new WolfsbaneCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));
    public static final GarlicCrop GARLIC = new GarlicCrop(FabricBlockSettings.copyOf(Blocks.WHEAT));

    public static void registerAllBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), WITCHS_CAULDRON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_oven"), WITCHS_OVEN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "mandrake"), MANDRAKE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "water_artichoke"), WATER_ARTICHOKE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "belladonna"), BELLADONNA);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "snowbell"), SNOWBELL);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "wolfsbane"), WOLFSBANE);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "garlic"), GARLIC);
    }
}
