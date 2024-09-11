package io.github.happyhippo77.witchery2.util;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.blocks.PlacedItem;
import io.github.happyhippo77.witchery2.block.entity.entities.PlacedItemEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public interface PlaceableItemInterface {
    default ActionResult place(ItemUsageContext context) {
        ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
        World world = itemPlacementContext.getWorld();
        BlockPos blockPos = itemPlacementContext.getBlockPos();
        PlayerEntity playerEntity = itemPlacementContext.getPlayer();
        ItemStack itemStack = itemPlacementContext.getStack();
        BlockState blockState = ModBlocks.PLACED_ITEM.getPlacementState(itemPlacementContext);
        BlockState blockState2 = world.getBlockState(blockPos);

        if (world.getBlockState(blockPos.down()).getBlock() == ModBlocks.ALTAR && blockState2.isReplaceable()) {
            context.getWorld().setBlockState(blockPos, blockState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            ((PlacedItemEntity) world.getBlockEntity(blockPos)).setItemStack(itemStack.copy());

            if (blockState2.isOf(blockState.getBlock())) {
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockPos, itemStack);
                }
            }
            BlockSoundGroup blockSoundGroup = blockState2.getSoundGroup();
            world.playSound(playerEntity, blockPos, blockSoundGroup.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundGroup.getVolume() + 1.0f) / 2.0f, blockSoundGroup.getPitch() * 0.8f);
            world.emitGameEvent(GameEvent.BLOCK_PLACE, blockPos, GameEvent.Emitter.of(playerEntity, blockState2));
            if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
                itemStack.setCount(0);
            }
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
