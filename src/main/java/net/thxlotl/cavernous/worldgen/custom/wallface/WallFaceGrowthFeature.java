package net.thxlotl.cavernous.worldgen.custom.wallface;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.List;

public class WallFaceGrowthFeature extends Feature<WallFaceGrowthConfiguration> {

    public WallFaceGrowthFeature(Codec<WallFaceGrowthConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<WallFaceGrowthConfiguration> featurePlaceContext) {
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        BlockPos blockpos = featurePlaceContext.origin();
        RandomSource randomsource = featurePlaceContext.random();
        WallFaceGrowthConfiguration wallFaceGrowthConfiguration = (WallFaceGrowthConfiguration)featurePlaceContext.config();
        if (!isAirOrWater(worldgenlevel.getBlockState(blockpos))) {
            return false;
        } else {
            List<Direction> list = wallFaceGrowthConfiguration.getShuffledDirections(randomsource);
            if (placeGrowthIfPossible(worldgenlevel, blockpos, worldgenlevel.getBlockState(blockpos), wallFaceGrowthConfiguration, randomsource, list)) {
                return true;
            } else {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();

                for(Direction direction : list) {
                    blockpos$mutableblockpos.set(blockpos);
                    List<Direction> list1 = wallFaceGrowthConfiguration.getShuffledDirectionsExcept(randomsource, direction.getOpposite());

                    for(int i = 0; i < wallFaceGrowthConfiguration.searchRange; ++i) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, direction);
                        BlockState blockstate = worldgenlevel.getBlockState(blockpos$mutableblockpos);
                        if (!isAirOrWater(blockstate) && !blockstate.is(wallFaceGrowthConfiguration.placeBlock)) {
                            break;
                        }

                        if (placeGrowthIfPossible(worldgenlevel, blockpos$mutableblockpos, blockstate, wallFaceGrowthConfiguration, randomsource, list1)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean placeGrowthIfPossible(WorldGenLevel level, BlockPos pos, BlockState state, WallFaceGrowthConfiguration config, RandomSource random, List<Direction> directions) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable();

        for(Direction direction : directions) {
            BlockState blockstate = level.getBlockState(blockpos$mutableblockpos.setWithOffset(pos, direction));
            if (blockstate.is(config.canBePlacedOn)) {
                BlockState blockstate1 = config.placeBlock.getStateAtDirection(state, direction, pos, level.getLevel());
                if (blockstate1 == null) {
                    return false;
                }

                level.setBlock(pos, blockstate1, 3);
                level.getChunk(pos).markPosForPostprocessing(pos);
                if (random.nextFloat() < config.chanceOfSpreading) {
                    config.placeBlock.getSpreader().spreadFromFaceTowardRandomDirection(blockstate1, level, pos, direction, random, true);
                }

                return true;
            }
        }

        return false;
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER);
    }
}
