package net.thxlotl.cavernous.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class AntEntity extends Animal {

    public static final AnimationState idleState = new AnimationState();
    public static final AnimationState walkState = new AnimationState();
    private int idleAnimationTimeOut = 0;

    public AntEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    // Entity's "behaviour"
    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, (double)2.0F));
        this.goalSelector.addGoal(2, new BreedGoal(this, (double)1.0F));
        this.goalSelector.addGoal(3, new TemptGoal(this, (double)1.25F, (p_401437_) -> p_401437_.is(ModTags.Items.ANT_FOOD), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, (double)1.25F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.FOLLOW_RANGE, 24.0d)
                .add(Attributes.ATTACK_DAMAGE, 1.0d)
                .add(Attributes.TEMPT_RANGE, (double)12.0F)
                ;
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return itemStack.is(ModTags.Items.ANT_FOOD);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        AntEntity baby = ModEntities.ANT.get().create(serverLevel, EntitySpawnReason.BREEDING);
        return baby;
    }

    private void setupAnimationStates() {

        if(this.idleAnimationTimeOut <= 0) {
            this.idleAnimationTimeOut = 40;
            this.idleState.start(this.tickCount);
        }
        else {
            --this.idleAnimationTimeOut;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
