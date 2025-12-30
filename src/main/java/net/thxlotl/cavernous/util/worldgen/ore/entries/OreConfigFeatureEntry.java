package net.thxlotl.cavernous.util.worldgen.ore.entries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;

public class OreConfigFeatureEntry {

    public OreConfiguredFeatureType featureType;
    public ResourceKey<ConfiguredFeature<?,?>> feature;

    public OreConfigFeatureEntry(OreConfiguredFeatureType featureType, ResourceKey<ConfiguredFeature<?,?>> feature) {
        this.featureType = featureType;
        this.feature = feature;
    }

}
