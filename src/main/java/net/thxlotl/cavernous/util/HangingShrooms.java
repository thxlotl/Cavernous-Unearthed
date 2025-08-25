package net.thxlotl.cavernous.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.thxlotl.cavernous.block.ModBlocks;

public class HangingShrooms {
    private static final double BONEMEAL_GROW_PROBABILITY_DECREASE_RATE = 0.5;
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
