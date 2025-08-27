package net.thxlotl.cavernous.worldgen.biolith;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import net.minecraft.ResourceLocationException;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.thxlotl.cavernous.Cavernous;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiolithUsage {

    public static final Logger LOGGER = LoggerFactory.getLogger(Cavernous.MODID);
    public static final String MOD_ID = Cavernous.MODID;
    public static final String MOD_NAME = "Cavernous Unearthed";


    public static void init()
    {
        ResourceKey<Biome> DESERT = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("minecraft", "desert"));
        ResourceKey<Biome> SNOWY_SLOPES = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("minecraft", "snowy_slopes"));
        ResourceKey<Biome> CRIMSON_FOREST = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("minecraft", "crimson_forest"));

        BiomePlacement.addOverworld(CRIMSON_FOREST, new Climate.ParameterPoint(
                Climate.Parameter.span(-1.0f, -0.15f),
                Climate.Parameter.span(-1.0f, -0.35f),
                Climate.Parameter.span(0.3f, 1.0f),
                Climate.Parameter.span(-0.375f, 0.05f),
                Climate.Parameter.point(0.0f),
                Climate.Parameter.span(0.0f, 1.0f),
                0L));

        BiomePlacement.replaceOverworld(SNOWY_SLOPES, DESERT);
    }
}
