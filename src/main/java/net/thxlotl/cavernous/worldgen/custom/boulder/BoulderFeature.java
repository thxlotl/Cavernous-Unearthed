package net.thxlotl.cavernous.worldgen.custom.boulder;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.ArrayList;
import java.util.List;

public class BoulderFeature extends Feature<BoulderConfiguration> {
    public BoulderFeature(Codec<BoulderConfiguration> codec) {
        super(codec);
    }

    private static final float NOISE_STRENGTH = 1.6f;
    private static final float FREQUENCY = 0.45f;

    @Override
    public boolean place(FeaturePlaceContext<BoulderConfiguration> featurePlaceContext) {

        List<BlockPos> placePositions = new ArrayList<>();

        RandomSource random = RandomSource.create(featurePlaceContext.origin().asLong());

        float radius = featurePlaceContext.config().radius().sample(random) + 1f;
        float xRadius = radius + random.nextIntBetweenInclusive(0, 2);
        float yRadius = radius + random.nextIntBetweenInclusive(0, 2);
        float zRadius = radius + random.nextIntBetweenInclusive(0, 2);

        BlockPos center = featurePlaceContext.origin();

        NormalNoise noise = NormalNoise.create(random, new NormalNoise.NoiseParameters(-4,1));

        for (int x = (int)-xRadius - 1; x <= (int)xRadius + 1; x++) {
            for (int y = (int)-yRadius - 1; y <= (int)yRadius + 1; y++) {
                for (int z = (int)-zRadius - 1; z <= (int)zRadius + 1; z++) {

                    float nx = (float) x / xRadius;
                    float ny = (float) y / yRadius;
                    float nz = (float) z / zRadius;

                    float noiseValue = Mth.clamp(
                            (float) noise.getValue(
                                    (center.getX() + x) * FREQUENCY,
                                    (center.getY() + y) * FREQUENCY,
                                    (center.getZ() + z) * FREQUENCY
                            ),
                            -1f,
                            1f
                    );

                    float base = nx * nx + ny * ny + nz * nz;

                    BlockPos blockPos = new BlockPos(center.offset(x, y, z));

                    if (base <= 1.0 + noiseValue * NOISE_STRENGTH) {

                        placePositions.add(blockPos);

//                        featurePlaceContext.level().setBlock(
//                                blockPos,
//                                featurePlaceContext.config().toPlace().getState(random, blockPos),
//                                2
//                        );
                    } else if (base <= 1.2 + noiseValue * NOISE_STRENGTH) {

                        WorldGenLevel level = featurePlaceContext.level();
                        if (neighborsTerrain(level, blockPos)) {
                            placePositions.add(blockPos);
                        }

                    }
                }
            }
        }

        for (BlockPos pos : placePositions) {

            featurePlaceContext.level().setBlock(
                    pos,
                    featurePlaceContext.config().toPlace().getState(featurePlaceContext.level(), random, pos),
                    2
            );

        }

        return true;
    }

    private boolean neighborsTerrain(WorldGenLevel level, BlockPos pos) {
        return !level.getBlockState(pos.above()).isAir() ||
                !level.getBlockState(pos.below()).isAir() ||
                !level.getBlockState(pos.north()).isAir() ||
                !level.getBlockState(pos.south()).isAir() ||
                !level.getBlockState(pos.east()).isAir() ||
                !level.getBlockState(pos.west()).isAir();

    }
}
