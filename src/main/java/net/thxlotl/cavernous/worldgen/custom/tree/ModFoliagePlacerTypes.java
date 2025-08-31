package net.thxlotl.cavernous.worldgen.custom.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;

public class ModFoliagePlacerTypes extends FoliagePlacerType {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Cavernous.MODID);

    public ModFoliagePlacerTypes(MapCodec codec) {
        super(codec);
    }

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<MushroomCapFoliagePlacer>> MUSHROOM_CAP_FOLIAGE_PLACER =
            FOLIAGE_PLACER.register("mushroom_cap_foliage_placer", () -> new FoliagePlacerType<>(MushroomCapFoliagePlacer.CODEC));



    public static void register(IEventBus eventBus)
    {
        FOLIAGE_PLACER.register(eventBus);
    }
}
