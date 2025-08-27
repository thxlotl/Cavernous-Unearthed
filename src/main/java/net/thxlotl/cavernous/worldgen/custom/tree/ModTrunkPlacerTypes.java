package net.thxlotl.cavernous.worldgen.custom.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;

public class ModTrunkPlacerTypes extends TrunkPlacerType {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Cavernous.MODID);

    public ModTrunkPlacerTypes(MapCodec codec) {
        super(codec);
    }

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<ToadstoolTrunkPlacer>> TOADSTOOL_TRUNK_PLACER =
            TRUNK_PLACER.register("toadstool_trunk_placer", () -> new TrunkPlacerType<>(ToadstoolTrunkPlacer.CODEC));



    public static void register(IEventBus eventBus)
    {
        TRUNK_PLACER.register(eventBus);
    }
}
