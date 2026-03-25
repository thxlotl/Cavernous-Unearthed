package net.thxlotl.cavernous.rendering;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.util.ModTags;

public class ModBlockTintSources {

    public static BlockTintSource obsidianstoneTint() {
        return new BlockTintSource() {

            int boost = ObsidianstoneTintProperties.boost;
            int maxRange = ObsidianstoneTintProperties.maxRange;
            int heatedColor = ObsidianstoneTintProperties.heatedColor;
            int unheatedColor = ObsidianstoneTintProperties.unheatedColor;

            @Override
            public int color(BlockState state) {
                return 6381412;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {

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

                float ratio = (float)nearestDistance / ((float)maxRange);

                return RenderUtil.blendGammaCorrected(heatedColor, unheatedColor, (float)Math.pow(ratio, 0.5));

            }
        };
    }




}
