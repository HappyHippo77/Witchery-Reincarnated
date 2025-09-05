package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.DistilleryEntity;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsOvenEntity;
import io.github.happyhippo77.witchery2.util.PoweredBlockWithEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
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

public class Distillery extends PoweredBlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty JARS = IntProperty.of("jars", 0, 4);

    public Distillery(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(JARS, 0).with(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DistilleryEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(JARS);
        builder.add(FACING);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        DistilleryEntity distilleryEntity = (DistilleryEntity) world.getBlockEntity(pos);
        if(distilleryEntity != null && distilleryEntity.getProgress() > 0) {
            double d0 = (double)pos.getX() + 0.4F + random.nextFloat() * 0.2F;
            double d1 = (double)pos.getY() + 1.0F + random.nextFloat() * 0.3F;
            double d2 = (double)pos.getZ() + 0.4F + random.nextFloat() * 0.2F;
            world.addParticle(ParticleTypes.INSTANT_EFFECT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DistilleryEntity) {
                ItemScatterer.spawn(world, pos, (DistilleryEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.DISTILLERY_ENTITY, DistilleryEntity::tick);
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

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        return switch (facing) {
            case EAST ->
                VoxelShapes.union(
                        VoxelShapes.cuboid(0, 0, 3 / 16d, 10 / 16d, 6 / 16d, 13 / 16d),
                        VoxelShapes.cuboid(2 / 16d, 6 / 16d, 5 / 16d, 8 / 16d, 10 / 16d, 11 / 16d),
                        VoxelShapes.cuboid(3 / 16d, 10 / 16d, 6 / 16d, 7 / 16d, 13 / 16d, 10 / 16d),
                        VoxelShapes.cuboid(5 / 16d, 13 / 16d, 7 / 16d, 9 / 16d, 15 / 16d, 9 / 16d),
                        VoxelShapes.cuboid(11 / 16d, 0, 0, 1, 1 / 16d, 1),
                        VoxelShapes.cuboid(13 / 16d, 1 / 16d, 0, 14 / 16d, 8 / 16d, 1 / 16d),
                        VoxelShapes.cuboid(13 / 16d, 1 / 16d, 15 / 16d, 14 / 16d, 8 / 16d, 1),
                        VoxelShapes.cuboid(13 / 16d, 8 / 16d, 0, 14 / 16d, 9 / 16d, 1)
                );
            case SOUTH ->
                VoxelShapes.union(
                        VoxelShapes.cuboid(3 / 16d, 0, 0, 13 / 16d, 6 / 16d, 10 / 16d),
                        VoxelShapes.cuboid(5 / 16d, 6 / 16d, 2 / 16d, 11 / 16d, 10 / 16d, 8 / 16d),
                        VoxelShapes.cuboid(6 / 16d, 10 / 16d, 3 / 16d, 10 / 16d, 13 / 16d, 7 / 16d),
                        VoxelShapes.cuboid(7 / 16d, 13 / 16d, 5 / 16d, 9 / 16d, 15 / 16d, 9 / 16d),
                        VoxelShapes.cuboid(0, 0, 11 / 16d, 1, 1 / 16d, 1),
                        VoxelShapes.cuboid(15 / 16d, 1 / 16d, 13 / 16d, 1, 8 / 16d, 14 / 16d),
                        VoxelShapes.cuboid(0, 1 / 16d, 13 / 16d, 1 / 16d, 8 / 16d, 14 / 16d),
                        VoxelShapes.cuboid(0, 8 / 16d, 13 / 16d, 1, 9 / 16d, 14 / 16d)
                );
            case WEST ->
                VoxelShapes.union(
                        VoxelShapes.cuboid(6 / 16d, 0, 3 / 16d, 1, 6 / 16d, 13 / 16d),
                        VoxelShapes.cuboid(8 / 16d, 6 / 16d, 5 / 16d, 14 / 16d, 10 / 16d, 11 / 16d),
                        VoxelShapes.cuboid(9 / 16d, 10 / 16d, 6 / 16d, 13 / 16d, 13 / 16d, 10 / 16d),
                        VoxelShapes.cuboid(7 / 16d, 13 / 16d, 7 / 16d, 11 / 16d, 15 / 16d, 9 / 16d),
                        VoxelShapes.cuboid(0, 0, 0, 5 / 16d, 1 / 16d, 1),
                        VoxelShapes.cuboid(2 / 16d, 1 / 16d, 15 / 16d, 3 / 16d, 8 / 16d, 1),
                        VoxelShapes.cuboid(2 / 16d, 1 / 16d, 0, 3 / 16d, 8 / 16d, 1 / 16d),
                        VoxelShapes.cuboid(2 / 16d, 8 / 16d, 0, 3 / 16d, 9 / 16d, 1)
                );
            default ->
                VoxelShapes.union(
                        VoxelShapes.cuboid(3 / 16d, 0, 6 / 16d, 13 / 16d, 6 / 16d, 1),
                        VoxelShapes.cuboid(5 / 16d, 6 / 16d, 8 / 16d, 11 / 16d, 10 / 16d, 14 / 16d),
                        VoxelShapes.cuboid(6 / 16d, 10 / 16d, 9 / 16d, 10 / 16d, 13 / 16d, 13 / 16d),
                        VoxelShapes.cuboid(7 / 16d, 13 / 16d, 7 / 16d, 9 / 16d, 15 / 16d, 11 / 16d),
                        VoxelShapes.cuboid(0, 0, 0, 1, 1 / 16d, 5 / 16d),
                        VoxelShapes.cuboid(0, 1 / 16d, 2 / 16d, 1 / 16d, 8 / 16d, 3 / 16d),
                        VoxelShapes.cuboid(15/ 16d, 1 / 16d, 2 / 16d, 1, 8 / 16d, 3 / 16d),
                        VoxelShapes.cuboid(0, 8 / 16d, 2 / 16d, 1, 9 / 16d, 3 / 16d)
                );
        };
    }
}
