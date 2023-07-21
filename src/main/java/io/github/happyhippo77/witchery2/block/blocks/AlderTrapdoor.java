package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.util.DoorType;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AlderTrapdoor extends TrapdoorBlock {
    public static final EnumProperty<DoorType> DOOR_TYPE = EnumProperty.of("door_type", DoorType.class);
    public AlderTrapdoor(Settings settings) {
        super(settings, ModBlocks.ALDER_BLOCK_SET);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false).with(HALF, BlockHalf.BOTTOM).with(POWERED, false).with(WATERLOGGED, false).with(DOOR_TYPE, DoorType.ALDER));

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(DOOR_TYPE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Block heldBlock = Blocks.AIR;
        if (player.getInventory().getMainHandStack().getItem() instanceof BlockItem) {
            heldBlock = ((BlockItem) player.getInventory().getMainHandStack().getItem()).getBlock();
        }

        if (DoorType.getBlocks().contains(heldBlock)) {
            world.setBlockState(pos, state.with(DOOR_TYPE, DoorType.fromBlock(heldBlock)));
            return ActionResult.SUCCESS;
        }
        else {
            return super.onUse(state, world, pos, player, hand, hit);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);

        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.north(), this);
        world.updateNeighborsAlways(pos.south(), this);
        world.updateNeighborsAlways(pos.east(), this);
        world.updateNeighborsAlways(pos.west(), this);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(OPEN) ? 15: 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (direction != Direction.UP && direction != Direction.DOWN) {
            return getWeakRedstonePower(state, world, pos, direction);
        }
        return 0;
    }

    // Disable redstone powering
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) {
            return;
        }
        boolean bl = world.method_8479(pos);
        if (bl != state.get(POWERED)) {
            if (state.get(WATERLOGGED).booleanValue()) {
                world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            }
        }
    }
}
