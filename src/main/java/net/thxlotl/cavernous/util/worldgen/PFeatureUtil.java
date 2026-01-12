package net.thxlotl.cavernous.util.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PFeatureUtil {

    public static List<PlacementModifier> cavePlacementModifers(int count) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN,
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        24
                ),
                BiomeFilter.biome()
        );
    }
    public static List<PlacementModifier> cavePlacementModifers(int count, Direction direction) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(
                        direction,
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        24
                ),
                BiomeFilter.biome()
        );
    }
    public static List<PlacementModifier> cavePlacementModifersNoScan(int count) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                BiomeFilter.biome()
        );
    }

    public static List<PlacementModifier> caveTreePlacementModifiers(int count, Block sapling) {
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(
                        Direction.DOWN,
                        BlockPredicate.allOf(
                                BlockPredicate.wouldSurvive(sapling.defaultBlockState(), Vec3i.ZERO),
                                BlockPredicate.matchesBlocks(Blocks.AIR)
                        ),
                        24
                ),
                BiomeFilter.biome()
        );
    }

}