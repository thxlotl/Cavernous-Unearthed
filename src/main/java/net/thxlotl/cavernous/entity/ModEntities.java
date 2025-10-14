package net.thxlotl.cavernous.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.FungalZombie;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;
import net.thxlotl.cavernous.entity.custom.projectile.HangingShroomSporePodProjectileEntity;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Cavernous.MODID);



    public static final Supplier<EntityType<HangingShroomSporePodProjectileEntity>> HANGING_SHROOM_SPORE_POD =
            ENTITY_TYPES.register("hanging_shroom_spore_pod",
                    () -> EntityType.Builder.<HangingShroomSporePodProjectileEntity>of(HangingShroomSporePodProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5f, 0.5f)
                            //.clientTrackingRange(4)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "hanging_shroom_spore_pod"))));

    public static final Supplier<EntityType<Ant>> ANT =
            ENTITY_TYPES.register("ant",
                    () -> EntityType.Builder.<Ant>of(Ant::new, MobCategory.CREATURE)
                            .sized(0.6f, 0.5f)
                            //.clientTrackingRange(4)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "ant"))));

    public static final Supplier<EntityType<InfectedAnt>> INFECTED_ANT =
            ENTITY_TYPES.register("infected_ant",
                    () -> EntityType.Builder.<InfectedAnt>of(InfectedAnt::new, MobCategory.MONSTER)
                            .sized(0.6f, 0.5f)
                            //.clientTrackingRange(4)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "infected_ant"))));

    public static final Supplier<EntityType<FungalZombie>> FUNGAL_ZOMBIE =
            ENTITY_TYPES.register("fungal_zombie",
                    () -> EntityType.Builder.<FungalZombie>of(FungalZombie::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "fungal_zombie"))));




    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
