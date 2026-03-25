package net.thxlotl.cavernous.util.worldgen;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.thxlotl.cavernous.worldgen.biome.ClimateParameters;

public class BiomeUtil {

    public static boolean isVolcanicRegion(DensityFunction erosionFunction, DensityFunction depthFunction, DensityFunction.FunctionContext functionContext) {
        //return erosionFunction.compute(functionContext) < (double)-0.225F && depthFunction.compute(functionContext) > (double)0.9F;

        Climate.ParameterPoint point = ClimateParameters.volcanicParametersSNAPSHOT;

        float buffer = 0.07f; //0.07
        return inParameterRange(erosionFunction.compute(functionContext), point.erosion(), buffer) && inParameterRange(depthFunction.compute(functionContext), point.depth(), buffer);
    }

    public static boolean inParameterRange(double input, Climate.Parameter parameter) {
        return input < parameter.max() && input > parameter.min();
    }
    public static boolean inParameterRange(double input, Climate.Parameter parameter, float buffer) {
        return input < parameter.max() + buffer && input > parameter.min() - buffer;
    }


}
