package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.HangingShrooms;

public class HangingShroomStemBlock extends GrowingPlantBodyBlock {
    public static final VoxelShape SHAPE = Block.column((double)4.0F, (double)0.0F, (double)16.0F);

    public HangingShroomStemBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return null;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return ModBlocks.HANGING_SHROOM_CAP.get();
    }


    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.is(ModBlocks.HANGING_SHROOM_CAP.asItem()))
        {
            boolean willGrow = false;
            int offset = 1;
            BlockState checkedState = state;
            while (true)
            {
                BlockPos targetPos = pos.below(offset);
                checkedState = level.getBlockState(targetPos);
                if (checkedState.isAir() && level.getEntitiesOfClass(LivingEntity.class, new AABB(targetPos)).isEmpty())
                {
                    willGrow = true;
                    break;
                }
                else if (!(checkedState.is(ModBlocks.HANGING_SHROOM_CAP) || checkedState.is(ModBlocks.HANGING_SHROOM_STEM)))
                {
                    willGrow = false;
                    break;
                }
                offset++;
            }

            if (willGrow)
            {
                BlockPos growPos = pos.below(offset);
                stack.consume(1, player);
                level.setBlockAndUpdate(growPos, ModBlocks.HANGING_SHROOM_CAP.get().defaultBlockState().setValue(BlockStateProperties.AGE_25, player.getRandom().nextInt(0, 25)));
                level.playLocalSound(growPos, this.soundType.getPlaceSound(), SoundSource.BLOCKS, 1, 1, false);
                return InteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
