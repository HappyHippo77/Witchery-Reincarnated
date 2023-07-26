package io.github.happyhippo77.witchery2.entity.entities;

import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.brewing.DispersalType;
import io.github.happyhippo77.witchery2.util.brewing.Effect;
import io.github.happyhippo77.witchery2.util.brewing.Effects;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.List;
import java.util.Objects;

public class BrewEntity extends ThrownItemEntity {
    public BrewEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public BrewEntity(World world, double x, double y, double z) {
        super(ModEntities.BREW_ENTITY, x, y, z, world);
    }

    public BrewEntity(World world, LivingEntity livingEntity) {
        super(ModEntities.BREW_ENTITY, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PROJECTILE_BREW;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        BlockPos pos = getBlockPos();
        NbtCompound nbt = this.getStack().getNbt();
        for (NbtElement element : nbt.getList("effects", NbtElement.COMPOUND_TYPE)) {
            Effect effect = Effects.getFromNbt((NbtCompound) element);
            if (nbt.getString("dispersal").equals(DispersalType.INSTANT.toString())) {
                effect.applyBlock(world, pos);

                int radius = 3 + nbt.getInt("extent");
                Box area = new Box(
                        pos.getX() - radius, pos.getY() - radius, pos.getZ() - radius,
                        pos.getX() + radius, pos.getY() + radius, pos.getZ() + radius
                );
                List<Entity> entities = this.world.getOtherEntities(this, area);

                for (Entity entity : entities) {
                    double distanceSq = hitResult.squaredDistanceTo(entity);
                    double scaling = 1 - Math.sqrt(distanceSq) / radius;
                    effect.setPowerScaling(scaling);
                    effect.setDurationScaling(scaling);
                    effect.applyEntity(world, entity);
                }

            } else if (nbt.getString("dispersal").equals(DispersalType.GAS.toString())) {
                // add gas brew block and set its effect
            } else if (nbt.getString("dispersal").equals(DispersalType.LIQUID.toString())) {
                // add liquid brew block and set its effect
            } else if (nbt.getString("dispersal").equals(DispersalType.TRIGGER.toString())) {
                // replace buttons, levers, wooden doors, and pressure places with "cursed" variants that apply entity effects to those that use them.
                // Do not apply block effects.
            }
        }

        int color = 0xFFFFFF;
        if (nbt != null && nbt.contains("color")) {
            color = nbt.getInt("color");
        }
        if (!world.isClient) {
            this.world.syncWorldEvent(WorldEvents.SPLASH_POTION_SPLASHED, pos, color);
        }
        this.discard();
    }

    @Override
    protected float getGravity() {
        return 0.05f;
    }
}
