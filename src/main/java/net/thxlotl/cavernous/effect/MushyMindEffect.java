package net.thxlotl.cavernous.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class MushyMindEffect extends MobEffect {
    public MushyMindEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // The actual effect logic
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {

        entity.setDeltaMovement(new Vec3(0.2D, 0.2D, 0.2D));

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
