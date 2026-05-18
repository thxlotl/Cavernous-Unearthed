package net.thxlotl.cavernous.rendering;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.datagen.tag.ModTags;

public class ModBlockTintSources {

    public static BlockTintSource obsidianstoneTint() {
        return new BlockTintSource() {

            int boost = ObsidianstoneUtil.boost;
            int maxRange = ObsidianstoneUtil.maxRange;
            int heatedColor = ObsidianstoneUtil.heatedColor;
            int unheatedColor = ObsidianstoneUtil.unheatedColor;

            @Override
            public int color(BlockState state) {
                return 6381412;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {

                float ratio = ObsidianstoneUtil.getHeatRatio(level, pos);

                return RenderUtil.blendGammaCorrected(heatedColor, unheatedColor, (float)Math.pow(ratio, 0.5));

            }
        };
    }

    public static BlockTintSource eruptiteTint() {
        return new BlockTintSource() {

            int boost = ObsidianstoneUtil.boost;
            int maxRange = ObsidianstoneUtil.maxRange;
            int heatedColor = ARGB.color(255, 255, 114, 0);

            int unheatedColor = ARGB.white(0);

            @Override
            public int color(BlockState state) {
                return ARGB.white(0);
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {

                heatedColor = ARGB.white(0);

                float ratio = ObsidianstoneUtil.getHeatRatio(level, pos);

                return RenderUtil.blendGammaCorrected(heatedColor, unheatedColor, (float)Math.pow(ratio, 0.5));

            }
        };
    }




}
