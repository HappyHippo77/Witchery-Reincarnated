package io.github.happyhippo77.witchery2.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.VineBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class SpanishMoss extends VineBlock {
    public SpanishMoss(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        for(int i = 0; i < 2; ++i) {
            super.randomTick(state, world, pos, random);
        }
    }
}
