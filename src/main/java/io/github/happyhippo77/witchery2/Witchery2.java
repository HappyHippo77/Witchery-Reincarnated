package io.github.happyhippo77.witchery2;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.block.item.ModBlockItems;
import io.github.happyhippo77.witchery2.entity.ModEntities;
import io.github.happyhippo77.witchery2.entity.entities.MandrakeEntity;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.particle.ModParticles;
import io.github.happyhippo77.witchery2.recipe.ModRecipes;
import io.github.happyhippo77.witchery2.screen.ModScreenHandlers;
import io.github.happyhippo77.witchery2.sounds.ModSounds;
import io.github.happyhippo77.witchery2.util.BubbleParticleDataSetter;
import io.github.happyhippo77.witchery2.util.PowerParticleDataSetter;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.IngredientRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Witchery2 implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "witchery2";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static BubbleParticleDataSetter bubbleParticleDataSetter = new BubbleParticleDataSetter();
	public static PowerParticleDataSetter powerParticleDataSetter = new PowerParticleDataSetter();

	// ItemGroups
	public static final ItemGroup WITCHERY2_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "main"))
			.icon(() -> new ItemStack(ModBlocks.WITCHS_CAULDRON))
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ItemGroupEvents.modifyEntriesEvent(WITCHERY2_GROUP).register(content -> {
			content.add(new ItemStack(ModBlocks.WITCHS_CAULDRON));
			content.add(new ItemStack(ModBlocks.WITCHS_OVEN));

			content.add(new ItemStack(ModItems.ANOINTING_PASTE));

			content.add(new ItemStack(ModItems.BELLADONNA_SEEDS));
			content.add(new ItemStack(ModItems.MANDRAKE_SEEDS));
			content.add(new ItemStack(ModItems.WATER_ARTICHOKE_SEEDS));
			content.add(new ItemStack(ModItems.SNOWBELL_SEEDS));
			content.add(new ItemStack(ModItems.WOLFSBANE_SEEDS));
			content.add(new ItemStack(ModItems.GARLIC));

			content.add(new ItemStack(ModItems.BELLADONNA_FLOWER));
			content.add(new ItemStack(ModItems.MANDRAKE_ROOT));
			content.add(new ItemStack(ModItems.WATER_ARTICHOKE_GLOBE));
			content.add(new ItemStack(ModItems.ICY_NEEDLE));
			content.add(new ItemStack(ModItems.WOLFSBANE));

			content.add(new ItemStack(ModBlocks.EMBER_MOSS));
			content.add(new ItemStack(ModBlocks.GLINT_WEED));
			content.add(new ItemStack(ModBlocks.SPANISH_MOSS));

			content.add(new ItemStack(ModItems.EXHALE_OF_THE_HORNED_ONE));
			content.add(new ItemStack(ModItems.MUTANDIS));
			content.add(new ItemStack(ModItems.CLAY_JAR));
			content.add(new ItemStack(ModItems.SOFT_CLAY_JAR));
			content.add(new ItemStack(ModItems.WOOD_ASH));
			content.add(new ItemStack(ModItems.QUICKLIME));
			content.add(new ItemStack(ModItems.EARMUFFS));

			content.add(new ItemStack(ModBlocks.ROWAN_LOG));
			content.add(new ItemStack(ModBlocks.ROWAN_WOOD));
			content.add(new ItemStack(ModBlocks.STRIPPED_ROWAN_LOG));
			content.add(new ItemStack(ModBlocks.STRIPPED_ROWAN_WOOD));
			content.add(new ItemStack(ModBlocks.ROWAN_PLANKS));
			content.add(new ItemStack(ModBlocks.ROWAN_LEAVES));
			content.add(new ItemStack(ModBlocks.ROWAN_SAPLING));

			content.add(new ItemStack(ModItems.ROWAN_BERRIES));
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(new ItemStack(ModItems.MANDRAKE_SPAWN_EGG));
		});

		ModSounds.registerAllSounds();
		ModBlocks.registerAllBlocks();
		ModBlockEntities.registerAllBlockEntities();
		ModBlockItems.registerAllBlocks();
		ModItems.registerAllItems();
		ModParticles.registerAllParticles();

		ModScreenHandlers.registerAllScreenHandlers();

		ModRecipes.registerRecipes();

		IngredientRegistry.registerIngredients();

		FabricDefaultAttributeRegistry.register(ModEntities.MANDRAKE, MandrakeEntity.setAttributes());
	}
}
