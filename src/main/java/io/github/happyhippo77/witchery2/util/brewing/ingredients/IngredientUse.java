package io.github.happyhippo77.witchery2.util.brewing.ingredients;

public enum IngredientUse {
    CAPACITY,
    POWER,
    DURATION,
    MODIFIER,
    DISPERSAL,
    EFFECT,
    GENERIC;

    public int getOrder() {
        return switch (this) {
            case CAPACITY -> 0;
            case POWER -> 1;
            case DURATION -> 2;
            case MODIFIER -> 3;
            case DISPERSAL -> 4;
            case EFFECT -> 5;
            case GENERIC -> 6;
        };
    }
}
