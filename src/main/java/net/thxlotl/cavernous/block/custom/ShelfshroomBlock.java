package net.thxlotl.cavernous.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ShelfshroomBlock extends Block implements BonemealableBlock {

    ResourceKey<ConfiguredFeature<?, ?>> feature = FungalCavesConfiguredFeatures.SHELFSHROOM;
    public static final EnumProperty<Direction> FACING;
    private static final Map<Direction, VoxelShape> SHAPES;
    private static final VoxelShape BASE_SHAPE = Shapes.or(
            Block.box((double)2.0F, (double)7.0F, (double)0.0F, (double)14.0F, (double)10.0F, (double)7.0F),
            Block.box((double)5.0F, (double)5.0F, (double)0.0F, (double)11.0F, (double)7.0F, (double)4.0F));

    public ShelfshroomBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SHAPES = Shapes.rotateHorizontal(BASE_SHAPE.optimize());
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        //return (BlockState)this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());

        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);

        return (BlockState) Arrays.stream(context.getNearestLookingDirections()).map((dir) -> getStateAtDirection(dir, blockpos, level)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public BlockState getStateAtDirection(Direction direction, BlockPos pos, Level level) {

        if (!isValidDirection(direction)) return null; // Remove this possibility if it is not included in directions

        BlockState state = this.defaultBlockState().setValue(FACING, direction);

        if (!canSurvive(level, pos, direction)) return null;
        else return state;
    }

    public boolean isValidDirection(Direction direction) {
        return Arrays.asList(Direction.Plane.HORIZONTAL.stream().toArray(Direction[]::new)).contains(direction);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }


    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return (double)level.random.nextFloat() < 0.55f;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Direction direction = blockState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        serverLevel.registryAccess().lookup(Registries.CONFIGURED_FEATURE).flatMap((p_379951_) ->
                p_379951_.get(feature)).ifPresent((p_380225_) -> ((ConfiguredFeature)p_380225_.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos.relative(direction)));
        serverLevel.setBlockAndUpdate(blockPos, ModBlocks.SHELFSHROOM_CAP_BLOCK.get().defaultBlockState());
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurvive(level, pos, state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    public static boolean canSurvive(LevelReader level, BlockPos pos, Direction facing) {
        BlockPos blockpos = pos.relative(facing);
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, facing.getOpposite());
    }

    protected BlockState updateShape(BlockState p_401118_, LevelReader p_401198_, ScheduledTickAccess p_401107_, BlockPos p_401142_, Direction p_401236_, BlockPos p_401082_, BlockState p_401336_, RandomSource p_401169_) {
        return !p_401118_.canSurvive(p_401198_, p_401142_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_401118_, p_401198_, p_401107_, p_401142_, p_401236_, p_401082_, p_401336_, p_401169_);
    }
}
