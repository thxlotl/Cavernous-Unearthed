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
    public static final Supplier<SimpleParticleType> GEYSER_AMBIENT = PARTICLE_TYPES.register(
            "geyser_ambient",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_BURST = PARTICLE_TYPES.register(
            "geyser_burst",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_BUBBLE = PARTICLE_TYPES.register(
            "geyser_bubble",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> VOLCANIC_ASH = PARTICLE_TYPES.register(
            "volcanic_ash",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> FUNGAL_SPORE = PARTICLE_TYPES.register(
            "fungal_spore",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> LAMPSHROOM_SPORE = PARTICLE_TYPES.register(
            "lampshroom_spore",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_STEAM = PARTICLE_TYPES.register(
            "geyser_steam",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_STEAM_1 = PARTICLE_TYPES.register(
            "geyser_steam_1",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_STEAM_2 = PARTICLE_TYPES.register(
            "geyser_steam_2",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_STEAM_3 = PARTICLE_TYPES.register(
            "geyser_steam_3",
            () -> new SimpleParticleType(false)
    );
    public static final Supplier<SimpleParticleType> GEYSER_STEAM_4 = PARTICLE_TYPES.register(
            "geyser_steam_4",
            () -> new SimpleParticleType(false)
    );



    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
