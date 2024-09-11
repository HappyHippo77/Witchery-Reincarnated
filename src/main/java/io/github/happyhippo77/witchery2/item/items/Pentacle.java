package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.util.PlaceableItemInterface;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class Pentacle extends Item implements PlaceableItemInterface {
    public Pentacle(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return place(context);
    }
}
