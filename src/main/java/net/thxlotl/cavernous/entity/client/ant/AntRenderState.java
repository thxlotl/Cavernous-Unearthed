package net.thxlotl.cavernous.entity.client.ant;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class AntRenderState extends LivingEntityRenderState {

    public Entity antEntity;
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState tweakAnimationState = new AnimationState();
}