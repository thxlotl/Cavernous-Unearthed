package net.thxlotl.cavernous.worldgen.biolith;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.worldgen.ModBiomes;
import net.thxlotl.cavernous.worldgen.ModNoises;
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
                Climate.Parameter.span(0.2f, 0.7f),
                Climate.Parameter.span(-1.0f, 1.0f),
                0L));
        SurfaceGeneration.addOverworldSurfaceRules(overworldRules, fungalRules());

        BiomePlacement.addOverworld(ModBiomes.VOLCANIC_CAVES, new Climate.ParameterPoint(
                Climate.Parameter.span(-1.0f, 1.0f), // Temperature
                Climate.Parameter.span(-1.0f, 1.0f), // Humidity
                Climate.Parameter.span(-1.05f, -0.11f), // Continentalness
                Climate.Parameter.span(0.05f, 0.45f), // Erosion
                Climate.Parameter.span(0.1f, 0.53f), // Depth
                Climate.Parameter.span(-1.0f, 1.0f), // Weirdness
                0L)); // Offset
        SurfaceGeneration.addOverworldSurfaceRules(overworldRules, volcanicRules());

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

    private static SurfaceRules.RuleSource volcanicRules()
    {
        Supplier<? extends Block> obsidianStone = ModBlocks.OBSIDIANSTONE;
        Supplier<? extends Block> scoria = ModBlocks.SCORIA;

        SurfaceRules.RuleSource volcanicCaves =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.05f, 0.05f),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                        SurfaceRules.state(Blocks.LAVA.defaultBlockState())
                                )
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.15f, 0.15f),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                                        SurfaceRules.state(Blocks.MAGMA_BLOCK.defaultBlockState())
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.3f, 0.3f),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
                                                        SurfaceRules.state(scoria.get().defaultBlockState())
                                                )
                                        )
                                )
                        ),
                        SurfaceRules.state(obsidianStone.get().defaultBlockState())
                );

        return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.VOLCANIC_CAVES), volcanicCaves);
    }
}
