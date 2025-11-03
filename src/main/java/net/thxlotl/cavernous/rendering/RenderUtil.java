package net.thxlotl.cavernous.rendering;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.worldgen.CustomBiomeData;
import org.joml.Vector3f;

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

    public static Vector3f getBaseColor(ClientLevel level, Camera camera, int renderDistance, float darkenWorldAmount) {

        Vec3 vec3 = camera.getPosition().subtract((double)2.0F, (double)2.0F, (double)2.0F).scale((double)0.25F);
        BiomeManager biomemanager = level.getBiomeManager();

        float sampledBoost = (float) CubicSampler.gaussianSampleVec3(
                vec3,
                (x, y, z) -> {
                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                    float value = key != null ? CustomBiomeData.get(CustomBiomeData.BIOME_BRIGHTNESS_BOOST, key) : 1.0f;
                    return new Vec3(value, value, value); // replicate float into RGB
                }
        ).x();
        float sampledOverride = (float) CubicSampler.gaussianSampleVec3(
                vec3,
                (x, y, z) -> {
                    Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                    ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                    float value = key != null ? CustomBiomeData.get(CustomBiomeData.BIOME_BRIGHTNESS_OVERRIDE, key) : 1.0f;
                    return new Vec3(value, value, value); // replicate float into RGB
                }
        ).x();

        float f = Mth.clamp(Mth.cos(level.getTimeOfDay(darkenWorldAmount) * ((float)Math.PI * 2F)) * 2.0F + 0.5F, 0.0F, 1.0F);

        //System.out.println("F: " + f + "Sampled Boost: " + sampledBoost);
        float multiplier = 1 - sampledOverride;

        Vec3 vec31 = level.effects().getBrightnessDependentFogColor(CubicSampler.gaussianSampleVec3(vec3, (p_423449_, p_423541_, p_423654_) -> Vec3.fromRGB24(((Biome)biomemanager.getNoiseBiomeAtQuart(p_423449_, p_423541_, p_423654_).value()).getFogColor())), f * multiplier + sampledBoost);
        float f1 = (float)vec31.x();
        float f2 = (float)vec31.y();
        float f3 = (float)vec31.z();
        if (renderDistance >= 4) {
            float f4 = Mth.sin(level.getSunAngle(darkenWorldAmount)) > 0.0F ? -1.0F : 1.0F;
            Vector3f vector3f = new Vector3f(f4, 0.0F, 0.0F);
            float f5 = camera.getLookVector().dot(vector3f);
            if (f5 > 0.0F && level.effects().isSunriseOrSunset(level.getTimeOfDay(darkenWorldAmount))) {
                int i = level.effects().getSunriseOrSunsetColor(level.getTimeOfDay(darkenWorldAmount));
                f5 *= ARGB.alphaFloat(i);
                f1 = Mth.lerp(f5, f1, ARGB.redFloat(i));
                f2 = Mth.lerp(f5, f2, ARGB.greenFloat(i));
                f3 = Mth.lerp(f5, f3, ARGB.blueFloat(i));
            }
        }

        int j = level.getSkyColor(camera.getPosition(), darkenWorldAmount);
        float f10 = ARGB.redFloat(j);
        float f11 = ARGB.greenFloat(j);
        float f12 = ARGB.blueFloat(j);
        float f6 = 0.25F + 0.75F * (float)renderDistance / 32.0F;
        f6 = 1.0F - (float)Math.pow((double)f6, (double)0.25F);
        f1 += (f10 - f1) * f6;
        f2 += (f11 - f2) * f6;
        f3 += (f12 - f3) * f6;
        float f7 = level.getRainLevel(darkenWorldAmount);
        if (f7 > 0.0F) {
            float f8 = 1.0F - f7 * 0.5F;
            float f9 = 1.0F - f7 * 0.4F;
            f1 *= f8;
            f2 *= f8;
            f3 *= f9;
        }

        float f13 = level.getThunderLevel(darkenWorldAmount);
        if (f13 > 0.0F) {
            float f14 = 1.0F - f13 * 0.5F;
            f1 *= f14;
            f2 *= f14;
            f3 *= f14;
        }

        return new Vector3f((float) vec31.x, (float) vec31.y, (float) vec31.z);
        //return new Vec3(f1, f2, f3);
    }

    public static int rgb(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
