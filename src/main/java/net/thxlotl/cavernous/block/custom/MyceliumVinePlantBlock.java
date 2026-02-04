package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;

public class MyceliumVinePlantBlock extends GrowingPlantBodyBlock {

    public MyceliumVinePlantBlock(Properties p_53886_) {
        super(p_53886_, Direction.DOWN, SHAPE, false);
    }

    private static final VoxelShape SHAPE = Block.column((double)14.0F, (double)0.0F, (double)16.0F);

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return null;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.MYCELIUM_VINE.get();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
