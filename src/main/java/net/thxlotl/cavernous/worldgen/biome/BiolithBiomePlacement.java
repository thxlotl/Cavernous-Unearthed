package net.thxlotl.cavernous.worldgen.biome;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.worldgen.ModNoises;

import java.util.function.Supplier;

public class BiolithBiomePlacement {

    public static void init()
    {
        Identifier overworldRules = Identifier.fromNamespaceAndPath("minecraft", "rules/overworld");

        BiomePlacement.addOverworld(ModBiomes.FUNGAL_CAVES, ClimateParameters.fungalParametersSNAPSHOT);
        SurfaceGeneration.addOverworldSurfaceRules(overworldRules, BiomeSurfaceRules.fungalRules());

        BiomePlacement.addOverworld(ModBiomes.VOLCANIC_CAVES, ClimateParameters.volcanicParametersSNAPSHOT); // Offset
        SurfaceGeneration.addOverworldSurfaceRules(overworldRules, BiomeSurfaceRules.volcanicRules());

    }

}
