package net.thxlotl.cavernous.util;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class MyceliumVine {
    private static final double BONEMEAL_GROW_PROBABILITY_DECREASE_RATE = 0.67;
    public static final double GROW_PER_TICK_PROBABILITY = 0.1;

    public static boolean isValidGrowthState(BlockState state) {
        return state.isAir();
    }

    public static int getBlocksToGrowWhenBonemealed(RandomSource random) {
        double d0 = (double)1.0F;

        int i;
        for(i = 0; random.nextDouble() < d0; ++i) {
            d0 *= BONEMEAL_GROW_PROBABILITY_DECREASE_RATE;
        }

        return i;
    }

}
