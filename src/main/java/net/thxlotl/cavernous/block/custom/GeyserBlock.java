package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.thxlotl.cavernous.block.entity.GeyserBlockEntity;
import net.thxlotl.cavernous.block.entity.ModBlockEntities;
import net.thxlotl.cavernous.particle.ModParticles;
import org.jetbrains.annotations.Nullable;

public class GeyserBlock extends BaseEntityBlock {

    public static final int SURFACE_PARTICLE_COUNT = 1;

    public static final BooleanProperty TRIGGERED;

    static {
        TRIGGERED = BlockStateProperties.TRIGGERED;
    }

    public GeyserBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(TRIGGERED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TRIGGERED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new GeyserBlockEntity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.GEYSER_BLOCK.get(), GeyserBlockEntity::tick);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {

        if(level.getBlockEntity(pos) instanceof GeyserBlockEntity geyser) {

            boolean triggered = state.getValue(TRIGGERED);
            boolean powered = level.hasNeighborSignal(pos);

            if (!triggered && powered) {

                geyser.launchTriggered = true;

                level.scheduleTick(pos, this, 1);

                level.setBlock(pos, state.setValue(TRIGGERED, true), 3);

            }

            if (!powered && triggered) {

                level.setBlock(pos, state.setValue(TRIGGERED, false), 3);
            }
        }

        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (!level.getBlockState(pos.below()).is(BlockTags.ICE)) {
            for (int i = 0; i < SURFACE_PARTICLE_COUNT; i++) {

                level.addParticle(
                        ModParticles.GEYSER_AMBIENT.get(),
                        pos.getX() + random.nextDouble(),
                        pos.getY() + 1,
                        pos.getZ() + random.nextDouble(),
                        random.nextDouble() * 0.1,
                        random.nextDouble() * 0.5,
                        random.nextDouble() * 0.1
                );
            }
        }

    }
}
