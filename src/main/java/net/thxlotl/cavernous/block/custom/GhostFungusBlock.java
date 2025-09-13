package net.thxlotl.cavernous.block.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.thxlotl.cavernous.block.entity.GhostFungusBlockEntity;
import net.thxlotl.cavernous.rendering.RenderUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class GhostFungusBlock extends SegmentedWallBlock implements BonemealableBlock, EntityBlock {

    public GhostFungusBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        if (levelReader instanceof Level level) {
            return hasAnyFillableFace(blockState, (Level) levelReader, blockPos);
        }
        else return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction direction = getRandomFace(serverLevel, randomSource, blockPos, blockState);
        if (direction != null) serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(getFaceProperty(direction), getFaceCount(blockState, direction) + 1));
    }

    private Direction getRandomFace(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction[] available = Arrays.stream(VALID_DIRECTIONS)
                .filter(dir -> (!hasFullFace(blockState, dir) && canAttachTo(serverLevel, blockPos, dir)))
                .toArray(Direction[]::new);

        if (available.length == 0) {
            return null;
        }

        return available[randomSource.nextInt(available.length)];
    }

    public static int getColor(BlockState state, BlockAndTintGetter getter, BlockPos pos, int i)
    {
        if (i != 0) return -1;
        //Vec3i greenColor = new Vec3i(24, 165, 16);
        Vec3i greenColor = new Vec3i(255, 255, 255);
        Vec3i offColor = new Vec3i(6, 27, 88);

        int blockLevel = getter.getBrightness(LightLayer.BLOCK, pos);
        int skyLevel = getter.getBrightness(LightLayer.SKY, pos);

        int lightLevel = Math.max(blockLevel, skyLevel);

        float percent = (float) lightLevel / 15f;
        return RenderUtil.rgb(
                Mth.lerpInt(percent, greenColor.getX(), offColor.getX()),
                Mth.lerpInt(percent, greenColor.getY(), offColor.getY()),
                Mth.lerpInt(percent, greenColor.getZ(), offColor.getZ()));

    }

    // Block entity stuff

    @Override
    public @org.jetbrains.annotations.Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GhostFungusBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

}
