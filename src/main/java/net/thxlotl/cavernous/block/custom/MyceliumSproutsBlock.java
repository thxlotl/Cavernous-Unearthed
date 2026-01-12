package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.block.ModBlocks;

public class MyceliumSproutsBlock extends SurfaceCoverPlant implements BonemealableBlock {
    public MyceliumSproutsBlock(Properties properties, TagKey<Block> placeOnTag) {
        super(properties, placeOnTag);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return ModBlocks.MYCELIUM_FERN.get().defaultBlockState().canSurvive(levelReader, blockPos) && levelReader.isEmptyBlock(blockPos.above());
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource randomSource, BlockPos pos, BlockState blockState) {
        DoublePlantBlock.placeAt(level, ModBlocks.MYCELIUM_FERN.get().defaultBlockState(), pos, 2);
    }
}
