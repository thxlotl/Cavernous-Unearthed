package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;

public class LampshroomStemBlock extends GrowingPlantBodyBlock {


    private static final VoxelShape BASE_SHAPE = Shapes.or(
            Block.column((double)5.0F, (double)0.0F, (double)16.0F));

    public LampshroomStemBlock(Properties properties) {
        super(properties, Direction.UP, BASE_SHAPE, false);
    }


    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmlandBlock || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BASE_SHAPE;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.LAMPSHROOM.get();
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
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return null;
    }
}
