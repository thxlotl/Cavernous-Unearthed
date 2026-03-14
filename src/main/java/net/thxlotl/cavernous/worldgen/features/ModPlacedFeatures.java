package net.thxlotl.cavernous.worldgen.features;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.worldgen.ore.CustomStoneOreFamily;
import net.thxlotl.cavernous.util.worldgen.ore.OreUtil;
import net.thxlotl.cavernous.util.worldgen.ore.entries.OrePlacedFeatureEntry;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;
import net.thxlotl.cavernous.worldgen.features.placed.FungalCavesPlacedFeatures;
import net.thxlotl.cavernous.worldgen.features.placed.VolcanicCavesPlacedFeatures;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModPlacedFeatures {

    // Register Features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        FungalCavesPlacedFeatures.bootstrap(context, configuredFeatures);
        VolcanicCavesPlacedFeatures.bootstrap(context, configuredFeatures);
    }





    //region ORE STUFF
    public static ResourceKey<PlacedFeature> oreKey(CustomStoneType stoneType, OrePlacedFeatureType oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        String prefix = stoneType.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }

    private static void registerOre(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, OrePlacedFeatureType type, CustomStoneOreFamily family) {

        EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> placedFeatureMap = family.placedFeatures;
        EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> configFeatureMap = family.configuredFeatures;

        register(context, placedFeatureMap.get(type), configuredFeatures.getOrThrow(configFeatureMap.get(OreUtil.getConfiguredFeatureType(type))), OreUtil.getPlacementModifiers(type));
    }
    public static void createOreForStoneType(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, CustomStoneType type)
    {
        CustomStoneOreFamily family = CustomStoneOreFamily.allFamilies.get(type);

        EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> placedFeatureMap = family.placedFeatures;

        for (OrePlacedFeatureType placedFeatureType : placedFeatureMap.keySet()) {
            registerOre(context, configuredFeatures, placedFeatureType, family);
        }

    }
    //endregion

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Cavernous.MODID, name));
    }
    public static void register (BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
