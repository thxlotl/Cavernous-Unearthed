package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.util.ModTags;
import org.joml.Vector3f;

public class ObsidianstoneBlock extends Block {

    private static final int boost = 1;

    private static final int maxRange = 6;
    public static final int heatedColor = ARGB.color(255, 255, 110, 40);
    public static final int unheatedColor = ARGB.color(255, 97 * boost, 95 * boost, 100 * boost);

    public ObsidianstoneBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public static int getColor(BlockState state, BlockAndTintGetter getter, BlockPos pos, int i)
    {
        if (i != 0) return -1;

        int nearestDistance = maxRange;

        for (int x = -maxRange; x <= maxRange; x++) {
            for (int y = -maxRange; y <= maxRange; y++) {
                for (int z = -maxRange; z <= maxRange; z++) {

                    BlockPos current = new BlockPos(x, y, z);

                    if (getter.getBlockState(pos.offset(current)).is(ModTags.Blocks.HOT_BLOCKS)) {

                        int currentDistance = new Vec3i(x, y, z).distManhattan(Vec3i.ZERO);

                        if (currentDistance < nearestDistance) nearestDistance = currentDistance;

                    }

                }
            }
        }

        float ratio = (float)nearestDistance / ((float)maxRange);

        ///return 0xFF00FF;
        return ARGB.linearLerp((float)Math.pow(ratio, 0.5), heatedColor, unheatedColor);

    }

}
