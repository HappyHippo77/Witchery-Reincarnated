package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.block.blocks.Distillery;
import io.github.happyhippo77.witchery2.block.entity.ImplementedInventory;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.customrecipe.CustomRecipeManager;
import io.github.happyhippo77.witchery2.customrecipe.CustomRecipeType;
import io.github.happyhippo77.witchery2.customrecipe.recipes.DistilleryRecipe;
import io.github.happyhippo77.witchery2.screen.DistilleryScreenHandler;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DistilleryEntity extends PoweredBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public static final int inventorySize = 7;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);

    public static final int delegateSize = 2;
    private final PropertyDelegate propertyDelegate;

    private int progress = 0;
    public static final int maxProgress = 800;

    public DistilleryEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISTILLERY_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> getAltar() != null && getAltar().getPower() >= 0.6f?0:1;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                if (index == 0) {
                    progress = value;
                }
            }

            public int size() {
                return delegateSize;
            }
        };
    }

    public int getProgress() {
        return this.progress;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.witchery2.distillery");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DistilleryScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos pos, BlockState state, DistilleryEntity entity) {
        if (world.isClient) {
            return;
        }

        BlockState jarState = state.with(Distillery.JARS, Math.min(entity.inventory.get(0).getCount(), 4));

        if (state != jarState) {
            world.setBlockState(pos, jarState);
        }

        DistilleryRecipe recipe = (DistilleryRecipe) CustomRecipeManager.matchRecipe(CustomRecipeType.DISTILLERY, entity.inventory);

        if (recipe != null && recipe.canCraft(entity.inventory)) {
            if (entity.consumePower(0.6f)) {
                entity.progress++;
                markDirty(world, pos, state);
                world.updateListeners(pos, state, state, 0);
                if (entity.progress > DistilleryEntity.maxProgress) {
                    craftItem(entity, recipe);
                    markDirty(world, pos, state);
                    world.updateListeners(pos, state, state, 0);
                }
            }
        } else {
            entity.progress = 0;
            markDirty(world, pos, state);
            world.updateListeners(pos, state, state, 0);
        }

    }

    private static void craftItem(DistilleryEntity entity, DistilleryRecipe recipe) {
        entity.removeStack(0, recipe.getJars());
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);

        if (entity.getStack(3).isEmpty()) {
            entity.setStack(3, recipe.getOutput().first.copy());
        } else if (entity.getStack(3).isItemEqual(recipe.getOutput().first)) {
            entity.getStack(3).setCount(entity.getStack(3).getCount() + recipe.getOutput().first.getCount());
        }

        if (entity.getStack(4).isEmpty()) {
            entity.setStack(4, recipe.getOutput().second.copy());
        } else if (entity.getStack(4).isItemEqual(recipe.getOutput().second)) {
            entity.getStack(4).setCount(entity.getStack(4).getCount() + recipe.getOutput().second.getCount());
        }

        if (entity.getStack(5).isEmpty()) {
            entity.setStack(5, recipe.getOutput().third.copy());
        } else if (entity.getStack(5).isItemEqual(recipe.getOutput().third)) {
            entity.getStack(5).setCount(entity.getStack(5).getCount() + recipe.getOutput().third.getCount());
        }

        if (entity.getStack(6).isEmpty()) {
            entity.setStack(6, recipe.getOutput().fourth.copy());
        } else if (entity.getStack(6).isItemEqual(recipe.getOutput().fourth)) {
            entity.getStack(6).setCount(entity.getStack(6).getCount() + recipe.getOutput().fourth.getCount());
        }

        entity.progress = 0;
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

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("progress");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        super.writeNbt(nbt);
        nbt.putInt("progress", progress);
    }
}
