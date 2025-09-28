package net.thxlotl.cavernous.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.fml.common.Mod;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.OreFeatureTypes;
import net.thxlotl.cavernous.util.OrePlacedFeatureTypes;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FEATHER_MOSS_PATCH = registerKey("feather_moss_patch");
    public static final ResourceKey<PlacedFeature> UNDERGROUND_MYCELIUM_PATCH = registerKey("mycelium_vegetation_patch");
    public static final ResourceKey<PlacedFeature> TOADSTOOL = registerKey("toadstool");
    public static final ResourceKey<PlacedFeature> HANGING_SHROOM = registerKey("hanging_shroom");
    public static final ResourceKey<PlacedFeature> LAMPSHROOM = registerKey("lampshroom");
    public static final ResourceKey<PlacedFeature> LAMPSHROOM_TREE = registerKey("lampshroom_tree");
    public static final ResourceKey<PlacedFeature> GHOST_FUNGUS = registerKey("ghost_fungus");
    public static final ResourceKey<PlacedFeature> BLEEDING_TOOTH_FUNGUS = registerKey("bleeding_tooth_fungus");
    public static final ResourceKey<PlacedFeature> CORDYCEPS = registerKey("cordyceps");

    private static final String fungatitePrefix = "fungatite";
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COAL_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.COAL_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COAL_UPPER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.COAL_UPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COPPER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.COPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_COPPER_LARGE = oreKey(fungatitePrefix, OrePlacedFeatureTypes.COPPER_LARGE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_MIDDLE = oreKey(fungatitePrefix, OrePlacedFeatureTypes.IRON_MIDDLE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_SMALL = oreKey(fungatitePrefix, OrePlacedFeatureTypes.IRON_SMALL);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_IRON_UPPER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.IRON_UPPER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_GOLD = oreKey(fungatitePrefix, OrePlacedFeatureTypes.GOLD);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_GOLD_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.GOLD_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_REDSTONE = oreKey(fungatitePrefix, OrePlacedFeatureTypes.REDSTONE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_REDSTONE_LOWER = oreKey(fungatitePrefix, OrePlacedFeatureTypes.REDSTONE_LOWER);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_LAPIS = oreKey(fungatitePrefix, OrePlacedFeatureTypes.LAPIS);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_LAPIS_BURIED = oreKey(fungatitePrefix, OrePlacedFeatureTypes.LAPIS_BURIED);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND = oreKey(fungatitePrefix, OrePlacedFeatureTypes.DIAMOND);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_BURIED = oreKey(fungatitePrefix, OrePlacedFeatureTypes.DIAMOND_BURIED);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_LARGE = oreKey(fungatitePrefix, OrePlacedFeatureTypes.DIAMOND_LARGE);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_DIAMOND_MEDIUM = oreKey(fungatitePrefix, OrePlacedFeatureTypes.DIAMOND_MEDIUM);
    public static final ResourceKey<PlacedFeature> FUNGATITE_ORE_EMERALD = oreKey(fungatitePrefix, OrePlacedFeatureTypes.EMERALD);


    // Ore setup
    public static final Map<String, EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>>> PLACED_ORE_MAPS =
            new HashMap<>();
    static {
        registerStoneTypeOres(fungatitePrefix);
    }
    private static void registerStoneTypeOres(String prefix) {
        EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>> map = new EnumMap<>(OrePlacedFeatureTypes.class);
        for (OrePlacedFeatureTypes type : OrePlacedFeatureTypes.values()) {
            map.put(type, oreKey(prefix, type));
        }
        PLACED_ORE_MAPS.put(prefix, map);
    }
    private static ResourceKey<PlacedFeature> oreKey(String prefix, OrePlacedFeatureTypes oreType)
    {
        String suffix = oreType.toString().toLowerCase();
        return registerKey(prefix + "_ore_" + suffix);
    }


    // Register Features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, FEATHER_MOSS_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.FEATHER_MOSS_PATCH),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                12

                        ),
                        BiomeFilter.biome()
                ));
        register(context, UNDERGROUND_MYCELIUM_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.UNDERGROUND_MYCELIUM_PATCH),
                List.of(
                        CountPlacement.of(125),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR),
                                12
                        ),
                        BiomeFilter.biome()
                ));
        register(context, TOADSTOOL, configuredFeatures.getOrThrow(ModConfiguredFeatures.TOADSTOOL),
                List.of(
                        CountPlacement.of(45),
                        InSquarePlacement.spread(),
                        SurfaceWaterDepthFilter.forMaxDepth(3),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.wouldSurvive(ModBlocks.TOADSTOOL_BUTTON.get().defaultBlockState(), Vec3i.ZERO),
                                12
                        ),
                        BiomeFilter.biome()
                ));
        register(context, HANGING_SHROOM, configuredFeatures.getOrThrow(ModConfiguredFeatures.HANGING_SHROOM),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.UP,
                                BlockPredicate.wouldSurvive(ModBlocks.HANGING_SHROOM_CAP.get().defaultBlockState(), Vec3i.ZERO),
                                12
                        ),
                        BiomeFilter.biome()
                ));

        register(context, LAMPSHROOM, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAMPSHROOM),
                List.of(
                        CountPlacement.of(60),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.LAMPSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                        ),
                                12
                        ),
                        BiomeFilter.biome()
                ));
        register(context, LAMPSHROOM_TREE, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAMPSHROOM_TREE),
                List.of(
                        CountPlacement.of(15),
                        InSquarePlacement.spread(),
                        SurfaceWaterDepthFilter.forMaxDepth(3),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.wouldSurvive(ModBlocks.LAMPSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                12
                        ),
                        BiomeFilter.biome()
                ));

