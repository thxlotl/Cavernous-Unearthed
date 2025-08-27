package net.thxlotl.cavernous.worldgen.custom.tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ToadstoolTrunkPlacer extends TrunkPlacer {

    private final IntProvider bendLength;
    private final IntProvider extrusionLength;
    private final IntProvider trunkTopBonusHeight;

    public static final MapCodec<ToadstoolTrunkPlacer>
            CODEC = RecordCodecBuilder.mapCodec(
            (toadstoolTrunkPlacerInstance) -> trunkPlacerParts(toadstoolTrunkPlacerInstance)
                    .and(
                            toadstoolTrunkPlacerInstance.group(
                                    IntProvider.codec(2, 16).fieldOf("bend_length").forGetter((bendInstance) -> bendInstance.bendLength),
                                    IntProvider.codec(2, 16).fieldOf("extrusion_length").forGetter((extrusionInstance) -> extrusionInstance.extrusionLength),
                                    IntProvider.codec(0, 16).fieldOf("trunk_top_bonus_height").forGetter((topInstance) -> topInstance.trunkTopBonusHeight)
                            ))
                                            .apply(toadstoolTrunkPlacerInstance, ToadstoolTrunkPlacer::new));


    public ToadstoolTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, IntProvider bendLength, IntProvider extrusionLength, IntProvider trunkTopBonusHeight) {
        super(baseHeight, heightRandA, heightRandB);
        this.bendLength = bendLength;
        this.extrusionLength = extrusionLength;;
        this.trunkTopBonusHeight = trunkTopBonusHeight;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.TOADSTOOL_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelSimulatedReader, BiConsumer<BlockPos, BlockState> biConsumer, RandomSource randomSource, int i, BlockPos blockPos, TreeConfiguration treeConfiguration) {

        setDirtAt(levelSimulatedReader, biConsumer, randomSource, blockPos.below(), treeConfiguration);

        int baseTrunkHeight = baseHeight + UniformInt.of(0, heightRandA).sample(randomSource) + UniformInt.of(0, heightRandB).sample(randomSource);
        int topTrunkHeight = baseTrunkHeight + trunkTopBonusHeight.sample(randomSource);
        int bendSetLength = bendLength.sample(randomSource);
        int extrusionSetLength = extrusionLength.sample(randomSource);
        boolean isVerticalBend = randomSource.nextBoolean();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);

        for (int t1 = 0; t1 < baseTrunkHeight; t1++)
        {
            placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(t1), treeConfiguration);
        }

        if (isVerticalBend)
        {
            for (int e1 = 0; e1 < extrusionSetLength; e1++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + e1), treeConfiguration);
            }
            for (int b1 = 0; b1 < bendSetLength; b1++)
            {
                biConsumer.accept(blockPos.above(baseTrunkHeight + extrusionSetLength).relative(direction, b1),
                        ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource, blockPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis())))
                );
                //placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight).relative(direction, e1), treeConfiguration);
            }
            for (int e2 = 0; e2 < extrusionSetLength; e2++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + extrusionSetLength - e2).relative(direction, bendSetLength), treeConfiguration);
            }
            for (int b2 = 0; b2 < bendSetLength; b2++)
            {
                biConsumer.accept(blockPos.above(baseTrunkHeight).relative(direction, bendSetLength + b2),
                        ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource, blockPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis())))
                );
                //placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight).relative(direction, e1), treeConfiguration);
            }
            for (int e3 = 0; e3 < extrusionSetLength; e3++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + e3).relative(direction, bendSetLength * 2), treeConfiguration);
            }
            for (int t2 = 0; t2 < topTrunkHeight; t2++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + extrusionSetLength + t2).relative(direction, bendSetLength * 2), treeConfiguration);
            }
            return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(baseTrunkHeight + extrusionSetLength + topTrunkHeight).relative(direction, bendSetLength * 2), 0, false));
        }
        else
        {
            for (int e1 = 0; e1 < extrusionSetLength; e1++)
            {
                biConsumer.accept(blockPos.above(baseTrunkHeight).relative(direction, e1),
                        ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource, blockPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis())))
                );
                //placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight).relative(direction, e1), treeConfiguration);
            }
            for (int b1 = 0; b1 < bendSetLength; b1++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + b1).relative(direction, extrusionSetLength), treeConfiguration);
            }
            for (int e2 = 0; e2 < extrusionSetLength; e2++)
            {
                biConsumer.accept(blockPos.above(baseTrunkHeight + bendSetLength).relative(direction, extrusionSetLength - e2),
                        ((BlockState) Function.identity().apply(treeConfiguration.trunkProvider.getState(randomSource, blockPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis())))
                );
                //placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + bendSetLength).relative(direction, extrusionSetLength - e2), treeConfiguration);
            }
            for (int t2 = 0; t2 < topTrunkHeight; t2++)
            {
                placeLog(levelSimulatedReader, biConsumer, randomSource, blockPos.above(baseTrunkHeight + bendSetLength + t2), treeConfiguration);
            }
            return ImmutableList.of(new FoliagePlacer.FoliageAttachment(blockPos.above(baseTrunkHeight + bendSetLength + topTrunkHeight), 0, false));
        }

    }

    protected static void setDirtAt(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        if (!((LevelReader)level).getBlockState(pos).onTreeGrow((LevelReader)level, blockSetter, random, pos, config) && config.forceDirt) {
            blockSetter.accept(pos, config.dirtProvider.getState(random, pos));
        }

    }
}
