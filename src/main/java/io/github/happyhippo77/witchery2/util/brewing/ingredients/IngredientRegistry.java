package io.github.happyhippo77.witchery2.util.brewing.ingredients;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.Range;
import io.github.happyhippo77.witchery2.util.brewing.DispersalType;
import io.github.happyhippo77.witchery2.util.brewing.Effects;
import io.github.happyhippo77.witchery2.util.brewing.Modifiers;
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
    private static Range effectModifierIndices;
    private static Range brewModifierIndices;
    private static Range extentIndices;
    private static Range lingerIndices;
    private static Range dispersalIndices;
    private static Range effectIndices;
    private static Range genericIndices;

    private static final List<CapacityIngredient> capacityIngredients = Arrays.asList(
            new CapacityIngredient(ModItems.MANDRAKE_ROOT, 0, 1, 1),
            new CapacityIngredient(Items.NETHER_WART, 50, 2, 2),
            // Tear of the goddess, 100, 2, 4
            // Diamond vapor, 150, 2, 6
            new CapacityIngredient(Items.DIAMOND, 150, 2, 8),
            // Nether star, 150, 4, 10
            new CapacityIngredient(ModBlocks.PENTACLE.asItem(), 1000, 6, 16)
    );
    private static final List<PowerIngredient> powerIngredients = Arrays.asList(
            new PowerIngredient(Items.GLOWSTONE_DUST, 50, 2),
            new PowerIngredient(Items.BLAZE_ROD, 100, 3)
            // Charged Attuned Stone, 150, 4
    );
    private static final List<DurationIngredient> durationIngredients = Arrays.asList(
            new DurationIngredient(Items.REDSTONE, 50, 2),
            new DurationIngredient(Items.OBSIDIAN, 100, 3)
            // Minedrake Bulb, 150, 4
    );
    private static final List<EffectModifierIngredient> effectModifierIngredients = Arrays.asList(
            new EffectModifierIngredient(Items.GOLD_NUGGET, 50, Modifiers.NO_PARTICLES),
            new EffectModifierIngredient(Items.FERMENTED_SPIDER_EYE, 25, Modifiers.INVERT),
            new EffectModifierIngredient(Items.NETHER_BRICK, 50, Modifiers.SKIP_BLOCK),
            new EffectModifierIngredient(Items.BRICK, 50, Modifiers.SKIP_ENTITY)
    );
    private static final List<BrewModifierIngredient> brewModifierIngredients = Arrays.asList(
            new BrewModifierIngredient(ModItems.ROWAN_BERRIES, 50, Modifiers.QUAFFING_MINUS_8),
            new BrewModifierIngredient(ModItems.EXHALE_OF_THE_HORNED_ONE, 0, Modifiers.QUAFFING_MINUS_4),
            new BrewModifierIngredient(ModBlocks.SPANISH_MOSS.asItem(), 50, Modifiers.QUAFFING_MINUS_4),
            new BrewModifierIngredient(Items.WHITE_WOOL, 0, Modifiers.WHITE),
            new BrewModifierIngredient(Items.LIGHT_GRAY_WOOL, 0, Modifiers.LIGHT_GRAY),
            new BrewModifierIngredient(Items.GRAY_WOOL, 0, Modifiers.GRAY),
            new BrewModifierIngredient(Items.BLACK_WOOL, 0, Modifiers.BLACK),
            new BrewModifierIngredient(Items.BROWN_WOOL, 0, Modifiers.BROWN),
            new BrewModifierIngredient(Items.RED_WOOL, 0, Modifiers.RED),
            new BrewModifierIngredient(Items.ORANGE_WOOL, 0, Modifiers.ORANGE),
            new BrewModifierIngredient(Items.YELLOW_WOOL, 0, Modifiers.YELLOW),
            new BrewModifierIngredient(Items.LIME_WOOL, 0, Modifiers.LIME),
            new BrewModifierIngredient(Items.GREEN_WOOL, 0, Modifiers.GREEN),
            new BrewModifierIngredient(Items.CYAN_WOOL, 0, Modifiers.CYAN),
            new BrewModifierIngredient(Items.LIGHT_BLUE_WOOL, 0, Modifiers.LIGHT_BLUE),
            new BrewModifierIngredient(Items.BLUE_WOOL, 0, Modifiers.BLUE),
            new BrewModifierIngredient(Items.PURPLE_WOOL, 0, Modifiers.PURPLE),
            new BrewModifierIngredient(Items.MAGENTA_WOOL, 0, Modifiers.MAGENTA),
            new BrewModifierIngredient(Items.PINK_WOOL, 0, Modifiers.PINK)
    );
    private static final List<ExtentIngredient> extentIngredients = Arrays.asList(
            new ExtentIngredient(ModItems.WOOD_ASH, 50, 2),
            new ExtentIngredient(Items.COCOA_BEANS, 100, 3)
            // Wispy Cotton, 150, 4
    );
    private static final List<LingerIngredient> lingerIngredients = Arrays.asList(
            new LingerIngredient(ModItems.BELLADONNA_FLOWER, 50, 2),
            new LingerIngredient(Items.LAPIS_LAZULI, 100, 3),
            new LingerIngredient(Items.END_STONE, 100, 4)
    );
    private static final List<DispersalIngredient> dispersalIngredients = Arrays.asList(
            new DispersalIngredient(Items.GUNPOWDER, 0, DispersalType.INSTANT),
            new DispersalIngredient(ModItems.WATER_ARTICHOKE_GLOBE, 0, DispersalType.INSTANT),
            // Wool of Bat, 0, DispersalType.GAS
            // Wormwood, 0, DispersalType.LIQUID
            new DispersalIngredient(Items.ZOMBIE_HEAD, 0, DispersalType.TRIGGER)
    );
    private static final List<EffectIngredient> effectIngredients = Arrays.asList(
            new EffectIngredient(Items.SNOWBALL, 0, 1, Effects.SNOW),
            new EffectIngredient(Items.COD, 0, 1, Effects.SWIM_SPEED)
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
        index = register(index, effectModifierIngredients);
        effectModifierIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, brewModifierIngredients);
        brewModifierIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, extentIngredients);
        extentIndices = new Range(lastIndex, index - 1);

        lastIndex = index;
        index = register(index, lingerIngredients);
        lingerIndices = new Range(lastIndex, index - 1);

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
            case EFFECT_MODIFIER -> range = effectModifierIndices;
            case BREW_MODIFIER -> range = brewModifierIndices;
            case EXTENT -> range = extentIndices;
            case LINGER -> range = lingerIndices;
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
