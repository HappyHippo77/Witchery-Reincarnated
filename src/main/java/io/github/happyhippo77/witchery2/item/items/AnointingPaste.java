package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Random;

public class AnointingPaste extends Item {
    public AnointingPaste(Settings settings) {
        super(settings);
    }


    private final Random random = new Random();

    private static float getRandomFloat(int min, int max) {
        min *= 100;
        max *= 100;
        Random r = new Random();
        return (float) (r.nextInt((max - min) + 1) + min) / 1000;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        if (block == Blocks.CAULDRON) {
            context.getWorld().setBlockState(context.getBlockPos(), ModBlocks.WITCHS_CAULDRON.getDefaultState(), 0);

            for (int i = 0; i <= 16; i++) {
                context.getWorld().addParticle(ParticleTypes.INSTANT_EFFECT, context.getBlockPos().getX() + 0.5 + getRandomFloat(-5, 5), context.getBlockPos().getY() + 0.5 + getRandomFloat(-5, 5), context.getBlockPos().getZ() + 0.5 + getRandomFloat(-5, 5), 0.05, 0.05, 0.05);
                context.getWorld().addParticle(ParticleTypes.EXPLOSION, context.getBlockPos().getX() + 0.5 + getRandomFloat(-10, 10), context.getBlockPos().getY() + 0.5 + getRandomFloat(-10, 10), context.getBlockPos().getZ() + 0.5 + getRandomFloat(-10, 10), 0.1, 0.1, 0.1);
            }

            context.getWorld().playSound(null, context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.75f, 0);
            context.getWorld().playSound(null, context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 0.75f, 0);

            if (!context.getPlayer().isCreative()) {
                context.getPlayer().getStackInHand(context.getHand()).decrement(1);
                return ActionResult.CONSUME;
            } else {
                return ActionResult.SUCCESS;
            }
        } else {
            return ActionResult.PASS;
        }
    }
}
