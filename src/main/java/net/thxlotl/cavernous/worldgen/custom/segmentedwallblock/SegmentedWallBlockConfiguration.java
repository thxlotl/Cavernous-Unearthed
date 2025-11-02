package net.thxlotl.cavernous.worldgen.custom.segmentedwallblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.thxlotl.cavernous.block.custom.SegmentedWallBlock;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;

public record SegmentedWallBlockConfiguration(BlockStateProvider block, IntProvider radius) implements FeatureConfiguration {

    public static final Codec<SegmentedWallBlockConfiguration> CODEC = RecordCodecBuilder.create(
            (configInstance) -> configInstance.group(
                    BlockStateProvider.CODEC.fieldOf("block").forGetter((config) -> config.block),
                    IntProvider.CODEC.fieldOf("radius").forGetter((config) -> config.radius)
            ).apply(configInstance, SegmentedWallBlockConfiguration::new));

}
