package net.thxlotl.cavernous.worldgen.biolith;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NoiseUtils;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.worldgen.ModBiomes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class BiolithUsage {

    public static final Logger LOGGER = LoggerFactory.getLogger(Cavernous.MODID);
    public static final String MOD_ID = Cavernous.MODID;
    public static final String MOD_NAME = "Cavernous Unearthed";


    public static void init()
    {

        ResourceLocation overworldRules = ResourceLocation.fromNamespaceAndPath("minecraft", "rules/overworld");

        BiomePlacement.addOverworld(ModBiomes.FUNGAL_CAVES, new Climate.ParameterPoint(
                Climate.Parameter.span(-0.45f, 0.2f),
                Climate.Parameter.span(-0.35f, 0.7f),
                Climate.Parameter.span(-1.05f, -0.455f),
                Climate.Parameter.span(-0.375f, 1f),
                Climate.Parameter.span(0.2f, 0.53f),
                Climate.Parameter.span(-1.0f, 1.0f),
                0L));
        SurfaceGeneration.addOverworldSurfaceRules(overworldRules, fungalRules());

    }

    private static SurfaceRules.RuleSource fungalRules()
    {
        Supplier<? extends Block> undergroundMycelium = ModBlocks.UNDERGROUND_MYCELIUM;
        Supplier<? extends Block> groundFungatite = ModBlocks.GROUND_FUNGATITE;
        Supplier<? extends Block> fungatite = ModBlocks.FUNGATITE;

        SurfaceRules.RuleSource fungalCaves =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 2, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.BADLANDS_SURFACE, -0.045, 0.045), SurfaceRules.state(groundFungatite.get().defaultBlockState()))),
                        SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.BADLANDS_SURFACE, -0.5, 0.5), SurfaceRules.state(undergroundMycelium.get().defaultBlockState()))),
                        SurfaceRules.state(fungatite.get().defaultBlockState())
                        );

        return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.FUNGAL_CAVES), fungalCaves);
    }
}
