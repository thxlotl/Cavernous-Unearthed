package net.thxlotl.cavernous.util;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;

public class MathUtil {

    public static float clampedRandomFloat(RandomSource random, float min, float max)
    {
        return min + random.nextFloat() * (max - min);
    }

    public static float scaleFloat(float input, float min, float max)
    {
        return min + input * (max - min);
    }
    public static double scaleFloat(double input, double min, double max)
    {
        return min + input * (max - min);
    }

    public static float circleDistribution(float input)
    {
        return Mth.sqrt(-Mth.square(input) + 1);
    }
}
