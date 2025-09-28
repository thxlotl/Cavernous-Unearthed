package net.thxlotl.cavernous.effect;

import net.minecraft.client.player.ClientInput;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;

public class MushyMindEffect extends MobEffect {
    public MushyMindEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    // The actual effect logic
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {

        //entity.travel();

        if (entity instanceof Ant) {

        }
        else if (entity instanceof InfectedAnt) {

        }
        else if (entity instanceof Player) {

        }
        else {
            int second = Math.round(Math.round((float) entity.tickCount / (float) 20));
            RandomSource random = RandomSource.create(second);
            random.nextInt();

            float forward = random.nextInt(3) - 1;
            float strafe = random.nextInt(3) - 1;

            entity.travel(new Vec3(strafe * 0.1f, 0, forward * 0.1f));
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
