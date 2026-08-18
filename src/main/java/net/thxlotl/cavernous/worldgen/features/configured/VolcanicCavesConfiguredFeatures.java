package net.thxlotl.cavernous.worldgen.features.configured;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.datagen.tag.ModTags;
import net.thxlotl.cavernous.util.worldgen.CFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.worldgen.custom.ModFeature;
import net.thxlotl.cavernous.worldgen.features.ModConfiguredFeatures;

import java.util.List;

public class VolcanicCavesConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> GEYSER_PILLAR = ModConfiguredFeatures.registerKey("geyser_pillar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GEYSER_CLUSTER = ModConfiguredFeatures.registerKey("geyser_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_FALL = ModConfiguredFeatures.registerKey("lava_fall");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGMA_FERN = ModConfiguredFeatures.registerKey("magma_fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SCORIA = ModConfiguredFeatures.registerKey("ore_scoria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ERUPTITE = ModConfiguredFeatures.registerKey("ore_eruptite");

    //region ORES
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_IRON = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.IRON);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_IRON_SMALL = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.IRON_SMALL);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_GOLD = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.GOLD);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_GOLD_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.GOLD_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_REDSTONE = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.REDSTONE);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_LAPIS = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.LAPIS);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_LAPIS_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.LAPIS_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_DIAMOND_BURIED = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.DIAMOND_BURIED);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_DIAMOND_LARGE = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.DIAMOND_LARGE);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_DIAMOND_MEDIUM = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.DIAMOND_MEDIUM);
    public static final ResourceKey<ConfiguredFeature<?,?>> OBSIDIANSTONE_ORE_DIAMOND_SMALL = ModConfiguredFeatures.oreKey(CustomStoneType.OBSIDIANSTONE, OreConfiguredFeatureType.DIAMOND_SMALL);
    //endregion

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
        FeatureUtils.register(context, GEYSER_CLUSTER, Feature.VEGETATION_PATCH,
                new VegetationPatchConfiguration(
                        ModTags.Blocks.GEYSER_PATCH_REPLACEABLE,
                        BlockStateProvider.simple(ModBlocks.OBSIDIANSTONE.get()),
                        PlacementUtils.inlinePlaced(holdergetter.getOrThrow(GEYSER_PILLAR),
                                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(Vec3i.ZERO, Blocks.AIR, Blocks.LAVA),
                                        BlockPredicate.wouldSurvive(Blocks.TORCH.defaultBlockState(), Vec3i.ZERO)
                                ))
                        ),
                        CaveSurface.FLOOR,
                        ConstantInt.of(4),
                        0.2f,
                        5,
                        0.2f,
                        UniformInt.of(1, 2),
                        0.4f
                ));
        //FeatureUtils.register(context, GEYSER_CLUSTER, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.GEYSER_BLOCK.get()));

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

        FeatureUtils.register(context, MAGMA_FERN, Feature.SIMPLE_BLOCK, CFeatureUtil.createSimpleBlock(ModBlocks.MAGMA_FERN.get()));

        FeatureUtils.register(context, ORE_SCORIA, Feature.ORE, CFeatureUtil.createBlobOre(ModBlocks.SCORIA.get()));

        ModConfiguredFeatures.createOreForStoneType(context, CustomStoneType.OBSIDIANSTONE, ModTags.Blocks.OBSIDIANSTONE_ORE_REPLACEABLE);

        FeatureUtils.register(context, ORE_ERUPTITE, Feature.ORE, new OreConfiguration(
                new TagMatchTest(ModTags.Blocks.OBSIDIANSTONE_ORE_REPLACEABLE),
                ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get().defaultBlockState(),
                18,
                0.0f
        ));
    }
}
