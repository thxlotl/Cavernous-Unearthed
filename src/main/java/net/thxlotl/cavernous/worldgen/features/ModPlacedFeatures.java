package net.thxlotl.cavernous.worldgen.features;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.OreFeatureTypes;
import net.thxlotl.cavernous.util.OrePlacedFeatureTypes;
import net.thxlotl.cavernous.worldgen.features.placed.FungalCavesPlacedFeatures;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModPlacedFeatures {

    // Register Features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        FungalCavesPlacedFeatures.bootstrap(context, configuredFeatures);
    }





    // Ore setup
    public static final Map<String, EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>>> PLACED_ORE_MAPS =
            new HashMap<>();
    public static void registerStoneTypeOres(String prefix) {
        EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>> map = new EnumMap<>(OrePlacedFeatureTypes.class);
        for (OrePlacedFeatureTypes type : OrePlacedFeatureTypes.values()) {
            map.put(type, oreKey(prefix, type));
        }
        PLACED_ORE_MAPS.put(prefix, map);
    }
    public static ResourceKey<PlacedFeature> oreKey(String prefix, OrePlacedFeatureTypes oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }
    public static void createOreForStoneType(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, String prefix)
    {
        EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>> configuredMap = ModConfiguredFeatures.ORE_MAPS.get(prefix);
        EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>> placedMap = PLACED_ORE_MAPS.get(prefix);

        register(context, placedMap.get(OrePlacedFeatureTypes.COAL_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COAL_BURIED)),
                List.of(
                        CountPlacement.of(20),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COAL_UPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COAL)),
                List.of(
                        CountPlacement.of(30),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(136), VerticalAnchor.belowTop(0))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COPPER_SMALL)),
                List.of(
                        CountPlacement.of(16),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COPPER_LARGE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COPPER_LARGE)),
                List.of(
                        CountPlacement.of(16),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_MIDDLE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON)),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_SMALL), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON_SMALL)),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_UPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON)),
                List.of(
                        CountPlacement.of(90),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.GOLD), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.GOLD_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.GOLD_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.GOLD_BURIED)),
                List.of(
                        CountPlacement.of(UniformInt.of(0, 1)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.REDSTONE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.REDSTONE)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.REDSTONE_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.REDSTONE)),
                List.of(
                        CountPlacement.of(8),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.LAPIS), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.LAPIS)),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.LAPIS_BURIED), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.LAPIS_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_SMALL)),
                List.of(
                        CountPlacement.of(7),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_BURIED), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_LARGE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_LARGE)),
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_MEDIUM), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_MEDIUM)),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.EMERALD), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.EMERALD)),
                List.of(
                        CountPlacement.of(100),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))),
                        BiomeFilter.biome()
                ));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
    }
    public static void register (BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
