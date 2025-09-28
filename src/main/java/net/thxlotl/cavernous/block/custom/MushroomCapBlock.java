package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.thxlotl.cavernous.datagen.custom.ModBlockStateProperties;

public class MushroomCapBlock extends Block {
    public MushroomCapBlock(Properties p_49795_) {
        super(p_49795_);
    }

    private static final BooleanProperty GILLED;
    private static final BooleanProperty NORTH;
    private static final BooleanProperty NORTHEAST;
    private static final BooleanProperty EAST;
    private static final BooleanProperty SOUTHEAST;
    private static final BooleanProperty SOUTH;
    private static final BooleanProperty SOUTHWEST;
    private static final BooleanProperty WEST;
    private static final BooleanProperty NORTHWEST;

    static {
        GILLED = ModBlockStateProperties.GILLED;

        NORTH  = BooleanProperty.create("north");
        NORTHEAST  = BooleanProperty.create("northeast");
        EAST  = BooleanProperty.create("east");
        SOUTHEAST  = BooleanProperty.create("southeast");
        SOUTH  = BooleanProperty.create("south");
        SOUTHWEST  = BooleanProperty.create("southwest");
        WEST  = BooleanProperty.create("west");
        NORTHWEST  = BooleanProperty.create("northwest");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter blockgetter = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return this.defaultBlockState()
                .setValue(GILLED, !blockgetter.getBlockState(blockpos.below()).is(this))
                .setValue(NORTH, blockgetter.getBlockState(blockpos.north()).is(this))
                .setValue(NORTHEAST, blockgetter.getBlockState(blockpos.north().east()).is(this))
                .setValue(EAST, blockgetter.getBlockState(blockpos.east()).is(this))
                .setValue(SOUTHEAST, blockgetter.getBlockState(blockpos.south().east()).is(this))
                .setValue(SOUTH, blockgetter.getBlockState(blockpos.south()).is(this))
                .setValue(SOUTHWEST, blockgetter.getBlockState(blockpos.south().west()).is(this))
                .setValue(WEST, blockgetter.getBlockState(blockpos.west()).is(this))
                .setValue(NORTHWEST, blockgetter.getBlockState(blockpos.north().west()).is(this));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return this.defaultBlockState()
                .setValue(GILLED, !level.getBlockState(pos.below()).is(this))
                .setValue(NORTH, level.getBlockState(pos.north()).is(this))
                .setValue(NORTHEAST, level.getBlockState(pos.north().east()).is(this))
                .setValue(EAST, level.getBlockState(pos.east()).is(this))
                .setValue(SOUTHEAST, level.getBlockState(pos.south().east()).is(this))
                .setValue(SOUTH, level.getBlockState(pos.south()).is(this))
                .setValue(SOUTHWEST, level.getBlockState(pos.south().west()).is(this))
                .setValue(WEST, level.getBlockState(pos.west()).is(this))
                .setValue(NORTHWEST, level.getBlockState(pos.north().west()).is(this));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{GILLED, NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST});
    }
}
