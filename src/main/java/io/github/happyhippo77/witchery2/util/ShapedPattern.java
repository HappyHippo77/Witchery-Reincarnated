package io.github.happyhippo77.witchery2.util;

import net.minecraft.item.ItemConvertible;

import java.util.*;

public class ShapedPattern {
    private final String row1;
    private final String row2;
    private final String row3;

    private final Map<Character, ItemConvertible> ingredients = new HashMap<>();

    public ShapedPattern(Map<ItemConvertible, List<Integer>> items) {
        Map<ItemConvertible, List<Integer>> itemsToAdd = new HashMap<>();

        int num1 = 0;
        for (ItemConvertible item : items.keySet()) {
            List<Integer> validatedList = new ArrayList<>();

            int num2 = 0;
            for (Integer i : items.get(item)) {
                //System.out.println(item);
                //System.out.println(!itemsToAdd.isEmpty() ? (itemsToAdd.values().toArray().length >= num1 ? ((List<Integer>) itemsToAdd.values().toArray()[num1-1]).get(num2) : "outOfBounds") : "empty");
                if (validatedList.contains(i)) {
                    throw new RuntimeException("ShapedPattern ingredients must have unique slots");
                } else if (i > 8 || i < 0) {
                    throw new RuntimeException("ShapedPattern ingredient slots must be 0-8");
                } else if (!itemsToAdd.isEmpty()) {
                    if (((List<Integer>) itemsToAdd.values().toArray()[num1-1]).size() > num2) {
                        if (((List<Integer>) itemsToAdd.values().toArray()[num1-1]).get(num2) == i) {
                            throw new RuntimeException("ShapedPattern ingredients must have unique slots");
                        }
                    }
                }
                else {
                    validatedList.add(i);
                }
                validatedList.add(i);
                num2++;
            }

            itemsToAdd.put(item, validatedList);
            num1++;
        }

        char[] slots = new char[9];
        int num3 = 0;
        for (ItemConvertible item : itemsToAdd.keySet()) {
            char label = (char) (num3 + '0');
            ingredients.put(label, item);

            if (num3 > 8) {
                throw new RuntimeException("ShapedPatterns cannot have more than 9 ingredients");
            } else {
                for (Integer i : itemsToAdd.get(item)) {
                    slots[i] = label;
                }
            }
            num3++;
        }
        int num4 = 0;
        for (char s : slots) {
            if (s == '\0') {
                slots[num4] = ' ';
            }
            num4++;
        }
        this.row1 = Character.toString(slots[0]) + slots[1] + slots[2];
        this.row2 = Character.toString(slots[3]) + slots[4] + slots[5];
        this.row3 = Character.toString(slots[6]) + slots[7] + slots[8];
    }

    public Map<Character, ItemConvertible> getIngredients() {
        return ingredients;
    }

    public String getRow1() {
        return row1;
    }

    public String getRow2() {
        return row2;
    }

    public String getRow3() {
        return row3;
    }
}
