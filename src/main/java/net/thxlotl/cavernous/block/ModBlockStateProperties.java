package net.thxlotl.cavernous.block;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {

    public static final IntegerProperty COUNT_NORTH;
    public static final IntegerProperty COUNT_EAST;
    public static final IntegerProperty COUNT_SOUTH;
    public static final IntegerProperty COUNT_WEST;
    public static final IntegerProperty COUNT_UP;
    public static final IntegerProperty COUNT_DOWN;
    public static final IntegerProperty TOTAL_COUNT;
    public static final BooleanProperty NATURAL;

    static {
        COUNT_NORTH = IntegerProperty.create("count_north", 0, 4);
        COUNT_EAST = IntegerProperty.create("count_east", 0, 4);
        COUNT_SOUTH = IntegerProperty.create("count_south", 0, 4);
        COUNT_WEST = IntegerProperty.create("count_west", 0, 4);
        COUNT_UP = IntegerProperty.create("count_up", 0, 4);
        COUNT_DOWN = IntegerProperty.create("count_down", 0, 4);
        TOTAL_COUNT = IntegerProperty.create("total_count", 0, 24);
        NATURAL = BooleanProperty.create("natural");
    }
}
