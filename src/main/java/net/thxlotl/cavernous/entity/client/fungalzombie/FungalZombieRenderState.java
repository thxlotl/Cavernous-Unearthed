package net.thxlotl.cavernous.entity.client.fungalzombie;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class FungalZombieRenderState extends HumanoidRenderState {

    public Entity entity;
    public boolean isAggressive;

    //public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState tweakAnimationState = new AnimationState();
    public final AnimationState armRaiseAnimationState = new AnimationState();

}
