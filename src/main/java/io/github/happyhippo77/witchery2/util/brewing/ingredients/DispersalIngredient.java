package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.util.brewing.DispersalType;
import net.minecraft.item.Item;

public class DispersalIngredient extends AbstractIngredient {

    private final DispersalType dispersalType;

    public DispersalIngredient(Item givenItem, int givenRequiredPower, DispersalType dispersalType) {
        super(givenItem, givenRequiredPower);
        this.dispersalType = dispersalType;
    }

    @Override
    public IngredientUse getUse() {
        return IngredientUse.DISPERSAL;
    }

    public DispersalType getDispersalType() {
        return dispersalType;
    }
}
