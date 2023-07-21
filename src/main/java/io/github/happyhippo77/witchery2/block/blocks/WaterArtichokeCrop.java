package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class WaterArtichokeCrop extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 4);

    public WaterArtichokeCrop(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos);
        FluidState fluidState2 = world.getFluidState(pos.up());
        return fluidState.getFluid() == Fluids.WATER && fluidState2.getFluid() == Fluids.EMPTY;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.WATER_ARTICHOKE_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
