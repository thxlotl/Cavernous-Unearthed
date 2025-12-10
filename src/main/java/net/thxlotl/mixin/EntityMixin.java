package net.thxlotl.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.extensions.IEntityExtension;
import net.neoforged.neoforge.fluids.FluidType;
import net.thxlotl.cavernous.block.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiPredicate;

@Mixin(Entity.class)
public abstract class EntityMixin implements IEntityExtension {

    @Inject(method = "isInLava", at = @At("RETURN"), cancellable = true)
    private void beforeReturn_isInLava(CallbackInfoReturnable<Boolean> cir) {

        Entity entity = (Entity)(Object)this;

        if (this.isInSmoothMagma(entity)) {
            cir.setReturnValue(true);
        }
    }

//    @Inject(
//            method = "isInFluidType",
//            at = @At("HEAD"),
//            cancellable = true
//    )
//    private void onIsInFluidType(BiPredicate<FluidType, Double> predicate, boolean bool, CallbackInfoReturnable<Boolean> cir) {
//
//        Entity entity = (Entity)(Object)this;
//
//        if (isInSmoothMagma(entity)) {
//            cir.setReturnValue(true); //Fluids.LAVA.getFluidType()
//        }
//
//    }

    public boolean isInSmoothMagma(Entity entity) {

        AABB aabb = entity.getBoundingBox();

        return BlockPos.betweenClosedStream(aabb).anyMatch((pos) -> {
            BlockState blockstate = entity.level().getBlockState(pos);
            return blockstate.is(ModBlocks.SOFT_MAGMA_BLOCK);
        });

    }
}
