package net.thxlotl.cavernous.util;

import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.block.ModBlockStateProperties;

import java.util.function.ToIntFunction;

public class GhostFungus {

    public static final ToIntFunction<BlockState> GHOST_FUNGUS_LIGHT =
            (state) -> Mth.clamp(state.getValue(ModBlockStateProperties.TOTAL_COUNT) + 1, 1, 7);
}
