package io.github.happyhippo77.witchery2.mixin;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
import io.github.happyhippo77.witchery2.util.ServerWorldVariables;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@Mixin(ServerWorld.class)
public class ServerWorldMixin implements ServerWorldVariables {
    @Unique
    private List<AltarEntity> altars = new ArrayList<>();
    @Unique
    private List<PoweredBlockEntity> poweredBlocks = new ArrayList<>();

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
