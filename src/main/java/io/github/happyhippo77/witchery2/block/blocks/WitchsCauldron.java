package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import io.github.happyhippo77.witchery2.networking.ServerPackets;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.sounds.ModSounds;
import io.github.happyhippo77.witchery2.util.MathUtil;
import io.github.happyhippo77.witchery2.util.PoweredBlockWithEntity;
import io.github.happyhippo77.witchery2.util.brewing.crafting.CauldronRecipeRegistry;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.EffectIngredient;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.IngredientRegistry;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.IngredientUse;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WitchsCauldron extends PoweredBlockWithEntity implements BlockEntityProvider {

    public WitchsCauldron(Settings settings) {
        super(settings);
    }

    private final java.util.Random r = new java.util.Random();

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        WitchsCauldronEntity entity = (WitchsCauldronEntity) world.getBlockEntity(pos);
        if (entity.clientRenderBubbles) {
            int i;
            double xPos;
            double zPos;
            double yPos = 0;
            switch (entity.getLevel()) {
                case LOW -> yPos = 0.375d;
                case MEDIUM -> yPos = 0.53125d;
                case FULL -> yPos = 0.6875d;
            }
            for (i = 0; i < 2; ++i) {
                xPos = 0.2D + random.nextDouble() * 0.6D;
                zPos = 0.2D + random.nextDouble() * 0.6D;
                Witchery2.bubbleParticleDataSetter.setData(entity.getColor());
                world.addParticle(ModParticles.BUBBLE_PARTICLE, pos.getX() + xPos, pos.getY() + yPos, pos.getZ() + zPos, 0, 0, 0);
            }
            if (entity.clientPlayBlop) {
                if (random.nextInt(5) == 0) {
                    world.playSoundAtBlockCenter(pos, ModSounds.RANDOM_BLOP, SoundCategory.BLOCKS, 0.8F + random.nextFloat() * 0.2F, 0.8F + random.nextFloat() * 0.2F, false);
                }
            }

            if (entity.clientRenderMagic) {
                for (i = 0; i < 1 + Math.min(entity.clientRitualSeconds, 5); ++i) {
                    xPos = 0.3D + random.nextDouble() * 0.4D;
                    zPos = 0.3D + random.nextDouble() * 0.4D;

                    float maxColorShift = 0.2F;
                    float doubleColorShift = maxColorShift * 2.0F;
                    float shiftR = random.nextFloat() * doubleColorShift - maxColorShift;
                    float shiftG = random.nextFloat() * doubleColorShift - maxColorShift;
                    float shiftB = random.nextFloat() * doubleColorShift - maxColorShift;

                    int maxAge = 25 + random.nextInt(10);

                    Witchery2.powerParticleDataSetter.setData(
                            new Color(
                                    MathUtil.clamp((int) (entity.getColor().getRed() + (255 * shiftR)), 0, 255),
                                    MathUtil.clamp((int) (entity.getColor().getGreen() + (255 * shiftG)), 0, 255),
                                    MathUtil.clamp((int) (entity.getColor().getBlue() + (255 * shiftB)), 0, 255)
                            ), entity.clientRenderRitual, entity.clientRenderRitual, maxAge);
                    world.addParticle(ModParticles.POWER_PARTICLE, pos.getX() + xPos, pos.getY() + yPos, pos.getZ() + zPos, random.nextDouble() * 0.08D - 0.04D, random.nextDouble() * 0.05D + 0.08D, random.nextDouble() * 0.08D - 0.04D);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if (!world.isClient) {
            if (entity instanceof ItemEntity) {
                WitchsCauldronEntity blockEntity = (WitchsCauldronEntity) world.getBlockEntity(pos);
                ItemStack itemStack = ((ItemEntity) entity).getStack();
                boolean accept = false;

                if (blockEntity.isBoiling()) {
                    if (IngredientRegistry.isIngredient(itemStack.getItem())) {
                        if (!blockEntity.getIngredients().contains(itemStack.getItem())) {
                            if (IngredientRegistry.fromItem(itemStack.getItem()).getUse() == IngredientUse.EFFECT) {
                                if (((EffectIngredient) IngredientRegistry.fromItem(itemStack.getItem())).brewHasCapacity(blockEntity.getIngredients())) {
                                    accept = true;
                                }
                            } else if (IngredientRegistry.fromItem(itemStack.getItem()).getUse() != IngredientUse.GENERIC) {
                                accept = true;
                            } else {
                                List<Item> recipe = new ArrayList<>(blockEntity.getIngredients());
                                recipe.add(itemStack.getItem());

                                if (CauldronRecipeRegistry.checkPrecursor(recipe)) {
                                    accept = true;
                                }
                            }

                            if (accept) {
                                blockEntity.addIngredient(itemStack.getItem());
                                entity.kill();

                                world.playSound(null, pos, ModSounds.RANDOM_SPLASH, SoundCategory.BLOCKS, 0.5F, 0.5f);
                                if (!world.isClient()) {
                                    for (PlayerEntity player : world.getPlayers()) {
                                        for (int i = 0; i < 8; i++) {
                                            ServerPackets.sendRenderParticle(player, ParticleTypes.SPLASH, pos.getX() + r.nextFloat(), (float) (pos.getY() + 0.5 + r.nextFloat()), pos.getZ() + r.nextFloat(), 0.0f, 0.0f, 0.0f);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WitchsCauldronEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.WITCHS_CAULDRON_ENTITY, WitchsCauldronEntity::tick);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof WitchsCauldronEntity entity) {
            return entity.onUse(state, world, pos, player, hand, hit);
        }
        return ActionResult.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                VoxelShapes.cuboid(3.0 / 16f, 2.0 / 16, 3.0 / 16, 13.0 / 16, 3.0 / 16, 13.0 / 16),
                VoxelShapes.cuboid(2.0 / 16f, 3.0 / 16, 2.0 / 16, 14.0 / 16, 4.0 / 16, 14.0 / 16),
                VoxelShapes.cuboid(1.0 / 16f, 4.0 / 16, 1.0 / 16, 15.0 / 16, 10.0 / 16, 15.0 / 16),
                VoxelShapes.cuboid(2.0 / 16f, 10.0 / 16, 2.0 / 16, 14.0 / 16, 12.0 / 16, 14.0 / 16),
                VoxelShapes.cuboid(1.0 / 16f, 12.0 / 16, 1.0 / 16, 15.0 / 16, 13.0 / 16, 15.0 / 16)
        );
    }
}
