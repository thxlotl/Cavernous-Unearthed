package net.thxlotl.cavernous.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLogsDecorator;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.custom.ToadstoolButtonBlock;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_VEGETATION = registerKey("feather_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_PATCH_BONEMEAL = registerKey("feather_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_PATCH = registerKey("feather_moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOADSTOOL = registerKey("toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHROOMWOOD_LOG_VEGETATION = registerKey("shroomwood_log_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL = registerKey("underground_mycelium_vegetation_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_VEGETATION = registerKey("underground_mycelium_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_PATCH = registerKey("underground_mycelium_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_PATCH_BONEMEAL = registerKey("underground_mycelium_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_SHROOM_SPORE_POD = registerKey("hanging_shroom_spore_pod");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        // Feather Moss
        SimpleBlockConfiguration featherMossVegetationConfig = new SimpleBlockConfiguration(
            new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 1)
                    .add(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 7)),
                true
        );
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION, Feature.SIMPLE_BLOCK, featherMossVegetationConfig);

        VegetationPatchConfiguration featherMossBonemealPatchConfig = new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_BLOCK.get()),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(FEATHER_MOSS_VEGETATION)),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0.5f,
                UniformInt.of(1,2),
                0.75f);
        FeatureUtils.register(context, FEATHER_MOSS_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, featherMossBonemealPatchConfig);

        VegetationPatchConfiguration featherMossPatchConfig = new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_BLOCK.get()),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(FEATHER_MOSS_VEGETATION)),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0.5f,
                UniformInt.of(3,4),
                0.75f);
        FeatureUtils.register(context, FEATHER_MOSS_PATCH, Feature.VEGETATION_PATCH, featherMossPatchConfig);

        // Toadstool
        TreeConfiguration toadstoolConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHROOMWOOD_LOG.get().defaultBlockState()),
                new ToadstoolTrunkPlacer(1, 1, 1, BiasedToBottomInt.of(2, 4), BiasedToBottomInt.of(2, 4), BiasedToBottomInt.of(1, 2)),
                BlockStateProvider.simple(ModBlocks.TOADSTOOL_CAP_BLOCK.get().defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                new TwoLayersFeatureSize(1, 0, 1)
            )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(1, 0, 0, BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_CARPET.get()), 1, List.of(Direction.UP)),
                        new AttachedToLeavesDecorator(1, 0, 0, BlockStateProvider.simple(ModBlocks.MUSHROOM_GILL_BLOCK.get()), 1, List.of(Direction.DOWN)),
                        new AttachedToLogsDecorator(0.85f, new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), 1)
                                .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 11)
                                .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 16))
                                , List.of(Direction.UP))
                ))
                .ignoreVines()
                .build();
        FeatureUtils.register(context, TOADSTOOL, Feature.TREE, toadstoolConfig);

        SimpleBlockConfiguration shroomwoodLogVegetationConfig = new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.NORTH), 1)
                        .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.EAST), 1)
                        .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.SOUTH), 1)
                        .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.WEST), 1)
                        .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 12)),
                true
        );
        FeatureUtils.register(context, SHROOMWOOD_LOG_VEGETATION, Feature.SIMPLE_BLOCK, shroomwoodLogVegetationConfig);

        // Mycelium
        SimpleBlockConfiguration undergroundMyceliumVegetationBonemealConfig = new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 4)
                        .add(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 1)),
                true
        );
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL, Feature.SIMPLE_BLOCK, undergroundMyceliumVegetationBonemealConfig);

        SimpleBlockConfiguration undergroundMyceliumVegetationConfig = new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 6)
                        .add(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 2)
                        .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 1)
                ),
                true
        );
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_VEGETATION, Feature.SIMPLE_BLOCK, undergroundMyceliumVegetationConfig);

        VegetationPatchConfiguration undergroundMyceliumPatchConfig = new VegetationPatchConfiguration(
                ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.UNDERGROUND_MYCELIUM.get()),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL)),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0.5f,
                UniformInt.of(1,2),
                0.5f);
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, undergroundMyceliumPatchConfig);
        
        RandomPatchConfiguration myceliumVegetationPatchConfig = new RandomPatchConfiguration(
                500,
                8,
                6,
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(UNDERGROUND_MYCELIUM_VEGETATION),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), Vec3i.ZERO))
        ));
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH, Feature.RANDOM_PATCH, myceliumVegetationPatchConfig);


        BlockColumnConfiguration hangingShroomSporePodConfig = new BlockColumnConfiguration(
                List.of(
                        new BlockColumnConfiguration.Layer(UniformInt.of(6, 8), SimpleStateProvider.simple(ModBlocks.HANGING_SHROOM_STEM.get())),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.HANGING_SHROOM_CAP.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, 15)))),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR),
                true
        );
        FeatureUtils.register(context, HANGING_SHROOM_SPORE_POD, Feature.BLOCK_COLUMN, hangingShroomSporePodConfig);

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register
            (BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
