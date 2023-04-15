package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Earmuffs extends ArmorItem {
    public Earmuffs(Settings settings) {
        super(ArmorMaterials.LEATHER, Type.HELMET, settings);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.COMMON;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack != null) {
            Text localText = Text.translatable((this.getTranslationKey() + ".tip"));
            List<Text> arr = new ArrayList<>();
        for (String s : localText.getString().split("\n")) {
                arr.add(Text.literal(s));
            }

            tooltip.addAll(arr);
        }
    }




}
