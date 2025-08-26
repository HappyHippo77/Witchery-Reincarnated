package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.util.PlaceableItemInterface;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;

public class Arthana extends SwordItem implements PlaceableItemInterface {

    public Arthana(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return place(context);
    }
}
