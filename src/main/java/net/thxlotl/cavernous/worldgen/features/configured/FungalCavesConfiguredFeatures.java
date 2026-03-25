package net.thxlotl.cavernous.worldgen.features.configured;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
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
import net.minecraft.util.valueproviders.UniformInt;
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
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.custom.ToadstoolButtonBlock;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.util.worldgen.CFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.WeightedBlockState;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.worldgen.custom.boulder.BoulderConfiguration;
import net.thxlotl.cavernous.worldgen.features.ModConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
import net.thxlotl.cavernous.worldgen.custom.segmentedwallblock.SegmentedWallBlockConfiguration;
import net.thxlotl.cavernous.worldgen.custom.tree.MushroomCapFoliagePlacer;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;

import java.util.List;

public class FungalCavesConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_VEGETATION_FUNGAL = ModConfiguredFeatures.registerKey("feather_moss_vegetation_fungal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FEATHER_MOSS_VEGETATION = ModConfiguredFeatures.registerKey("feather_moss_vegetation");
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> PUFFSHROOM = ModConfiguredFeatures.registerKey("puffshroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIUM_SPROUT_PATCH = ModConfiguredFeatures.registerKey("mycelium_sprout_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM_PATCH_CLUSTER = ModConfiguredFeatures.registerKey("lampshroom_patch_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM_CLUSTER = ModConfiguredFeatures.registerKey("lampshroom_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FUNGATITE_BOULDER = ModConfiguredFeatures.registerKey("fungatite_boulder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIUM_VINES = ModConfiguredFeatures.registerKey("mycelium_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_GHOST_FUNGUS_CLUSTER = ModConfiguredFeatures.registerKey("blue_ghost_fungus_cluster");

    //region ORES
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.COAL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.COAL_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_LARGE = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.COPPER_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_SMALL = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.COPPER_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.IRON);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON_SMALL = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.IRON_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.GOLD);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.GOLD_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_REDSTONE = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.REDSTONE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.LAPIS);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.LAPIS_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.DIAMOND_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_LARGE = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.DIAMOND_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_MEDIUM = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.DIAMOND_MEDIUM);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_SMALL = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.DIAMOND_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_EMERALD = ModConfiguredFeatures.oreKey(CustomStoneType.FUNGATITE, OreConfiguredFeatureType.EMERALD);
    //endregion

    // Register features here
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        // Feather Moss
        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 1),
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 7)
        ));

        FeatureUtils.register(context, FEATHER_MOSS_VEGETATION_FUNGAL, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 2),
                new WeightedBlockState(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 12),
                new WeightedBlockState(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 3)
        ));

        FeatureUtils.register(context, FEATHER_MOSS_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                holdergetter,
                BlockTags.MOSS_REPLACEABLE,
                ModBlocks.FEATHER_MOSS_BLOCK.get(),
                FEATHER_MOSS_VEGETATION,
                0.5f,
                1, 2
        ));

        FeatureUtils.register(context, FEATHER_MOSS_PATCH, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                holdergetter,
                BlockTags.MOSS_REPLACEABLE,
                ModBlocks.FEATHER_MOSS_BLOCK.get(),
                FEATHER_MOSS_VEGETATION_FUNGAL,
                0.65f,
                2, 3
        ));


        FeatureUtils.register(context, LAMPSHROOM_PATCH_CLUSTER, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(
                ModBlocks.LAMPSHROOM_PATCH.get()
        ));

        FeatureUtils.register(context, BLUE_GHOST_FUNGUS_CLUSTER, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(
                ModBlocks.BLUE_GHOST_FUNGUS.get()
        ));

        // Toadstool
        TreeConfiguration toadstoolConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHROOMWOOD_LOG.get().defaultBlockState()),
                new ToadstoolTrunkPlacer(1, 1, 1, BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(1, 2)),
                BlockStateProvider.simple(ModBlocks.TOADSTOOL_CAP_BLOCK.get().defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1)),
                new TwoLayersFeatureSize(3, 0, 3)
        )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(1, 0, 0, BlockStateProvider.simple(ModBlocks.FEATHER_MOSS_CARPET.get()), 1, List.of(Direction.UP)),
                        new AttachedToLogsDecorator(0.85f, new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), 1)
                                .add(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 11)
                                .add(ModBlocks.FEATHER_MOSS_CARPET.get().defaultBlockState(), 16))
                                , List.of(Direction.UP))
                ))
                .ignoreVines()
                .build();
        FeatureUtils.register(context, TOADSTOOL, Feature.TREE, toadstoolConfig);

        FeatureUtils.register(context, SHROOMWOOD_LOG_VEGETATION, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.NORTH), 1),
                new WeightedBlockState(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.EAST), 1),
                new WeightedBlockState(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.WEST), 1),
                new WeightedBlockState(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState().setValue(ToadstoolButtonBlock.FACING, Direction.SOUTH), 1),
                new WeightedBlockState(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 12)

        ));

        //region MYCELIUM
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 4),
                new WeightedBlockState(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 1)
        ));

        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_VEGETATION, Feature.SIMPLE_BLOCK, CFeatureUtil.createWeightedState(
                new WeightedBlockState(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 12),
                new WeightedBlockState(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 8),
                new WeightedBlockState(ModBlocks.TOADSTOOL_PATCH.get().defaultBlockState(), 4)
        ));

        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                holdergetter,
                ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE,
                ModBlocks.UNDERGROUND_MYCELIUM.get(),
                UNDERGROUND_MYCELIUM_VEGETATION_BONEMEAL,
                0.3f,
                1, 2
        ));

        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH, Feature.VEGETATION_PATCH, CFeatureUtil.createSurfaceVegetationPatch(
                holdergetter,
                ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE,
                ModBlocks.UNDERGROUND_MYCELIUM.get(),
                UNDERGROUND_MYCELIUM_VEGETATION,
                0.7f,
                2, 5
        ));

        FeatureUtils.register(context, MYCELIUM_SPROUT_PATCH, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(
                ModBlocks.MYCELIUM_SPROUTS.get()
        ));
        //endregion


        BlockColumnConfiguration hangingShroomSporePodConfig = new BlockColumnConfiguration(
                List.of(
                        new BlockColumnConfiguration.Layer(UniformInt.of(6, 8), SimpleStateProvider.simple(ModBlocks.FLIPSHROOM_STEM.get())),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.FLIPSHROOM.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, 15)))),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR),
                true
        );
        FeatureUtils.register(context, HANGING_SHROOM_SPORE_POD, Feature.BLOCK_COLUMN, hangingShroomSporePodConfig);

        BlockColumnConfiguration hangingShroomConfig = new BlockColumnConfiguration(
                List.of(
                        new BlockColumnConfiguration.Layer(BiasedToBottomInt.of(1, 12), SimpleStateProvider.simple(ModBlocks.FLIPSHROOM_STEM.get())),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.FLIPSHROOM.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, 12)))),
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

        // This is a dumb bandaid so I need to redo this part
        //
        FeatureUtils.register(context, LAMPSHROOM, Feature.BLOCK_COLUMN, lampshroomConfig);
        FeatureUtils.register(context, LAMPSHROOM_CLUSTER, Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfiguration(
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LAMPSHROOM)),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(LAMPSHROOM))
        ));

        TreeConfiguration lampshroomTreeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SHROOMWOOD_LOG.get().defaultBlockState()),
                new ToadstoolTrunkPlacer(1, 1, 1, BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(2, 3), BiasedToBottomInt.of(1, 2)),
                BlockStateProvider.simple(ModBlocks.LAMPSHROOM_CAP_BLOCK.get().defaultBlockState()),
                new MushroomCapFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), UniformInt.of(3, 6)),
                new TwoLayersFeatureSize(3, 0, 3)
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


        FeatureUtils.register(context, BLEEDING_TOOTH_FUNGUS, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get()));

        FeatureUtils.register(context, PUFFSHROOM, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.PUFFSHROOM.get()));

        FeatureUtils.register(context, CORDYCEPS, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.CORDYCEPS_PATCH.get()));

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

        BoulderConfiguration fungatiteBoulderConfig = new BoulderConfiguration(SimpleStateProvider.simple(ModBlocks.FUNGATITE.get()), BiasedToBottomInt.of(2, 4));
        FeatureUtils.register(context, FUNGATITE_BOULDER, ModFeature.BOULDER_FEATURE.get(), fungatiteBoulderConfig);

        VegetationPatchConfiguration myceliumVineConfig = new VegetationPatchConfiguration(
                ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE,
                BlockStateProvider.simple(ModBlocks.FUNGATITE.get()),
                PlacementUtils.inlinePlaced(
                        Holder.direct(
                                new ConfiguredFeature<>(
                                    Feature.BLOCK_COLUMN,
                                    new BlockColumnConfiguration(
                                            List.of(
                                                    new BlockColumnConfiguration.Layer(BiasedToBottomInt.of(2, 10), BlockStateProvider.simple(ModBlocks.MYCELIUM_VINE_PLANT.get())),
                                                    new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(ModBlocks.MYCELIUM_VINE.get()))
                                            ),
                                            Direction.DOWN,
                                            BlockPredicate.matchesBlocks(Blocks.AIR),
                                            true
                                    )
                                )
                        )
                ),
                CaveSurface.CEILING,
                ConstantInt.of(1),
                0f,
                6,
                0.5f,
                UniformInt.of(2, 4),
                0.45f
        );
        FeatureUtils.register(context, MYCELIUM_VINES, Feature.VEGETATION_PATCH, myceliumVineConfig);

        ModConfiguredFeatures.createOreForStoneType(context, CustomStoneType.FUNGATITE, ModTags.Blocks.FUNGATITE_ORE_REPLACEABLE);
    }
}
