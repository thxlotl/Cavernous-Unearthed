package net.thxlotl.cavernous.worldgen.features.pairs;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.thxlotl.cavernous.worldgen.features.ModPlacedFeatures;

import java.util.List;

/*

smart to implement smth like this but I am going to wait until later LOL have fun future me 🖕

 */
public class FeatureBootstrapperUtil {

    public static void RegisterFeaturePair(BootstrapContext<PlacedFeature> context, FeaturePair pair, List<PlacementModifier> modifiers) {

        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        ModPlacedFeatures.register(context, pair.placedKey, configuredFeatures.getOrThrow(pair.configuredKey), modifiers);

    }

}
