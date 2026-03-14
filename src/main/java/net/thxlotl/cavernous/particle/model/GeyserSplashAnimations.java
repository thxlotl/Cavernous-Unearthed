package net.thxlotl.cavernous.particle.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class GeyserSplashAnimations {
    public static final AnimationDefinition burst = AnimationDefinition.Builder.withLength(0.8333F)
            .addAnimation("Inner", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.0278F, KeyframeAnimations.scaleVec(0.1429F, 0.3286F, 0.1429F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1389F, KeyframeAnimations.scaleVec(0.6F, 1.2072F, 0.6F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3056F, KeyframeAnimations.scaleVec(0.8F, 1.5F, 0.8F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4167F, KeyframeAnimations.scaleVec(1.0F, 1.4F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.2F, 1.2333F, 1.2F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.6111F, KeyframeAnimations.scaleVec(1.5F, 0.9F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.6944F, KeyframeAnimations.scaleVec(1.7F, 0.6F, 1.7F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.7778F, KeyframeAnimations.scaleVec(1.9F, 0.0F, 1.9F), AnimationChannel.Interpolations.LINEAR)
            ))
            .addAnimation("outer", new AnimationChannel(AnimationChannel.Targets.SCALE,
                    new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.0278F, KeyframeAnimations.scaleVec(0.1429F, 0.3286F, 0.1429F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.1389F, KeyframeAnimations.scaleVec(0.6F, 1.2072F, 0.6F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.3333F, KeyframeAnimations.scaleVec(0.9F, 1.5F, 0.9F), AnimationChannel.Interpolations.CATMULLROM),
                    new Keyframe(0.4444F, KeyframeAnimations.scaleVec(1.0F, 1.4F, 1.0F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.5556F, KeyframeAnimations.scaleVec(1.2F, 1.2333F, 1.2F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.6667F, KeyframeAnimations.scaleVec(1.5F, 0.9F, 1.5F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.75F, KeyframeAnimations.scaleVec(1.7F, 0.6F, 1.7F), AnimationChannel.Interpolations.LINEAR),
                    new Keyframe(0.8333F, KeyframeAnimations.scaleVec(1.9F, 0.0F, 1.9F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();
}
