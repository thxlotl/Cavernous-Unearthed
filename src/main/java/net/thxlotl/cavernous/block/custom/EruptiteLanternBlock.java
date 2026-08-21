package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EruptiteLanternBlock extends LanternBlock {

    private static final VoxelShape SHAPE_STANDING = Shapes.or(
            Block.column((double)3.0F, (double)8.0F, (double)9.0F), // Cap
            Block.column((double)6.0F, (double)0.0F, (double)1.0F), // Base
            Block.column((double)5.0F, (double)1.0F, (double)8.0F) // Bulb
    );
    private static final VoxelShape SHAPE_HANGING = SHAPE_STANDING.move((double)0.0F, (double)0.0625F, (double)0.0F).optimize();

    public EruptiteLanternBlock(Properties properties) {
        super(properties);
    }
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (Boolean)state.getValue(HANGING) ? SHAPE_HANGING : SHAPE_STANDING;
    }
}
