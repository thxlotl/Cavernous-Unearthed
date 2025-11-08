package net.thxlotl.cavernous.event;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.entity.ModBlockEntities;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.client.ant.AntModel;
import net.thxlotl.cavernous.entity.client.hangingshroomsporepod.HangingShroomSporePodModel;
import net.thxlotl.cavernous.entity.client.ant.InfectedAntModel;
import net.thxlotl.cavernous.entity.client.fungalzombie.FungalZombieModel;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.FungalZombie;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;
import net.thxlotl.cavernous.particle.ModParticles;
import net.thxlotl.cavernous.particle.SporeParticle;
import net.thxlotl.cavernous.particle.UndergroundMyceliumParticle;

@EventBusSubscriber(modid = Cavernous.MODID, value = Dist.CLIENT)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(HangingShroomSporePodModel.LAYER_LOCATION, HangingShroomSporePodModel::createBodyLayer);
        event.registerLayerDefinition(AntModel.LAYER_LOCATION, AntModel::createBodyLayer);
        event.registerLayerDefinition(InfectedAntModel.LAYER_LOCATION, InfectedAntModel::createBodyLayer);
        event.registerLayerDefinition(FungalZombieModel.LAYER_LOCATION, FungalZombieModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ANT.get(), Ant.createAttributes().build());
        event.put(ModEntities.INFECTED_ANT.get(), InfectedAnt.createAttributes().build());
        event.put(ModEntities.FUNGAL_ZOMBIE.get(), FungalZombie.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.PUFFSHROOM_SPORE.get(), SporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.UNDERGROUND_MYCELIUM.get(), UndergroundMyceliumParticle.Provider::new);
    }

}
