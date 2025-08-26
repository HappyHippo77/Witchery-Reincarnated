package io.github.happyhippo77.witchery2.block.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Stockade extends Block {
    public Stockade() {
        super(FabricBlockSettings.of(Material.WOOD).hardness(25).resistance(20));
    }
    public Stockade(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);
        if (!world.isClient && entity instanceof LivingEntity) {
            entity.damage(world.getDamageSources().cactus(), 3f);
        }
    }

    public static final BooleanProperty ZCONNECT = BooleanProperty.of("zconnect");
    public static final BooleanProperty XCONNECT = BooleanProperty.of("xconnect");
    public static final BooleanProperty BELOW = BooleanProperty.of("below");

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean zConnect = ctx.getWorld().getBlockState(ctx.getBlockPos().north()).getBlock() instanceof Stockade ||
                ctx.getWorld().getBlockState(ctx.getBlockPos().south()).getBlock() instanceof Stockade;
        boolean xConnect = ctx.getWorld().getBlockState(ctx.getBlockPos().east()).getBlock() instanceof Stockade ||
                ctx.getWorld().getBlockState(ctx.getBlockPos().west()).getBlock() instanceof Stockade;
        boolean below = ctx.getWorld().getBlockState(ctx.getBlockPos().up()).getBlock() instanceof Stockade;

        return this.getDefaultState().with(ZCONNECT, zConnect).with(XCONNECT, xConnect).with(BELOW, below);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);

        boolean zConnect = world.getBlockState(pos.north()).getBlock() instanceof Stockade ||
                world.getBlockState(pos.south()).getBlock() instanceof Stockade;
        boolean xConnect = world.getBlockState(pos.east()).getBlock() instanceof Stockade ||
                world.getBlockState(pos.west()).getBlock() instanceof Stockade;
        boolean below = world.getBlockState(pos.up()).getBlock() instanceof Stockade;

        if (state.get(ZCONNECT) != zConnect || state.get(XCONNECT) != xConnect || state.get(BELOW) != below) {
            world.setBlockState(pos, state.with(ZCONNECT, zConnect).with(XCONNECT, xConnect).with(BELOW, below));
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ZCONNECT).add(XCONNECT).add(BELOW);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        float f = 0.3F;
        float f1 = 0.3F;
        float f2 = 0.7F;
        float f3 = 0.7F;
        float height = 0.9f;
        if(state.get(ZCONNECT)) {
            f1 = 0.05F;
            f3 = 0.95F;
        }
        if(state.get(XCONNECT)) {
            f = 0.05F;
            f2 = 0.95F;
        }
        if ((state.get(ZCONNECT) && state.get(XCONNECT)) || (state.get(BELOW))) {
            height = 1.0f;
        }

        return VoxelShapes.cuboid(f, 0.0, f1, f2, height, f3);
    }
}
