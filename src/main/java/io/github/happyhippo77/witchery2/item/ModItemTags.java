package io.github.happyhippo77.witchery2.item;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> ROWAN_LOGS = TagKey.of(RegistryKeys.ITEM, new Identifier(Witchery2.MOD_ID, "rowan_logs"));
}
