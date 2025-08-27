package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class PoweredBlockEntity extends BlockEntity {
    public BlockPos altarPosition = null;

    public PoweredBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        if (world != null && !world.isClient) {
            if (!((ServerWorldVariables)world).getPoweredBlocks().contains(this)) {
                ((ServerWorldVariables)world).addPoweredBlock(this);
            }
        }
    }

    public AltarEntity getAltar() {
        if (world != null && !world.isClient && this.altarPosition != null) {
            return (AltarEntity) world.getBlockEntity(this.altarPosition);
        }
        return null;
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
                    this.altarPosition = nearestAltar.getPos();
                } else {
                    this.altarPosition = null;
                }
            } else {
                this.altarPosition = null;
            }

            markDirty();
            world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
        }
    }

    public static void recheckAltars(World world) {
        if (world != null && !world.isClient) {
            for (PoweredBlockEntity poweredBlock : ((ServerWorldVariables)world).getPoweredBlocks()) {
                poweredBlock.searchForAltars();
            }
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        boolean hasAltar = nbt.getBoolean("hasAltar");
        if (hasAltar) {
            int[] pos = nbt.getIntArray("altarPos");
            this.altarPosition = new BlockPos(pos[0], pos[1], pos[2]);
            markDirty();
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        nbt.putBoolean("hasAltar", this.altarPosition != null);
        if (this.altarPosition != null) {
            nbt.putIntArray("altarPos", new int[]{this.altarPosition.getX(), this.altarPosition.getY(), this.altarPosition.getZ()});
        }
    }
}
