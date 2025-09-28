package net.thxlotl.cavernous.worldgen.custom.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class LeaveCustomVineDecorator extends TreeDecorator {

    public static final MapCodec<LeaveCustomVineDecorator> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.floatRange(0.0F, 1.0F)
                            .fieldOf("probability")
                            .forGetter(d -> d.probability),
                    BuiltInRegistries.BLOCK.byNameCodec()
                            .fieldOf("vine_block")
                            .xmap(block -> (VineBlock) block, vine -> vine)
                            .forGetter(d -> d.vineBlock)
            ).apply(instance, LeaveCustomVineDecorator::new));


    private final float probability;
    private final VineBlock vineBlock;

    public LeaveCustomVineDecorator(float probability, VineBlock vineBlock) {
        this.probability = probability;
        this.vineBlock = vineBlock;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorType.CUSTOM_LEAVE_VINE.get();
    }

    public void place(TreeDecorator.Context p_226039_) {

        RandomSource randomsource = p_226039_.random();
        p_226039_.leaves().forEach((p_226035_) -> {
            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos = p_226035_.west();
                if (p_226039_.isAir(blockpos)) {
                    addHangingVine(blockpos, VineBlock.EAST, p_226039_, this.vineBlock);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos1 = p_226035_.east();
                if (p_226039_.isAir(blockpos1)) {
                    addHangingVine(blockpos1, VineBlock.WEST, p_226039_, this.vineBlock);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos2 = p_226035_.north();
                if (p_226039_.isAir(blockpos2)) {
                    addHangingVine(blockpos2, VineBlock.SOUTH, p_226039_, this.vineBlock);
                }
            }

            if (randomsource.nextFloat() < this.probability) {
                BlockPos blockpos3 = p_226035_.south();
                if (p_226039_.isAir(blockpos3)) {
                    addHangingVine(blockpos3, VineBlock.NORTH, p_226039_, this.vineBlock);
                }
            }

        });
    }

    private static void addHangingVine(BlockPos pos, BooleanProperty sideProperty, TreeDecorator.Context context, VineBlock vineBlock) {
        placeVine(pos, sideProperty, context, vineBlock);
        int i = 4;

        for(BlockPos blockpos = pos.below(); context.isAir(blockpos) && i > 0; --i) {
            placeVine(blockpos, sideProperty, context, vineBlock);
            blockpos = blockpos.below();
        }

    }

    private static void placeVine(BlockPos pos, BooleanProperty sideProperty, TreeDecorator.Context context, VineBlock vineBlock) {
        context.setBlock(pos, (BlockState) vineBlock.defaultBlockState().setValue(sideProperty, true));
    }

}
