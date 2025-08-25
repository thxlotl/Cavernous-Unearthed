package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class ShroomwoodBlock extends ShroomwoodLogBlock{
    public ShroomwoodBlock(Properties p_55926_) {
        super(p_55926_);

    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }
}
