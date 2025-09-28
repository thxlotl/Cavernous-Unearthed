package net.thxlotl.cavernous.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;

public class InfectedAntAttackGoal extends MeleeAttackGoal {

    // Both in ticks
    private static final int STRIKE_TIME = 10;
    private static final int ANIM_LENGTH = 20;

    private final InfectedAnt ant;

    private boolean preparingAttack = false;
    private boolean hasAttacked = false;
    private int attackBuildUp = 0;

    public InfectedAntAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
        ant = ((InfectedAnt) mob);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {

        super.tick();
        if (preparingAttack) {
            if (attackBuildUp >= ANIM_LENGTH) {
                ant.setAttacking(false);
                preparingAttack = false;
                attackBuildUp = 0;
            }
            else attackBuildUp ++;
        }

        System.out.println("Attack build up = " + attackBuildUp);

    }

    @Override
    public void stop() {
        ant.setAttacking(false);
        super.stop();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.canPerformAttack(target)) { // Target is in range
            preparingAttack = true;

            if (isTimeToStartAttackAnimation()) {
                ant.setAttacking(true);
                this.hasAttacked = false;
            }

            if (this.isTimeToAttack()) {
                performAttack(target);
            }
        }
        else {
            doNotPerformAttack();
        }
    }

    private boolean isTimeToStartAttackAnimation() {
        return this.attackBuildUp == 0 && preparingAttack;
    }

    @Override
    protected boolean isTimeToAttack() {
        return this.attackBuildUp >= STRIKE_TIME && !this.hasAttacked;
    }

    @Override
    protected boolean canPerformAttack(LivingEntity entity) {
        return this.mob.isWithinMeleeAttackRange(entity) && this.mob.getSensing().hasLineOfSight(entity);
    }

    @Override
    protected void resetAttackCooldown() {
        attackBuildUp = 0;
    }

    private void performAttack(LivingEntity target) {
        this.hasAttacked = true;
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(getServerLevel(this.mob), target);
    }

    private void doNotPerformAttack() {
        resetAttackCooldown();
        preparingAttack = false;
        ant.setAttacking(false);




        ant.attackAnimationTimeout = 0;
    }
}
