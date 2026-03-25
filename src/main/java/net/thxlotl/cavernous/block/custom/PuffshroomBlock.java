package net.thxlotl.cavernous.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thxlotl.cavernous.particle.ModParticles;
import net.thxlotl.cavernous.util.MathUtil;
import net.thxlotl.cavernous.util.ModTags;

public class PuffshroomBlock extends VegetationBlock {


    public PuffshroomBlock(Properties properties) {
        super(properties);
    }

    private static final float CLOUD_OFFSET = 2.0f;
    private static final float CLOUD_RADIUS = 10.0f;
    private static final int CLOUD_PARTICLE_COUNT = 7;
    private static final int SPEW_PARTICLE_COUNT = 4;

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return null;
    }

    private static final VoxelShape SHAPE = Shapes.or(
            Block.column((double)12.0F, (double)3.0F, (double)14.0F),
            Block.column((double)6.0F, (double)0.0F, (double)3.0F));;

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(ModTags.Blocks.SHROOMWOOD_LOGS) || super.mayPlaceOn(state, level, pos) || state.is(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        Vec3 origin = new Vec3(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);

        if (random.nextFloat() < 0.23f) {

            for (int i = 0; i < SPEW_PARTICLE_COUNT; i++) {

                level.addParticle(
                        ModParticles.PUFFSHROOM_SPORE.get(),
                        origin.x, origin.y + 0.5f, origin.z,
                        MathUtil.scaleFloat(random.nextFloat(), -1, 1), MathUtil.scaleFloat(random.nextFloat(), 1, 3), MathUtil.scaleFloat(random.nextFloat(), -1, 1)
                );
            }
        }
        
        for (int i = 0; i < CLOUD_PARTICLE_COUNT; i++) {

            float distance = random.nextFloat() * CLOUD_RADIUS;
            float theta = random.nextFloat() * 2 * Mth.PI;
            float phi = random.nextFloat() * 2 * Mth.PI;

            float x = distance * Mth.sin(phi) * Mth.cos(theta) + (float)origin.x;
            float y = distance * Mth.cos(phi) + (float)origin.y;
            float z = distance * Mth.sin(phi) * Mth.sin(theta) + (float)origin.z;

            level.addParticle(
                    ModParticles.PUFFSHROOM_SPORE.get(),
                    x, y + CLOUD_OFFSET, z,
                    0, 0, 0);
        }
    }
}
