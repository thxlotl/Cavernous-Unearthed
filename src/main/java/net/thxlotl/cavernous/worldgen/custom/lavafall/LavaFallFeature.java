package net.thxlotl.cavernous.worldgen.custom.lavafall;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SpringFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;

public class LavaFallFeature extends Feature<NoneFeatureConfiguration> {

    private Direction checkedDirectionsLava[] = {Direction.UP, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    private Direction checkedDirectionsAir[] = {Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

    public LavaFallFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {

        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        BlockPos lavaPos = featurePlaceContext.origin();
        BlockPos airPos = featurePlaceContext.origin();

        while ((worldGenLevel.getBlockState(airPos).isAir() || lavaPos == airPos) && !worldGenLevel.isOutsideBuildHeight(airPos)) {
            airPos = airPos.below();
        }

        if (!canPlaceLava(featurePlaceContext) || !canPlaceAir(featurePlaceContext, airPos)) return false;
        else {
            worldGenLevel.setBlock(lavaPos, Blocks.LAVA.defaultBlockState(), 2);
            worldGenLevel.scheduleTick(lavaPos, Fluids.LAVA, 0);
            worldGenLevel.setBlock(airPos, Blocks.AIR.defaultBlockState(), 2);
        }

        return true;
    }

    private boolean canPlaceLava(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext) {

        WorldGenLevel worldGenLevel = featurePlaceContext.level();

        boolean flag = true;
        for (Direction direction : checkedDirectionsLava) {
            if (worldGenLevel.getBlockState(featurePlaceContext.origin().relative(direction)).isAir()) flag = false;
        }
        return flag;
    }
    private boolean canPlaceAir(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext, BlockPos airPos) {

        WorldGenLevel worldGenLevel = featurePlaceContext.level();

        boolean flag = true;
        for (Direction direction : checkedDirectionsAir) {
            if (worldGenLevel.getBlockState(airPos.relative(direction)).isAir()) flag = false;
        }
        return flag;
    }
}
