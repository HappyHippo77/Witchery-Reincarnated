package io.github.happyhippo77.witchery2.world;


import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.world.trees.WitcheryFoliagePlacer;
import io.github.happyhippo77.witchery2.world.trees.AlderTrunkPlacer;
import io.github.happyhippo77.witchery2.world.trees.HawthornTrunkPlacer;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ROWAN_KEY = registerKey("rowan");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ALDER_KEY = registerKey("alder");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HAWTHORN_KEY = registerKey("hawthorn");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        register(context, ROWAN_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ROWAN_LOG),
                new StraightTrunkPlacer(5, 3, 0),
                BlockStateProvider.of(ModBlocks.ROWAN_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 2)).build());
        register(context, ALDER_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ALDER_LOG),
                new AlderTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(ModBlocks.ALDER_LEAVES),
                new WitcheryFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(1)),
                new TwoLayersFeatureSize(0, 0, 0)).build());
        register(context, HAWTHORN_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.HAWTHORN_LOG),
                new HawthornTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(ModBlocks.HAWTHORN_LEAVES),
                new WitcheryFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(1)),
                new TwoLayersFeatureSize(0, 0, 0)).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Witchery2.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
