package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.thxlotl.cavernous.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {

        if(context.getItemInHand().getItem() instanceof AxeItem) {

            // CREATE A MAP FOR THIS LATER
            if(state.is(ModBlocks.SHROOMWOOD_LOG)){
                return stripLog(state, ModBlocks.STRIPPED_SHROOMWOOD_LOG.get());
            }
            if(state.is(ModBlocks.SHROOMWOOD)){
                return stripLog(state, ModBlocks.STRIPPED_SHROOMWOOD.get());
            }
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    private BlockState stripLog(BlockState state, Block strippedVariant)
    {
        return strippedVariant.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
    }
}
