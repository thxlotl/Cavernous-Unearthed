package net.thxlotl.cavernous.worldgen.custom.wallface;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.block.custom.SegmentedWallBlock;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class WallFaceSpreader {
    public static final WallFaceSpreader.SpreadType[] DEFAULT_SPREAD_ORDER;
    private final WallFaceSpreader.SpreadConfig config;

    public WallFaceSpreader(SegmentedWallBlock block) {
        this((WallFaceSpreader.SpreadConfig)(new WallFaceSpreader.DefaultSpreaderConfig(block)));
    }

    public WallFaceSpreader(WallFaceSpreader.SpreadConfig config) {
        this.config = config;
    }

    public boolean canSpreadInAnyDirection(BlockState state, BlockGetter level, BlockPos pos, Direction spreadDirection) {
        return Direction.stream().anyMatch((p_221611_) -> {
            WallFaceSpreader.SpreadConfig var10006 = this.config;
            Objects.requireNonNull(var10006);
            return this.getSpreadFromFaceTowardDirection(state, level, pos, spreadDirection, p_221611_, var10006::canSpreadInto).isPresent();
        });
    }

    public Optional<WallFaceSpreader.SpreadPos> spreadFromRandomFaceTowardRandomDirection(BlockState state, LevelAccessor level, BlockPos pos, RandomSource random) {
        return (Optional)Direction.allShuffled(random).stream().filter((p_221680_) -> this.config.canSpreadFrom(state, p_221680_)).map((p_221629_) -> this.spreadFromFaceTowardRandomDirection(state, level, pos, p_221629_, random, false)).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
    }

    public long spreadAll(BlockState state, LevelAccessor level, BlockPos pos, boolean markForPostprocessing) {
        return (Long)Direction.stream().filter((p_221670_) -> this.config.canSpreadFrom(state, p_221670_)).map((p_221667_) -> this.spreadFromFaceTowardAllDirections(state, level, pos, p_221667_, markForPostprocessing)).reduce(0L, Long::sum);
    }

    public Optional<WallFaceSpreader.SpreadPos> spreadFromFaceTowardRandomDirection(BlockState state, LevelAccessor level, BlockPos pos, Direction spreadDirection, RandomSource random, boolean markForPostprocessing) {
        return (Optional)Direction.allShuffled(random).stream().map((p_221677_) -> this.spreadFromFaceTowardDirection(state, level, pos, spreadDirection, p_221677_, markForPostprocessing)).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
    }

    private long spreadFromFaceTowardAllDirections(BlockState state, LevelAccessor level, BlockPos pos, Direction spreadDirection, boolean markForPostprocessing) {
        return Direction.stream().map((p_221656_) -> this.spreadFromFaceTowardDirection(state, level, pos, spreadDirection, p_221656_, markForPostprocessing)).filter(Optional::isPresent).count();
    }

    @VisibleForTesting
    public Optional<WallFaceSpreader.SpreadPos> spreadFromFaceTowardDirection(BlockState state, LevelAccessor level, BlockPos pos, Direction spreadDirection, Direction face, boolean markForPostprocessing) {
        WallFaceSpreader.SpreadConfig var10006 = this.config;
        Objects.requireNonNull(var10006);
        return this.getSpreadFromFaceTowardDirection(state, level, pos, spreadDirection, face, var10006::canSpreadInto).flatMap((p_221600_) -> this.spreadToFace(level, p_221600_, markForPostprocessing));
    }

    public Optional<WallFaceSpreader.SpreadPos> getSpreadFromFaceTowardDirection(BlockState state, BlockGetter level, BlockPos pos, Direction spreadDirection, Direction face, WallFaceSpreader.SpreadPredicate predicate) {
        if (face.getAxis() == spreadDirection.getAxis()) {
            return Optional.empty();
        } else if (this.config.isOtherBlockValidAsSource(state) || this.config.hasFace(state, spreadDirection) && !this.config.hasFace(state, face)) {
            for(WallFaceSpreader.SpreadType multifacespreader$spreadtype : this.config.getSpreadTypes()) {
                WallFaceSpreader.SpreadPos multifacespreader$spreadpos = multifacespreader$spreadtype.getSpreadPos(pos, face, spreadDirection);
                if (predicate.test(level, pos, multifacespreader$spreadpos)) {
                    return Optional.of(multifacespreader$spreadpos);
                }
            }

            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    public Optional<WallFaceSpreader.SpreadPos> spreadToFace(LevelAccessor level, WallFaceSpreader.SpreadPos pos, boolean markForPostprocessing) {
        BlockState blockstate = level.getBlockState(pos.pos());
        return this.config.placeBlock(level, pos, blockstate, markForPostprocessing) ? Optional.of(pos) : Optional.empty();
    }

    static {
        DEFAULT_SPREAD_ORDER = new WallFaceSpreader.SpreadType[]{WallFaceSpreader.SpreadType.SAME_POSITION, WallFaceSpreader.SpreadType.SAME_PLANE, WallFaceSpreader.SpreadType.WRAP_AROUND};
    }

    public static class DefaultSpreaderConfig implements WallFaceSpreader.SpreadConfig {
        protected SegmentedWallBlock block;

        public DefaultSpreaderConfig(SegmentedWallBlock block) {
            this.block = block;
        }

        @Nullable
        public BlockState getStateForPlacement(BlockState p_221694_, BlockGetter p_221695_, BlockPos p_221696_, Direction p_221697_) {
            return this.block.getRandomStateAtDirection(p_221694_, p_221697_, p_221696_, p_221695_);
        }

        protected boolean stateCanBeReplaced(BlockGetter level, BlockPos pos, BlockPos spreadPos, Direction direction, BlockState state) {
            return state.isAir() || state.is(this.block) || state.is(Blocks.WATER) && state.getFluidState().isSource();
        }

        public boolean canSpreadInto(BlockGetter p_221685_, BlockPos p_221686_, WallFaceSpreader.SpreadPos p_221687_) {
            BlockState blockstate = p_221685_.getBlockState(p_221687_.pos());
            return this.stateCanBeReplaced(p_221685_, p_221686_, p_221687_.pos(), p_221687_.face(), blockstate) && this.block.isValidStateForPlacement(p_221685_, blockstate, p_221687_.pos(), p_221687_.face());
        }
    }

    public interface SpreadConfig {
        @Nullable
        BlockState getStateForPlacement(BlockState var1, BlockGetter var2, BlockPos var3, Direction var4);

        boolean canSpreadInto(BlockGetter var1, BlockPos var2, WallFaceSpreader.SpreadPos var3);

        default WallFaceSpreader.SpreadType[] getSpreadTypes() {
            return WallFaceSpreader.DEFAULT_SPREAD_ORDER;
        }

        default boolean hasFace(BlockState state, Direction direction) {
            return SegmentedWallBlock.hasFace(state, direction);
        }

        default boolean isOtherBlockValidAsSource(BlockState otherBlock) {
            return false;
        }

        default boolean canSpreadFrom(BlockState state, Direction direction) {
            return this.isOtherBlockValidAsSource(state) || this.hasFace(state, direction);
        }

        default boolean placeBlock(LevelAccessor level, WallFaceSpreader.SpreadPos pos, BlockState state, boolean markForPostprocessing) {
            BlockState blockstate = this.getStateForPlacement(state, level, pos.pos(), pos.face());
            if (blockstate != null) {
                if (markForPostprocessing) {
                    level.getChunk(pos.pos()).markPosForPostprocessing(pos.pos());
                }

                return level.setBlock(pos.pos(), blockstate, 2);
            } else {
                return false;
            }
        }
    }

    public static record SpreadPos(BlockPos pos, Direction face) {
    }

    public static enum SpreadType {
        SAME_POSITION {
            public WallFaceSpreader.SpreadPos getSpreadPos(BlockPos p_221751_, Direction p_221752_, Direction p_221753_) {
                return new WallFaceSpreader.SpreadPos(p_221751_, p_221752_);
            }
        },
        SAME_PLANE {
            public WallFaceSpreader.SpreadPos getSpreadPos(BlockPos p_221758_, Direction p_221759_, Direction p_221760_) {
                return new WallFaceSpreader.SpreadPos(p_221758_.relative(p_221759_), p_221760_);
            }
        },
        WRAP_AROUND {
            public WallFaceSpreader.SpreadPos getSpreadPos(BlockPos p_221765_, Direction p_221766_, Direction p_221767_) {
                return new WallFaceSpreader.SpreadPos(p_221765_.relative(p_221766_).relative(p_221767_), p_221766_.getOpposite());
            }
        };

        public abstract WallFaceSpreader.SpreadPos getSpreadPos(BlockPos var1, Direction var2, Direction var3);
    }

    @FunctionalInterface
    public interface SpreadPredicate {
        boolean test(BlockGetter var1, BlockPos var2, WallFaceSpreader.SpreadPos var3);
    }
}
