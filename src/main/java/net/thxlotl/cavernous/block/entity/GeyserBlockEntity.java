package net.thxlotl.cavernous.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.block.custom.GeyserBlock;
import net.thxlotl.cavernous.particle.ModParticles;
import net.thxlotl.cavernous.rendering.ObsidianstoneUtil;
import net.thxlotl.cavernous.datagen.tag.ModTags;

import java.util.List;

public class GeyserBlockEntity extends BlockEntity {

    // Config
    private static final float LAUNCH_VELOCITY = 1.5f;
    private static final int STAND_TIME_TOLERANCE = 15;
    private static final int LAUNCH_TIME_TOLERANCE = 5;
    private static final double BOX_WIDTH = 0.45f;
    private static final double LAUNCH_BOX_HEIGHT = 1.4f;
    private static final double CHECK_BOX_HEIGHT = 0.4f;
    private static final int LAUNCH_PARTICLE_COUNT = 100;
    private static final int BUBBLE_PARTICLE_COUNT = 4;
    private static final float RANDOM_BURST_CHANCE = 0.0001f;
    private static final ParticleOptions[] STEAM_PARTICLE_LIST = {
            ModParticles.GEYSER_STEAM_1.get(),
            ModParticles.GEYSER_STEAM_2.get(),
            ModParticles.GEYSER_STEAM_3.get(),
            ModParticles.GEYSER_STEAM_4.get()
    };
    private static final ParticleOptions[] BURST_PARTICLE_LIST = {
            ModParticles.GEYSER_BURST_1.get(),
            ModParticles.GEYSER_BURST_2.get(),
            ModParticles.GEYSER_BURST_3.get(),
            ModParticles.GEYSER_BURST_4.get()
    };
    private static final int STEAM_PARTICLE_TICK_INTERVAL = 10;
    private static final int LAUNCH_COOLDOWN_AMOUNT = 90;

    // Variables
    public int stoodOnTime;
    public boolean standTriggered;
    public int launchTimer;
    public boolean launchTriggered;
    public int ticksSinceSteam;
    public int ticksSinceLaunch;

