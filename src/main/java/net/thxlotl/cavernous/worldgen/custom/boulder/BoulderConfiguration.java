package net.thxlotl.cavernous.worldgen.custom.boulder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record BoulderConfiguration(BlockStateProvider toPlace, IntProvider radius) implements FeatureConfiguration {

    public static final Codec<BoulderConfiguration> CODEC = RecordCodecBuilder.create(
            (configInstance) -> configInstance.group(
                            BlockStateProvider.CODEC.fieldOf("to_place").forGetter((config) -> config.toPlace),
                            IntProvider.CODEC.fieldOf("radius").forGetter((config) -> config.radius)
            ).apply(configInstance, BoulderConfiguration::new)
    );

}
