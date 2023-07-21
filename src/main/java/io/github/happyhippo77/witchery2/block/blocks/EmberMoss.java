package io.github.happyhippo77.witchery2.block.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EmberMoss extends PlantBlock {
    public EmberMoss(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(!world.isClient && entity instanceof LivingEntity && !entity.isOnFire() && !entity.isFireImmune() && (!(entity instanceof PlayerEntity) || !((PlayerEntity)entity).isCreative())) {
            entity.setOnFireFor(3);
            ((ServerWorld) world).spawnParticles(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 16, 0.25, 0.5, 0.25, 0);
            world.playSound(null, pos, SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.BLOCKS, 1f, 0.5f);
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!world.isClient && world.random.nextInt(6) == 0) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            byte b0 = 4;
            int l = 5;

            int i1;
            int j1;
            int k1;
            int t = 0;
            for(i1 = x - b0; i1 <= x + b0; ++i1) {
                for(j1 = z - b0; j1 <= z + b0; ++j1) {
                    for(k1 = y - 1; k1 <= y + 1; ++k1) {
                        t++;
                        if(world.getBlockState(new BlockPos(i1, k1, j1)).getBlock() == this) {
                            l--;
                            if(l <= 0) {
                                return;
                            }
                        }
                    }
                }
            }

            i1 = x + random.nextInt(3) - 1;
            j1 = y + random.nextInt(2) - random.nextInt(2);
            k1 = z + random.nextInt(3) - 1;

            for(int l1 = 0; l1 < 4; ++l1) {
                if(world.isAir(new BlockPos(i1, j1, k1)) && this.canBlockSpread(world, i1, j1, k1)) {
                    x = i1;
                    y = j1;
                    z = k1;
                }

                i1 = x + random.nextInt(3) - 1;
                j1 = y + random.nextInt(2) - random.nextInt(2);
                k1 = z + random.nextInt(3) - 1;
            }

            if(world.isAir(new BlockPos(i1, j1, k1)) && this.canBlockSpread(world, i1, j1, k1)) {
                BlockState old = world.getBlockState(new BlockPos(i1, j1, k1));
                world.setBlockState(new BlockPos(i1, j1, k1), this.getDefaultState(), 0, 2);
                world.updateListeners(pos, old, world.getBlockState(new BlockPos(i1, j1, k1)), 0);
            }
        }
    }

    public boolean canBlockSpread(World world, int posX, int posY, int posZ) {
        Block block = world.getBlockState(new BlockPos(posX, posY - 1, posZ)).getBlock();
        return this.canBlockStay(world, posX, posY, posZ) && (block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.MYCELIUM || block == Blocks.SAND || block == Blocks.FARMLAND);
    }

    public boolean canBlockStay(World world, int posX, int posY, int posZ) {
        Material material = world.getBlockState(new BlockPos(posX, posY - 1, posZ)).getMaterial();
        return material != null && material.isSolid();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.099999994F, 0.0F, 0.099999994F, 0.9F, 0.4F, 0.9F);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(random.nextInt(100) == 0) {
            double x = pos.getX() + 0.2F + random.nextFloat() * 0.8F;
            double y = pos.getY() + 0.15F + random.nextFloat() * 0.3F;
            double z = pos.getZ() + 0.2F + random.nextFloat() * 0.8F;
            world.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }
}
