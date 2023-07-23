package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

import java.util.Random;

public class MandrakeCrop extends CropBlock {

    private static final float NIGHT_MANDRAKE_SPAWN_CHANCE = 0.1f;
    private static final float DAY_MANDRAKE_SPAWN_CHANCE = 0.9f;

    private final Random r = new Random();

    public static final IntProperty AGE = IntProperty.of("age", 0, 4);

    public MandrakeCrop(Settings settings) {
        super(settings);
    }

    public void spawnMandrake(World world, BlockPos pos) {
        ModEntities.MANDRAKE.spawn((ServerWorld) world, pos, SpawnReason.TRIGGERED);
        ((ServerWorld) world).spawnParticles(ParticleTypes.POOF, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 16, 0.35, 0.35, 0.35, 0);
    }

    public void spawnDrops(World world, BlockPos pos, boolean maxAge) {
        double x = pos.getX() + 0.5;
        double y = pos.getY();
        double z = pos.getZ() + 0.5;
        ItemStack seeds;
        if (maxAge) {
            seeds = new ItemStack(ModItems.MANDRAKE_SEEDS, r.nextFloat() <= 0.25 ? 2 : 1);
            ItemStack root = new ItemStack(ModItems.MANDRAKE_ROOT, 1);
            ItemEntity rootEntity = new ItemEntity(world, x, y, z, root);
            world.spawnEntity(rootEntity);
        } else {
            seeds = new ItemStack(ModItems.MANDRAKE_SEEDS, 1);
        }
        ItemEntity seedsEntity = new ItemEntity(world, x, y, z, seeds);
        world.spawnEntity(seedsEntity);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient) {
            if (!player.isCreative()) {
                if (state.get(AGE) == 4) {
                    if (!(world.getDifficulty() == Difficulty.PEACEFUL)) {
                        float chance = world.isDay() ? DAY_MANDRAKE_SPAWN_CHANCE : NIGHT_MANDRAKE_SPAWN_CHANCE;
                        if (r.nextFloat() <= chance) {
                            spawnMandrake(world, pos);
                        } else {
                            spawnDrops(world, pos, true);
                        }
                    } else {
                        spawnDrops(world, pos, true);
                    }
                } else {
                    spawnDrops(world, pos, false);
                }
            }
        }
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.MANDRAKE_SEEDS;
    }

    @Override
    public int getMaxAge() {
        return 4;
    }

    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
