package net.thxlotl.cavernous.rendering;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.PanoramicScreenshotParameters;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.world.attribute.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.util.CubicSampler;
import net.thxlotl.cavernous.worldgen.biome.BiomeData;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Objects;

public class RenderUtil {

    private static float darkenWorldAmountO;
    private static float darkenWorldAmount;

    public static float getDarkenWorldAmount(float partialTicks) {

        if (Minecraft.getInstance().level.tickRateManager().runsNormally()) {
            darkenWorldAmountO = darkenWorldAmount;
            if (Minecraft.getInstance().gui.getBossOverlay().shouldDarkenScreen()) {
                darkenWorldAmount += 0.05F;
                if (darkenWorldAmount > 1.0F) {
                    darkenWorldAmount = 1.0F;
                }
            } else if (darkenWorldAmount > 0.0F) {
                darkenWorldAmount -= 0.0125F;
            }
        }

        return Mth.lerp(partialTicks, darkenWorldAmountO, darkenWorldAmount);
    }

//    public static Vector3f getBaseColor(ClientLevel level, Camera camera, int renderDistance, float darkenWorldAmount) {
//
//        Vec3 vec3 = camera.position().subtract((double)2.0F, (double)2.0F, (double)2.0F).scale((double)0.25F);
//        BiomeManager biomemanager = level.getBiomeManager();
//
//        float sampledBoost = (float) CubicSampler.gaussianSampleVec3(
//                vec3,
//                (x, y, z) -> {
//                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
//                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
//                    float value = key != null ? BiomeData.get(BiomeData.BIOME_BRIGHTNESS_BOOST, key) : 1.0f;
//                    return new Vec3(value, value, value); // replicate float into RGB
//                }
//        ).x();
//        float sampledOverride = (float) CubicSampler.gaussianSampleVec3(
//                vec3,
//                (x, y, z) -> {
//                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
//                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
//                    float value = key != null ? BiomeData.get(BiomeData.BIOME_BRIGHTNESS_OVERRIDE, key) : 1.0f;
//                    return new Vec3(value, value, value); // replicate float into RGB
//                }
//        ).x();
//
//        float f = Mth.clamp(Mth.cos(level.getTimeOfDay(darkenWorldAmount) * ((float)Math.PI * 2F)) * 2.0F + 0.5F, 0.0F, 1.0F);
//
//        //System.out.println("F: " + f + "Sampled Boost: " + sampledBoost);
//        float multiplier = 1 - sampledOverride;
//
//        Vec3 vec31 = level.effects().getBrightnessDependentFogColor(CubicSampler.gaussianSampleVec3(vec3, (p_423449_, p_423541_, p_423654_) -> Vec3.fromRGB24(((Biome)biomemanager.getNoiseBiomeAtQuart(p_423449_, p_423541_, p_423654_).value()).getFogColor())), f * multiplier + sampledBoost);
//        float f1 = (float)vec31.x();
//        float f2 = (float)vec31.y();
//        float f3 = (float)vec31.z();
//        if (renderDistance >= 4) {
//            float f4 = Mth.sin(level.getSunAngle(darkenWorldAmount)) > 0.0F ? -1.0F : 1.0F;
//            Vector3f vector3f = new Vector3f(f4, 0.0F, 0.0F);
//            float f5 = camera.getLookVector().dot(vector3f);
//            if (f5 > 0.0F && level.effects().isSunriseOrSunset(level.getTimeOfDay(darkenWorldAmount))) {
//                int i = level.effects().getSunriseOrSunsetColor(level.getTimeOfDay(darkenWorldAmount));
//                f5 *= ARGB.alphaFloat(i);
//                f1 = Mth.lerp(f5, f1, ARGB.redFloat(i));
//                f2 = Mth.lerp(f5, f2, ARGB.greenFloat(i));
//                f3 = Mth.lerp(f5, f3, ARGB.blueFloat(i));
//            }
//        }
//
//        int j = level.getSkyColor(camera.getPosition(), darkenWorldAmount);
//        float f10 = ARGB.redFloat(j);
//        float f11 = ARGB.greenFloat(j);
//        float f12 = ARGB.blueFloat(j);
//        float f6 = 0.25F + 0.75F * (float)renderDistance / 32.0F;
//        f6 = 1.0F - (float)Math.pow((double)f6, (double)0.25F);
//        f1 += (f10 - f1) * f6;
//        f2 += (f11 - f2) * f6;
//        f3 += (f12 - f3) * f6;
//        float f7 = level.getRainLevel(darkenWorldAmount);
//        if (f7 > 0.0F) {
//            float f8 = 1.0F - f7 * 0.5F;
//            float f9 = 1.0F - f7 * 0.4F;
//            f1 *= f8;
//            f2 *= f8;
//            f3 *= f9;
//        }
//
//        float f13 = level.getThunderLevel(darkenWorldAmount);
//        if (f13 > 0.0F) {
//            float f14 = 1.0F - f13 * 0.5F;
//            f1 *= f14;
//            f2 *= f14;
//            f3 *= f14;
//        }
//
//        return new Vector3f((float) vec31.x, (float) vec31.y, (float) vec31.z);
//        //return new Vec3(f1, f2, f3);
//    }

