package net.thxlotl.cavernous.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;

public class FungalZombie extends Zombie {

    public final AnimationState tweakAnimationState = new AnimationState();
    private int tweakAnimationTimeout = 0;
    private int tweakAnimationPlaytime = 0;
    private boolean playingTweak = false;

    public FungalZombie(EntityType<? extends Zombie> p_34271_, Level p_34272_) {
        super(p_34271_, p_34272_);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Zombie.createAttributes().add(Attributes.STEP_HEIGHT, (double)1.0F);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            clientTick();
        }
    }

    private void clientTick() {

        if (this.tweakAnimationTimeout <= 0) {
            this.tweakAnimationState.start(this.tickCount);
            this.tweakAnimationTimeout = 40 + random.nextIntBetweenInclusive(25, 80);
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
