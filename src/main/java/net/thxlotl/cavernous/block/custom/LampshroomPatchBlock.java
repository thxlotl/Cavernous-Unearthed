package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.ModTags;

public class LampshroomPatchBlock extends VegetationBlock implements BonemealableBlock {
    public LampshroomPatchBlock(Properties p_401368_) {
        super(p_401368_);
    }

    private static final VoxelShape SHAPE = Block.column((double)12.0F, (double)0.0F, (double)10.0F);
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.SHROOMWOOD_LOGS) || super.mayPlaceOn(state, level, pos) || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return (double)level.random.nextFloat() < 0.75;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {

        BlockState button = ModBlocks.LAMPSHROOM.get().defaultBlockState();
        serverLevel.setBlock(blockPos, button, 3);
    }
}
