package net.thxlotl.cavernous.worldgen.features.configured;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.worldgen.CFeatureUtil;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
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

        // Mojang ur killing me
//        FeatureUtils.register(context, GEYSER_CLUSTER, Feature.RANDOM_PATCH,
//                new RandomPatchConfiguration(
//                        12,
//                        1,
//                        4,
//                        PlacementUtils.inlinePlaced(
//                                holdergetter.getOrThrow(GEYSER_PILLAR),
//                                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.TORCH.defaultBlockState(), Vec3i.ZERO)),
//                                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Blocks.AIR), BlockPredicate.matchesTag(BlockTags.REPLACEABLE)))
//                        )
//                ));
        FeatureUtils.register(context, GEYSER_CLUSTER, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.GEYSER_BLOCK.get()));
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
