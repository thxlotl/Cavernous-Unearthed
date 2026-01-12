package net.thxlotl.cavernous.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.particle.ModParticles;
import net.thxlotl.cavernous.util.worldgen.ore.CustomStoneOreFamily;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;
import net.thxlotl.cavernous.worldgen.features.placed.FungalCavesPlacedFeatures;

import java.util.EnumMap;

public class BiomeBuilders {

    //region Helper Methods

    // Default features
    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    // Default ores
    public static void addCustomStoneOres (BiomeGenerationSettings.Builder builder, CustomStoneType type) {

        EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> placedFeatures = CustomStoneOreFamily.allFamilies.get(type).placedFeatures;

        for (OrePlacedFeatureType placedFeatureType : placedFeatures.keySet()) {
            builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, placedFeatures.get(placedFeatureType));
        }
    }

    // Default Spawns
    public static void globalOverworldSpawns(MobSpawnSettings.Builder builder) {
        //BiomeDefaultFeatures.caveSpawns(builder);
        BiomeDefaultFeatures.commonSpawns(builder);
    }

    //endregion

    public static Biome fungalCaves(BootstrapContext<Biome> context) {

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
        addCustomStoneOres(biomeBuilder, CustomStoneType.FUNGATITE);

        // Custom features
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FungalCavesPlacedFeatures.ORE_GROUND_FUNGATITE);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, FungalCavesPlacedFeatures.SHELFSHROOM);

        ///biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.FEATHER_MOSS_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.UNDERGROUND_MYCELIUM_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.MYCELIUM_SPROUT_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.LAMPSHROOM_PATCH_CLUSTER);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.TOADSTOOL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.LAMPSHROOM_TREE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.LAMPSHROOM_CLUSTER);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.BLEEDING_TOOTH_FUNGUS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.CORDYCEPS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.HANGING_SHROOM);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.GHOST_FUNGUS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FungalCavesPlacedFeatures.PUFFSHROOM);

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
                        .skyColor(10014123) //12377016 old color
                        .fogColor(10014123)
                        .grassColorOverride(7311404)
                        .foliageColorOverride(7311404)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES)).build())
                .build();
    }

    public static Biome volcanicCaves(BootstrapContext<Biome> context) {

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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        // Custom features

        // Biome characteristics
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(7039851)
                        .waterFogColor(3552822)
                        .skyColor(8870956)
                        .fogColor(13464130)
                        .grassColorOverride(7039851)
                        .foliageColorOverride(7039851)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES))
                        .ambientParticle(new AmbientParticleSettings(ModParticles.VOLCANIC_ASH.get(), 0.07f))
                        .build())
                .build();
    }

}
