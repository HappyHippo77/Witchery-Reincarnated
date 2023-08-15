package io.github.happyhippo77.witchery2.block.entity;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsOvenEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<WitchsCauldronEntity> WITCHS_CAULDRON_ENTITY;
    public static BlockEntityType<WitchsOvenEntity> WITCHS_OVEN_ENTITY;
    public static BlockEntityType<AltarEntity> ALTAR_ENTITY;

    public static void registerAllBlockEntities() {
        WITCHS_CAULDRON_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Witchery2.MOD_ID, "witchs_cauldron"), FabricBlockEntityTypeBuilder.create(WitchsCauldronEntity::new, ModBlocks.WITCHS_CAULDRON).build(null));
        WITCHS_OVEN_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Witchery2.MOD_ID, "witchs_oven"), FabricBlockEntityTypeBuilder.create(WitchsOvenEntity::new, ModBlocks.WITCHS_OVEN).build(null));
        ALTAR_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Witchery2.MOD_ID, "altar"), FabricBlockEntityTypeBuilder.create(AltarEntity::new, ModBlocks.ALTAR).build(null));
    }
}
