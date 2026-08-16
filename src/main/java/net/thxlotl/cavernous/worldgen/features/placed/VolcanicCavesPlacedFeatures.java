package net.thxlotl.cavernous.worldgen.features.placed;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.worldgen.PFeatureUtil;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;
import net.thxlotl.cavernous.worldgen.features.ModPlacedFeatures;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.features.configured.VolcanicCavesConfiguredFeatures;

import java.util.List;

import static net.thxlotl.cavernous.worldgen.features.ModPlacedFeatures.*;

public class VolcanicCavesPlacedFeatures {

    public static final ResourceKey<PlacedFeature> GEYSER_CLUSTER = registerKey("geyser_cluster");
    public static final ResourceKey<PlacedFeature> LAVA_FALL = registerKey("lava_fall");
    public static final ResourceKey<PlacedFeature> MAGMA_FERN = registerKey("magma_fern");
    public static final ResourceKey<PlacedFeature> ORE_SCORIA = registerKey("ore_scoria");

    //region ORES

    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_IRON_MIDDLE = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.IRON_MIDDLE);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_IRON_SMALL = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.IRON_SMALL);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_IRON_UPPER = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.IRON_UPPER);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_GOLD = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.GOLD);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_GOLD_LOWER = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.GOLD_LOWER);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_REDSTONE = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.REDSTONE);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_REDSTONE_LOWER = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.REDSTONE_LOWER);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_LAPIS = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.LAPIS);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_LAPIS_BURIED = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.LAPIS_BURIED);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_DIAMOND = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.DIAMOND);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_DIAMOND_BURIED = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.DIAMOND_BURIED);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_DIAMOND_LARGE = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.DIAMOND_LARGE);
    public static final ResourceKey<PlacedFeature> OBSIDIANSTONE_ORE_DIAMOND_MEDIUM = oreKey(CustomStoneType.OBSIDIANSTONE, OrePlacedFeatureType.DIAMOND_MEDIUM);


    //endregion


    // Register Features
    public static void bootstrap(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures) {

        register(context, GEYSER_CLUSTER, configuredFeatures.getOrThrow(VolcanicCavesConfiguredFeatures.GEYSER_CLUSTER), PFeatureUtil.caveTreePlacementModifiers(10, Blocks.TORCH));

        register(context, MAGMA_FERN, configuredFeatures.getOrThrow(VolcanicCavesConfiguredFeatures.MAGMA_FERN), PFeatureUtil.caveTreePlacementModifiers(20, ModBlocks.MAGMA_FERN.get()));

        register(context, LAVA_FALL, configuredFeatures.getOrThrow(VolcanicCavesConfiguredFeatures.LAVA_FALL),
                List.of(
                        CountPlacement.of(25),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.UP,
                                BlockPredicate.allOf(
                                        BlockPredicate.solid(Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Vec3i.ZERO.below(), Blocks.AIR)
                                ),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        register(context, ORE_SCORIA, configuredFeatures.getOrThrow(VolcanicCavesConfiguredFeatures.ORE_SCORIA), PFeatureUtil.cavePlacementModifersNoScan(30));

        ModPlacedFeatures.createOreForStoneType(context, configuredFeatures, CustomStoneType.OBSIDIANSTONE);

    }
}
