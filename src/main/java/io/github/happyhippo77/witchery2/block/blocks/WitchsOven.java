package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsOvenEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WitchsOven extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public WitchsOven(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        WitchsOvenEntity entity = (WitchsOvenEntity) world.getBlockEntity(pos);

        if (entity.fuelTime > 0) {
            Direction facing = world.getBlockState(pos).get(Properties.HORIZONTAL_FACING);
            float x = pos.getX() + 0.5F;
            float y = pos.getY() + 0.2F + random.nextFloat() * 6.0F / 16.0F;
            float z = pos.getZ() + 0.5F;
            float f3 = 0.52F;
            float f4 = random.nextFloat() * 0.6F - 0.3F;
            if (facing == Direction.WEST) {
                world.addParticle(ParticleTypes.SMOKE, (x - f3), y, (z + f4), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, (x - f3), y, (z + f4), 0.0D, 0.0D, 0.0D);
            } else if (facing == Direction.EAST) {
                world.addParticle(ParticleTypes.SMOKE, (x + f3), y, (z + f4), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, (x + f3), y, (z + f4), 0.0D, 0.0D, 0.0D);
            } else if (facing == Direction.NORTH) {
                world.addParticle(ParticleTypes.SMOKE, (x + f4), y, (z - f3), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, (x + f4), y, (z - f3), 0.0D, 0.0D, 0.0D);
            } else if (facing == Direction.SOUTH) {
                world.addParticle(ParticleTypes.SMOKE, (x + f4), y, (z + f3), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, (x + f4), y, (z + f3), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void calculateFunnelsAndUpdateEntity(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);
        int maxProgress = 180;
        double fumeChance = 0.3;

        if (world.getBlockState(pos.down()).getBlock() instanceof FumeFunnel) {
            maxProgress -= 20;
            fumeChance += ((FumeFunnel) world.getBlockState(pos.down()).getBlock()).fumeChanceBonus();
        }
        if (world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).getBlock() instanceof FumeFunnel) {
            maxProgress -= 20;
            fumeChance += ((FumeFunnel) world.getBlockState(pos.offset(facing.rotateYCounterclockwise())).getBlock()).fumeChanceBonus();
        }
        if (world.getBlockState(pos.offset(facing.rotateYClockwise())).getBlock() instanceof FumeFunnel) {
            maxProgress -= 20;
            fumeChance += ((FumeFunnel) world.getBlockState(pos.offset(facing.rotateYClockwise())).getBlock()).fumeChanceBonus();
        }

        WitchsOvenEntity ovenEntity = (WitchsOvenEntity) world.getBlockEntity(pos);
        ovenEntity.setMaxProgress(maxProgress);
        ovenEntity.setFumeChance(fumeChance);
        ovenEntity.markDirty();
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        calculateFunnelsAndUpdateEntity(state, world, pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        calculateFunnelsAndUpdateEntity(state, world, pos);
    }

    // Block Entity Stuff

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitchsOvenEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WitchsOvenEntity) {
                ItemScatterer.spawn(world, pos, (WitchsOvenEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.WITCHS_OVEN_ENTITY, WitchsOvenEntity::tick);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape main = VoxelShapes.union(
                VoxelShapes.cuboid(2 / 16f, 1 / 16f, 2 / 16f, 14 / 16f, 9 / 16f, 14 / 16f),
                VoxelShapes.cuboid(1 / 16f, 9 / 16f, 1 / 16f, 15 / 16f, 10 / 16f, 15 / 16f),
                VoxelShapes.cuboid(3 / 16f, 10 / 16f, 3 / 16f, 13 / 16f, 11 / 16f, 13 / 16f)
        );

        Direction facing = state.get(FACING);
        if (facing == Direction.NORTH) {
            return VoxelShapes.union(
                    main,
                    VoxelShapes.cuboid(6 / 16f, 3 / 16f, 11 / 16f, 10 / 16f, 16 / 16f, 15 / 16f),
                    VoxelShapes.cuboid(6 / 16f, 12 / 16f, 15 / 16f, 10 / 16f, 14 / 16f, 16 / 16f)
            );
        } else if (facing == Direction.EAST) {
            return VoxelShapes.union(
                    main,
                    VoxelShapes.cuboid(1 / 16f, 3 / 16f, 6 / 16f, 5 / 16f, 16 / 16f, 10 / 16f),
                    VoxelShapes.cuboid(0 / 16f, 12 / 16f, 6 / 16f, 1 / 16f, 16 / 16f, 10 / 16f)
            );
        } else if (facing == Direction.SOUTH) {
            return VoxelShapes.union(
                    main,
                    VoxelShapes.cuboid(6 / 16f, 3 / 16f, 1 / 16f, 10 / 16f, 16 / 16f, 5 / 16f),
                    VoxelShapes.cuboid(6 / 16f, 12 / 16f, 0 / 16f, 10 / 16f, 16 / 16f, 1 / 16f)
            );
        } else {
            return VoxelShapes.union(
                    main,
                    VoxelShapes.cuboid(11 / 16f, 3 / 16f, 6 / 16f, 15 / 16f, 16 / 16f, 10 / 16f),
                    VoxelShapes.cuboid(15 / 16f, 12 / 16f, 6 / 16f, 16 / 16f, 16 / 16f, 10 / 16f)
            );
        }
    }
}
