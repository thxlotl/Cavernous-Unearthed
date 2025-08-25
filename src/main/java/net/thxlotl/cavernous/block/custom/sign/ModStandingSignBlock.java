package net.thxlotl.cavernous.block.custom.sign;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.thxlotl.cavernous.block.entity.ModSignBlockEntity;

public class ModStandingSignBlock extends StandingSignBlock {
    public ModStandingSignBlock(WoodType p_56991_, Properties p_56990_) {
        super(p_56991_, p_56990_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return new ModSignBlockEntity(p_154556_, p_154557_);
    }
}
