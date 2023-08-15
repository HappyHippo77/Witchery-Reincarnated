package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsOvenEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Altar extends BlockWithEntity implements BlockEntityProvider {
    public static final BooleanProperty JOINED = BooleanProperty.of("joined");
    private List<BlockPos> joined_altars = new ArrayList<>();
    public Altar(Settings settings) {
        super(settings);
    }

    private void updateArtifacts(AltarEntity entity, World world, BlockPos pos) {
        if (entity.sourceEntity != null) {
            entity.sourceEntity.artifacts.remove(pos.up());
            entity.sourceEntity.artifacts.put(pos.up(), world.getBlockState(pos.up()).getBlock());
            entity.sourceEntity.updateArtifacts();
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(JOINED)) {
            if (!world.isClient) {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
            AltarEntity e = ((AltarEntity)world.getBlockEntity(pos));
            e.sourceEntity.updateSources(world, e.sourceEntity.getPos());
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (sourcePos.equals(pos.up())) {
            updateArtifacts((AltarEntity) world.getBlockEntity(pos), world, pos);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ALTAR_ENTITY, AltarEntity::tick);
    }

    private void recursivelyCollectJoins(World world, BlockPos pos) {
        if (!joined_altars.contains(pos) && world.getBlockState(pos).getBlock().equals(ModBlocks.ALTAR)) {
            joined_altars.add(pos);
            recursivelyCollectJoins(world, pos.north());
            recursivelyCollectJoins(world, pos.west());
            recursivelyCollectJoins(world, pos.south());
            recursivelyCollectJoins(world, pos.east());
        }
    }

    private boolean updateJoins(World world) {
        if (joined_altars.size() == 6) {
            for (BlockPos blockPos : joined_altars) {
                world.setBlockState(blockPos, ModBlocks.ALTAR.getDefaultState().with(JOINED, true));
            }
            return true;
        }
        else {
            for (BlockPos blockPos : joined_altars) {
                world.setBlockState(blockPos, ModBlocks.ALTAR.getDefaultState().with(JOINED, false));
            }
            return false;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        joined_altars.clear();
        recursivelyCollectJoins(world, pos);
        boolean joined = updateJoins(world);
        if (joined) {
            ((AltarEntity) world.getBlockEntity(pos)).sourcePos = pos;
            for (BlockPos p : joined_altars) {
                AltarEntity blockEntity = (AltarEntity) world.getBlockEntity(p);
                blockEntity.sourcePos = pos;
                blockEntity.sourceEntity = (AltarEntity) world.getBlockEntity(pos);
                updateArtifacts(blockEntity, world, p);
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        joined_altars.clear();
        recursivelyCollectJoins(world, pos);
        joined_altars.remove(pos);
        updateJoins(world);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(JOINED, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(JOINED);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AltarEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
