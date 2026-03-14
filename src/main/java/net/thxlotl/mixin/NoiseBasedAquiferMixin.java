//package net.thxlotl.mixin;
//
//import net.minecraft.world.level.biome.Climate;
//import net.minecraft.world.level.dimension.DimensionType;
//import net.minecraft.world.level.levelgen.Aquifer;
//import net.minecraft.world.level.levelgen.DensityFunction;
//import net.thxlotl.cavernous.util.worldgen.BiomeUtil;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.ModifyVariable;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(Aquifer.NoiseBasedAquifer.class)
//public class NoiseBasedAquiferMixin {
//
//    @Shadow @Final private DensityFunction erosion;
//
//    @Shadow @Final private DensityFunction depth;
//
////    @Inject(
////            method = "computeSubstance", // method name
////            at = @At("HEAD"), //where to put
////            cancellable = true // can cancel the return value
////    )
////    private void computeSubstance(DensityFunction.FunctionContext context, double someNumber, CallbackInfoReturnable<BlockState> cir) {
////
////        if (inBiomeByParameterPoint(ClimateParameters.volcanicParameters, this.erosion, this.depth, context)) cir.setReturnValue(Blocks.RED_CONCRETE.defaultBlockState());
////
////    }
//
//    @Inject(
//            method = "computeSurfaceLevel", // method name
//            at = @At("HEAD"), //where to put,
//            cancellable = true
//    )
//    private void computeSurfaceLevel(int x, int y, int z, Aquifer.FluidStatus fluidStatus, int maxSurfaceLevel, boolean fluidPresent, CallbackInfoReturnable<Integer> cir) {
//
//        DensityFunction.SinglePointContext densityfunction$singlepointcontext = new DensityFunction.SinglePointContext(x, y, z);
//
//        if (!BiomeUtil.isVolcanicRegion(this.erosion, this.depth, densityfunction$singlepointcontext)) {
//            return;
//        }
//
//        int k = DimensionType.WAY_BELOW_MIN_Y;
//        cir.setReturnValue(k);
//    }
//
//    private boolean inBiomeByParameterPoint(Climate.ParameterPoint point, DensityFunction erosionFunction, DensityFunction depthFunction, DensityFunction.FunctionContext functionContext) {
//
//        //return erosionFunction.compute(functionContext) < (double)-0.225F && depthFunction.compute(functionContext) > (double)0.9F;
//
//        float buffer = 0.07f; //0.07
//        return BiomeUtil.inParameterRange(erosionFunction.compute(functionContext), point.erosion(), buffer) && BiomeUtil.inParameterRange(depthFunction.compute(functionContext), point.depth(), buffer);
//
//    }
//}
