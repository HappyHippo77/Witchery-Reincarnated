package io.github.happyhippo77.witchery2.util.brewing;

public enum DispersalType {
    INSTANT,
    GAS,
    LIQUID,
    TRIGGER,
    BASIC;


    @Override
    public String toString() {
        return switch (this) {

            case INSTANT -> "instant";
            case GAS -> "gas";
            case LIQUID -> "liquid";
            case TRIGGER -> "trigger";
            case BASIC -> "basic";
        };
    }

    public static DispersalType fromString(String string) {
        return switch (string) {
            case "instant" -> INSTANT;
            case "gas" -> GAS;
            case "liquid" -> LIQUID;
            case "trigger" -> TRIGGER;
            default -> BASIC;
        };
    }
}
