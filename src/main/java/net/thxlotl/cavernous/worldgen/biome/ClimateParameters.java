package net.thxlotl.cavernous.worldgen.biome;

import net.minecraft.world.level.biome.Climate;

public class ClimateParameters {

    public static final Climate.ParameterPoint fungalParameters = new Climate.ParameterPoint(
            Climate.Parameter.span(-0.45f, 0.2f),
            Climate.Parameter.span(-0.35f, 0.7f),
            Climate.Parameter.span(-1.05f, -0.455f),
            Climate.Parameter.span(-0.375f, 1f),
            Climate.Parameter.span(0.2f, 0.7f),
            Climate.Parameter.span(-1.0f, 1.0f),
            0L);
    public static final Climate.ParameterPoint volcanicParameters = new Climate.ParameterPoint(
            Climate.Parameter.span(-1.0f, 1.0f), // Temperature
            Climate.Parameter.span(-1.0f, 1.0f), // Humidity
            Climate.Parameter.span(-1.05f, -0.11f), // Continentalness
            Climate.Parameter.span(0.05f, 0.45f), // Erosion
            Climate.Parameter.span(0.35f, 1f), // Depth
            Climate.Parameter.span(-1.0f, 1.0f), // Weirdness
            0L);

    public static final Climate.ParameterPoint fungalParametersSNAPSHOT = new Climate.ParameterPoint(
            Climate.Parameter.span(-1.0f, 0.0f), // half world
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(0.35f, 1f),
            Climate.Parameter.span(-1.0f, 1.0f),
            0L);

    public static final Climate.ParameterPoint volcanicParametersSNAPSHOT = new Climate.ParameterPoint(
            Climate.Parameter.span(0.0f, 1.0f), // other half
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(-1.0f, 1.0f),
            Climate.Parameter.span(0.35f, 1f),
            Climate.Parameter.span(-1.0f, 1.0f),
            0L);

}
