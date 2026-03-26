package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.datagen.tag.ModTags;

public class MyceliumFernBlock extends DoublePlantBlock {
    public MyceliumFernBlock(Properties p_401368_) {
        super(p_401368_);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return super.mayPlaceOn(state, level, pos) || state.is(ModTags.Blocks.MYCELIUM_SPROUTS_PLACEABLE);
    }

}
