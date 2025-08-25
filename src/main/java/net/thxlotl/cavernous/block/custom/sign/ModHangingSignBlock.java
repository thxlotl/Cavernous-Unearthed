package net.thxlotl.cavernous.block.custom.sign;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.thxlotl.cavernous.block.entity.ModHangingSignBlockEntity;

public class ModHangingSignBlock extends CeilingHangingSignBlock {
    public ModHangingSignBlock(WoodType p_248716_, Properties p_250481_) {
        super(p_248716_, p_250481_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_249338_, BlockState p_250706_) {
        return new ModHangingSignBlockEntity(p_249338_, p_250706_);
    }
}
