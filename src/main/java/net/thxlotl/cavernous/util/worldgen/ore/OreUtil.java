package net.thxlotl.cavernous.util.worldgen.ore;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;

import java.util.EnumMap;
import java.util.List;

public class OreUtil {

    public static EnumMap<OrePlacedFeatureType, OreConfiguredFeatureType> featureRelation = new EnumMap<>(OrePlacedFeatureType.class);
    public static EnumMap<OrePlacedFeatureType, List<PlacementModifier>> featurePlacementList = new EnumMap<>(OrePlacedFeatureType.class);

    public static OreConfiguredFeatureType getConfiguredFeatureType(OrePlacedFeatureType type) {
        return featureRelation.get(type);
    }
    public static List<PlacementModifier> getPlacementModifiers(OrePlacedFeatureType type) {
        return featurePlacementList.get(type);
    }

    static  {
        featureRelation.put(OrePlacedFeatureType.COAL_LOWER, OreConfiguredFeatureType.COAL_BURIED);
        featureRelation.put(OrePlacedFeatureType.COAL_UPPER, OreConfiguredFeatureType.COAL);
        featureRelation.put(OrePlacedFeatureType.COPPER, OreConfiguredFeatureType.COPPER_SMALL);
        featureRelation.put(OrePlacedFeatureType.COPPER_LARGE, OreConfiguredFeatureType.COPPER_LARGE);
        featureRelation.put(OrePlacedFeatureType.IRON_MIDDLE, OreConfiguredFeatureType.IRON);
        featureRelation.put(OrePlacedFeatureType.IRON_SMALL, OreConfiguredFeatureType.IRON_SMALL);
        featureRelation.put(OrePlacedFeatureType.IRON_UPPER, OreConfiguredFeatureType.IRON);
        featureRelation.put(OrePlacedFeatureType.GOLD, OreConfiguredFeatureType.GOLD_BURIED);
        featureRelation.put(OrePlacedFeatureType.GOLD_LOWER, OreConfiguredFeatureType.GOLD_BURIED);
        featureRelation.put(OrePlacedFeatureType.REDSTONE, OreConfiguredFeatureType.REDSTONE);
        featureRelation.put(OrePlacedFeatureType.REDSTONE_LOWER, OreConfiguredFeatureType.REDSTONE);
        featureRelation.put(OrePlacedFeatureType.LAPIS, OreConfiguredFeatureType.LAPIS);
        featureRelation.put(OrePlacedFeatureType.LAPIS_BURIED, OreConfiguredFeatureType.LAPIS_BURIED);
        featureRelation.put(OrePlacedFeatureType.DIAMOND, OreConfiguredFeatureType.DIAMOND_SMALL);
        featureRelation.put(OrePlacedFeatureType.DIAMOND_BURIED, OreConfiguredFeatureType.DIAMOND_BURIED);
        featureRelation.put(OrePlacedFeatureType.DIAMOND_LARGE, OreConfiguredFeatureType.DIAMOND_LARGE);
        featureRelation.put(OrePlacedFeatureType.DIAMOND_MEDIUM, OreConfiguredFeatureType.DIAMOND_MEDIUM);
        featureRelation.put(OrePlacedFeatureType.EMERALD, OreConfiguredFeatureType.EMERALD);

        featurePlacementList.put(OrePlacedFeatureType.COAL_LOWER, List.of(
                CountPlacement.of(20),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(192))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.COAL_UPPER, List.of(
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(136), VerticalAnchor.belowTop(0))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.COPPER, List.of(
                CountPlacement.of(16),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.COPPER_LARGE, List.of(
                CountPlacement.of(16),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.IRON_MIDDLE, List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.IRON_SMALL, List.of(
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(72))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.IRON_UPPER, List.of(
                CountPlacement.of(90),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.GOLD, List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.GOLD_LOWER, List.of(
                CountPlacement.of(UniformInt.of(0, 1)),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.REDSTONE, List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(15))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.REDSTONE_LOWER, List.of(
                CountPlacement.of(8),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(-32), VerticalAnchor.aboveBottom(32))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.LAPIS, List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-32), VerticalAnchor.absolute(32))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.LAPIS_BURIED, List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(64))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.DIAMOND, List.of(
                CountPlacement.of(7),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.DIAMOND_BURIED, List.of(
                CountPlacement.of(4),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.DIAMOND_LARGE, List.of(
                RarityFilter.onAverageOnceEvery(9),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.DIAMOND_MEDIUM, List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-4))),
                BiomeFilter.biome()
        ));

        featurePlacementList.put(OrePlacedFeatureType.EMERALD, List.of(
                CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(480))),
                BiomeFilter.biome()
        ));
    }
}
