package io.github.happyhippo77.witchery2.customrecipe.recipes;

import io.github.happyhippo77.witchery2.customrecipe.CustomRecipe;
import io.github.happyhippo77.witchery2.customrecipe.CustomRecipeType;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.Pair;
import io.github.happyhippo77.witchery2.util.Quadruple;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class DistilleryRecipe extends CustomRecipe {
    private final Pair<ItemStack> input;
    private final Quadruple<ItemStack> output;
    private final int jars;

    public DistilleryRecipe(Pair<ItemStack> input, Quadruple<ItemStack> output, int jars) {
        super(CustomRecipeType.DISTILLERY);
        this.input = input;
        this.output = output;
        this.jars = jars;
    }

    public Quadruple<ItemStack> getOutput() {
        return output;
    }

    public int getJars() {
        return jars;
    }

    @Override
    public boolean matches(DefaultedList<ItemStack> inventory) {
        boolean oneInput = input.first.isEmpty() || input.second.isEmpty();

        if (oneInput) {
            if (input.first.isEmpty()) {
                if ((inventory.get(1).isItemEqual(input.second) && inventory.get(2).isEmpty()) || (inventory.get(2).isItemEqual(input.second) && inventory.get(1).isEmpty())) {
                    return inventory.get(0).getItem() == ModItems.CLAY_JAR &&
                            inventory.get(0).getCount() >= jars;
                }
            } else {
                if ((inventory.get(1).isItemEqual(input.first) && inventory.get(2).isEmpty()) || (inventory.get(2).isItemEqual(input.first) && inventory.get(1).isEmpty())) {
                    return inventory.get(0).getItem() == ModItems.CLAY_JAR &&
                            inventory.get(0).getCount() >= jars;
                }
            }
        }
        return (inventory.get(1).isItemEqual(input.first) || inventory.get(1).isItemEqual(input.second)) &&
                        (inventory.get(2).isItemEqual(input.first) || inventory.get(2).isItemEqual(input.second)) &&
                        inventory.get(0).getItem() == ModItems.CLAY_JAR &&
                        inventory.get(0).getCount() >= jars;
    }

    private boolean isSlotFillable(ItemStack slot, ItemStack output) {
        return output.isEmpty() || slot.isEmpty() || (slot.isItemEqual(output) && slot.getCount() + output.getCount() <= slot.getMaxCount());
    }

    @Override
    public boolean canCraft(DefaultedList<ItemStack> inventory) {
        return
                isSlotFillable(inventory.get(3), output.first) &&
                isSlotFillable(inventory.get(4), output.second) &&
                isSlotFillable(inventory.get(5), output.third) &&
                isSlotFillable(inventory.get(6), output.fourth);
    }
}
