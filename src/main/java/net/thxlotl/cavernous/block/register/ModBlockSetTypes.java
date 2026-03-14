package net.thxlotl.cavernous.block.register;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetTypes {

    public static final BlockSetType POLISHED_OBSIDIANTSTONE;

    static {
        POLISHED_OBSIDIANTSTONE = BlockSetType.register(new BlockSetType(
                "polished_obsidianstone",
                true,
                true,
                false,
                BlockSetType.PressurePlateSensitivity.MOBS,
                SoundType.STONE,
                SoundEvents.IRON_DOOR_CLOSE,
                SoundEvents.IRON_DOOR_OPEN,
                SoundEvents.IRON_TRAPDOOR_CLOSE,
                SoundEvents.IRON_TRAPDOOR_OPEN,
                SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
                SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.STONE_BUTTON_CLICK_OFF,
                SoundEvents.STONE_BUTTON_CLICK_ON));
    }
}
