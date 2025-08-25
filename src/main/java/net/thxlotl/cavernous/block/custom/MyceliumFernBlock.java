package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MyceliumFernBlock extends VegetationBlock {
    public MyceliumFernBlock(Properties p_401368_) {
        super(p_401368_);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    private static final VoxelShape SHAPE = Block.column((double)10.0F, (double)0.0F, (double)13.0F);
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
