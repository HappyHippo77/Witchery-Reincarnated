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
import io.github.happyhippo77.witchery2.world.trees.ModPlacers;
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
			content.add(new ItemStack(ModBlocks.ROWAN_LOG));
			content.add(new ItemStack(ModBlocks.ROWAN_WOOD));
			content.add(new ItemStack(ModBlocks.STRIPPED_ROWAN_LOG));
			content.add(new ItemStack(ModBlocks.STRIPPED_ROWAN_WOOD));
			content.add(new ItemStack(ModBlocks.ROWAN_PLANKS));
			content.add(new ItemStack(ModBlocks.ROWAN_STAIRS));
			content.add(new ItemStack(ModBlocks.ROWAN_SLAB));
			content.add(new ItemStack(ModBlocks.ROWAN_FENCE));
			content.add(new ItemStack(ModBlocks.ROWAN_FENCE_GATE));
			content.add(new ItemStack(ModBlocks.ROWAN_DOOR));
			content.add(new ItemStack(ModBlocks.ROWAN_TRAPDOOR));
			content.add(new ItemStack(ModBlocks.ROWAN_PRESSURE_PLATE));
			content.add(new ItemStack(ModBlocks.ROWAN_BUTTON));
			content.add(new ItemStack(ModBlocks.ALDER_LOG));
			content.add(new ItemStack(ModBlocks.ALDER_WOOD));
			content.add(new ItemStack(ModBlocks.STRIPPED_ALDER_LOG));
			content.add(new ItemStack(ModBlocks.STRIPPED_ALDER_WOOD));
			content.add(new ItemStack(ModBlocks.ALDER_PLANKS));
			content.add(new ItemStack(ModBlocks.ALDER_STAIRS));
			content.add(new ItemStack(ModBlocks.ALDER_SLAB));
			content.add(new ItemStack(ModBlocks.ALDER_FENCE));
			content.add(new ItemStack(ModBlocks.ALDER_FENCE_GATE));
			content.add(new ItemStack(ModBlocks.ALDER_DOOR));
			content.add(new ItemStack(ModBlocks.ALDER_TRAPDOOR));
			content.add(new ItemStack(ModBlocks.ALDER_PRESSURE_PLATE));
			content.add(new ItemStack(ModBlocks.ALDER_BUTTON));
			content.add(new ItemStack(ModBlocks.HAWTHORN_LOG));
			content.add(new ItemStack(ModBlocks.HAWTHORN_WOOD));
			content.add(new ItemStack(ModBlocks.STRIPPED_HAWTHORN_LOG));
			content.add(new ItemStack(ModBlocks.STRIPPED_HAWTHORN_WOOD));
			content.add(new ItemStack(ModBlocks.HAWTHORN_PLANKS));
			content.add(new ItemStack(ModBlocks.HAWTHORN_STAIRS));
			content.add(new ItemStack(ModBlocks.HAWTHORN_SLAB));
			content.add(new ItemStack(ModBlocks.HAWTHORN_FENCE));
			content.add(new ItemStack(ModBlocks.HAWTHORN_FENCE_GATE));
			content.add(new ItemStack(ModBlocks.HAWTHORN_DOOR));
			content.add(new ItemStack(ModBlocks.HAWTHORN_TRAPDOOR));
			content.add(new ItemStack(ModBlocks.HAWTHORN_PRESSURE_PLATE));
			content.add(new ItemStack(ModBlocks.HAWTHORN_BUTTON));

			content.add(new ItemStack(ModItems.ROWAN_SIGN));
			content.add(new ItemStack(ModItems.ALDER_SIGN));
			content.add(new ItemStack(ModItems.HAWTHORN_SIGN));

			content.add(new ItemStack(ModBlocks.ROWAN_LEAVES));
			content.add(new ItemStack(ModBlocks.ALDER_LEAVES));
			content.add(new ItemStack(ModBlocks.HAWTHORN_LEAVES));

			content.add(new ItemStack(ModBlocks.ROWAN_SAPLING));
			content.add(new ItemStack(ModBlocks.ALDER_SAPLING));
			content.add(new ItemStack(ModBlocks.HAWTHORN_SAPLING));
			content.add(new ItemStack(ModBlocks.GLINT_WEED));
			content.add(new ItemStack(ModBlocks.SPANISH_MOSS));
			content.add(new ItemStack(ModBlocks.EMBER_MOSS));

			content.add(new ItemStack(ModBlocks.WITCHS_CAULDRON));
			content.add(new ItemStack(ModBlocks.WITCHS_OVEN));
			content.add(new ItemStack(ModBlocks.FUME_FUNNEL));
			content.add(new ItemStack(ModBlocks.FILTERED_FUME_FUNNEL));
			content.add(new ItemStack(ModBlocks.ALTAR));
			content.add(new ItemStack(ModBlocks.INFINITY_EGG));
			content.add(new ItemStack(ModBlocks.CANDELABRA));
			content.add(new ItemStack(ModBlocks.CHALICE));
			content.add(new ItemStack(ModBlocks.FILLED_CHALICE));
			content.add(new ItemStack(ModItems.PENTACLE));
			content.add(new ItemStack(ModItems.ARTHANA));

			content.add(new ItemStack(ModBlocks.OAK_STOCKADE));
			content.add(new ItemStack(ModBlocks.SPRUCE_STOCKADE));
			content.add(new ItemStack(ModBlocks.BIRCH_STOCKADE));
			content.add(new ItemStack(ModBlocks.JUNGLE_STOCKADE));
			content.add(new ItemStack(ModBlocks.ACACIA_STOCKADE));
			content.add(new ItemStack(ModBlocks.DARK_OAK_STOCKADE));
			content.add(new ItemStack(ModBlocks.MANGROVE_STOCKADE));
			content.add(new ItemStack(ModBlocks.ICE_STOCKADE));
			content.add(new ItemStack(ModBlocks.ROWAN_STOCKADE));
			content.add(new ItemStack(ModBlocks.ALDER_STOCKADE));
			content.add(new ItemStack(ModBlocks.HAWTHORN_STOCKADE));

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
			content.add(new ItemStack(ModItems.ROWAN_BERRIES));

			content.add(new ItemStack(ModItems.ANOINTING_PASTE));
			content.add(new ItemStack(ModItems.MUTANDIS));
			content.add(new ItemStack(ModItems.WOOD_ASH));
			content.add(new ItemStack(ModItems.QUICKLIME));
			content.add(new ItemStack(ModItems.SOFT_CLAY_JAR));
			content.add(new ItemStack(ModItems.CLAY_JAR));
			content.add(new ItemStack(ModItems.FOUL_FUME));
			content.add(new ItemStack(ModItems.EXHALE_OF_THE_HORNED_ONE));
			content.add(new ItemStack(ModItems.BREATH_OF_THE_GODDESS));
			content.add(new ItemStack(ModItems.HINT_OF_REBIRTH));
			content.add(new ItemStack(ModItems.WHIFF_OF_MAGIC));
			content.add(new ItemStack(ModItems.ODOUR_OF_PURITY));
			content.add(new ItemStack(ModItems.REEK_OF_MISFORTUNE));

			content.add(new ItemStack(ModItems.KOBOLDITE_DUST));
			content.add(new ItemStack(ModItems.KOBOLDITE_NUGGET));
			content.add(new ItemStack(ModItems.KOBOLDITE_INGOT));
			content.add(new ItemStack(ModItems.FUME_FILTER));
			content.add(new ItemStack(ModItems.REDSTONE_SOUP));
			content.add(new ItemStack(ModItems.ROWAN_DOOR_KEY));
			content.add(new ItemStack(ModItems.ATTUNED_STONE));

			content.add(new ItemStack(ModItems.EARMUFFS));
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
		ModPlacers.registerAllPlacers();

		ModScreenHandlers.registerAllScreenHandlers();

		ModRecipes.registerRecipes();

		IngredientRegistry.registerIngredients();

		FabricDefaultAttributeRegistry.register(ModEntities.MANDRAKE, MandrakeEntity.setAttributes());
	}
}
