package io.github.happyhippo77.witchery2.screen;

import io.github.happyhippo77.witchery2.block.entity.entities.AltarEntity;
import io.github.happyhippo77.witchery2.block.entity.entities.WitchsOvenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AltarScreenHandler extends ScreenHandler {
    public final PropertyDelegate propertyDelegate;

    public AltarScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, new ArrayPropertyDelegate(AltarEntity.delegateSize));
    }
    public AltarScreenHandler(int syncId, PropertyDelegate delegate) {
        super(ModScreenHandlers.ALTAR_SCREEN_HANDLER, syncId);
        this.propertyDelegate = delegate;
        addProperties(delegate);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
