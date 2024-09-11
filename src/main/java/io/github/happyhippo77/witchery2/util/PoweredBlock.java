package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PoweredBlock {
    default int getPower(AltarEntity e) {
        if (e != null) {
            return e.getPower();
        }
        else {
            return 0;
        }
    }

    default void takePower(AltarEntity e, int power) {
        if (e != null) {
            e.decrementPower(power);
        }
    }

    default AltarEntity searchForAltars(World world, BlockPos center) {
        return null;
    }
}