//        register(context, GHOST_FUNGUS, configuredFeatures.getOrThrow(ModConfiguredFeatures.GHOST_FUNGUS),
//                List.of(
//                        CountPlacement.of(UniformInt.of(150, 160)),
//                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
//                        InSquarePlacement.spread(),
//                        BiomeFilter.biome()
//                ));


        register(context, BLEEDING_TOOTH_FUNGUS, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLEEDING_TOOTH_FUNGUS),
                List.of(
                        CountPlacement.of(35),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                        EnvironmentScanPlacement.scanningFor(
                                Direction.DOWN,
                                BlockPredicate.allOf(
                                        BlockPredicate.wouldSurvive(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get().defaultBlockState(), Vec3i.ZERO),
                                        BlockPredicate.matchesBlocks(Blocks.AIR)
                                ),
                                12
                        ),
                        BiomeFilter.biome()
                ));

        register(context, CORDYCEPS, configuredFeatures.getOrThrow(ModConfiguredFeatures.CORDYCEPS),
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
                                12
                        ),
                        BiomeFilter.biome()
                ));

        createOreForStoneType(context, configuredFeatures, fungatitePrefix);

    }

    private static void createOreForStoneType(BootstrapContext<PlacedFeature> context, HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures, String prefix)
    {
        EnumMap<OreFeatureTypes, ResourceKey<ConfiguredFeature<?, ?>>> configuredMap = ModConfiguredFeatures.ORE_MAPS.get(prefix);
        EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>> placedMap = PLACED_ORE_MAPS.get(prefix);

        register(context, placedMap.get(OrePlacedFeatureTypes.COAL_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COAL_BURIED)),
                List.of(
                        CountPlacement.of(20),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COAL_UPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COAL)),
                List.of(
                        CountPlacement.of(30),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(136), VerticalAnchor.belowTop(0))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COPPER_SMALL)),
                List.of(
                        CountPlacement.of(16),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.COPPER_LARGE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.COPPER_LARGE)),
                List.of(
                        CountPlacement.of(16),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_MIDDLE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON)),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_SMALL), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON_SMALL)),
                List.of(
                        CountPlacement.of(10),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.IRON_UPPER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.IRON)),
                List.of(
                        CountPlacement.of(90),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.GOLD), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.GOLD_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.GOLD_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.GOLD_BURIED)),
                List.of(
                        CountPlacement.of(UniformInt.of(0, 1)),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.REDSTONE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.REDSTONE)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.REDSTONE_LOWER), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.REDSTONE)),
                List.of(
                        CountPlacement.of(8),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.LAPIS), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.LAPIS)),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.LAPIS_BURIED), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.LAPIS_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_SMALL)),
                List.of(
                        CountPlacement.of(7),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_BURIED), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_BURIED)),
                List.of(
                        CountPlacement.of(4),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_LARGE), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_LARGE)),
                List.of(
                        RarityFilter.onAverageOnceEvery(9),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.DIAMOND_MEDIUM), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.DIAMOND_MEDIUM)),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))),
                        BiomeFilter.biome()
                ));
        register(context, placedMap.get(OrePlacedFeatureTypes.EMERALD), configuredFeatures.getOrThrow(configuredMap.get(OreFeatureTypes.EMERALD)),
                List.of(
                        CountPlacement.of(100),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))),
                        BiomeFilter.biome()
                ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
    }

    private static void register (BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
