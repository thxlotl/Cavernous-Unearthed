package net.thxlotl.cavernous.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.OrePlacedFeatureTypes;

import java.util.EnumMap;

public class ModBiomes {
    // Biome initialization
    public static final ResourceKey<Biome> FUNGAL_CAVES = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "fungal_caves"));



    // Generate data
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(FUNGAL_CAVES, fungalCaves(context));
    }



    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }
    public static void addStoneTypeOres(BiomeGenerationSettings.Builder builder, String prefix) {

        EnumMap<OrePlacedFeatureTypes, ResourceKey<PlacedFeature>> map = ModPlacedFeatures.PLACED_ORE_MAPS.get(prefix);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.COAL_LOWER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.COAL_UPPER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.COPPER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.COPPER_LARGE))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.IRON_MIDDLE))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.IRON_SMALL))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.IRON_UPPER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.GOLD))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.GOLD_LOWER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.REDSTONE))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.REDSTONE_LOWER))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.LAPIS))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.LAPIS_BURIED))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.DIAMOND))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.DIAMOND_BURIED))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.DIAMOND_LARGE))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.DIAMOND_MEDIUM))
                .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, map.get(OrePlacedFeatureTypes.EMERALD));
    }
    public static void globalOverworldSpawns(MobSpawnSettings.Builder builder)
    {
        BiomeDefaultFeatures.caveSpawns(builder);
        BiomeDefaultFeatures.commonSpawns(builder);
    }



    private static Biome fungalCaves(BootstrapContext<Biome> context) {

        // Build mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        globalOverworldSpawns(spawnBuilder);

        // Build feature generation
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Default features
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        // Ores
        addStoneTypeOres(biomeBuilder, "fungatite");
        // Custom features
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.FEATHER_MOSS_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.UNDERGROUND_MYCELIUM_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.TOADSTOOL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.HANGING_SHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LAMPSHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.LAMPSHROOM_TREE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLEEDING_TOOTH_FUNGUS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CORDYCEPS);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.GHOST_FUNGUS);

        // Biome characteristics
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.5f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4178916)
                        .waterFogColor(335155)
                        .skyColor(12377016)
                        .fogColor(12377016)
                        .grassColorOverride(7311404)
                        .foliageColorOverride(7311404)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES)).build())
                .build();
    }
}
