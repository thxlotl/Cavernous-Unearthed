package net.thxlotl.cavernous.event;

import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.rendering.ObsidianstoneTint;
import net.thxlotl.cavernous.block.entity.ModBlockEntities;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.client.ant.AntModel;
import net.thxlotl.cavernous.entity.client.hangingshroomsporepod.HangingShroomSporePodModel;
import net.thxlotl.cavernous.entity.client.ant.InfectedAntModel;
import net.thxlotl.cavernous.entity.client.fungalzombie.FungalZombieModel;
import net.thxlotl.cavernous.entity.custom.Ant;
import net.thxlotl.cavernous.entity.custom.FungalZombie;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;
import net.thxlotl.cavernous.particle.*;
import net.thxlotl.cavernous.particle.custom.*;

@EventBusSubscriber(modid = Cavernous.MODID, value = Dist.CLIENT)
public class RegisterEvents {

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
        event.registerSpriteSet(ModParticles.FUNGAL_SPORE.get(), SporeParticle.Provider::new);
        event.registerSpriteSet(ModParticles.LAMPSHROOM_SPORE.get(), SporeParticle.LampshroomProvider::new);
        event.registerSpriteSet(ModParticles.UNDERGROUND_MYCELIUM.get(), UndergroundMyceliumParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GEYSER_AMBIENT.get(), GeyserAmbientParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GEYSER_BURST.get(), GeyserBurstParticle.BurstProvider::new);
        event.registerSpriteSet(ModParticles.GEYSER_BUBBLE.get(), GeyserBurstParticle.BubbleProvider::new);
        event.registerSpriteSet(ModParticles.VOLCANIC_ASH.get(), VolcanicAshParticle.Provider::new);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event)
    {
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_STAIRS.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_SLAB.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_WALL.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.POLISHED_OBSIDIANSTONE.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_BRICKS.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get());
        event.register(ObsidianstoneTint::getColor, ModBlocks.OBSIDIANSTONE_BRICK_WALL.get());
    }

}
