package net.thxlotl.cavernous.worldgen.custom.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower TOADSTOOL = new TreeGrower(Cavernous.MODID + ":toadstool", Optional.empty(), Optional.of(ModConfiguredFeatures.TOADSTOOL), Optional.empty());
}
