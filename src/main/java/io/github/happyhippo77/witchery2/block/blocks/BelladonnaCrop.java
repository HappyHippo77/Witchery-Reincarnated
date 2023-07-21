package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class BelladonnaCrop extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 4);

    public BelladonnaCrop(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.BELLADONNA_SEEDS;
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
