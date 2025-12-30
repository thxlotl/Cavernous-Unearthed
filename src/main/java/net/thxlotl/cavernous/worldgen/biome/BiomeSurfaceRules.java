package net.thxlotl.cavernous.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.worldgen.ModNoises;

import java.util.function.Supplier;

public class BiomeSurfaceRules {

    public static SurfaceRules.RuleSource fungalRules()
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

    public static SurfaceRules.RuleSource volcanicRules()
    {
        Supplier<? extends Block> obsidianStone = ModBlocks.OBSIDIANSTONE;
        Supplier<? extends Block> scoria = ModBlocks.SCORIA;
        Supplier<? extends Block> softMagma = ModBlocks.SOFT_MAGMA_BLOCK;

        SurfaceRules.RuleSource volcanicCaves =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.05f, 0.05f),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.stoneDepthCheck(0, false, 1, CaveSurface.FLOOR),
                                        SurfaceRules.state(softMagma.get().defaultBlockState())
                                )
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.stoneDepthCheck(0, true, 3, CaveSurface.FLOOR),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.1f, 0.1f),
                                                SurfaceRules.state(Blocks.MAGMA_BLOCK.defaultBlockState())
                                        )
//                                        ,
//                                        SurfaceRules.ifTrue(
//                                                SurfaceRules.noiseCondition(ModNoises.VOLCANIC, -0.3f, 0.3f),
//                                                SurfaceRules.ifTrue(
//                                                        SurfaceRules.stoneDepthCheck(0, false, CaveSurface.FLOOR),
//                                                        SurfaceRules.state(scoria.get().defaultBlockState())
//                                                )
//                                        )
                                )
                        ),
                        SurfaceRules.state(obsidianStone.get().defaultBlockState())
                );

        return SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.VOLCANIC_CAVES), volcanicCaves);
    }

}
