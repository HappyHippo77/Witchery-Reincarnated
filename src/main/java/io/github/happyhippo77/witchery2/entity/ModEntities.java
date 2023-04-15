package io.github.happyhippo77.witchery2.entity;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.entity.entities.MandrakeEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MandrakeEntity> MANDRAKE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Witchery2.MOD_ID, "mandrake"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MandrakeEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.9f)).build());
}
