package net.thxlotl.cavernous.worldgen.features.pairs;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.worldgen.CFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.PFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.WeightedBlockState;

public class FungalCavesFeatureBootstrapper {

    public static final FeaturePair FEATHER_MOSS_PATCH_FUNGAL = FeaturePair.of("feather_moss_patch_fungal"); // Feather moss patch that spawns in fungal caves
    public static final FeaturePair FEATHER_MOSS_VEGETATION_FUNGAL = FeaturePair.of("feather_moss_vegetation_fungal"); // Vegetation on naturally spawned feather moss patch
    public static final FeaturePair FEATHER_MOSS_PATCH_BONEMEAL = FeaturePair.of("feather_moss_patch_bonemeal"); // Feather moss patch that spawns in fungal caves
    public static final FeaturePair FEATHER_MOSS_VEGETATION_BONEMEAL = FeaturePair.of("feather_moss_vegetation_bonemeal"); // Vegetation on naturally spawned feather moss patch


    public static void bootstrapConfigured(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        HolderGetter<ConfiguredFeature<?, ?>> cfHolderGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        //region Feather Moss
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION_FUNGAL.configuredKey, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 2),
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 12),
                new WeightedBlockState(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 3)
        ));
        FeatureUtils.register(context, FEATHER_MOSS_PATCH_FUNGAL.configuredKey, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                cfHolderGetter,
                BlockTags.MOSS_REPLACEABLE,
                ModBlocks.FEATHER_MOSS_BLOCK.get(),
                FEATHER_MOSS_VEGETATION_FUNGAL.configuredKey,
                0.65f,
                2, 3
        ));
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION_BONEMEAL.configuredKey, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 1),
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 7)
        ));
        FeatureUtils.register(context, FEATHER_MOSS_PATCH_BONEMEAL.configuredKey, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                cfHolderGetter,
                BlockTags.MOSS_REPLACEABLE,
                ModBlocks.FEATHER_MOSS_BLOCK.get(),
                FEATHER_MOSS_VEGETATION_BONEMEAL.configuredKey,
                0.5f,
                1, 2
        ));
        //endregion

    }

    public static void bootstrapPlaced(BootstrapContext<PlacedFeature> context) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        FeatureBootstrapperUtil.RegisterFeaturePair(context, FEATHER_MOSS_PATCH_FUNGAL, PFeatureUtil.cavePlacementModifers(65));

    }

}
