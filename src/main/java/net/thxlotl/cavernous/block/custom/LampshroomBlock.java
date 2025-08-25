package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.util.ModTags;
import net.thxlotl.cavernous.worldgen.tree.ModTreeGrowers;

import java.util.Map;

public class LampshroomBlock extends VegetationBlock {


    private static final VoxelShape BASE_SHAPE = Shapes.or(
            Block.box((double)4.0F, (double)4.5F, (double)4.0F, (double)12.0F, (double)15.5F, (double)12.0F),
            Block.column((double)5.0F, (double)0.0F, (double)7.0F));


    public LampshroomBlock(Properties p_401368_) {
        super(p_401368_);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmBlock || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BASE_SHAPE;
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

}
