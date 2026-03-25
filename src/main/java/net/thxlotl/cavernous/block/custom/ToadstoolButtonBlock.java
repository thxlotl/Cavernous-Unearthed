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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.worldgen.custom.tree.ModTreeGrowers;

import java.util.Map;

public class ToadstoolButtonBlock extends VegetationBlock implements BonemealableBlock {

    public static final EnumProperty<Direction> FACING;
    private static final Map<Direction, VoxelShape> SHAPES;

    private static final VoxelShape BASE_SHAPE = Shapes.or(
            Block.box((double)1.0F, (double)10.0F, (double)1.0F, (double)15.0F, (double)16.0F, (double)15.0F),
            Block.box((double)7.0F, (double)0.0F, (double)7.0F, (double)9.0F, (double)16.0F, (double)9.0F),
            Block.box((double)7.0F, (double)2.0F, (double)9.0F, (double)9.0F, (double)8.0F, (double)14.0F));
    private static final VoxelShape FAT_SHAPE = Shapes.or(
            Block.column((double)14.0F, (double)10.0F, (double)16.0F),
            Block.column((double)3.0F, (double)0.0F, (double)10.0F),
            Block.box((double)6.5F, (double)2.0F, (double)9.5F, (double)9.5F, (double)8.0F, (double)13.5F));


    private static final VoxelShape SHAPE_NO_ROTATE = Shapes.or(
            Block.column((double)12.0F, (double)10.0F, (double)16.0F),
            Block.column((double)8.0F, (double)0.0F, (double)10.0F));

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SHAPES = Shapes.rotateHorizontal(FAT_SHAPE.optimize());
    }

    public ToadstoolButtonBlock(Properties p_401368_) {
        super(p_401368_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmlandBlock || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        ///return SHAPES.get(state.getValue(FACING));
        return SHAPE_NO_ROTATE;
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    // Bonemeal
    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getFluidState(blockPos.above()).isEmpty();
    }
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return (double)level.getRandom().nextFloat() < 0.45;
    }
    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        ModTreeGrowers.TOADSTOOL.growTree(serverLevel, serverLevel.getChunkSource().getGenerator(), blockPos, blockState, randomSource);
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


    // Bouncy

    @Override
    public void fallOn(Level level, BlockState state, BlockPos blockPos, Entity entity, double fallDistance) {
        if (!entity.isSuppressingBounce()) {
            entity.causeFallDamage(fallDistance, 0.0F, entity.damageSources().fall());
        }

    }
    @Override
    public void updateEntityMovementAfterFallOn(BlockGetter blockGetter, Entity entity) {
        if (entity.isSuppressingBounce() || entity.getDeltaMovement().y > -0.6f) {
            super.updateEntityMovementAfterFallOn(blockGetter, entity);
        } else {
            this.bounceUp(entity);
        }

    }
    private void bounceUp(Entity entity) {

        entity.level().playLocalSound(entity.blockPosition(), SoundEvents.HONEY_BLOCK_FALL, SoundSource.BLOCKS, 1, 1, false);
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < (double)0.0F) {
            double d0 = entity instanceof LivingEntity ? (double)0.6F : 0.5;
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }

    }
}
