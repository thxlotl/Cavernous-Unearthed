package net.thxlotl.cavernous.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.thxlotl.cavernous.Cavernous;

public class ModBiomes {

    // Biome initialization
    public static final ResourceKey<Biome> FUNGAL_CAVES = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "fungal_caves"));
    public static final ResourceKey<Biome> VOLCANIC_CAVES = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "volcanic_caves"));

    // Generate data
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(FUNGAL_CAVES, BiomeBuilders.fungalCaves(context));
        context.register(VOLCANIC_CAVES, BiomeBuilders.volcanicCaves(context));
    }

}
