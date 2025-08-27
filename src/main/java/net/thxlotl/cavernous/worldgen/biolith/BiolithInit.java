package net.thxlotl.cavernous.worldgen.biolith;

import net.neoforged.fml.common.Mod;
import net.thxlotl.cavernous.Cavernous;

@Mod(Cavernous.MODID)
@SuppressWarnings("unused")
public class BiolithInit {
    public BiolithInit() {
        BiolithUsage.LOGGER.info("Biolith usage is initializing...");

        BiolithUsage.init();
    }
}
