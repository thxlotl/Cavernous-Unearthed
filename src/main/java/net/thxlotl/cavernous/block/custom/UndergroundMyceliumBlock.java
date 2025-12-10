package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.particle.ModParticles;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

public class UndergroundMyceliumBlock extends Block implements BonemealableBlock {


    ResourceKey<ConfiguredFeature<?, ?>> feature = FungalCavesConfiguredFeatures.UNDERGROUND_MYCELIUM_PATCH_BONEMEAL;

    public UndergroundMyceliumBlock(Properties p_49795_) {
        super(p_49795_);
    }


    private static boolean canBeMycelium(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = reader.getBlockState(blockpos);
        if (blockstate.getFluidState().getAmount() == 8) {
            return false;
        }
        else {
            int i = LightEngine.getLightBlockInto(state, blockstate, Direction.UP, blockstate.getLightBlock());
            return i < 15;
        }
    }

    private static boolean canPropagate(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return canBeMycelium(state, level, pos) && !level.getFluidState(blockpos).is(FluidTags.WATER) && (level.getMaxLocalRawBrightness(pos.above()) >= 5);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Set block to fungatite if it is covered
        if (!canBeMycelium(state, level, pos)) {
            if (!level.isAreaLoaded(pos, 1)) {
                return;
            }
            level.setBlockAndUpdate(pos, ModBlocks.FUNGATITE.get().defaultBlockState());
        }
        else { // Growth logic
            if (!level.isAreaLoaded(pos, 3)) {
                return;
            }

            BlockState blockstate = this.defaultBlockState();
            for(int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (level.getBlockState(blockpos).is(ModBlocks.FUNGATITE.get()) && canPropagate(blockstate, level, blockpos)) {
                    level.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) == 0) {
            double posX = (double)pos.getX() + random.nextDouble();
            double posY = (double)pos.getY() + 1.1;
            double posZ = (double)pos.getZ() + random.nextDouble();
            // CHANGE PARTICLE TYPE LATER
            level.addParticle(ModParticles.UNDERGROUND_MYCELIUM.get(), posX, posY, posZ, (double)0.0F, (double)0.0F, (double)0.0F);
        }
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        serverLevel.registryAccess().lookup(Registries.CONFIGURED_FEATURE).flatMap((p_379951_) ->
                p_379951_.get(feature)).ifPresent((p_380225_) -> ((ConfiguredFeature)p_380225_.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.above()));
    }
}
