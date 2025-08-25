package net.thxlotl.cavernous.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ModHangingSignBlockEntity extends SignBlockEntity {


        private static final int MAX_TEXT_LINE_WIDTH = 60;
        private static final int TEXT_LINE_HEIGHT = 9;

    public ModHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_HANGING_SIGN.get(), pPos, pBlockState);
    }

        public int getTextLineHeight() {
        return 9;
    }

        public int getMaxTextLineWidth() {
        return 60;
    }

        public SoundEvent getSignInteractionFailedSoundEvent() {
        return SoundEvents.WAXED_HANGING_SIGN_INTERACT_FAIL;
    }

}
