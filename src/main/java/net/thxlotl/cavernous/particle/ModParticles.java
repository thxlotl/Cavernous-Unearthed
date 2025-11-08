package net.thxlotl.cavernous.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Cavernous.MODID);


    public static final Supplier<SimpleParticleType> PUFFSHROOM_SPORE = PARTICLE_TYPES.register(
            "puffshroom_spore",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> UNDERGROUND_MYCELIUM = PARTICLE_TYPES.register(
            "underground_mycelium",
            () -> new SimpleParticleType(false)
    );


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
