package io.github.happyhippo77.witchery2.util.brewing;

public enum CauldronLevel {
    EMPTY,
    LOW,
    MEDIUM,
    FULL;
    public int toInt() {
        switch (this) {
            case EMPTY -> {
                return 0;
            }
            case LOW -> {
                return 1;
            }
            case MEDIUM -> {
                return 2;
            }
            default -> {
                return 3;
            }
        }
    }

    public CauldronLevel fromInt(int i) {
        switch (i) {
            case 0 -> {
                return EMPTY;
            }
            case 1 -> {
                return LOW;
            }
            case 2 -> {
                return MEDIUM;
            }
            default -> {
                return FULL;
            }
        }
    }

    public CauldronLevel increase() {
        switch (this) {
            case EMPTY -> {
                return LOW;
            }
            case LOW -> {
                return CauldronLevel.MEDIUM;
            }
            default -> {
                return CauldronLevel.FULL;
            }
        }
    }

    public CauldronLevel decrease() {
        switch (this) {
            case FULL -> {
                return MEDIUM;
            }
            case MEDIUM -> {
                return CauldronLevel.LOW;
            }
            default -> {
                return CauldronLevel.EMPTY;
            }
        }
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isFull() {
        return this == FULL;
    }
}