    public GeyserBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GEYSER_BLOCK.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GeyserBlockEntity geyser) {

        geyser.ticksSinceSteam++;
        geyser.ticksSinceLaunch++;

        List<LivingEntity> entities = geyser.getCheckedEntities(level, pos);
        List<LivingEntity> checkedEntities =
                entities
                .stream()
                .filter(e -> !e.isCrouching())
                .toList();

        if (checkedEntities.isEmpty() || geyser.inLaunchCooldown()) {
            geyser.stoodOnTime = geyser.stoodOnTime > 0 ? geyser.stoodOnTime - 1: 0;
        }
        else if (!geyser.isLocked()) {
            geyser.stoodOnTime += 1;

            createBubbleParticles(geyser);

            for (Entity entity : entities) {
                if (level.getRandom().nextFloat() < 0.3f) {
                    level.playSound(entity, pos, SoundEvents.MAGMA_CUBE_JUMP, SoundSource.BLOCKS, 0.2f, 0.5f);
                }
            }

            if (geyser.stoodOnTime > STAND_TIME_TOLERANCE) {
                geyser.standTriggered = true;
            }
        }

        if (geyser.standTriggered) {
            geyser.launchTimer += 1;
        }

        if (geyser.launchTimer > LAUNCH_TIME_TOLERANCE) {
            geyser.launchTriggered = true;
            //geyser.launch(entities, geyser);
        }

        boolean triggered = state.getValue(GeyserBlock.getTriggered());
        boolean powered = level.hasNeighborSignal(pos);

        if (geyser.launchTriggered || RandomSource.create().nextFloat() < RANDOM_BURST_CHANCE || (powered && !triggered)) {
            List<Entity> toLaunch = geyser.getEntitiesToLaunch(level, pos);
            geyser.launch(toLaunch, geyser);
        }


        if (geyser.willProduceSteamParticles())
        {
            geyser.spawnSteamParticle();
        }

    }

    public List<Entity> getEntitiesToLaunch(Level level, BlockPos pos) {
        return level.getEntitiesOfClass(Entity.class, getLaunchBox(pos));
    }
    public List<LivingEntity> getCheckedEntities(Level level, BlockPos pos) {
        return level.getEntitiesOfClass(LivingEntity.class, getCheckBox(pos));
    }

    public void spawnSteamParticle() {

        int select = this.getLevel().getRandom().nextInt(STEAM_PARTICLE_LIST.length);
        ParticleOptions particle = STEAM_PARTICLE_LIST[select];
        BlockPos pos = this.getBlockPos();

        level.addAlwaysVisibleParticle(
                particle,
                pos.getCenter().x,
                pos.getY() + 1.1,
                pos.getCenter().z,
                0,
                0,
                0
        );

        this.ticksSinceSteam = 0;

    }

    public void spawnBurstParticle() {

        int select = this.getLevel().getRandom().nextInt(BURST_PARTICLE_LIST.length);
        ParticleOptions particle = BURST_PARTICLE_LIST[select];
        BlockPos pos = this.getBlockPos();

        level.addAlwaysVisibleParticle(
                particle,
                pos.getCenter().x,
                pos.getY() + 0.6,
                pos.getCenter().z,
                0,
                0,
                0
        );
    }

    private boolean inLaunchCooldown() {
        return this.ticksSinceLaunch < LAUNCH_COOLDOWN_AMOUNT;
    }

    private boolean willProduceSteamParticles() {

        BlockPos pos = this.getBlockPos();
        Level level = this.getLevel();

        return
                this.ticksSinceSteam > STEAM_PARTICLE_TICK_INTERVAL &&
                !level.getBlockState(pos.below()).is(BlockTags.ICE) &&
                level.getBlockState(pos.above()).is(BlockTags.REPLACEABLE) &&
                !(this.stoodOnTime > 0) &&
                !this.inLaunchCooldown();
    }

    public void launch(List<Entity> entities, GeyserBlockEntity geyser) {

        for (Entity entity : entities) {
            entity.addDeltaMovement(new Vec3(0, calculateLaunchVelocity(geyser), 0));
            entity.hurtMarked = true;

            level.playSound(entity, geyser.getBlockPos(), SoundEvents.PLAYER_SPLASH_HIGH_SPEED, SoundSource.BLOCKS, 0.5f, 1.3f);
        }

        geyser.ticksSinceLaunch = 0;
        geyser.createLaunchParticles();

        geyser.launchTimer = 0;
        geyser.stoodOnTime = 0;
        geyser.standTriggered = false;
        geyser.launchTriggered = false;
    }

    private float calculateLaunchVelocity(GeyserBlockEntity geyser) {

        float ratio = (getHeatAmount(geyser.level, geyser.getBlockState(), geyser.getBlockPos()) + 6) / 10f;

        return (ratio) * LAUNCH_VELOCITY;
    }

    public static int getHeatAmount(Level level, BlockState state, BlockPos pos)
    {
        int nearestDistance = ObsidianstoneUtil.maxRange;

        for (int x = -ObsidianstoneUtil.maxRange; x <= ObsidianstoneUtil.maxRange; x++) {
            for (int y = -ObsidianstoneUtil.maxRange; y <= ObsidianstoneUtil.maxRange; y++) {
                for (int z = -ObsidianstoneUtil.maxRange; z <= ObsidianstoneUtil.maxRange; z++) {

                    BlockPos current = new BlockPos(x, y, z);

                    if (level.getBlockState(pos.offset(current)).is(ModTags.Blocks.HOT_BLOCKS)) {

                        int currentDistance = new Vec3i(x, y, z).distManhattan(Vec3i.ZERO);

                        if (currentDistance < nearestDistance) nearestDistance = currentDistance;

                    }

                }
            }
        }

        return ObsidianstoneUtil.maxRange + 1 - nearestDistance;
    }

    private void createLaunchParticles() {

        BlockPos pos = this.getBlockPos();
        Level level = this.level;

        for (int i = 0; i < LAUNCH_PARTICLE_COUNT; i++) {

//            if (level instanceof ServerLevel serverLevel) {
//                serverLevel.sendParticles(
//                        ModParticles.GEYSER_BURST.get(),
//                        pos.getCenter().x,
//                        pos.getY() + 0.6,
//                        pos.getCenter().z,
//                        1,
//                        0,
//                        0,
//                        0,
//                        0
//                );
//            }

            this.spawnBurstParticle();

        }
    }

    private static void createBubbleParticles(GeyserBlockEntity geyser) {

        BlockPos pos = geyser.getBlockPos();
        Level level = geyser.level;

        for (int i = 0; i < BUBBLE_PARTICLE_COUNT; i++) {

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ModParticles.GEYSER_BUBBLE.get(),
                        pos.getCenter().x,
                        pos.getY() + 1.1f,
                        pos.getCenter().z,
                        1,
                        0,
                        0,
                        0,
                        0
                );
            }

        }
    }

    private static AABB getLaunchBox(BlockPos pos) {
        Vec3 center = new Vec3(pos.getCenter().x, pos.getCenter().y + 0.45f, pos.getCenter().z);
        return new AABB(
                center.x - BOX_WIDTH,
                center.y,
                center.z - BOX_WIDTH,
                center.x + BOX_WIDTH,
                center.y + LAUNCH_BOX_HEIGHT,
                center.z + BOX_WIDTH
        );
    }

    private static AABB getCheckBox(BlockPos pos) {
        Vec3 center = new Vec3(pos.getCenter().x, pos.getCenter().y + 0.45f, pos.getCenter().z);
        return new AABB(
                center.x - BOX_WIDTH,
                center.y,
                center.z - BOX_WIDTH,
                center.x + BOX_WIDTH,
                center.y + CHECK_BOX_HEIGHT,
                center.z + BOX_WIDTH
        );
    }

    private boolean isLocked() {
        return this.getLevel().getBlockState(this.getBlockPos().below()).is(BlockTags.ICE);
    }
}
