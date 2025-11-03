package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.thxlotl.cavernous.worldgen.custom.tree.ModTreeGrowers;

import java.util.Map;

public class SpringshroomBlock extends VegetationBlock {


    private static final VoxelShape SHAPE = Shapes.or(
            Block.column((double)16.0F, (double)11.0F, (double)16.0F),
            Block.column((double)6.0F, (double)0.0F, (double)16.0F));


    public SpringshroomBlock(Properties p_401368_) {
        super(p_401368_);
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmBlock || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }


    // Properties
    @Override
    protected boolean isPathfindable(BlockState p_401351_, PathComputationType p_401371_) {
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
        if (entity.isSuppressingBounce() || entity.getDeltaMovement().y > -0.3f) {
            super.updateEntityMovementAfterFallOn(blockGetter, entity);
        } else if (entity instanceof LivingEntity livingEntity){
            this.bounceUp(livingEntity);
        }

    }
    private void bounceUp(LivingEntity entity) {



        entity.level().playLocalSound(entity.blockPosition(), SoundEvents.HONEY_BLOCK_FALL, SoundSource.BLOCKS, 1, 1, false);
        Vec3 hitVelocity = entity.getDeltaMovement();

        float g = (float)entity.getAttributeValue(Attributes.GRAVITY); // per tick
        float r = 0.98f; // * per tick
        float hitSpeed = Mth.abs((float)hitVelocity.y);



        if (hitVelocity.y < (double)0.0F) {
            entity.setDeltaMovement(hitVelocity.x, -hitVelocity.y * 1.225, hitVelocity.z);
        }
    }
}
