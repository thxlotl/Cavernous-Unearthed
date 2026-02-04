package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.MyceliumVine;

public class MyceliumVineBlock extends GrowingPlantHeadBlock {
    public MyceliumVineBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, MyceliumVine.GROW_PER_TICK_PROBABILITY);
    }

    private static final VoxelShape SHAPE = Block.column((double)12.0F, (double)4.0F, (double)16.0F);

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return null;
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.MYCELIUM_VINE_PLANT.get();
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return MyceliumVine.getBlocksToGrowWhenBonemealed(randomSource);
    }

    @Override
    protected boolean canGrowInto(BlockState blockState) {
        return MyceliumVine.isValidGrowthState(blockState);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
