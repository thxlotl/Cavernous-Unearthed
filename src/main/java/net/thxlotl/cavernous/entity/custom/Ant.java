package net.thxlotl.cavernous.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.ai.AntAttackGoal;
import net.thxlotl.cavernous.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Ant extends Animal implements NeutralMob {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Ant.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;


    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME;
    private static final UniformInt PERSISTENT_ANGER_TIME;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    static {
        PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
        DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Ant.class, EntityDataSerializers.INT);
    }

    public Ant(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
        builder.define(ATTACKING, false);
    }


    // Entity's "behaviour"
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, (double)1.0F));
        this.goalSelector.addGoal(3, new TemptGoal(this, (double)1.25F, (p_401437_) -> p_401437_.is(ModTags.Items.ANT_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, (double)1.25F));
        this.goalSelector.addGoal(5, new AntAttackGoal(this, (double)1.0F, true));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, this::isAngryAt));
    }

    public int getRemainingPersistentAngerTime() {
        return (Integer)this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    public void setRemainingPersistentAngerTime(int p_406218_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_406218_);
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_406276_) {
        this.persistentAngerTarget = p_406276_;
    }

    @Override
    public boolean isAngryAt(LivingEntity entity, ServerLevel level) {
        if (!this.canAttack(entity)) {
            return false;
        } else {
            return entity.getType() == EntityType.PLAYER && this.isAngryAtAllPlayers(level) ? true : entity.getUUID().equals(this.getPersistentAngerTarget());
        }
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.24d)
                .add(Attributes.FOLLOW_RANGE, 24.0d)
                .add(Attributes.ATTACK_DAMAGE, 1.5d)
                .add(Attributes.TEMPT_RANGE, (double)12.0F)
                ;
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModTags.Items.ANT_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        Ant baby = ModEntities.ANT.get().create(serverLevel, EntitySpawnReason.BREEDING);
        return baby;
    }

    private void setupAttackAnimationState() {

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            this.attackAnimationTimeout = 20; // Length in ticks of animation
            this.attackAnimationState.start(this.tickCount);
        } else { --this.attackAnimationTimeout; }

        if (!this.isAttacking() && this.attackAnimationTimeout <= 0) {
            this.attackAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAttackAnimationState();
        }
    }
}
