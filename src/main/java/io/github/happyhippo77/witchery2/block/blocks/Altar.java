package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
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
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Altar extends BlockWithEntity implements BlockEntityProvider {
    public static final BooleanProperty JOINED = BooleanProperty.of("joined");
    public Altar(Settings settings) {
        super(settings);
    }

    private void updateArtifacts(AltarEntity entity, World world, BlockPos pos) {
        if (entity.getCoreEntity() != null) {
            entity.getCoreEntity().markForArtifactUpdates();
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
                AltarEntity e = ((AltarEntity)world.getBlockEntity(pos));
                e.getCoreEntity().updateSources(world, e.getCoreEntity().getPos());
                e.getCoreEntity().updateArtifacts();
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (world.isClient) {
            return;
        }
        if (sourcePos.equals(pos.up())) {
            updateArtifacts((AltarEntity) world.getBlockEntity(pos), world, pos);
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.ALTAR_ENTITY, AltarEntity::tick);
    }

    private void updateJoinedAltars(World world, BlockPos pos, boolean excludeSelf) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        ArrayList<BlockPos> visited = new ArrayList<>();
        ArrayList<BlockPos> toVisit = new ArrayList<>();
        toVisit.add(new BlockPos(x, y, z));
        boolean valid = true;

        BlockPos newCore;
        while(!toVisit.isEmpty()) {
            newCore = toVisit.get(0);
            toVisit.remove(0);
            int altarsFound = 0;
            BlockPos[] blocksToCheck = new BlockPos[]{newCore.north(), newCore.south(), newCore.east(), newCore.west()};

            for (BlockPos checkPos : blocksToCheck) {
                if (this.stateManager.getStates().contains(world.getBlockState(checkPos))) {
                    if (!visited.contains(checkPos) && !toVisit.contains(checkPos)) {
                        toVisit.add(checkPos);
                    }

                    ++altarsFound;
                }
            }
            if (!(newCore.equals(pos) && excludeSelf)) {
                if (altarsFound < 2 || altarsFound > 3) {
                    valid = false;
                }
                visited.add(newCore);
            }
        }

        if (!visited.isEmpty()) {
            BlockPos coreAltarPos = visited.get(0);

            if (valid && visited.size() == 6) {
                for (BlockPos p : visited) {
                    world.setBlockState(p, ModBlocks.ALTAR.getDefaultState().with(JOINED, true));

                    AltarEntity altarEntity = (AltarEntity) world.getBlockEntity(p);

                    altarEntity.setCoreEntity((AltarEntity) world.getBlockEntity(coreAltarPos));
                    altarEntity.getCoreEntity().updateSources(world, pos);
                    updateArtifacts(altarEntity, world, p);
                }
            } else {
                for (BlockPos p : visited) {
                    world.setBlockState(p, ModBlocks.ALTAR.getDefaultState().with(JOINED, false));

                    AltarEntity altarEntity = (AltarEntity) world.getBlockEntity(p);

                    altarEntity.setCoreEntity((AltarEntity) world.getBlockEntity(p));
                    altarEntity.joinedAltars.clear();
                    PoweredBlockEntity.recheckAltars(world);
                }
            }
        }

        if(excludeSelf) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof AltarEntity altarEntity) {
                altarEntity.setCoreEntity(altarEntity);
            }
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        updateJoinedAltars(world, pos, false);

        PoweredBlockEntity.recheckAltars(world);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        updateJoinedAltars(world, pos, true);
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
