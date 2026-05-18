package net.thxlotl.cavernous.rendering;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.Level;
import net.thxlotl.cavernous.datagen.tag.ModTags;

public class ObsidianstoneUtil {

    public static final int boost = 1;
    public static final int maxRange = 6;
    public static final int heatedColor = ARGB.color(255, 255, 110, 40);
    public static final int unheatedColor = ARGB.color(255, 97 * boost, 95 * boost, 100 * boost);

    public static float getHeatRatio(BlockAndTintGetter level, BlockPos pos) {

        int nearestDistance = maxRange;

        for (int x = -maxRange; x <= maxRange; x++) {
            for (int y = -maxRange; y <= maxRange; y++) {
                for (int z = -maxRange; z <= maxRange; z++) {

                    BlockPos current = new BlockPos(x, y, z);

                    if (level.getBlockState(pos.offset(current)).is(ModTags.Blocks.HOT_BLOCKS)) {

                        int currentDistance = new Vec3i(x, y, z).distManhattan(Vec3i.ZERO);

                        if (currentDistance < nearestDistance) nearestDistance = currentDistance;

                    }

                }
            }
        }

        return (float)nearestDistance / ((float)maxRange);
    }

}
