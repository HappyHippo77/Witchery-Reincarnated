package io.github.happyhippo77.witchery2.block;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.blocks.MandrakeCrop;
import io.github.happyhippo77.witchery2.block.blocks.WitchsCauldron;
import io.github.happyhippo77.witchery2.block.blocks.WitchsOven;
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

    public static void registerAllBlocks() {
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), WITCHS_CAULDRON);
        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "witchs_oven"), WITCHS_OVEN);

        Registry.register(Registries.BLOCK, new Identifier(Witchery2.MOD_ID, "mandrake"), MANDRAKE);
    }
}
