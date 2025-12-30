package net.thxlotl.cavernous.util.worldgen.ore.entries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;

public class OrePlacedFeatureEntry {

    public OrePlacedFeatureType featureType;
    public ResourceKey<PlacedFeature> feature;

    public OrePlacedFeatureEntry(OrePlacedFeatureType featureType, ResourceKey<PlacedFeature> feature) {
        this.featureType = featureType;
        this.feature = feature;
    }

}