    public static Vector3f getBaseColor(ClientLevel level, Camera camera, int effectiveRenderDistance, float partialTick) {

        Vec3 vec3 = camera.position().subtract((double)2.0F, (double)2.0F, (double)2.0F).scale((double)0.25F);
        BiomeManager biomemanager = level.getBiomeManager();

        float sampledBoost = (float) CubicSampler.gaussianSampleVec3(
                vec3,
                (x, y, z) -> {
                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                    float value = key != null ? BiomeData.get(BiomeData.BIOME_BRIGHTNESS_BOOST, key) : 1.0f;
                    return new Vec3(value, value, value); // replicate float into RGB
                }
        ).x();
        float sampledOverride = (float) CubicSampler.gaussianSampleVec3(
                vec3,
                (x, y, z) -> {
                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                    float value = key != null ? BiomeData.get(BiomeData.BIOME_BRIGHTNESS_OVERRIDE, key) : 1.0f;
                    return new Vec3(value, value, value); // replicate float into RGB
                }
        ).x();

        float thing = Mth.clamp(Mth.cos(level.getDayTime() * ((float)Math.PI * 2F)) * 2.0F + 0.5F, 0.0F, 1.0F);

        //System.out.println("F: " + f + "Sampled Boost: " + sampledBoost);
        float multiplier = 1 - sampledOverride;




        int i = (Integer)camera.attributeProbe().getValue(EnvironmentAttributes.FOG_COLOR, partialTick);
        if (effectiveRenderDistance >= 4) {
            float f = (Float)camera.attributeProbe().getValue(EnvironmentAttributes.SUN_ANGLE, partialTick) * ((float)Math.PI / 180F);
            float f1 = Mth.sin((double)f) > 0.0F ? -1.0F : 1.0F;
            PanoramicScreenshotParameters panoramicscreenshotparameters = Minecraft.getInstance().gameRenderer.getPanoramicScreenshotParameters();
            Vector3fc vector3fc = panoramicscreenshotparameters != null ? panoramicscreenshotparameters.forwardVector() : camera.forwardVector();
            float f2 = vector3fc.dot(f1, 0.0F, 0.0F);
            if (f2 > 0.0F) {
                int j = (Integer)camera.attributeProbe().getValue(EnvironmentAttributes.SUNRISE_SUNSET_COLOR, partialTick);
                float f3 = ARGB.alphaFloat(j);
                if (f3 > 0.0F) {
                    i = ARGB.srgbLerp(f2 * f3, i, ARGB.opaque(j));
                }
            }
        }

        int baseColor = (Integer)camera.attributeProbe().getValue(EnvironmentAttributes.SKY_COLOR, partialTick);
        int k = applyWeatherDarken(baseColor, level.getRainLevel(partialTick), level.getThunderLevel(partialTick), multiplier);
        float f4 = Math.min((Float)camera.attributeProbe().getValue(EnvironmentAttributes.SKY_FOG_END_DISTANCE, partialTick) / 16.0F, (float)effectiveRenderDistance);
        float f5 = Mth.clampedLerp(f4 / 32.0F, 0.25F, 1.0F);
        f5 = 1.0F - (float)Math.pow((double)f5, (double)0.25F);

        int vanillaColor = ARGB.srgbLerp(f5, i, k);
        Vec3 pos = camera.position();
        int caveColor = getFogColorFromLevel(level, pos);

        int resolvedColor = ARGB.srgbLerp(multiplier, caveColor, vanillaColor);

        return new Vector3f(ARGB.redFloat(caveColor), ARGB.greenFloat(caveColor), ARGB.blueFloat(caveColor));
    }

    private static int getFogColorFromLevel(ClientLevel level, Vec3 pos) {

        SpatialAttributeInterpolator biomeInterpolator = new SpatialAttributeInterpolator();
        biomeInterpolator.clear();
        Vec3 scaledPos = pos.scale((double)0.25F);
        BiomeManager biomeManager = level.getBiomeManager();
        Objects.requireNonNull(biomeManager);
        GaussianSampler.sample(scaledPos, biomeManager::getNoiseBiomeAtQuart, (p_458003_, p_458163_) -> biomeInterpolator.accumulate(p_458003_, ((Biome)p_458163_.value()).getAttributes()));

        return level.environmentAttributes().getValue(EnvironmentAttributes.FOG_COLOR, pos, biomeInterpolator);
    }

    private static int applyWeatherDarken(int k, float rainLevel, float thunderLevel, float multiplier) {

        int storedK = k;

        if (rainLevel > 0.0F) {
            float f = 1.0F - rainLevel * 0.5F;
            float f1 = 1.0F - rainLevel * 0.4F;
            k = ARGB.scaleRGB(k, f, f, f1);
        }

        if (thunderLevel > 0.0F) {
            k = ARGB.scaleRGB(k, 1.0F - thunderLevel * 0.5F);
        }

        return storedK;

        //return ARGB.linearLerp(multiplier, storedK, k);
    }

    private static int multiplyColor(int color, float multiplier) {
        Vec3 vecColor = new Vec3(ARGB.redFloat(color), ARGB.greenFloat(color), ARGB.blueFloat(color));
        vecColor = vecColor.multiply(multiplier, multiplier, multiplier);
        return  ARGB.color(vecColor);
    }

    public static int rgb(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
