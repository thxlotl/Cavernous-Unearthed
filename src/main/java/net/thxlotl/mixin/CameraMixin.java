package net.thxlotl.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.player.ClientInput;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.gen.Accessor;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

//@Mixin(Camera.class)
public abstract class CameraMixin {

//    @Accessor("level")
//    abstract BlockGetter getLevel();

//    @Inject(
//            method = "getFluidInCamera",
//            at = @At(
//                    value = "INVOKE",
//                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z",
//                    shift = At.Shift.AFTER
//            ),
//            cancellable = true
//    )
//    private void addCustomCameraFog(CallbackInfoReturnable<FogType> cir) {
//
//        Camera camera = (Camera) (Object) this;
//        Camera.NearPlane camera$nearplane = camera.getNearPlane();
//
//        for(Vec3 vec3 : Arrays.asList(((NearPlaneAccessor) camera$nearplane).getForward(), camera$nearplane.getTopLeft(), camera$nearplane.getTopRight(), camera$nearplane.getBottomLeft(), camera$nearplane.getBottomRight())) {
//            Vec3 vec31 = camera.getPosition().add(vec3);
//            BlockPos blockpos = BlockPos.containing(vec31);
//
//            BlockState blockstate = this.getLevel().getBlockState(blockpos);
//            if (blockstate.is(ModBlocks.SOFT_MAGMA_BLOCK)) {
//                cir.setReturnValue(FogType.LAVA);
//            }
//        }
//    }
}
