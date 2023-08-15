package io.github.happyhippo77.witchery2.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class Chalice extends Block {
    private final boolean filled;

    public Chalice(Settings settings, boolean filled) {
        super(settings);
        this.filled = filled;
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1;
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
        return !world.isAir(new BlockPos(x, y - 1, z)) && material != null && material.blocksLight() && material.isSolid();
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if(filled) {
            double d0 = (double)((float)pos.getX() + 0.45F);
            double d1 = (double)((float)pos.getY() + 0.4F);
            double d2 = (double)((float)pos.getZ() + 0.5F);
            world.addParticle(new DustParticleEffect(new Vector3f(1, 0.2f, 0), 1), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(5.5 / 16, 0 / 16f, 5.5 / 16, 10.5 / 16, 7 / 16f, 10.5 / 16);
    }
}
