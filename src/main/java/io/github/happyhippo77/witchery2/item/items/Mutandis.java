package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mutandis extends Item {
    public Mutandis(Settings settings) {
        super(settings);
    }

    public static final List<Block> flowerPots = Arrays.asList(
            Blocks.POTTED_OAK_SAPLING,
            Blocks.POTTED_SPRUCE_SAPLING,
            Blocks.POTTED_BIRCH_SAPLING,
            Blocks.POTTED_JUNGLE_SAPLING,
            Blocks.POTTED_ACACIA_SAPLING,
            Blocks.POTTED_DARK_OAK_SAPLING,
            Blocks.POTTED_MANGROVE_PROPAGULE,
            Blocks.POTTED_FERN,
            Blocks.POTTED_DANDELION,
            Blocks.POTTED_POPPY,
            Blocks.POTTED_BLUE_ORCHID,
            Blocks.POTTED_ALLIUM,
            Blocks.POTTED_AZURE_BLUET,
            Blocks.POTTED_RED_TULIP,
            Blocks.POTTED_ORANGE_TULIP,
            Blocks.POTTED_WHITE_TULIP,
            Blocks.POTTED_PINK_TULIP,
            Blocks.POTTED_OXEYE_DAISY,
            Blocks.POTTED_CORNFLOWER,
            Blocks.POTTED_LILY_OF_THE_VALLEY,
            Blocks.POTTED_WITHER_ROSE,
            Blocks.POTTED_RED_MUSHROOM,
            Blocks.POTTED_BROWN_MUSHROOM,
            Blocks.POTTED_DEAD_BUSH,
            Blocks.POTTED_CACTUS
    );
    public static final List<Block> other = Arrays.asList(
            Blocks.OAK_SAPLING,
            Blocks.SPRUCE_SAPLING,
            Blocks.BIRCH_SAPLING,
            Blocks.JUNGLE_SAPLING,
            Blocks.ACACIA_SAPLING,
            Blocks.DARK_OAK_SAPLING,
            ModBlocks.ROWAN_SAPLING,
            ModBlocks.ALDER_SAPLING,
            ModBlocks.HAWTHORN_SAPLING,
            ModBlocks.EMBER_MOSS,
            ModBlocks.GLINT_WEED,
            ModBlocks.SPANISH_MOSS,
            Blocks.GRASS,
            Blocks.FERN,
            Blocks.LILY_PAD,
            Blocks.BROWN_MUSHROOM,
            Blocks.RED_MUSHROOM,
            Blocks.POPPY,
            Blocks.DANDELION

            // NETHER WART IN THE DREAM WORLD?
    );

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            List<Block> options = null;

            if (flowerPots.contains(context.getWorld().getBlockState(context.getBlockPos()).getBlock())) {
                options = new ArrayList<>(flowerPots);
                options.remove(context.getWorld().getBlockState(context.getBlockPos()).getBlock());
            } else if (other.contains(context.getWorld().getBlockState(context.getBlockPos()).getBlock())) {
                options = new ArrayList<>(other);
                options.remove(context.getWorld().getBlockState(context.getBlockPos()).getBlock());
            }

            if (options != null) {
                context.getWorld().setBlockState(context.getBlockPos(), options.get(context.getWorld().random.nextInt(options.size())).getDefaultState());
                ((ServerWorld) context.getWorld()).spawnParticles(ParticleTypes.INSTANT_EFFECT, context.getBlockPos().getX() + 0.5, context.getBlockPos().getY() + 0.5, context.getBlockPos().getZ() + 0.5, 16, 0.5, 0.5, 0.5, 0);
                context.getWorld().playSound(null, context.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1f, 0.5f);
                context.getStack().setCount(context.getStack().getCount() - 1);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.PASS;
            }
        }
        return ActionResult.PASS;
    }
}
