package net.thxlotl.cavernous.block.custom.sign;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.thxlotl.cavernous.block.entity.ModSignBlockEntity;

public class ModWallSignBlock extends WallSignBlock {
    public ModWallSignBlock(WoodType p_58069_, Properties p_58068_) {
        super(p_58069_, p_58068_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return new ModSignBlockEntity(p_154556_, p_154557_);
    }
}
