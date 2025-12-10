package net.thxlotl.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.player.ClientInput;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Camera.NearPlane.class)
public interface NearPlaneAccessor {

    @Accessor("forward")
    Vec3 getForward();
}
