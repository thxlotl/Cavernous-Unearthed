package net.thxlotl.cavernous.worldgen;

import net.minecraft.world.level.biome.Climate;

public class WorldgenUtil {

    public static boolean inParameterRange(double input, Climate.Parameter parameter) {
        return input < parameter.max() && input > parameter.min();
    }
    public static boolean inParameterRange(double input, Climate.Parameter parameter, float buffer) {
        return input < parameter.max() + buffer && input > parameter.min() - buffer;
    }

}
