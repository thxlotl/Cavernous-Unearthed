package net.thxlotl.cavernous.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.thxlotl.cavernous.Cavernous;

public class ModNoises {

    public static final ResourceKey<NormalNoise.NoiseParameters> VOLCANIC = createKey("volcanic");

    private static ResourceKey<NormalNoise.NoiseParameters> createKey(String key) {
        return ResourceKey.create(Registries.NOISE, Identifier.fromNamespaceAndPath(Cavernous.MODID, key));
    }

    public static void bootstrap(BootstrapContext<NormalNoise.NoiseParameters> context) {
        register(context, VOLCANIC, -5, 1, 2, 1);
    }

    private static void register(
            BootstrapContext<NormalNoise.NoiseParameters> context,
            ResourceKey<NormalNoise.NoiseParameters> key,
            int firstOctave,
            double amplitude,
            double... otherAmplitudes
    ) {
        context.register(key, new NormalNoise.NoiseParameters(firstOctave, amplitude, otherAmplitudes));
    }
}
