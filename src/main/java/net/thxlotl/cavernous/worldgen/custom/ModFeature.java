package net.thxlotl.cavernous.worldgen.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.worldgen.custom.tree.ToadstoolTrunkPlacer;
import net.thxlotl.cavernous.worldgen.custom.wallface.WallFaceGrowthConfiguration;
import net.thxlotl.cavernous.worldgen.custom.wallface.WallFaceGrowthFeature;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomConfiguration;
import net.thxlotl.cavernous.worldgen.custom.wallshroom.WallShroomFeature;

import java.util.function.Supplier;

public class ModFeature {
    private ModFeature() {}

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, Cavernous.MODID);

    public static final DeferredHolder<Feature<?>, WallFaceGrowthFeature> WALLFACE_GROWTH =
            FEATURES.register("wallface_growth", () -> new WallFaceGrowthFeature(WallFaceGrowthConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, WallShroomFeature> WALLSHROOM_FEATURE =
            FEATURES.register("wallshroom_feature", () -> new WallShroomFeature(WallShroomConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}
