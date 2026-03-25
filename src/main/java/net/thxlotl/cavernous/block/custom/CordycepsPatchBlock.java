package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.effect.ModEffects;
import net.thxlotl.cavernous.entity.custom.Ant;

public class CordycepsPatchBlock extends VegetationBlock {

    private static final VoxelShape SHAPE = Block.column((double)11.0F, (double)0.0F, (double)10.0F);

    public CordycepsPatchBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmlandBlock || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean p_451772_) {

        if (!level.isClientSide() && entity instanceof LivingEntity livingEntity) {

            if (entity instanceof Ant) {

            }
            else {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.MUSHY_MIND_EFFECT, 100, 0));
            }
        }

        super.entityInside(state, level, pos, entity, effectApplier, p_451772_);
    }
}
