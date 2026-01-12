package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SurfaceCoverPlant extends VegetationBlock {

    private TagKey<Block> placeOnTag;

    public SurfaceCoverPlant(Properties properties, TagKey<Block> placeOnTag) {
        super(properties);
        this.placeOnTag = placeOnTag;
    }

    private static final VoxelShape SHAPE = Block.column((double)12.0F, (double)0.0F, (double)4.0F);

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return super.mayPlaceOn(state, level, pos) || state.is(placeOnTag);
    }
}
