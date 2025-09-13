package net.thxlotl.cavernous.worldgen;

import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
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
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.custom.ToadstoolButtonBlock;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.util.OreFeatureTypes;
import net.thxlotl.cavernous.util.OreTypes;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
import net.thxlotl.cavernous.worldgen.custom.wallface.WallFaceGrowthConfiguration;
import net.thxlotl.cavernous.worldgen.custom.tree.MushroomCapFoliagePlacer;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_SHROOM = registerKey("hanging_shroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM = registerKey("lampshroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAMPSHROOM_TREE = registerKey("lampshroom_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GHOST_FUNGUS = registerKey("ghost_fungus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLEEDING_TOOTH_FUNGUS = registerKey("bleeding_tooth_fungus");

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
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL = oreKey(fungatitePrefix,OreFeatureTypes.COAL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COAL_BURIED = oreKey(fungatitePrefix,OreFeatureTypes.COAL_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_LARGE = oreKey(fungatitePrefix,OreFeatureTypes.COPPER_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_COPPER_SMALL = oreKey(fungatitePrefix,OreFeatureTypes.COPPER_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON = oreKey(fungatitePrefix,OreFeatureTypes.IRON);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_IRON_SMALL = oreKey(fungatitePrefix,OreFeatureTypes.IRON_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD = oreKey(fungatitePrefix,OreFeatureTypes.GOLD);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_GOLD_BURIED = oreKey(fungatitePrefix,OreFeatureTypes.GOLD_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_REDSTONE = oreKey(fungatitePrefix,OreFeatureTypes.REDSTONE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS = oreKey(fungatitePrefix,OreFeatureTypes.LAPIS);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_LAPIS_BURIED = oreKey(fungatitePrefix,OreFeatureTypes.LAPIS_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_BURIED = oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_LARGE = oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_MEDIUM = oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_MEDIUM);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_DIAMOND_SMALL = oreKey(fungatitePrefix,OreFeatureTypes.DIAMOND_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> FUNGATITE_ORE_EMERALD = oreKey(fungatitePrefix,OreFeatureTypes.EMERALD);


    // Ore setup
    public static final Map<String, EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>>> ORE_MAPS =
            new HashMap<>();
    static {
        registerStoneTypeOres(fungatitePrefix);
    }
    private static void registerStoneTypeOres(String prefix) {
        EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>> map = new EnumMap<>(OreFeatureTypes.class);
        for (OreFeatureTypes type : OreFeatureTypes.values()) {
            map.put(type, oreKey(prefix, type));
        }
        ORE_MAPS.put(prefix, map);
    }
    private static ResourceKey<ConfiguredFeature<?, ?>> oreKey(String prefix, OreFeatureTypes oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }

    // Register features here
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
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(FEATHER_MOSS_VEGETATION),
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
                        .add(ModBlocks.MYCELIUM_SPROUTS.get().defaultBlockState(), 8)
                        .add(ModBlocks.FEATHER_MOSS_TUFTS.get().defaultBlockState(), 6)
                        .add(ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), 6)
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
                0.5f,
                UniformInt.of(1,2),
                0.5f);
        FeatureUtils.register(context, UNDERGROUND_MYCELIUM_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, undergroundMyceliumPatchConfig);
        
        RandomPatchConfiguration myceliumVegetationPatchConfig = new RandomPatchConfiguration(
                1000,
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
                        new BlockColumnConfiguration.Layer(BiasedToBottomInt.of(1, 5), SimpleStateProvider.simple(ModBlocks.LAMPSHROOM_STEM.get())),
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
                new TwoLayersFeatureSize(1, 0, 1)
        )
                .ignoreVines()
                .build();
        FeatureUtils.register(context, LAMPSHROOM_TREE, Feature.TREE, lampshroomTreeConfig);


//        WallFaceGrowthConfiguration ghostFungusConfig = new WallFaceGrowthConfiguration(
//                ModBlocks.GHOST_FUNGUS.get(),
//                20,
//                0.8f,
//                HolderSet.direct(
//                        Block::builtInRegistryHolder,
//                        ModBlocks.FUNGATITE.get(),
//                        ModBlocks.FEATHER_MOSS_BLOCK.get(),
//                        ModBlocks.UNDERGROUND_MYCELIUM.get(),
//                        Blocks.STONE,
//                        Blocks.ANDESITE,
//                        Blocks.DIORITE,
//                        Blocks.GRANITE,
//                        Blocks.DRIPSTONE_BLOCK,
//                        Blocks.CALCITE,
//                        Blocks.TUFF,
//                        Blocks.DEEPSLATE
//                )
//        );
        //FeatureUtils.register(context, GHOST_FUNGUS, ModFeature.WALLFACE_GROWTH.get(), ghostFungusConfig);


        SimpleBlockConfiguration bleedingToothConfig = new SimpleBlockConfiguration(
                BlockStateProvider.simple(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get().defaultBlockState()),
                true
        );
        FeatureUtils.register(context, BLEEDING_TOOTH_FUNGUS, Feature.SIMPLE_BLOCK, bleedingToothConfig);



        createOreForStoneType(context, ModTags.Blocks.FUNGATITE_ORE_REPLACEABLE, fungatitePrefix, FUNGATITE_ORES);
    }


    private static void createOreForStoneType(BootstrapContext<ConfiguredFeature<?, ?>> context, TagKey<Block> targetTag, String prefix, EnumMap<OreTypes, Block> oreBlockMap)
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
