package net.thxlotl.cavernous.worldgen.custom.wallface;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.thxlotl.cavernous.block.custom.SegmentedWallBlock;

import java.util.List;
import java.util.Objects;

public class WallFaceGrowthConfiguration implements FeatureConfiguration {

    public static final Codec<WallFaceGrowthConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec()
                    .flatXmap(WallFaceGrowthConfiguration::apply, DataResult::success)
                    .fieldOf("block").forGetter(cfg -> cfg.placeBlock),
            Codec.intRange(1, 64).fieldOf("search_range").orElse(10).forGetter(cfg -> cfg.searchRange),
            Codec.floatRange(0.0F, 1.0F).fieldOf("chance_of_spreading").orElse(0.5F).forGetter(cfg -> cfg.chanceOfSpreading),
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("can_be_placed_on").forGetter(cfg -> cfg.canBePlacedOn)
    ).apply(instance, WallFaceGrowthConfiguration::new));

    public final SegmentedWallBlock placeBlock;
    public final int searchRange;
    public final float chanceOfSpreading;
    public final HolderSet<Block> canBePlacedOn;
    private final ObjectArrayList<Direction> validDirections;

    private static DataResult<SegmentedWallBlock> apply(Block block) {
        DataResult var10000;
        if (block instanceof SegmentedWallBlock multifacespreadeableblock) {
            var10000 = DataResult.success(multifacespreadeableblock);
        } else {
            var10000 = DataResult.error(() -> "Growth block should be a multiface spreadeable block");
        }

        return var10000;
    }

    public WallFaceGrowthConfiguration(SegmentedWallBlock placeBlock, int searchRange, float chanceOfSpreading, HolderSet<Block> canBePlacedOn) {
        this.placeBlock = placeBlock;
        this.searchRange = searchRange;
        this.chanceOfSpreading = chanceOfSpreading;
        this.canBePlacedOn = canBePlacedOn;
        this.validDirections = new ObjectArrayList(6);

        Direction.Plane var10000 = Direction.Plane.HORIZONTAL;
        ObjectArrayList var10001 = this.validDirections;
        Objects.requireNonNull(var10001);
        var10000.forEach(var10001::add);
    }

    public List<Direction> getShuffledDirectionsExcept(RandomSource random, Direction direction) {
        return Util.toShuffledList(this.validDirections.stream().filter((p_225412_) -> p_225412_ != direction), random);
    }

    public List<Direction> getShuffledDirections(RandomSource random) {
        return Util.shuffledCopy(this.validDirections, random);
    }
}
