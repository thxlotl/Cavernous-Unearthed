package net.thxlotl.cavernous.worldgen.custom.tree;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;

public class ModTreeDecoratorType<P extends TreeDecorator> {

    private final MapCodec<P> codec;

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Cavernous.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<LeaveCustomVineDecorator>> CUSTOM_LEAVE_VINE =
            TREE_DECORATOR.register("custom_leave_vine", () -> new TreeDecoratorType<>(LeaveCustomVineDecorator.CODEC));



    public static void register(IEventBus eventBus)
    {
        TREE_DECORATOR.register(eventBus);
    }


    public ModTreeDecoratorType(MapCodec<P> codec) {
        this.codec = codec;
    }

    public MapCodec<P> codec() {
        return this.codec;
    }
}
