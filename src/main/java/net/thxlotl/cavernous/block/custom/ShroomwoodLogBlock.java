package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

public class ShroomwoodLogBlock extends ModFlammableRotatedPillarBlock implements BonemealableBlock {
    public ShroomwoodLogBlock(Properties p_55926_) {
        super(p_55926_);
    }

    ResourceKey<ConfiguredFeature<?, ?>> feature = FungalCavesConfiguredFeatures.SHROOMWOOD_LOG_VEGETATION;

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir() && blockState.getValue(BlockStateProperties.AXIS) != Direction.Axis.Y;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {

        serverLevel.registryAccess().lookup(Registries.CONFIGURED_FEATURE).flatMap((p_379951_) -> p_379951_.get(feature)).ifPresent((p_380225_) -> ((ConfiguredFeature)p_380225_.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above()));

    }
}