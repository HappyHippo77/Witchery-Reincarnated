package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class Pentacle extends Block {
    public static final DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

    public Pentacle(Settings settings) {
        super(settings);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(!this.canBlockStay(world, pos.getX(), pos.getY(), pos.getZ())) {
            if(!world.isClient) {
                Block.dropStacks(state, world, pos);
                world.breakBlock(pos, false);
            }
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        Material material = world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial();
        return world.getBlockState(new BlockPos(x, y, z).down()).getBlock() == ModBlocks.ALTAR;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == ModBlocks.ALTAR;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(3 / 16f, 0, 3 / 16f, 13 / 16f, 1 / 16f, 13 / 16f);
    }
}
