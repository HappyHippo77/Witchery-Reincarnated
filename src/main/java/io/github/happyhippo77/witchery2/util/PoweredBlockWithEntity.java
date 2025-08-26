package io.github.happyhippo77.witchery2.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class PoweredBlockWithEntity extends BlockWithEntity {
    protected PoweredBlockWithEntity(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        if (!world.isClient) {
            PoweredBlockEntity entity = (PoweredBlockEntity) world.getBlockEntity(pos);
            entity.searchForAltars();
        }
    }
}
