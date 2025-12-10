package net.thxlotl.cavernous.worldgen.features;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.OreFeatureTypes;
import net.thxlotl.cavernous.util.OreTypes;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModConfiguredFeatures {

    // Register features here
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        FungalCavesConfiguredFeatures.bootstrap(context);
    }




    // Ore setup
    public static final Map<String, EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>>> ORE_MAPS =
            new HashMap<>();
    public static void registerStoneTypeOres(String prefix) {
        EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>> map = new EnumMap<>(OreFeatureTypes.class);
        for (OreFeatureTypes type : OreFeatureTypes.values()) {
            map.put(type, oreKey(prefix, type));
        }
        ORE_MAPS.put(prefix, map);
    }
    public static ResourceKey<ConfiguredFeature<?, ?>> oreKey(String prefix, OreFeatureTypes oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }
    public static void createOreForStoneType(BootstrapContext<ConfiguredFeature<?, ?>> context, TagKey<Block> targetTag, String prefix, EnumMap<OreTypes, Block> oreBlockMap)
    {
        EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>> oreMap = ORE_MAPS.get(prefix);

        OreConfiguration coalConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.COAL).defaultBlockState())),
                17,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.COAL), Feature.ORE, coalConfig);

        OreConfiguration coalBuriedConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.COAL).defaultBlockState())),
                17,
                0.5f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.COAL_BURIED), Feature.ORE, coalBuriedConfig);

        OreConfiguration copperLargeConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.COPPER).defaultBlockState())),
                20,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.COPPER_LARGE), Feature.ORE, copperLargeConfig);

        OreConfiguration copperSmallConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.COPPER).defaultBlockState())),
                10,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.COPPER_SMALL), Feature.ORE, copperSmallConfig);

        OreConfiguration ironConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.IRON).defaultBlockState())),
                9,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.IRON), Feature.ORE, ironConfig);

        OreConfiguration ironSmallConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.IRON).defaultBlockState())),
                4,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.IRON_SMALL), Feature.ORE, ironSmallConfig);

        OreConfiguration goldConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.GOLD).defaultBlockState())),
                9,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.GOLD), Feature.ORE, goldConfig);

        OreConfiguration goldBuriedConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.GOLD).defaultBlockState())),
                9,
                0.5f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.GOLD_BURIED), Feature.ORE, goldBuriedConfig);

        OreConfiguration redstoneConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.REDSTONE).defaultBlockState())),
                8,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.REDSTONE), Feature.ORE, redstoneConfig);

        OreConfiguration lapisConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.LAPIS).defaultBlockState())),
                7,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.LAPIS), Feature.ORE, lapisConfig);

        OreConfiguration lapisBuriedConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.LAPIS).defaultBlockState())),
                7,
                1.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.LAPIS_BURIED), Feature.ORE, lapisBuriedConfig);

        OreConfiguration diamondBuriedConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.DIAMOND).defaultBlockState())),
                8,
                1.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.DIAMOND_BURIED), Feature.ORE, diamondBuriedConfig);

        OreConfiguration diamondLargeConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.DIAMOND).defaultBlockState())),
                12,
                0.7f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.DIAMOND_LARGE), Feature.ORE, diamondLargeConfig);

        OreConfiguration diamondMediumConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.DIAMOND).defaultBlockState())),
                8,
                0.5f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.DIAMOND_MEDIUM), Feature.ORE, diamondMediumConfig);

        OreConfiguration diamondSmallConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.DIAMOND).defaultBlockState())),
                4,
                0.5f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.DIAMOND_SMALL), Feature.ORE, diamondSmallConfig);

        OreConfiguration emeraldConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(targetTag), oreBlockMap.get(OreTypes.EMERALD).defaultBlockState())),
                3,
                0.0f
        );
        FeatureUtils.register(context, oreMap.get(OreFeatureTypes.EMERALD), Feature.ORE, emeraldConfig);
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register
            (BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
