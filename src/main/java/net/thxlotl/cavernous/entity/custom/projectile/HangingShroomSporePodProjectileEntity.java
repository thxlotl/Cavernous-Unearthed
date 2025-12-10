package net.thxlotl.cavernous.entity.custom.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.item.ModItems;
import net.thxlotl.cavernous.util.MathUtil;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

public class HangingShroomSporePodProjectileEntity extends ThrowableItemProjectile {

    public float spinXMultiplier;
    public float spinYMultiplier;
    public boolean spinXDirection;
    public boolean spinYDirection;

    ResourceKey<ConfiguredFeature<?, ?>> feature = FungalCavesConfiguredFeatures.HANGING_SHROOM_SPORE_POD;

    public HangingShroomSporePodProjectileEntity(Level level, LivingEntity owner, ItemStack item) {
        super(ModEntities.HANGING_SHROOM_SPORE_POD.get(), owner, level, item);
        RandomSource random = owner.getRandom();
        this.spinXMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinYMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinXDirection = random.nextBoolean();
        this.spinYDirection = random.nextBoolean();
    }

    public HangingShroomSporePodProjectileEntity(Level level, double x, double y, double z, ItemStack item) {
        super(ModEntities.HANGING_SHROOM_SPORE_POD.get(), x, y, z, level, item);
        RandomSource random = level.getRandom();
        this.spinXMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinYMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinXDirection = random.nextBoolean();
        this.spinYDirection = random.nextBoolean();
    }
    public HangingShroomSporePodProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(ModEntities.HANGING_SHROOM_SPORE_POD.get(), p_37443_);
        RandomSource random = level().getRandom();
        this.spinXMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinYMultiplier = MathUtil.clampedRandomFloat(random, 0.8f, 1.2f);
        this.spinXDirection = random.nextBoolean();
        this.spinYDirection = random.nextBoolean();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.HANGING_SHROOM_SPORE_POD.get();
    }

    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0);

        spawnParticleCloud(result.getEntity().level(), result.getEntity().position());
    }

    protected void onHit(HitResult result) {
        super.onHit(result);

        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();

            ServerLevel serverLevel = (ServerLevel) level();
            //BlockPos pos = new BlockPos((int) result.getLocation().x(), (int) result.getLocation().y(), (int) result.getLocation().z());
            BlockPos pos = this.blockPosition();

            if(level().getBlockState(pos).isFaceSturdy(level(), pos, Direction.DOWN) && level().getBlockState(pos.below()).isAir())
            {
                serverLevel.registryAccess().lookup(Registries.CONFIGURED_FEATURE).flatMap((p_379951_) -> p_379951_.get(feature)).ifPresent((p_380225_) ->
                        ((ConfiguredFeature)p_380225_.value()).place(serverLevel, serverLevel.getChunkSource().getGenerator(), level().getRandom(), pos.below()));
            }
        }

        spawnParticleCloud(this.level(), result.getLocation());
        }


    private void spawnParticleCloud(Level level, Position pos)
    {
        if(!level.isClientSide())
        {
            int particleCount = 50;
            float range = 1.5f;

            for (int i = 0; i < particleCount; i++) {

                double u = random.nextDouble();
                double v = random.nextDouble();
                double w = random.nextDouble();

                double theta = 2 * Math.PI * u;
                double phi = Math.acos(2 * v - 1);
                double r = range * Math.cbrt(w);

                double posXRandom = pos.x() + r * Math.sin(phi) * Math.cos(theta);
                double posYRandom = pos.y() +r * Math.sin(phi) * Math.sin(theta);
                double posZRandom = pos.z() +r * Math.cos(phi);

                float scale = MathUtil.clampedRandomFloat(level.getRandom(), 2, 4);

                ((ServerLevel) level).sendParticles
                        (new DustParticleOptions(14352266, scale),
                                posXRandom, posYRandom, posZRandom,
                                1, 0, 0, 0, 5);
            }
        }
    }
}
