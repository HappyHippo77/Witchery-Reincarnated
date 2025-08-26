package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PoweredBlockEntity extends BlockEntity {
    public AltarEntity altar = null;

    public PoweredBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        if (world != null && !world.isClient) {
            if (!((ServerWorldVariables)world).getPoweredBlocks().contains(this)) {
                ((ServerWorldVariables)world).addPoweredBlock(this);
            }
        }
    }

    @Override
    public void markRemoved() {
        super.markRemoved();

        if (world != null && !world.isClient) {
            ((ServerWorldVariables)world).removePoweredBlock(this);
        }
    }

    @Override
    public void cancelRemoval() {
        super.cancelRemoval();

        if (world != null && !world.isClient) {
            if (!((ServerWorldVariables)world).getPoweredBlocks().contains(this)) {
                ((ServerWorldVariables)world).addPoweredBlock(this);
            }
        }
    }

    public void searchForAltars() {
        if (world != null) {
            if (world.isClient) {
                return;
            }

            AltarEntity nearestAltar = null;

            for (AltarEntity altarEntity : ((ServerWorldVariables)world).getAltars()) {
                if (altarEntity.getWorld() == world && !altarEntity.isRemoved() && altarEntity.getCoreEntity() == altarEntity && !altarEntity.joinedAltars.isEmpty()) {
                    if (nearestAltar == null) {
                        nearestAltar = altarEntity;
                    } else {
                        int px = pos.getX();
                        int py = pos.getY();
                        int pz = pos.getZ();
                        int nax = nearestAltar.getPos().getX();
                        int nay = nearestAltar.getPos().getY();
                        int naz = nearestAltar.getPos().getZ();
                        int ax = altarEntity.getPos().getX();
                        int ay = altarEntity.getPos().getY();
                        int az = altarEntity.getPos().getZ();
                        double distanceToAltar = Math.sqrt(Math.abs(Math.pow(px - ax, 2) + Math.pow(py - ay, 2) + Math.pow(pz - az, 2)));
                        double distanceToNearestAltar = Math.sqrt(Math.abs(Math.pow(px - nax, 2) + Math.pow(py - nay, 2) + Math.pow(pz - naz, 2)));
                        if (distanceToAltar < distanceToNearestAltar) {
                            nearestAltar = altarEntity;
                        }
                    }
                }
            }

            if (nearestAltar != null) {
                if (nearestAltar.withinRange(world, pos)) {
                    altar = nearestAltar;
                }
            } else {
                altar = null;
            }
        }
    }

    public void setAltarIfValid(World world, BlockPos pos) {
        AltarEntity altarEntity = (AltarEntity) world.getBlockEntity(pos);
        if (altarEntity != null && altarEntity.getWorld() == world && !altarEntity.isRemoved() && !altarEntity.joinedAltars.isEmpty() && altarEntity.withinRange(world, pos)) {
            altar = altarEntity;
        }
    }
}
