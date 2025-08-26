package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class FumeFunnel extends Block {
    private final boolean filtered;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty RIGHT = BooleanProperty.of("right");
    public static final BooleanProperty LEFT = BooleanProperty.of("left");
    public static final BooleanProperty CHIMNEY = BooleanProperty.of("chimney");
    public FumeFunnel(boolean filtered, Settings settings) {
        super(settings);
        this.filtered = filtered;
    }

    private List<Object> calculateAttachment(Direction facing, World world, BlockPos pos) {
        boolean right = false;
        boolean left = false;
        boolean chimney = false;
        if (world.getBlockState(pos.offset(facing.rotateYClockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            right = true;
        }
        if (world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            left = true;
        }
        if (world.getBlockState(pos.down()).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            chimney = true;
        }
        return Arrays.asList(right, left, chimney);
    }

    private BlockState getPrimaryOven(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);

        BlockState oven = null;

        if (world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = world.getBlockState(pos.offset(facing.rotateYCounterclockwise()));
        }
        else if (world.getBlockState(pos.offset(facing.rotateYClockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = world.getBlockState(pos.offset(facing.rotateYClockwise()));
        }
        else if (world.getBlockState(pos.down()).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = world.getBlockState(pos.down());
        }

        return oven;
    }

    private BlockPos getPrimaryOvenPos(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);

        BlockPos oven = null;

        if (world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = pos.offset(facing.rotateYCounterclockwise());
        }
        else if (world.getBlockState(pos.offset(facing.rotateYClockwise())).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = pos.offset(facing.rotateYClockwise());
        }
        else if (world.getBlockState(pos.down()).getBlock().equals(ModBlocks.WITCHS_OVEN)) {
            oven = pos.down();
        }

        return oven;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        BlockState state = this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
        List<Object> attachments = calculateAttachment(state.get(FACING), world, pos);
        state = state.with(RIGHT, (boolean)attachments.get(0));
        state = state.with(LEFT, (boolean)attachments.get(1));
        state = state.with(CHIMNEY, (boolean)attachments.get(2));
        return state;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        List<Object> newAttachments = calculateAttachment(state.get(FACING), world, pos);
        if (!state.get(RIGHT).equals(newAttachments.get(0)) || !state.get(LEFT).equals(newAttachments.get(1)) || !state.get(CHIMNEY).equals(newAttachments.get(2))) {
            boolean right = (boolean) newAttachments.get(0);
            boolean left = (boolean) newAttachments.get(1);
            boolean chimney = (boolean) newAttachments.get(2);
            world.setBlockState(pos, state.with(LEFT, left).with(RIGHT, right).with(CHIMNEY, chimney));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        FumeFunnel block = ((FumeFunnel)state.getBlock());
        BlockState oven = getPrimaryOven(state, world, pos);
        if (oven != null) {
            oven.getBlock().onUse(oven, world, getPrimaryOvenPos(state, world, pos), player, hand, hit);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(RIGHT).add(LEFT).add(CHIMNEY);
    }

    public boolean isFiltered() {
        return filtered;
    }

    public double fumeChanceBonus() {
        return filtered ? 0.3 : 0.25;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        boolean chimney = state.get(CHIMNEY);

        if (!chimney) {
            return VoxelShapes.union(
                    VoxelShapes.cuboid(2 / 16f,0 / 16f,2 / 16f, 14 / 16f, 1 / 16f, 14 / 16f),
                    VoxelShapes.cuboid(3 / 16f, 1 / 16f, 3 / 16f, 13 / 16f, 12 / 16f, 13 / 16f),
                    VoxelShapes.cuboid(2 / 16f, 12 / 16f, 2 / 16f, 14 / 16f, 13 / 16f, 14 / 16f),
                    VoxelShapes.cuboid(5 / 16f, 13 / 16f, 5 / 16f, 11 / 16f, 14 / 16f, 11 / 16f)
            );
        }
        else {
            if (direction == Direction.NORTH) {
                return VoxelShapes.union(
                        VoxelShapes.cuboid(6 / 16f, 0 / 16f, 11 / 16f, 10 / 16f, 10 / 16f, 15 / 16f),
                        VoxelShapes.cuboid(5 / 16f, 10 / 16f, 10 / 16f, 11 / 16f, 13 / 16f, 16 / 16f)
                );
            } else if (direction == Direction.EAST) {
                return VoxelShapes.union(
                        VoxelShapes.cuboid(1 / 16f, 0 / 16f, 6 / 16f, 5 / 16f, 10 / 16f, 10 / 16f),
                        VoxelShapes.cuboid(0 / 16f, 10 / 16f, 5 / 16f, 6 / 16f, 13 / 16f, 10 / 16f)
                );
            } else if (direction == Direction.SOUTH) {
                return VoxelShapes.union(
                        VoxelShapes.cuboid(6 / 16f, 0 / 16f, 1 / 16f, 10 / 16f, 10 / 16f, 5 / 16f),
                        VoxelShapes.cuboid(5 / 16f, 10 / 16f, 0 / 16f, 11 / 16f, 13 / 16f, 6 / 16f)
                );
            } else {
                return VoxelShapes.union(
                        VoxelShapes.cuboid(11 / 16f, 0 / 16f, 6 / 16f, 15 / 16f, 10 / 16f, 10 / 16f),
                        VoxelShapes.cuboid(10 / 16f, 10 / 16f, 5 / 16f, 16 / 16f, 13 / 16f, 10 / 16f)
                );
            }
        }
    }
}
