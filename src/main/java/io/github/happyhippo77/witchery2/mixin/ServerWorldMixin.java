package io.github.happyhippo77.witchery2.mixin;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
import io.github.happyhippo77.witchery2.util.ServerWorldVariables;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

@Mixin(ServerWorld.class)
public class ServerWorldMixin implements ServerWorldVariables {
    @Unique
    private final List<AltarEntity> altars = new ArrayList<>();
    @Unique
    private final List<PoweredBlockEntity> poweredBlocks = new ArrayList<>();

    @Override
    public List<AltarEntity> getAltars() {
        return altars;
    }

    @Override
    public void addAltar(AltarEntity altar) {
        altars.add(altar);
    }

    @Override
    public void removeAltar(AltarEntity altar) {
        altars.remove(altar);
    }

    @Override
    public List<PoweredBlockEntity> getPoweredBlocks() {
        return poweredBlocks;
    }

    @Override
    public void addPoweredBlock(PoweredBlockEntity block) {
        poweredBlocks.add(block);
    }

    @Override
    public void removePoweredBlock(PoweredBlockEntity block) {
        poweredBlocks.remove(block);
    }
}
