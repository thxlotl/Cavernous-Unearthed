package net.thxlotl.cavernous.worldgen.features.pairs;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.Cavernous;

public class FeaturePair {

    public final ResourceKey<ConfiguredFeature<?, ?>> configuredKey;
    public final ResourceKey<PlacedFeature> placedKey;

    public FeaturePair(String name) {
        this.configuredKey = registerConfiguredKey(name);
        this.placedKey = registerPlacedKey(name);
    }

    public static FeaturePair of(String name) {
        return new FeaturePair(name);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Cavernous.MODID, name));
    }
    private static ResourceKey<PlacedFeature> registerPlacedKey(String name) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Cavernous.MODID, name));
    }

}
