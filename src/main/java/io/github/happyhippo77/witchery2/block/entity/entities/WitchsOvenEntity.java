package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.block.entity.ImplementedInventory;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.recipe.WitchsOvenRecipe;
import io.github.happyhippo77.witchery2.screen.WitchsOvenScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
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

import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class WitchsOvenEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public static final int inventorySize = 5;
    public static final int delegateSize = 4;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(inventorySize, ItemStack.EMPTY);

    private final PropertyDelegate propertyDelegate;
    private int progress = 0;
    // 180 - 20 * fume funnels
    private int maxProgress = 180;
    public int fuelTime = 0;
    private int maxFuelTime = 0;
    private double fumeChance = 0.3;

    public WitchsOvenEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WITCHS_OVEN_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    case 2 -> fuelTime;
                    case 3 -> maxFuelTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    case 2 -> fuelTime = value;
                    case 3 -> maxFuelTime = value;
                }
            }

            public int size() {
                return delegateSize;
            }
        };
    }

    public void setFumeChance(double fumeChance) {
        this.fumeChance = fumeChance;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.witchery2.witchs_oven");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new WitchsOvenScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
        nbt.putInt("fuelTime", fuelTime);
        nbt.putInt("maxFuelTime", maxFuelTime);
        nbt.putInt("maxProgress", maxProgress);
        nbt.putDouble("fumeChance", fumeChance);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("progress");
        fuelTime = nbt.getInt("fuelTime");
        maxFuelTime = nbt.getInt("maxFuelTime");
        maxProgress = nbt.getInt("maxProgress");
        fumeChance = nbt.getDouble("fumeChance");
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

    public static void tick(World world, BlockPos pos, BlockState state, WitchsOvenEntity entity) {

        if (world.isClient()) {
            return;
        }

        if (entity.fuelTime > 0) {
            world.updateListeners(pos, entity.getCachedState(), entity.getCachedState(), Block.NOTIFY_LISTENERS);
            entity.fuelTime--;
        }
        else {
            world.updateListeners(pos, entity.getCachedState(), entity.getCachedState(), Block.NOTIFY_LISTENERS);
        }

        if (hasRecipe(entity)) {
            if (entity.fuelTime <= 0) {
                entity.maxFuelTime = FuelRegistry.INSTANCE.get(entity.getStack(0).getItem()) != null ? FuelRegistry.INSTANCE.get(entity.getStack(0).getItem()) : 0;
                entity.removeStack(0, 1);
                entity.fuelTime = entity.maxFuelTime;
            }

            entity.progress++;
            markDirty(world, pos, state);
            if (entity.progress >= entity.maxProgress) {
                craftItem(entity);
            }
        }
        else {
            entity.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetMaxFuelTime() {
        maxFuelTime = 0;
    }

    private void resetProgress() {
        progress = 0;
    }

    private static final Random r = new Random();
    private static void craftItem(WitchsOvenEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<WitchsOvenRecipe> recipe = entity.getWorld().getRecipeManager().getFirstMatch(WitchsOvenRecipe.Type.INSTANCE, inventory, entity.getWorld());

        if (hasRecipe(entity)) {
            entity.removeStack(1, 1);
            entity.setStack(2, new ItemStack(recipe.get().getOutput().getItem(), entity.getStack(2).getCount() + 1));


            if (r.nextFloat() <= entity.fumeChance) {
                    if (canInsertIntoFume(inventory)) {
                        if (canInsertItemIntoFume(inventory, recipe.get().getFume().getItem())) {
                            if (inventory.getStack(3).getItem().equals(ModItems.CLAY_JAR)) {
                                entity.removeStack(3, 1);
                                entity.setStack(4, new ItemStack(recipe.get().getFume().getItem(), entity.getStack(4).getCount() + 1));
                        }
                    }
                }
            }

            entity.resetProgress();
            entity.markDirty();
        }
    }

    private static boolean hasRecipe(WitchsOvenEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<WitchsOvenRecipe> match = entity.getWorld().getRecipeManager().getFirstMatch(WitchsOvenRecipe.Type.INSTANCE, inventory, entity.getWorld());
        boolean hasFuelInSlot = FuelRegistry.INSTANCE.get(entity.getStack(0).getItem()) != null;

        boolean hasFuel = entity.fuelTime > 0 || hasFuelInSlot;

        return match.isPresent() && canInsertIntoOutput(inventory) && canInsertItemIntoOutput(inventory, match.get().getOutput().getItem()) && hasFuel;
    }

    private static boolean canInsertItemIntoOutput(SimpleInventory inventory, Item output) {
        return inventory.getStack(2).getItem() == output || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertIntoOutput(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

    private static boolean canInsertItemIntoFume(SimpleInventory inventory, Item fume) {
        return inventory.getStack(4).getItem() == fume || inventory.getStack(4).isEmpty();
    }

    private static boolean canInsertIntoFume(SimpleInventory inventory) {
        return inventory.getStack(4).getMaxCount() > inventory.getStack(4).getCount();
    }
}
