package net.thxlotl.cavernous.worldgen.custom.wallshroom;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class WallShroomFeature extends Feature<WallShroomConfiguration> {
    public WallShroomFeature(Codec<WallShroomConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WallShroomConfiguration> context) {

        WallShroomConfiguration config = context.config();
        WorldGenLevel wgLevel = context.level();
        BlockPos origin = context.origin();

        int radius = config.radius().sample(context.random());

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                //BlockPos currentPos = new BlockPos(x + origin.getX(), origin.getY(), z + origin.getZ());
                BlockPos currentPos = origin.offset(x, 0, z);
                BlockState placeState = config.toPlace().getState(wgLevel, context.random(), currentPos);

                if (canPlace(origin, currentPos, radius) && wgLevel.getBlockState(currentPos).isAir() && placeState.canSurvive(wgLevel, currentPos)) {

                    wgLevel.setBlock(currentPos, placeState, 2);

                    if (config.scheduleTick()) {
                        wgLevel.scheduleTick(currentPos, wgLevel.getBlockState(currentPos).getBlock(), 1);
                    }

                }

            }
        }

        return true;

        //return false;
    }

    protected boolean canPlace(BlockPos origin, BlockPos pos, int radius) {
        return Math.pow(pos.getX() - origin.getX(), 2) + Math.pow(pos.getZ() - origin.getZ(), 2) <= Math.pow(radius + 0.5f, 2);
    }
}
