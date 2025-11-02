package net.thxlotl.cavernous.worldgen.custom.segmentedwallblock;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.thxlotl.cavernous.block.custom.SegmentedWallBlock;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;

public class SegmentedWallBlockFeature extends Feature<SegmentedWallBlockConfiguration> {

    public SegmentedWallBlockFeature(Codec<SegmentedWallBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SegmentedWallBlockConfiguration> context) {

        Block toPlace = context.config().block().getState(context.random(), context.origin()).getBlock();

        if (toPlace instanceof SegmentedWallBlock block) {

            SegmentedWallBlockConfiguration config = context.config();
            WorldGenLevel level = context.level();
            BlockPos origin = context.origin();
            int radius = context.config().radius().sample(context.random());

            for (int x = -radius ; x <= radius ; x++) {
                for (int y = -radius ; y <= radius ; y++) {
                    for (int z = -radius ; z <= radius ; z++) {

                        BlockPos currentPos = origin.offset(x, y, z);

                        if (canPlace(origin, currentPos, radius, level)) {

                            BlockState placeState = block.defaultBlockState();
                            boolean willPlace = false;

                            for (Direction direction : SegmentedWallBlock.VALID_DIRECTIONS) {
                                if (canSetFace(level, currentPos, direction)) {
                                    placeState = placeState.setValue(SegmentedWallBlock.getFaceProperty(direction), context.random().nextInt(0, 5));
                                    willPlace = true;
                                }
                            }
                            if (!block.hasAnyFace(placeState)) willPlace = false;

                            if (willPlace) level.setBlock(currentPos, placeState, 2);

                        }
                    }
                }
            }

        }

        return true;
    }
    private boolean canPlace(BlockPos origin, BlockPos pos, int radius, BlockGetter level) {
        boolean inRange = Math.pow(pos.getX() - origin.getX(), 2) + Math.pow(pos.getZ() - origin.getZ(), 2) + Math.pow(pos.getY() - origin.getY(), 2) <= Math.pow(radius, 2);
        boolean isAir = level.getBlockState(pos).isAir();
        return isAir && inRange;
    }
    private boolean canSetFace(BlockGetter level, BlockPos pos, Direction direction) {

        BlockPos wallPos = pos.relative(direction);
        BlockState wallState = level.getBlockState(wallPos);

        return wallState.isFaceSturdy(level, wallPos, direction.getOpposite());
    }
}
