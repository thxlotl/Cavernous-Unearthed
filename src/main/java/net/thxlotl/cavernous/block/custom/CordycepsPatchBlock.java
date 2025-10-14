package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.event.EventHooks;
import net.thxlotl.cavernous.effect.ModEffects;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;

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
        return state.is(BlockTags.DIRT) || state.getBlock() instanceof FarmBlock || state.is(BlockTags.MUSHROOM_GROW_BLOCK);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier) {

        if (!level.isClientSide() && entity instanceof LivingEntity livingEntity) {

            if (entity instanceof Ant) {

            }
            else {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.MUSHY_MIND_EFFECT, 100, 0));
            }
        }

        super.entityInside(state, level, pos, entity, effectApplier);
    }
}
