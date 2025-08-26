package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.entity.entities.PlacedItemEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PlacedItem extends BlockWithEntity {
    public static DirectionProperty FACING = DirectionProperty.of("facing", Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);

    public PlacedItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void spawnBreakParticles(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        world.playSoundAtBlockCenter(pos, soundGroup.getBreakSound(), SoundCategory.BLOCKS, (soundGroup.getVolume() + 1.0f) / 2.0f, soundGroup.getPitch() * 0.8f, false);
        VoxelShape voxelShape = state.getOutlineShape(world, pos);
        voxelShape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            double d = Math.min(1.0, maxX - minX);
            double e = Math.min(1.0, maxY - minY);
            double f = Math.min(1.0, maxZ - minZ);
            int i = Math.max(2, MathHelper.ceil(d / 0.25));
            int j = Math.max(2, MathHelper.ceil(e / 0.25));
            int k = Math.max(2, MathHelper.ceil(f / 0.25));
            for (int l = 0; l < i; ++l) {
                for (int m = 0; m < j; ++m) {
                    for (int n = 0; n < k; ++n) {
                        double g = ((double) l + 0.5) / (double) i;
                        double h = ((double) m + 0.5) / (double) j;
                        double o = ((double) n + 0.5) / (double) k;
                        double p = g * d + minX;
                        double q = h * e + minY;
                        double r = o * f + minZ;
                        world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, ((PlacedItemEntity) world.getBlockEntity(pos)).getItemStack()), pos.getX() + p, pos.getY() + q, pos.getZ() + r, (g - 0.5) * 0.25, (h - 0.5) * 0.6, (o - 0.5) * 0.25);
                        //world.addParticle(new BlockDustParticle(world, (double) pos.getX() + p, (double) pos.getY() + q, (double) pos.getZ() + r, g - 0.5, h - 0.5, o - 0.5, state, pos));
                    }
                }
            }
        });
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
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    private void dropItem(World world, BlockPos pos) {
        PlacedItemEntity blockEntity = (PlacedItemEntity) world.getBlockEntity(pos);
        if (blockEntity.getItemStack() != ItemStack.EMPTY) {
            dropStack(world, pos, blockEntity.getItemStack());
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if(!this.canBlockStay(world, pos.getX(), pos.getY(), pos.getZ())) {
            if(!world.isClient) {
                dropItem(world, pos);
                world.breakBlock(pos, false);
            }
        }
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        Material material = world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial();
        return !world.isAir(new BlockPos(x, y - 1, z)) && material != null && material.blocksLight() && material.isSolid();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (!player.isCreative()) {
                dropItem(world, pos);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ((PlacedItemEntity)world.getBlockEntity(pos)).getItemStack();
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PlacedItemEntity(pos, state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(3 / 16f, 0, 3 / 16f, 13 / 16f, 1 / 16f, 13 / 16f);
    }
}
