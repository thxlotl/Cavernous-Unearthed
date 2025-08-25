package net.thxlotl.cavernous.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.thxlotl.cavernous.Cavernous;

public class ModWoodTypes {
    public static final WoodType SHROOMWOOD = WoodType.register(new WoodType(Cavernous.MODID + ":shroomwood", BlockSetType.MANGROVE));
}
