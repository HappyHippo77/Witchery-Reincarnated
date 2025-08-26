package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class PlacedItemEntity extends BlockEntity {
    private ItemStack itemStack = ItemStack.EMPTY;

    public PlacedItemEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLACED_ITEM_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        NbtCompound itemNbt = new NbtCompound();
        this.itemStack.writeNbt(itemNbt);
        nbt.put("item", itemNbt);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.itemStack = ItemStack.fromNbt((NbtCompound) nbt.get("item"));
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
        markDirty();
    }
}
