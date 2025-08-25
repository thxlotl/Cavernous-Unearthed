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



    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
