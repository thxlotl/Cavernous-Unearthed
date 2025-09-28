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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.ai.AntAttackGoal;
import net.thxlotl.cavernous.entity.ai.InfectedAntAttackGoal;
import net.thxlotl.cavernous.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class InfectedAnt extends Monster {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(InfectedAnt.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState tweakAnimationState = new AnimationState();
    private int tweakAnimationTimeout = 0;
    private int tweakAnimationPlaytime = 0;
    private boolean playingTweak = false;

    public InfectedAnt(EntityType<? extends Monster> p_27557_, Level p_27558_) {
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
        builder.define(ATTACKING, false);
    }

    // Entity's "behaviour"
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Ant.class, true));
        this.goalSelector.addGoal(2, new InfectedAntAttackGoal(this, (double)1.0F, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.24d)
                .add(Attributes.FOLLOW_RANGE, 24.0d)
                .add(Attributes.ATTACK_DAMAGE, 2.0d)
                ;
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
            clientTick();
        }
    }

    private void clientTick() {

        this.setupAttackAnimationState();

        if (this.tweakAnimationTimeout <= 0) {
            this.tweakAnimationState.start(this.tickCount);
            this.tweakAnimationTimeout = 40 + random.nextIntBetweenInclusive(25, 160);
            this.playingTweak = true;
        }
        else --this.tweakAnimationTimeout;

        if (this.playingTweak) {
            this.tweakAnimationPlaytime ++;
        }
        if (this.tweakAnimationPlaytime > 41) {
            this.tweakAnimationPlaytime = 0;
            this.playingTweak = false;
            this.tweakAnimationState.stop();
        }

    }
}
