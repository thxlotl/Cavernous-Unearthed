package net.thxlotl.cavernous.worldgen;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.Cavernous;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FEATHER_MOSS_PATCH = registerKey("feather_moss_patch");
    public static final ResourceKey<PlacedFeature> UNDERGROUND_MYCELIUM_PATCH = registerKey("mycelium_vegetation_patch");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, FEATHER_MOSS_PATCH, configuredFeatures.getOrThrow(ModConfiguredFeatures.FEATHER_MOSS_PATCH),
                List.of(
                        CountPlacement.of(75),
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




    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return  ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
    }

    private static void register (BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
