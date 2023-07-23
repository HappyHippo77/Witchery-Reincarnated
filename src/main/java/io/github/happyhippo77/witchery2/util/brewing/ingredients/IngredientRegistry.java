package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.Range;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientRegistry {
    private static final List<AbstractIngredient> ingredients = new ArrayList<>();

    private static Range capacityIndices;
    private static Range powerIndices;
    private static Range durationIndices;
    private static Range modifierIndices;
    private static Range dispersalIndices;
    private static Range effectIndices;
    private static Range genericIndices;

    private static final List<CapacityIngredient> capacityIngredients = Arrays.asList(
            new CapacityIngredient(ModItems.MANDRAKE_ROOT, 0, 1, 0),
            new CapacityIngredient(Items.NETHER_WART, 0, 2, 1)
            // ...
    );
    private static final List<PowerIngredient> powerIngredients = Arrays.asList(
            new PowerIngredient(Items.GLOWSTONE_DUST, 50, 0),
            new PowerIngredient(Items.BLAZE_ROD, 100, 1)
            // Attuned Stone, 150, 2
    );
    private static final List<DurationIngredient> durationIngredients = Arrays.asList(
            new DurationIngredient(Items.REDSTONE, 50, 0),
            new DurationIngredient(Items.OBSIDIAN, 100, 1)
            // Mandrake Bulb, 150, 2
    );
    private static final List<ModifierIngredient> modifierIngredients = Arrays.asList(
            new ModifierIngredient(Items.GOLD_NUGGET, 50, "no_particles"),
            new ModifierIngredient(Items.FERMENTED_SPIDER_EYE, 25, "invert")
            // ...
            // FIGURE OUT HOW TO HANDLE THIS
    );
    private static final List<DispersalIngredient> dispersalIngredients = Arrays.asList(
            new DispersalIngredient(Items.GUNPOWDER, 0, DispersalType.INSTANT),
            new DispersalIngredient(ModItems.WATER_ARTICHOKE_GLOBE, 0, DispersalType.INSTANT),
            // Wool of Bat, 0, DispersalType.GAS
            // Wormwood, 0, DispersalType.LIQUID
            new DispersalIngredient(Items.ZOMBIE_HEAD, 0, DispersalType.TRIGGER)
    );
    private static final List<EffectIngredient> effectIngredients = Arrays.asList(
            new EffectIngredient(Items.SNOWBALL, 0, 1, "snow"),
            new EffectIngredient(Items.COD, 0, 1, "swim_speed")
            // ...
            // FIGURE OUT HOW TO HANDLE THIS
    );
    private static final List<GenericIngredient> genericIngredients = Arrays.asList(
            new GenericIngredient(Items.PORKCHOP, 0),
            new GenericIngredient(Items.BEEF, 0),
            new GenericIngredient(Items.CHICKEN, 0),
            new GenericIngredient(Items.MUTTON, 0),
            new GenericIngredient(Items.RABBIT, 0),
            new GenericIngredient(ModItems.EXHALE_OF_THE_HORNED_ONE, 0),
            new GenericIngredient(Items.EGG, 0)
            // ...
    );

    public static void registerIngredients() {
        int index;
        index = register(0, capacityIngredients);
        capacityIndices = new Range(0, index - 1);

        int lastIndex = index;
        index = register(index, powerIngredients);
        powerIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, durationIngredients);
        durationIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, modifierIngredients);
        modifierIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, dispersalIngredients);
        dispersalIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, effectIngredients);
        effectIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, genericIngredients);
        genericIndices = new Range(lastIndex, index - 1);
    }

    private static int register(int firstIndex, List<? extends AbstractIngredient> list) {
        int index = firstIndex;
        for (AbstractIngredient ingredient : list) {
            ingredients.add(index, ingredient);
            index++;
        }
        return index;
    }

    public List<AbstractIngredient> getIngredients() {
        return ingredients;
    }

    public static boolean isIngredient(Item item) {
        boolean match = false;
        for (AbstractIngredient ingredient : ingredients) {
            if (ingredient.getItem().equals(item)) {
                match = true;
                break;
            }
        }
        return match;
    }
    public static boolean isIngredientType(Item item, IngredientUse use) {
        Range range = new Range(-2, -2);
        switch (use) {
            case CAPACITY -> range = capacityIndices;
            case POWER -> range = powerIndices;
            case DURATION -> range = durationIndices;
            case MODIFIER -> range = modifierIndices;
            case DISPERSAL -> range = dispersalIndices;
            case EFFECT -> range = effectIndices;
            case GENERIC -> range = genericIndices;
        }

        int index = -1;
        for (AbstractIngredient ingredient : ingredients) {
            if (ingredient.getItem().equals(item)) {
                index = ingredients.indexOf(ingredient);
            }
        }
        return range.contains(index);
    }

    public static AbstractIngredient fromItem(Item item) {
        for (AbstractIngredient ingredient : ingredients) {
            if (ingredient.getItem().equals(item)) {
                return ingredient;
            }
        }
        return null;
    }
}
