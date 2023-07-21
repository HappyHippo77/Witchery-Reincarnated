package io.github.happyhippo77.witchery2.world.trees;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.mixin.FoliagePlacerTypeInvoker;
import io.github.happyhippo77.witchery2.mixin.TrunkPlacerTypeInvoker;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModPlacers {
    public static TrunkPlacerType<AlderTrunkPlacer> ALDER_TRUNK_PLACER = null;
    public static TrunkPlacerType<HawthornTrunkPlacer> HAWTHORN_TRUNK_PLACER = null;
    public static FoliagePlacerType<WitcheryFoliagePlacer> WITCHERY_FOLIAGE_PLACER = null;

    public static void registerAllPlacers() {
        ALDER_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister(Witchery2.MOD_ID + ":alder_trunk_placer", AlderTrunkPlacer.CODEC);
        HAWTHORN_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister(Witchery2.MOD_ID + ":hawthorn_trunk_placer", HawthornTrunkPlacer.CODEC);
        WITCHERY_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister(Witchery2.MOD_ID + ":witchery_foliage_placer", WitcheryFoliagePlacer.CODEC);
    }
}
