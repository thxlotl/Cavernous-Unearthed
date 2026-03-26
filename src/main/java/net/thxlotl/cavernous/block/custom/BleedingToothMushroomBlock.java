package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.datagen.tag.ModTags;

public class BleedingToothMushroomBlock extends VegetationBlock{
    public BleedingToothMushroomBlock(Properties p_401368_) {
        super(p_401368_);
    }

    private static final VoxelShape SHAPE = Shapes.or(
            Block.column((double)12.0F, (double)3.0F, (double)15.0F),
            Block.column((double)6.0F, (double)0.0F, (double)3.0F));;

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.SHROOMWOOD_LOGS) || super.mayPlaceOn(state, level, pos) || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

}
