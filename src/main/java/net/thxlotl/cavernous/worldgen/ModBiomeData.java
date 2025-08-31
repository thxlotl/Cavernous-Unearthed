package net.thxlotl.cavernous.worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.HashMap;
import java.util.Map;

public class ModBiomeData {
    public static final Map<ResourceKey<Biome>, Float> BIOME_FOG_NEAR_OFFSET = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 10.0f
    ));
    public static final Map<ResourceKey<Biome>, Float> BIOME_FOG_FAR_MULTIPLIER = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 0.08f
    ));
    public static final Map<ResourceKey<Biome>, Float> BIOME_BRIGHTNESS_BOOST = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 0.4f
    ));
    public static final Map<ResourceKey<Biome>, Float> BIOME_BRIGHTNESS_OVERRIDE = new HashMap<>(Map.of(
            ModBiomes.FUNGAL_CAVES, 1.0f
    ));

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

}
