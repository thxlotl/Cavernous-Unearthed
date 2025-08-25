package net.thxlotl.cavernous.block.custom.sign;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.thxlotl.cavernous.block.entity.ModHangingSignBlockEntity;

public class ModWallHangingSignBlock extends WallHangingSignBlock {
    public ModWallHangingSignBlock(WoodType p_252140_, Properties p_251606_) {
        super(p_252140_, p_251606_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_250745_, BlockState p_250905_) {
        return new ModHangingSignBlockEntity(p_250745_, p_250905_);
    }
}
