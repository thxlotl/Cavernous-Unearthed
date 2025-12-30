package net.thxlotl.cavernous.util.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

public class CFeatureUtil {

    public static SimpleBlockConfiguration createWeightedState(WeightedBlockState... weightedBlockStates){

        WeightedList.Builder<BlockState> listBuilder = WeightedList.builder();
        for (WeightedBlockState wbs : weightedBlockStates) {
            listBuilder.add(wbs.state, wbs.weight);
        }
        return new SimpleBlockConfiguration(new WeightedStateProvider(listBuilder), true);

    }

    public static VegetationPatchConfiguration createSurfaceVegetationPatch(HolderGetter<ConfiguredFeature<?, ?>> holdergetter,
                                                                            TagKey<Block> tag,
                                                                            Block block,
                                                                            ResourceKey<ConfiguredFeature<?, ?>> featureKey,
                                                                            float vegChance,
                                                                            int rMin, int rMax) {
        return new VegetationPatchConfiguration(
                tag,
                BlockStateProvider.simple(block),
                PlacementUtils.inlinePlaced(holdergetter.getOrThrow(featureKey),
                        BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.SHORT_GRASS.defaultBlockState(), Vec3i.ZERO)),
                        BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)
                        )),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                vegChance,
                UniformInt.of(rMin, rMax),
                0.75f);
    }

    public static VegetationPatchConfiguration createSurfaceVegetationPatch(TagKey<Block> tag,
                                                                            Block block,
                                                                            int rMin, int rMax) {
        return new VegetationPatchConfiguration(
                tag,
                BlockStateProvider.simple(block),
                PlacementUtils.inlinePlaced(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE))),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                6,
                0,
                UniformInt.of(rMin, rMax),
                0.75f);
    }

    public static TreeConfiguration createForkingMushroom(Block capBlock) {

        return new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()),
                new ForkingTrunkPlacer(3, 2, 3),
                SimpleStateProvider.simple(capBlock.defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0)
        )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(
                                1,
                                0,
                                0,
                                SimpleStateProvider.simple(Blocks.MOSS_CARPET.defaultBlockState()),
                                1,
                                List.of(Direction.UP)
                        ),
                        new LeaveVineDecorator(0.25f)
                ))
                .ignoreVines()
                .build();
    }

    public static TreeConfiguration createCherryTrunkMushroom(Block capBlock) {

        return new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.MUSHROOM_STEM.defaultBlockState()),
                new CherryTrunkPlacer(
                        6, 2, 3,
                        new WeightedListInt(
                                WeightedList.<IntProvider>builder()
                                        .add(ConstantInt.of(2), 2)
                                        .add(ConstantInt.of(3), 5)
                                        .build()
                        ),
                        BiasedToBottomInt.of(3, 5),
                        UniformInt.of(-6, -5),
                        UniformInt.of(-2, 0)
                ),
                SimpleStateProvider.simple(capBlock.defaultBlockState()),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(0, 0, 0)
        )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(
                                1,
                                0,
                                0,
                                SimpleStateProvider.simple(Blocks.MOSS_CARPET.defaultBlockState()),
                                1,
                                List.of(Direction.UP)
                        ),
                        new LeaveVineDecorator(0.25f)
                ))
                .ignoreVines()
                .build();
    }
}