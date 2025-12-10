package net.thxlotl.cavernous.worldgen.custom.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower TOADSTOOL = new TreeGrower(Cavernous.MODID + ":toadstool", Optional.empty(), Optional.of(FungalCavesConfiguredFeatures.TOADSTOOL), Optional.empty());
    public static final TreeGrower LAMPSHROOM = new TreeGrower(Cavernous.MODID + ":lampshroom", Optional.empty(), Optional.of(FungalCavesConfiguredFeatures.LAMPSHROOM_TREE), Optional.empty());
}
