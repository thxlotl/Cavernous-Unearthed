package net.thxlotl.cavernous.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class GhostFungusBlockEntity extends BlockEntity {

    public GhostFungusBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GHOST_FUNGUS.get(), pos, blockState);
    }
}
