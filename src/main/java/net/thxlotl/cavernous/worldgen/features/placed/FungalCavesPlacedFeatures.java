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
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;
import net.thxlotl.cavernous.worldgen.features.ModPlacedFeatures;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

import java.util.List;

import static net.thxlotl.cavernous.worldgen.features.ModPlacedFeatures.*;

public class FungalCavesPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FEATHER_MOSS_PATCH = registerKey("feather_moss_patch");
    public static final ResourceKey<PlacedFeature> UNDERGROUND_MYCELIUM_PATCH = registerKey("mycelium_vegetation_patch");
    public static final ResourceKey<PlacedFeature> TOADSTOOL = registerKey("toadstool");
    public static final ResourceKey<PlacedFeature> HANGING_SHROOM = registerKey("hanging_shroom");
    public static final ResourceKey<PlacedFeature> LAMPSHROOM = registerKey("lampshroom");
    public static final ResourceKey<PlacedFeature> LAMPSHROOM_TREE = registerKey("lampshroom_tree");
    public static final ResourceKey<PlacedFeature> GHOST_FUNGUS = registerKey("ghost_fungus");
    public static final ResourceKey<PlacedFeature> BLEEDING_TOOTH_FUNGUS = registerKey("bleeding_tooth_fungus");
    public static final ResourceKey<PlacedFeature> CORDYCEPS = registerKey("cordyceps");
    public static final ResourceKey<PlacedFeature> ORE_GROUND_FUNGATITE = registerKey("ore_ground_fungatite");
    public static final ResourceKey<PlacedFeature> SHELFSHROOM = registerKey("shelfshroom");
    public static final ResourceKey<PlacedFeature> PUFFSHROOM = registerKey("puffshroom");

    private static final String fungatitePrefix = "fungatite";
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COAL_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureType.COAL_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COAL_UPPER = oreKey(fungatitePrefix, OrePlacedFeatureType.COAL_UPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COPPER = oreKey(fungatitePrefix, OrePlacedFeatureType.COPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COPPER_LARGE = oreKey(fungatitePrefix, OrePlacedFeatureType.COPPER_LARGE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_MIDDLE = oreKey(fungatitePrefix, OrePlacedFeatureType.IRON_MIDDLE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_SMALL = oreKey(fungatitePrefix, OrePlacedFeatureType.IRON_SMALL);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_UPPER = oreKey(fungatitePrefix, OrePlacedFeatureType.IRON_UPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_GOLD = oreKey(fungatitePrefix, OrePlacedFeatureType.GOLD);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_GOLD_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureType.GOLD_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_REDSTONE = oreKey(fungatitePrefix, OrePlacedFeatureType.REDSTONE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_REDSTONE_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureType.REDSTONE_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_LAPIS = oreKey(fungatitePrefix, OrePlacedFeatureType.LAPIS);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_LAPIS_BURIED = oreKey(fungatitePrefix, OrePlacedFeatureType.LAPIS_BURIED);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND = oreKey(fungatitePrefix, OrePlacedFeatureType.DIAMOND);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_BURIED = oreKey(fungatitePrefix, OrePlacedFeatureType.DIAMOND_BURIED);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_LARGE = oreKey(fungatitePrefix, OrePlacedFeatureType.DIAMOND_LARGE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_MEDIUM = oreKey(fungatitePrefix, OrePlacedFeatureType.DIAMOND_MEDIUM);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_EMERALD = oreKey(fungatitePrefix, OrePlacedFeatureType.EMERALD);


    static {
        ModPlacedFeatures.registerStoneTypeOres(fungatitePrefix);
    }


    // Register Features
    public static void bootstrap(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures) {


        register(context, FEATHER_MOSS_PATCH, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.FEATHER_MOSS_PATCH),
                List.of(
                        CountPlacement.of(130),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24

                        ),
                        BiomeFilter.biome()
                ));
        register(context, UNDERGROUND_MYCELIUM_PATCH, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.UNDERGROUND_MYCELIUM_PATCH),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));
        register(context, TOADSTOOL, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.TOADSTOOL),
                List.of(
                        CountPlacement.of(40),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR)),
                                24
                        ),
                        //SurfaceWaterDepthFilter.forMaxDepth(3),
                        BiomeFilter.biome()
                ));
        register(context, HANGING_SHROOM, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.HANGING_SHROOM),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.UP,
                                BlockPredicate.allOf(List.of(
                                        BlockPredicate.wouldSurvive(ModBlocks.HANGING_SHROOM_CAP.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), ModBlocks.HANGING_SHROOM_CAP.get())),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), ModBlocks.SHELFSHROOM_CAP_BLOCK.get()))
                                )),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        register(context, LAMPSHROOM, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.LAMPSHROOM),
                List.of(
                        CountPlacement.of(110),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.LAMPSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                ),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));
        register(context, LAMPSHROOM_TREE, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.LAMPSHROOM_TREE),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR)),
                                24
                        ),
                        //SurfaceWaterDepthFilter.forMaxDepth(3),
                        BiomeFilter.biome()
                ));

        register(context, GHOST_FUNGUS, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.GHOST_FUNGUS),
                List.of(
                        CountPlacement.of(ConstantInt.of(40)),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.solid(),
                                        BlockPredicate.anyOf(
                                                BlockPredicate.matchesBlocks(new Vec3i(1, 0, 0), Blocks.AIR),
                                                BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 0), Blocks.AIR),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0), Blocks.AIR),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), Blocks.AIR),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 0, 1), Blocks.AIR),
                                                BlockPredicate.matchesBlocks(new Vec3i(0, 0, -1), Blocks.AIR)
                                        )
                                ),
                                BlockPredicate.alwaysTrue(),
                                24
                        ),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                ));


        register(context, BLEEDING_TOOTH_FUNGUS, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.BLEEDING_TOOTH_FUNGUS),
                List.of(
                        CountPlacement.of(35),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                ),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        register(context, PUFFSHROOM, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.PUFFSHROOM),
                List.of(
                        CountPlacement.of(25),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(24), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.PUFFSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                ),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        register(context, CORDYCEPS, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.CORDYCEPS),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.CORDYCEPS_PATCH.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                ),
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        register(context, ORE_GROUND_FUNGATITE, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.ORE_GROUND_FUNGATITE),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.belowTop(0)),
                        BiomeFilter.biome()
                ));

        register(context, SHELFSHROOM, configuredFeatures.getOrThrow(FungalCavesConfiguredFeatures.SHELFSHROOM),
                List.of(
                        CountPlacement.of(175),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.UP,
                                BlockPredicate.allOf(
                                        BlockPredicate.solid(Vec3i.ZERO.above()),
                                        BlockPredicate.solid(Vec3i.ZERO.below()),
                                        BlockPredicate.solid(),
                                        BlockPredicate.anyOf(
                                                BlockPredicate.allOf(
                                                        BlockPredicate.matchesBlocks(new Vec3i(1, 0, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(1, 1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(1, -1, 0), Blocks.AIR)
                                                ),
                                                BlockPredicate.allOf(
                                                        BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(-1, 1, 0), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(-1, -1, 0), Blocks.AIR)
                                                ),
                                                BlockPredicate.allOf(
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 1), Blocks.AIR)
                                                ),
                                                BlockPredicate.allOf(
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, -1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, -1), Blocks.AIR),
                                                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, -1), Blocks.AIR)
                                                )
                                        )
                                ),
                                24
                        ),
                        BiomeFilter.biome()
                ));

        ModPlacedFeatures.createOreForStoneType(context, configuredFeatures, CustomStoneType.FUNGATITE);

    }
}
