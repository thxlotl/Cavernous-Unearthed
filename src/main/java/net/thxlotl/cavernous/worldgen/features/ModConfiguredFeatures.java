package net.thxlotl.cavernous.worldgen.features;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.worldgen.ore.*;
import net.thxlotl.cavernous.util.worldgen.ore.entries.OreConfigurationEntry;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreResourceType;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.features.configured.VolcanicCavesConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.features.pairs.FungalCavesFeatureBootstrapper;

import java.util.EnumMap;
import java.util.List;

public class ModConfiguredFeatures {

    // Register features here
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        //FungalCavesConfiguredFeatures.bootstrap(context);
        FungalCavesFeatureBootstrapper.bootstrapConfigured(context);
        VolcanicCavesConfiguredFeatures.bootstrap(context);
    }


    //region ORE STUFF
    public static ResourceKey<ConfiguredFeature<?, ?>> oreKey(CustomStoneType customStone, OreConfiguredFeatureType oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        String prefix = customStone.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }
    private static OreConfiguration makeOreConfig(OreConfiguredFeatureType featureType, TagKey<Block> targetTag, EnumMap<OreResourceType, Block> oreBlockMap) {

        OreConfigurationEntry entry = OreConfigurationEntry.oreConfigs.get(featureType);

        return new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(entry.resourceType).defaultBlockState())),
                entry.size,
                entry.discardChance
        );
    }
    public static void createOreForStoneType(BootstrapContext<ConfiguredFeature<?, ?>> context, CustomStoneType stoneOreType, TagKey<Block> targetTag)
    {
        CustomStoneOreFamily family = CustomStoneOreFamily.allFamilies.get(stoneOreType);

        EnumMap<OreResourceType, Block> oreBlockMap = family.oreBlocks;
        EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> configFeatureMap = family.configuredFeatures;

        for (OreConfiguredFeatureType type : configFeatureMap.keySet()) {
            FeatureUtils.register(context, configFeatureMap.get(type), Feature.ORE, makeOreConfig(type, targetTag, oreBlockMap));
        }
//
//        // Make configs
//        OreConfiguration coalConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.COAL).defaultBlockState())),
//                17,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.COAL), Feature.ORE, coalConfig);
//
//        OreConfiguration coalBuriedConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.COAL).defaultBlockState())),
//                17,
//                0.5f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.COAL_BURIED), Feature.ORE, coalBuriedConfig);
//
//        OreConfiguration copperLargeConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.COPPER).defaultBlockState())),
//                20,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.COPPER_LARGE), Feature.ORE, copperLargeConfig);
//
//        OreConfiguration copperSmallConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.COPPER).defaultBlockState())),
//                10,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.COPPER_SMALL), Feature.ORE, copperSmallConfig);
//
//        OreConfiguration ironConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.IRON).defaultBlockState())),
//                9,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.IRON), Feature.ORE, ironConfig);
//
//        OreConfiguration ironSmallConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.IRON).defaultBlockState())),
//                4,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.IRON_SMALL), Feature.ORE, ironSmallConfig);
//
//        OreConfiguration goldConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.GOLD).defaultBlockState())),
//                9,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.GOLD), Feature.ORE, goldConfig);
//
//        OreConfiguration goldBuriedConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.GOLD).defaultBlockState())),
//                9,
//                0.5f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.GOLD_BURIED), Feature.ORE, goldBuriedConfig);
//
//        OreConfiguration redstoneConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.REDSTONE).defaultBlockState())),
//                8,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.REDSTONE), Feature.ORE, redstoneConfig);
//
//        OreConfiguration lapisConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.LAPIS).defaultBlockState())),
//                7,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.LAPIS), Feature.ORE, lapisConfig);
//
//        OreConfiguration lapisBuriedConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.LAPIS).defaultBlockState())),
//                7,
//                1.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.LAPIS_BURIED), Feature.ORE, lapisBuriedConfig);
//
//        OreConfiguration diamondBuriedConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.DIAMOND).defaultBlockState())),
//                8,
//                1.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.DIAMOND_BURIED), Feature.ORE, diamondBuriedConfig);
//
//        OreConfiguration diamondLargeConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.DIAMOND).defaultBlockState())),
//                12,
//                0.7f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.DIAMOND_LARGE), Feature.ORE, diamondLargeConfig);
//
//        OreConfiguration diamondMediumConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.DIAMOND).defaultBlockState())),
//                8,
//                0.5f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.DIAMOND_MEDIUM), Feature.ORE, diamondMediumConfig);
//
//        OreConfiguration diamondSmallConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.DIAMOND).defaultBlockState())),
//                4,
//                0.5f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.DIAMOND_SMALL), Feature.ORE, diamondSmallConfig);
//
//        OreConfiguration emeraldConfig = new OreConfiguration(
//                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreResourceType.EMERALD).defaultBlockState())),
//                3,
//                0.0f
//        );
//        FeatureUtils.register(context, configFeatureMap.get(OreConfiguredFeatureType.EMERALD), Feature.ORE, emeraldConfig);
    }
    //endregion

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Cavernous.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register
            (BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
