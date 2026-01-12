//package net.thxlotl.mixin;
//
//import net.minecraft.world.level.biome.Climate;
//import net.minecraft.world.level.dimension.DimensionType;
//import net.minecraft.world.level.levelgen.Aquifer;
//import net.minecraft.world.level.levelgen.DensityFunction;
//import net.thxlotl.cavernous.Cavernous;
//import net.thxlotl.cavernous.worldgen.WorldgenUtil;
//import net.thxlotl.cavernous.worldgen.biome.ClimateParameters;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
//
//@Mixin(Aquifer.NoiseBasedAquifer.class)
//public class NoiseBasedAquiferMixin {
//
//    @Shadow @Final private DensityFunction erosion;
//
//    @Shadow @Final private DensityFunction depth;
//
//    @Inject(
//            method = "computeSurfaceLevel", // method name
//            at = @At("RETURN"), //where to put
//            cancellable = true, // can cancel the return value
//            locals = LocalCapture.CAPTURE_FAILSOFT
//    )
//    private void cancelAquiferLiquidPlacement(int x, int y, int z, Aquifer.FluidStatus fluidStatus, int maxSurfaceLevel, boolean fluidPresent, CallbackInfoReturnable<Integer> cir, DensityFunction.SinglePointContext densityfunction$singlepointcontext, double d0, double d1, int k) {
//
//        Cavernous.LOGGER.info("This message is being displayed");
//        if (inBiomeByParameterPoint(ClimateParameters.volcanicParameters, this.erosion, this.depth, densityfunction$singlepointcontext)) cir.setReturnValue(DimensionType.WAY_BELOW_MIN_Y);
//
//    }
//
//    private boolean inBiomeByParameterPoint(Climate.ParameterPoint point, DensityFunction erosionFunction, DensityFunction depthFunction, DensityFunction.FunctionContext functionContext) {
//
//        //return erosionFunction.compute(functionContext) < (double)-0.225F && depthFunction.compute(functionContext) > (double)0.9F;
//
//        float buffer = 0.07f; //0.07
//        return WorldgenUtil.inParameterRange(erosionFunction.compute(functionContext), point.erosion(), buffer) && WorldgenUtil.inParameterRange(depthFunction.compute(functionContext), point.depth(), buffer);
//
//    }
//}
