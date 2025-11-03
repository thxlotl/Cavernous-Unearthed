package net.thxlotl.cavernous.worldgen.datagen.configuredfeatures;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thxlotl.cavernous.block.ModBlockStateProperties;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.custom.ToadstoolButtonBlock;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.util.OreFeatureTypes;
import net.thxlotl.cavernous.util.OreTypes;
import net.thxlotl.cavernous.worldgen.ModConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
import net.thxlotl.cavernous.worldgen.custom.segmentedwallblock.SegmentedWallBlockConfiguration;
import net.thxlotl.cavernous.worldgen.custom.tree.LeaveCustomVineDecorator;
import net.thxlotl.cavernous.worldgen.custom.tree.MushroomCapFoliagePlacer;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;

import java.util.EnumMap;
import java.util.List;

public class FungalCavesConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_VEGETATION = ModConfiguredFeatures.registerKey("feather_moss_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_VEGETATION_BONEMEAL = ModConfiguredFeatures.registerKey("feather_moss_vegetation_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_PATCH_BONEMEAL = ModConfiguredFeatures.registerKey("feather_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_PATCH = ModConfiguredFeatures.registerKey("feather_moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOADSTOOL = ModConfiguredFeatures.registerKey("toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHROOMWOOD_LOG_VEGETATION = ModConfiguredFeatures.registerKey("shroomwood_log_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL = ModConfiguredFeatures.registerKey("underground_mycelium_vegetation_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_VEGETATION = ModConfiguredFeatures.registerKey("underground_mycelium_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_PATCH = ModConfiguredFeatures.registerKey("underground_mycelium_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROUND_MYCELIUM_PATCH_BONEMEAL = ModConfiguredFeatures.registerKey("underground_mycelium_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_SHROOM_SPORE_POD = ModConfiguredFeatures.registerKey("hanging_shroom_spore_pod");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_SHROOM = ModConfiguredFeatures.registerKey("hanging_shroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM = ModConfiguredFeatures.registerKey("lampshroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM_TREE = ModConfiguredFeatures.registerKey("lampshroom_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOST_FUNGUS = ModConfiguredFeatures.registerKey("ghost_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLEEDING_TOOTH_FUNGUS = ModConfiguredFeatures.registerKey("bleeding_tooth_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORDYCEPS = ModConfiguredFeatures.registerKey("cordyceps");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_GROUND_FUNGATITE = ModConfiguredFeatures.registerKey("ore_ground_fungatite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHELFSHROOM = ModConfiguredFeatures.registerKey("shelfshroom");

    private static final String fungatitePrefix = "fungatite";
    private static final EnumMap<OreTypes, Block> FUNGATITE_ORES =
            new EnumMap<>(OreTypes.class);
    static {
        FUNGATITE_ORES.put(OreTypes.COAL, ModBlocks.FUNGATITE_COAL_ORE.get());
        FUNGATITE_ORES.put(OreTypes.COPPER, ModBlocks.FUNGATITE_COPPER_ORE.get());
        FUNGATITE_ORES.put(OreTypes.IRON, ModBlocks.FUNGATITE_IRON_ORE.get());
        FUNGATITE_ORES.put(OreTypes.GOLD, ModBlocks.FUNGATITE_GOLD_ORE.get());
        FUNGATITE_ORES.put(OreTypes.REDSTONE, ModBlocks.FUNGATITE_REDSTONE_ORE.get());
        FUNGATITE_ORES.put(OreTypes.LAPIS, ModBlocks.FUNGATITE_LAPIS_ORE.get());
        FUNGATITE_ORES.put(OreTypes.DIAMOND, ModBlocks.FUNGATITE_DIAMOND_ORE.get());
        FUNGATITE_ORES.put(OreTypes.EMERALD, ModBlocks.FUNGATITE_EMERALD_ORE.get());
    }
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL = ModConfiguredFeatures.oreKey(fungatitePrefix, OreFeatureTypes.COAL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL_BURIED = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.COAL_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_LARGE = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.COPPER_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_SMALL = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.COPPER_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.IRON);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON_SMALL = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.IRON_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.GOLD);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD_BURIED = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.GOLD_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_REDSTONE = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.REDSTONE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.LAPIS);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS_BURIED = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.LAPIS_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_BURIED = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_LARGE = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_MEDIUM = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_MEDIUM);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_SMALL = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_EMERALD = ModConfiguredFeatures.oreKey(fungatitePrefix,OreFeatureTypes.EMERALD);


    static {
        ModConfiguredFeatures.registerStoneTypeOres(fungatitePrefix);
    }

    // Register features here
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        // Feather Moss
        SimpleBlockConfiguration featherMossVegetationBonemealConfig = new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 1)
                        .add(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 7)),
                true
        );
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION_BONEMEAL, Feature.SIMPLE_BLOCK, featherMossVegetationBonemealConfig);

        SimpleBlockConfiguration featherMossVegetationConfig = new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 1)
                        .add(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 7)
                        .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 2)),
                true
        );
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION, Feature.SIMPLE_BLOCK, featherMossVegetationConfig);

        VegetationPatchConfiguration featherMossBonemealPatchConfig = new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_BLOCK.get()),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(FEATHER_MOSS_VEGETATION_BONEMEAL),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), Vec3i.ZERO)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)
                        )),
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
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(FEATHER_MOSS_VEGETATION),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), Vec3i.ZERO)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)
                        )),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0.5f,
                UniformInt.of(2,3),
                0.75f);
        FeatureUtils.register(context, FEATHER_MOSS_PATCH, Feature.VEGETATION_PATCH, featherMossPatchConfig);

        // Toadstool
        TreeConfiguration toadstoolConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHROOMWOOD_LOG.get().defaultBlockState()),
                new ToadstoolTrunkPlacer(1, 1, 1, BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(1, 2)),
                BlockStateProvider.simple(ModBlocks.TOADSTOOL_CAP_BLOCK.get().defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                new TwoLayersFeatureSize(0, 0, 0)
        )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(1, 0, 0, BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_CARPET.get()), 1, List.of(Direction.UP)),
                        new AttachedToLogsDecorator(0.85f, new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), 1)
                                .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 11)
                                .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 16))
                                , List.of(Direction.UP)),
                        new LeaveCustomVineDecorator(0.2f, ModBlocks.HANGING_FEATHER_MOSS.get())
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
                        .add(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 8)
                        .add(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 8)
                        .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 4)
                ),
                true
        );
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_VEGETATION, Feature.SIMPLE_BLOCK, undergroundMyceliumVegetationConfig);

        VegetationPatchConfiguration undergroundMyceliumPatchConfig = new VegetationPatchConfiguration(
                ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.UNDERGROUND_MYCELIUM.get()),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), Vec3i.ZERO)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)
                        )),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0.3f,
                UniformInt.of(1,2),
                0.5f);
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, undergroundMyceliumPatchConfig);

        RandomPatchConfiguration myceliumVegetationPatchConfig = new RandomPatchConfiguration(
                500,
                8,
                6,
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(UNDERGROUND_MYCELIUM_VEGETATION),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), Vec3i.ZERO)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)
                        )));
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

        BlockColumnConfiguration hangingShroomConfig = new BlockColumnConfiguration(
                List.of(
                        new BlockColumnConfiguration.Layer(BiasedToBottomInt.of(1, 12), SimpleStateProvider.simple(ModBlocks.HANGING_SHROOM_STEM.get())),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.HANGING_SHROOM_CAP.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, 12)))),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR),
                true
        );
        FeatureUtils.register(context, HANGING_SHROOM, Feature.BLOCK_COLUMN, hangingShroomConfig);


        BlockColumnConfiguration lampshroomConfig = new BlockColumnConfiguration(
                List.of(
                        new BlockColumnConfiguration.Layer(BiasedToBottomInt.of(1, 7), SimpleStateProvider.simple(ModBlocks.LAMPSHROOM_STEM.get())),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.LAMPSHROOM.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, 25)))),
                Direction.UP,
                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR),
                true
        );
        FeatureUtils.register(context, LAMPSHROOM, Feature.BLOCK_COLUMN, lampshroomConfig);

        TreeConfiguration lampshroomTreeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHROOMWOOD_LOG.get().defaultBlockState()),
                new ToadstoolTrunkPlacer(1, 1, 1, BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(1, 2)),
                BlockStateProvider.simple(ModBlocks.LAMPSHROOM_CAP_BLOCK.get().defaultBlockState()),
                new MushroomCapFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), UniformInt.of(3, 6)),
                new TwoLayersFeatureSize(0, 0, 0)
        )
                .ignoreVines()
                .decorators(List.of(
                        new AttachedToLogsDecorator(0.85f, new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.LAMPSHROOM.get().defaultBlockState(), 1)
                                .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 10))
                                ,List.of(Direction.UP))
                ))
                .build();
        FeatureUtils.register(context, LAMPSHROOM_TREE, Feature.TREE, lampshroomTreeConfig);


        SegmentedWallBlockConfiguration ghostFungusConfig = new SegmentedWallBlockConfiguration(
                BlockStateProvider.simple(ModBlocks.GHOST_FUNGUS.get()),
                BiasedToBottomInt.of(1, 3)
                );
        FeatureUtils.register(context, GHOST_FUNGUS, ModFeature.SEGMENTED_WALL_BLOCK_FEATURE.get(), ghostFungusConfig);


        SimpleBlockConfiguration bleedingToothConfig = new SimpleBlockConfiguration(
                BlockStateProvider.simple(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get().defaultBlockState()),
                true
        );
        FeatureUtils.register(context, BLEEDING_TOOTH_FUNGUS, Feature.SIMPLE_BLOCK, bleedingToothConfig);

        SimpleBlockConfiguration corycepsConfig = new SimpleBlockConfiguration(
                BlockStateProvider.simple(ModBlocks.CORDYCEPS_PATCH.get().defaultBlockState()),
                true
        );
        FeatureUtils.register(context, CORDYCEPS, Feature.SIMPLE_BLOCK, corycepsConfig);

        OreConfiguration groundFungatiteConfig = new OreConfiguration(
                List.of(OreConfiguration.target(new TagMatchTest(ModTags.Blocks.FUNGATITE_ORE_REPLACEABLE), ModBlocks.GROUND_FUNGATITE.get().defaultBlockState())),
                35,
                0.0f
        );
        FeatureUtils.register(context, ORE_GROUND_FUNGATITE, Feature.ORE, groundFungatiteConfig);


        WallShroomConfiguration shelfshroomConfig = new WallShroomConfiguration(
                BlockStateProvider.simple(ModBlocks.SHELFSHROOM_CAP_BLOCK.get().defaultBlockState()),
                ConstantInt.of(2)
        );
        FeatureUtils.register(context, SHELFSHROOM, ModFeature.WALLSHROOM_FEATURE.get(), shelfshroomConfig);


        ModConfiguredFeatures.createOreForStoneType(context, ModTags.Blocks.FUNGATITE_ORE_REPLACEABLE, fungatitePrefix, FUNGATITE_ORES);
    }
}
