package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlockStateProperties;
import net.thxlotl.cavernous.worldgen.custom.wallface.WallFaceSpreader;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class SegmentedWallBlock extends Block {

    private final WallFaceSpreader spreader = new WallFaceSpreader(this);
    public WallFaceSpreader getSpreader() {
        return this.spreader;
    }

    protected static Direction[] VALID_DIRECTIONS;

    public static final IntegerProperty COUNT_NORTH;
    public static final IntegerProperty COUNT_EAST;
    public static final IntegerProperty COUNT_SOUTH;
    public static final IntegerProperty COUNT_WEST;

    public static final IntegerProperty TOTAL_COUNT;

    private static final EnumMap<Direction, IntegerProperty> COUNT_PER_DIRECTION = new EnumMap<>(Direction.class);
    private final Function<BlockState, VoxelShape> shapes;

    static {
        COUNT_NORTH = ModBlockStateProperties.COUNT_NORTH;
        COUNT_EAST = ModBlockStateProperties.COUNT_EAST;
        COUNT_SOUTH = ModBlockStateProperties.COUNT_SOUTH;
        COUNT_WEST = ModBlockStateProperties.COUNT_WEST;
        TOTAL_COUNT = ModBlockStateProperties.TOTAL_COUNT;

        COUNT_PER_DIRECTION.put(Direction.NORTH, COUNT_NORTH);
        COUNT_PER_DIRECTION.put(Direction.EAST, COUNT_EAST);
        COUNT_PER_DIRECTION.put(Direction.SOUTH, COUNT_SOUTH);
        COUNT_PER_DIRECTION.put(Direction.WEST, COUNT_WEST);

        VALID_DIRECTIONS = Direction.Plane.HORIZONTAL.stream().toArray(Direction[]::new);
    }

    public SegmentedWallBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(getDefaultBlockState(this.stateDefinition));
        this.shapes = this.makeShapes();
    }

    private BlockState getDefaultBlockState(StateDefinition<Block, BlockState> stateDefinition)
    {
        BlockState state = (BlockState) stateDefinition.any().setValue(TOTAL_COUNT, 0);
        for (IntegerProperty intProp : COUNT_PER_DIRECTION.values())
        {
            state.trySetValue(intProp, 0);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        for (Direction dir : VALID_DIRECTIONS) {
            builder.add(getFaceProperty(dir));
        }
        builder.add(TOTAL_COUNT);
    }


    protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return !context.isSecondaryUseActive() && context.getItemInHand().is(this.asItem()) && hasAnyFillableFace(state, context.getLevel(), context.getClickedPos());
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        return (BlockState) Arrays.stream(context.getNearestLookingDirections()).map((dir) -> getStateAtDirection(blockstate, dir, blockpos, level)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public BlockState getStateAtDirection(BlockState currentState, Direction direction, BlockPos pos, BlockGetter level) {
        Block block = this;
        IntegerProperty countProperty = getFaceProperty(direction);

        if (!this.isValidStateForPlacement(level, currentState, pos, direction)) return null; // Remove this possibility if it cannot place
        if (!isValidDirection(direction)) return null; // Remove this possibility if it is not included in directions

        if (currentState.is(block))
        {
            int faceCount = getFaceCount(currentState, direction);
            if (currentState.is(block) && faceCount >= 4) {
                return null;
            }
            else return currentState.setValue(countProperty, faceCount + 1).setValue(TOTAL_COUNT, getTotalCount(currentState) + 1);
        }
        else {
            return this.defaultBlockState().setValue(countProperty, 1).setValue(TOTAL_COUNT, 1);
        }
    }

    public boolean isValidDirection(Direction direction) {
        return Arrays.asList(VALID_DIRECTIONS).contains(direction);
    }

    public BlockState getRandomStateAtDirection(BlockState currentState, Direction direction, BlockPos pos, BlockGetter level) {
        Block block = this;
        IntegerProperty countProperty = getFaceProperty(direction);

        int randomCount = RandomSource.create().nextInt(1, 5);

        if (!this.isValidStateForPlacement(level, currentState, pos, direction)) return null;
        if (!isValidDirection(direction)) return null;

        if (currentState.is(block))
        {
            return currentState.setValue(countProperty, randomCount).setValue(TOTAL_COUNT, getTotalCount(currentState) + randomCount);
        }
        else {
            return this.defaultBlockState().setValue(countProperty, randomCount).setValue(TOTAL_COUNT, randomCount);
        }
    }

    public boolean isValidStateForPlacement(BlockGetter level, BlockState state, BlockPos pos, Direction direction) {
        if (!this.isFaceSupported(direction) || state.is(this) && hasFullFace(state, direction)) {
            return false;
        } else {
            BlockPos blockpos = pos.relative(direction);
            return canAttachTo(level, direction, blockpos, level.getBlockState(blockpos));
        }
    }
    protected boolean isFaceSupported(Direction face) {
        return true;
    }

//    private boolean hasAnyVacantFace(BlockState state) {
//        for(Direction direction : VALID_DIRECTIONS) {
//            if (!hasFace(state, direction)) {
//                return true;
//            }
//        }
//        return false;
//    }

    protected boolean hasAnyFillableFace(BlockState state, Level level, BlockPos pos) {
        for(Direction dir : VALID_DIRECTIONS) {
            if (!hasFullFace(state, dir) && canAttachTo(level, pos, dir)) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasFace(BlockState state, Direction direction) {
        IntegerProperty countProperty = getFaceProperty(direction);
        return 0 < state.getValueOrElse(countProperty, 0);
    }
    public static boolean hasFullFace(BlockState state, Direction direction) {
        IntegerProperty countProperty = getFaceProperty(direction);
        return 4 <= state.getValueOrElse(countProperty, 0);
    }
    public static IntegerProperty getFaceProperty(Direction direction) {
        return (IntegerProperty) COUNT_PER_DIRECTION.get(direction);
    }
    public static Integer getFaceCount(BlockState state, Direction direction) {
        return state.getValue(getFaceProperty(direction));
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        boolean flag = false;

        for(Direction direction : VALID_DIRECTIONS) {
            if (hasFace(state, direction)) {
                if (!canAttachTo(level, pos, direction)) {
                    return false;
                }

                flag = true;
            }
        }

        return flag;
    }
    public static boolean canAttachTo(BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        BlockState blockstate = level.getBlockState(blockpos);
        return canAttachTo(level, direction, blockpos, blockstate);
    }
    public static boolean canAttachTo(BlockGetter level, Direction direction, BlockPos pos, BlockState state) {
        return Block.isFaceFull(state.getBlockSupportShape(level, pos), direction.getOpposite()) || Block.isFaceFull(state.getCollisionShape(level, pos), direction.getOpposite());
    }

    protected boolean hasAnyFace(BlockState state) {
        for(Direction direction : VALID_DIRECTIONS) {
            if (hasFace(state, direction)) {
                return true;
            }
        }
        return false;
    }
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos,
                                     Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {

        if (!hasAnyFace(state)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return hasFace(state, direction) && !canAttachTo(level, direction, neighborPos, neighborState) ? removeFace(state, getFaceProperty(direction)) : state;
        }
    }
    private BlockState removeFace(BlockState state, IntegerProperty faceProp) {
        BlockState blockstate = (BlockState)state.setValue(faceProp, 0);
        return hasAnyFace(blockstate) ? blockstate : Blocks.AIR.defaultBlockState();
    }


    private Function<BlockState, VoxelShape> makeShapes() {
        Map<Direction, VoxelShape> map = Shapes.rotateAll(Block.boxZ((double)16.0F, (double)0.0F, (double)1.0F));

        return this.getShapeForEachState((state) -> {
            VoxelShape voxelshape = Shapes.empty();

            for(Direction direction : VALID_DIRECTIONS) {
                if (hasFace(state, direction)) {
                    voxelshape = Shapes.or(voxelshape, (VoxelShape)map.get(direction));
                }
            }

            return voxelshape.isEmpty() ? Shapes.block() : voxelshape;
        });
    }
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return (VoxelShape)this.shapes.apply(state);
        //return Block.box(0, 0, 0, 16, 16, 16);
    }

    private Integer getTotalCount(BlockState state)
    {
        Integer count = 0;
        for (Direction direction : VALID_DIRECTIONS)
        {
            count += getFaceCount(state, direction);
        }
        return count;
    }
}
