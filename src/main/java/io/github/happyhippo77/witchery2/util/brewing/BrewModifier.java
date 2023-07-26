package io.github.happyhippo77.witchery2.util.brewing;

import io.github.happyhippo77.witchery2.block.entity.entities.WitchsCauldronEntity;
import net.minecraft.item.ItemStack;

public abstract class BrewModifier extends Modifier {

    public BrewModifier(String text) {
        super(text);
    }

    public abstract void applyBrew(ItemStack brew);
    public abstract void applyCauldron(WitchsCauldronEntity cauldron);
}
