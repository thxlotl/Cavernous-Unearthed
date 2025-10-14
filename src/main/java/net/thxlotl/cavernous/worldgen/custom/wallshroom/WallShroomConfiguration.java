package net.thxlotl.cavernous.worldgen.custom.wallshroom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record WallShroomConfiguration(BlockStateProvider toPlace, IntProvider radius, boolean scheduleTick) implements FeatureConfiguration {

    public static final Codec<WallShroomConfiguration> CODEC = RecordCodecBuilder.create(
            (configInstance) -> configInstance.group(
                    BlockStateProvider.CODEC.fieldOf("to_place").forGetter((config) -> config.toPlace),
                    IntProvider.CODEC.fieldOf("radius").forGetter((config) -> config.radius),
                    Codec.BOOL.optionalFieldOf("schedule_tick", false).forGetter((config) -> config.scheduleTick)
            ).apply(configInstance, WallShroomConfiguration::new));


    public WallShroomConfiguration(BlockStateProvider stateProvider, IntProvider intProvider) {
        this(stateProvider, intProvider, true);
    }

}
