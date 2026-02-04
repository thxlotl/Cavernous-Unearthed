package net.thxlotl.cavernous.worldgen.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class BiomeData {

    public static final Map<ResourceKey<Biome>, Float> BIOME_FOG_NEAR_OFFSET = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 10.0f,
            ModBiomes.VOLCANIC_CAVES, 8.0f
    ));

    public static final Map<ResourceKey<Biome>, Float> BIOME_FOG_FAR_MULTIPLIER = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 0.08f,
            ModBiomes.VOLCANIC_CAVES, 0.07f
    ));

    public static final Map<ResourceKey<Biome>, Float> BIOME_BRIGHTNESS_BOOST = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 0.4f,
            ModBiomes.VOLCANIC_CAVES, 0.4f
    ));

    public static final Map<ResourceKey<Biome>, Float> BIOME_BRIGHTNESS_OVERRIDE = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 1.0f,
            ModBiomes.VOLCANIC_CAVES, 1.0f
    ));

    public static final Map<ResourceKey<Biome>, Vec3> BIOME_FOG_COLOR = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, computeBiomeColor(7839842) //old color 10014123 , newer old color 9881216
    ));



    // Access
    public static void put(Map<ResourceKey<Biome>, Float> map, ResourceKey<Biome> biome, float value) {
        map.put(biome, value);
    }
    public static float get(Map<ResourceKey<Biome>, Float> map, ResourceKey<Biome> biome) {

        float defaultValue = 1.0f;
        if(map == BIOME_FOG_NEAR_OFFSET || map == BIOME_BRIGHTNESS_BOOST || map == BIOME_BRIGHTNESS_OVERRIDE) {
            defaultValue = 0.0f;
        }

        return map.getOrDefault(biome, defaultValue);
    }
    public static Vec3 getVec3(Map<ResourceKey<Biome>, Vec3> map, ResourceKey<Biome> biome) {

        Vec3 defaultValue = new Vec3(1f, 1f, 1f);
        return map.getOrDefault(biome, defaultValue);
    }

    // Utility
    private static Vec3 vec3fToVec3(Vector3f vec3f) {
        return new Vec3(vec3f.x, vec3f.y, vec3f.z);
    }
    private static Vec3 computeBiomeColor(int color) {
        return vec3fToVec3(ARGB.vector3fFromRGB24(color));
    }

}
