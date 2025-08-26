package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;

import java.util.List;

public interface ServerWorldVariables {
    List<AltarEntity> getAltars();
    void addAltar(AltarEntity altar);
    void removeAltar(AltarEntity altar);
    List<PoweredBlockEntity> getPoweredBlocks();
    void addPoweredBlock(PoweredBlockEntity block);
    void removePoweredBlock(PoweredBlockEntity block);
}
