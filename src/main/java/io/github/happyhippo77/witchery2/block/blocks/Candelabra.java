package io.github.happyhippo77.witchery2.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Candelabra extends Block {
    public Candelabra(Settings settings) {
        super(settings);
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
        double yMid = (double)pos.getY() + 1.05D;
        double yOuter = (double)pos.getY() + 0.9D;
        double mid = 0.5D;
        double near = 0.2D;
        double far = 0.8D;
        if(random.nextInt(4) != 0) {
            world.addParticle(ParticleTypes.FLAME, (double)pos.getX() + 0.5D, yMid, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D, yMid, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        if(random.nextInt(4) != 0) {
            world.addParticle(ParticleTypes.FLAME, (double)pos.getX() + 0.8D, yOuter, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.8D, yOuter, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        if(random.nextInt(4) != 0) {
            world.addParticle(ParticleTypes.FLAME, (double)pos.getX() + 0.2D, yOuter, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.2D, yOuter, (double)pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        if(random.nextInt(4) != 0) {
            world.addParticle(ParticleTypes.FLAME, (double)pos.getX() + 0.5D, yOuter, (double)pos.getZ() + 0.8D, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D, yOuter, (double)pos.getZ() + 0.8D, 0.0D, 0.0D, 0.0D);
        }

        if(random.nextInt(4) != 0) {
            world.addParticle(ParticleTypes.FLAME, (double)pos.getX() + 0.5D, yOuter, (double)pos.getZ() + 0.2D, 0.0D, 0.0D, 0.0D);
            world.addParticle(ParticleTypes.SMOKE, (double)pos.getX() + 0.5D, yOuter, (double)pos.getZ() + 0.2D, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(1.5 / 16, 0 / 16f, 1.5 / 16, 14.5 / 16,15 / 16f, 14.5 / 16);
    }
}
