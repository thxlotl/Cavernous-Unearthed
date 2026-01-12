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
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.HangingShrooms;
import net.thxlotl.cavernous.util.Lampshrooms;
import net.thxlotl.cavernous.worldgen.custom.tree.ModTreeGrowers;

public class LampshroomBlock extends GrowingPlantHeadBlock {


    private static final VoxelShape BASE_SHAPE = Shapes.or(
            Block.box((double)4.0F, (double)4.5F, (double)4.0F, (double)12.0F, (double)12.5F, (double)12.0F),
            Block.column((double)5.0F, (double)0.0F, (double)7.0F));

    public LampshroomBlock(Properties properties) {
        super(properties, Direction.UP, BASE_SHAPE, true, 0);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BASE_SHAPE;
    }

    @Override
    protected Block getBodyBlock() {
        return ModBlocks.LAMPSHROOM_STEM.get();
    }

    // Properties
    @Override
    protected boolean isPathfindable(BlockState p_401351_, PathComputationType p_401371_) {
        return false;
    }
    @Override
    protected boolean propagatesSkylightDown(BlockState p_401261_) {
        return true;
    }
    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }
    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    @Override
    protected MapCodec<? extends GrowingPlantHeadBlock> codec() {
        return null;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if (onGround(level, blockPos))
        {
            return (double)level.random.nextFloat() < 0.35;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
        return Lampshrooms.getBlocksToGrowWhenBonemealed(randomSource);
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {

        boolean onGround = onGround(serverLevel, blockPos);

        if (onGround) {
            ModTreeGrowers.LAMPSHROOM.growTree(serverLevel, serverLevel.getChunkSource().getGenerator(), blockPos, blockState, randomSource);
        }
        else {
            bonemealGrowingPlant(serverLevel, randomSource, blockPos, blockState);
        }
    }

    private boolean onGround(Level level, BlockPos pos)
    {
        BlockState groundState = level.getBlockState(pos.below());
        return groundState.is(BlockTags.DIRT) || groundState.getBlock() instanceof FarmBlock || groundState.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    private void bonemealGrowingPlant(ServerLevel serverLevel, RandomSource randomSource, BlockPos pos, BlockState state)
    {
        BlockPos blockpos = pos.relative(this.growthDirection);
        int i = Math.min((Integer)state.getValue(AGE) + 1, 25);
        int j = this.getBlocksToGrowWhenBonemealed(randomSource);

        for(int k = 0; k < j && this.canGrowInto(serverLevel.getBlockState(blockpos)); ++k) {
            serverLevel.setBlockAndUpdate(blockpos, (BlockState)state.setValue(AGE, i));
            blockpos = blockpos.relative(this.growthDirection);
            i = Math.min(i + 1, 25);
        }
    }

    @Override
    protected boolean canGrowInto(BlockState blockState) {
        return blockState.isAir();
    }
}
