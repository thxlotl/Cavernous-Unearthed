package net.thxlotl.cavernous.worldgen.custom.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class MushroomCapFoliagePlacer extends FoliagePlacer {

    private final IntProvider height;

    public static final MapCodec<MushroomCapFoliagePlacer>
            CODEC = RecordCodecBuilder.mapCodec(
            (MushroomCapFoliageInstance) -> foliagePlacerParts(MushroomCapFoliageInstance)
                    .and(IntProviders.codec(3, 16).fieldOf("height").forGetter((heightInstance) -> heightInstance.height))
                    .apply(MushroomCapFoliageInstance, MushroomCapFoliagePlacer::new));

    public MushroomCapFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.MUSHROOM_CAP_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(WorldGenLevel levelReader, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration treeConfig, int i, FoliageAttachment foliageAttachment, int foliageHeight, int foliageRadius, int offset) {
        this.placeLeavesCap(levelReader, foliageSetter, random, treeConfig, foliageAttachment.pos(), foliageRadius + 1, offset, foliageAttachment.doubleTrunk(), false);
        for(int y = 1; y <= foliageHeight - 2; ++y) {
            this.placeLeavesCap(levelReader, foliageSetter, random, treeConfig, foliageAttachment.pos(), foliageRadius, offset + y, foliageAttachment.doubleTrunk(), false);
        }
        this.placeLeavesCap(levelReader, foliageSetter, random, treeConfig, foliageAttachment.pos(), foliageRadius, offset + foliageHeight - 1, foliageAttachment.doubleTrunk(), true);

    }

    @Override
    public int foliageHeight(RandomSource randomSource, int i, TreeConfiguration treeConfiguration) {
        return this.height.sample(randomSource);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return false;
    }

    protected boolean shouldSkipRoundedEdges(RandomSource randomSource, int localX, int localY, int localZ, int range, boolean large, boolean roundedEdges) {
        return Mth.abs(localX) == range && Mth.abs(localZ) == range && roundedEdges;
    }

    protected void placeLeavesCap(
            WorldGenLevel level,
            FoliagePlacer.FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration treeConfiguration,
            BlockPos pos,
            int range,
            int localY,
            boolean large,
            boolean roundEdges
    ) {
        int i = large ? 1 : 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j = -range; j <= range + i; j++) {
            for (int k = -range; k <= range + i; k++) {
                if (!this.shouldSkipRoundedEdges(random, j, localY, k, range, large, roundEdges)) {
                    blockpos$mutableblockpos.setWithOffset(pos, j, localY, k);
                    tryPlaceLeaf(level, foliageSetter, random, treeConfiguration, blockpos$mutableblockpos);
                }
            }
        }
    }

}
