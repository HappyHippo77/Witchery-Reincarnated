package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.screen.AltarScreenHandler;
import io.github.happyhippo77.witchery2.util.PowerSources;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
import io.github.happyhippo77.witchery2.util.ServerWorldVariables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AltarEntity extends BlockEntity implements NamedScreenHandlerFactory {
    private BlockPos corePos = this.pos;
    private AltarEntity coreEntity = null;
    private final PropertyDelegate propertyDelegate;
    public static final int delegateSize = 4;
    private int power = 0;
    private int maxPower = 0;
    private int powerScale = 1;
    private int rechargeRate = 1;
    private int rangeScale = 1;
    public List<BlockPos> joinedAltars = new ArrayList<>();
    private boolean artifactUpdates = false;

    public AltarEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ALTAR_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> coreEntity.power;
                    case 1 -> coreEntity.maxPower;
                    case 2 -> coreEntity.rechargeRate;
                    case 3 -> coreEntity.powerScale;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> coreEntity.power = value;
                    case 1 -> coreEntity.maxPower = value;
                    case 2 -> coreEntity.rechargeRate = value;
                    case 3 -> coreEntity.powerScale = value;
                }
            }

            public int size() {
                return delegateSize;
            }
        };
    }

    @Override
    public void markRemoved() {
        super.markRemoved();

        if (world != null && !world.isClient) {
            ((ServerWorldVariables)world).removeAltar(this);
        }

        PoweredBlockEntity.recheckAltars(world);
    }

    @Override
    public void cancelRemoval() {
        super.cancelRemoval();

        if (world != null && !world.isClient) {
            if (!((ServerWorldVariables) world).getAltars().contains(this)) {
                ((ServerWorldVariables) world).addAltar(this);
            }
        }

        PoweredBlockEntity.recheckAltars(world);
    }

    public AltarEntity getCoreEntity() {
        return coreEntity;
    }

    public void setCoreEntity(AltarEntity coreEntity) {
        this.coreEntity = coreEntity;
        this.corePos = coreEntity.getPos();
        this.coreEntity.joinedAltars.add(this.getPos());
        markDirty();
        coreEntity.markDirty();
        world.updateListeners(getPos(), getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
        world.updateListeners(coreEntity.getPos(), coreEntity.getCachedState(), coreEntity.getCachedState(), Block.NOTIFY_LISTENERS);
    }

    public void setClientPower(World world, int power) {
        if (world.isClient) {
            this.power = power;
        }
    }

    public int getPower() {
        return power;
    }

    public boolean checkAndDecrementPower(int amount) {
        if (this.power > amount) {
            this.power -= amount;
            return true;
        }
        return false;
    }

    public int getRangeScale() {
        return rangeScale;
    }

    public void markForArtifactUpdates() {
        artifactUpdates = true;
    }

    public void updateArtifacts() {
        Map<BlockPos, Block> artifacts = new HashMap<>();
        for (BlockPos pos : this.joinedAltars) {
            artifacts.put(pos.up(), world.getBlockState(pos.up()).getBlock());
        }

        int tempRechargeRate = 1;
        int tempPowerScale = 1;
        int tempRangeScale = 1;
        boolean headFound = false;
        boolean candleFound = false;
        boolean pentacleFound = false;
        boolean chaliceFound = false;
        boolean arthanaFound = false;
        boolean eggFound = false;

        for (Block block : artifacts.values()) {
            if (!headFound && block == Blocks.PLAYER_HEAD) {
                tempRechargeRate += 3;
                tempPowerScale += 3;
                headFound = true;
            }
            if (!headFound && block == Blocks.WITHER_SKELETON_SKULL) {
                tempRechargeRate += 2;
                tempPowerScale += 2;
                headFound = true;
            }
            if (!headFound && block == Blocks.SKELETON_SKULL) {
                tempRechargeRate += 1;
                tempPowerScale += 1;
                headFound = true;
            }
        }
        for (Block block : artifacts.values()) {
            if (!candleFound && block == ModBlocks.CANDELABRA) {
                tempRechargeRate += 2;
                candleFound = true;
            }
            if (!candleFound && block == Blocks.TORCH) {
                tempRechargeRate += 1;
                candleFound = true;
            }
        }
        for (Map.Entry<BlockPos, Block> entry : artifacts.entrySet()) {
            if (!pentacleFound && entry.getValue() == ModBlocks.PLACED_ITEM) {
                if (((PlacedItemEntity)world.getBlockEntity(entry.getKey())).getItemStack().getItem() == ModItems.PENTACLE) {
                    tempRechargeRate *= 2;
                    pentacleFound = true;
                }
            }
        }
        for (Block block : artifacts.values()) {
            if (!chaliceFound && block == ModBlocks.FILLED_CHALICE) {
                tempPowerScale += 2;
                chaliceFound = true;
            }
            if (!chaliceFound && block == ModBlocks.CHALICE) {
                tempPowerScale += 1;
                chaliceFound = true;
            }
        }

        for (Map.Entry<BlockPos, Block> entry : artifacts.entrySet()) {
            if (!arthanaFound && entry.getValue() == ModBlocks.PLACED_ITEM) {
                if (((PlacedItemEntity)world.getBlockEntity(entry.getKey())).getItemStack().getItem() == ModItems.ARTHANA) {
                    tempRangeScale = 2;
                    arthanaFound = true;
                }
            }
        }

        for (Block block : artifacts.values()) {
            if (!eggFound && block == ModBlocks.INFINITY_EGG) {
                tempRechargeRate *= 10;
                tempPowerScale *= 10;
                eggFound = true;
            }
        }

        coreEntity.rechargeRate = tempRechargeRate;
        coreEntity.powerScale = tempPowerScale;
        coreEntity.rangeScale = tempRangeScale;

        PoweredBlockEntity.recheckAltars(world);

        coreEntity.markDirty();
        world.updateListeners(coreEntity.getPos(), coreEntity.getCachedState(), coreEntity.getCachedState(), Block.NOTIFY_LISTENERS);

        coreEntity.artifactUpdates = false;
    }

    public void updateSources(World world, BlockPos pos) {
        PowerSources powerSources = new PowerSources();

        for(int var12 = pos.getY() - 14; var12 <= pos.getY() + 14; ++var12) {
            for(int var16 = pos.getZ() + 14; var16 >= pos.getZ() - 14; --var16) {
                for(int var15 = pos.getX() - 14; var15 <= pos.getX() + 14; ++var15) {
                    Block block = world.getBlockState(new BlockPos(var15, var12, var16)).getBlock();
                    powerSources.addBlock(block);
                }
            }
        }

        this.maxPower = powerSources.getPower();
        this.markDirty();
        world.updateListeners(getPos(), getCachedState(), getCachedState(), Block.NOTIFY_LISTENERS);
    }

    public boolean withinRange(World world, BlockPos pos) {
        if (world == this.getWorld()) {
            int px = pos.getX();
            int py = pos.getY();
            int pz = pos.getZ();
            int ax = this.getPos().getX();
            int ay = this.getPos().getY();
            int az = this.getPos().getZ();
            double distance = Math.sqrt(Math.abs(Math.pow(px - ax, 2) + Math.pow(py - ay, 2) + Math.pow(pz - az, 2)));
            int maxDistance = this.getRangeScale() == 1 ? 17 : 32;
            return distance < maxDistance;
        }
        return false;
    }



    private int ticks = 0;
    public static void tick(World world, BlockPos pos, BlockState state, AltarEntity entity) {
        if (world.isClient) {
            return;
        }
        // We need this to load from nbt, since we can't get the block entity before the world is loaded.
        if (entity.coreEntity == null && entity.corePos != null) {
            entity.coreEntity = (AltarEntity) world.getBlockEntity(entity.corePos);
            if (entity.coreEntity == null) {
                entity.corePos = null;
            }
        }

        float maxPowerScaled = entity.maxPower * (float)entity.powerScale;
        if (entity.coreEntity == entity) {
            if (entity.ticks >= 20) {
                if (entity.artifactUpdates) {
                    entity.updateArtifacts();
                }
                if (entity.power < maxPowerScaled) {
                    entity.power = (int) Math.min(entity.power + 10.0F * (float)entity.rechargeRate, maxPowerScaled);
                    entity.markDirty();
                }
                else if (entity.power > maxPowerScaled) {
                    entity.power = (int) maxPowerScaled;
                    entity.markDirty();
                }
                entity.ticks = 0;
            } else {
                entity.ticks++;
            }
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.witchery2.altar");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AltarScreenHandler(syncId, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        BlockPos pos = Objects.requireNonNullElse(coreEntity, this).getPos();
        nbt.putIntArray("corePos", Arrays.asList(pos.getX(), pos.getY(), pos.getZ()));
        nbt.putInt("power", power);
        nbt.putInt("maxPower", maxPower);
        nbt.putInt("powerScale", powerScale);
        nbt.putInt("rechargeRate", rechargeRate);
        nbt.putInt("rangeScale", rangeScale);
        NbtList joinedAltarNbt = new NbtList();
        for (BlockPos p : joinedAltars) {
            joinedAltarNbt.add(new NbtIntArray(Arrays.asList(p.getX(), p.getY(), p.getZ())));
        }
        nbt.put("joinedAltars", joinedAltarNbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        // We do this instead of setting the core entity because we cannot get the world before it is loaded.
        corePos = new BlockPos(nbt.getIntArray("corePos")[0], nbt.getIntArray("corePos")[1], nbt.getIntArray("corePos")[2]);
        power = nbt.getInt("power");
        maxPower = nbt.getInt("maxPower");
        powerScale = nbt.getInt("powerScale");
        rechargeRate = nbt.getInt("rechargeRate");
        rangeScale = nbt.getInt("rangeScale");
        joinedAltars = new ArrayList<>();
        for (NbtElement element : nbt.getList("joinedAltars", 11)) {
            NbtIntArray intArray = (NbtIntArray) element;
            joinedAltars.add(new BlockPos(intArray.get(0).intValue(), intArray.get(1).intValue(), intArray.get(2).intValue()));
        }
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
}
