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
import net.neoforged.fml.common.Mod;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.custom.ToadstoolButtonBlock;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.util.worldgen.CFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.WeightedBlockState;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
import net.thxlotl.cavernous.worldgen.custom.boulder.BoulderConfiguration;
import net.thxlotl.cavernous.worldgen.custom.segmentedwallblock.SegmentedWallBlockConfiguration;
import net.thxlotl.cavernous.worldgen.custom.tree.MushroomCapFoliagePlacer;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;
import net.thxlotl.cavernous.worldgen.features.ModConfiguredFeatures;

import java.util.List;

public class VolcanicCavesConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GEYSER_PILLAR = ModConfiguredFeatures.registerKey("geyser_pillar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEYSER_CLUSTER = ModConfiguredFeatures.registerKey("geyser_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_FALL = ModConfiguredFeatures.registerKey("lava_fall");

    // Register features here
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

        //region Geyser
        FeatureUtils.register(context, GEYSER_PILLAR, Feature.BLOCK_COLUMN,
                new BlockColumnConfiguration(
                        List.of(
                                BlockColumnConfiguration.layer(BiasedToBottomInt.of(0, 4), SimpleStateProvider.simple(ModBlocks.OBSIDIANSTONE.get())),
                                BlockColumnConfiguration.layer(ConstantInt.of(1), SimpleStateProvider.simple(ModBlocks.GEYSER_BLOCK.get()))
                        ),
                        Direction.UP,
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        true
                ));

        FeatureUtils.register(context, GEYSER_CLUSTER, Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        12,
                        1,
                        4,
                        PlacementUtils.inlinePlaced(
                                holdergetter.getOrThrow(GEYSER_PILLAR),
                                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.TORCH.defaultBlockState(), Vec3i.ZERO)),
                                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Blocks.AIR), BlockPredicate.matchesTag(BlockTags.REPLACEABLE)))
                        )
                ));
        //endregion

//        FeatureUtils.register(context, LAVA_FALL, Feature.BLOCK_COLUMN,
//                new BlockColumnConfiguration(
//                        List.of(
//                                BlockColumnConfiguration.layer(ConstantInt.of(40), SimpleStateProvider.simple(Blocks.AIR)),
//                                BlockColumnConfiguration.layer(ConstantInt.of(1), SimpleStateProvider.simple(Blocks.LAVA))
//                        ),
//                        Direction.UP,
//                        BlockPredicate.anyOf(
//                                BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), Blocks.AIR),
//                                BlockPredicate.allOf(
//                                        BlockPredicate.matchesBlocks(Vec3i.ZERO.below(2), Blocks.AIR),
//                                        BlockPredicate.solid(Vec3i.ZERO.north().below()),
//                                        BlockPredicate.solid(Vec3i.ZERO.east().below()),
//                                        BlockPredicate.solid(Vec3i.ZERO.south().below()),
//                                        BlockPredicate.solid(Vec3i.ZERO.west().below())
//                                )
//                        ),
//                        true
//                ));
        FeatureUtils.register(context, LAVA_FALL, ModFeature.LAVA_FALL_FEATURE.get(), new NoneFeatureConfiguration());

    }
}
