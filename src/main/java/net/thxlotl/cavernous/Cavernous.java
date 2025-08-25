package net.thxlotl.cavernous;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.block.entity.ModBlockEntities;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.entity.client.HangingShroomSporePodRenderer;
import net.thxlotl.cavernous.item.ModCreativeModeTabs;
import net.thxlotl.cavernous.item.ModItems;
import net.thxlotl.cavernous.util.ModWoodTypes;
import net.thxlotl.cavernous.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Cavernous.MODID)
public class Cavernous {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cavernous";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Cavernous(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (Cavernous) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register mod creative tab
        ModCreativeModeTabs.register(modEventBus);

        // Register mod items & blocks
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register Trunk Types
        ModTrunkPlacerTypes.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.TEST_ITEM);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        {
            event.accept(ModBlocks.FUNGATITE);
            event.accept(ModBlocks.TEST_BLOCK_ORE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            Sheets.addWoodType(ModWoodTypes.SHROOMWOOD);

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FEATHER_MOSS_TUFTS.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANCIENT_FERN.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.CLUSTER_SHROOM.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MYCELIUM_SPROUTS.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MYCELIUM_FERN.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GILLED_MUSHROOM.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAMPSHROOM.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MUSHVINE.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOADSTOOL_PATCH.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.TOADSTOOL_BUTTON.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HANGING_SHROOM_STEM.get(), ChunkSectionLayer.CUTOUT_MIPPED);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HANGING_SHROOM_CAP.get(), ChunkSectionLayer.CUTOUT_MIPPED);

            EntityRenderers.register(ModEntities.HANGING_SHROOM_SPORE_POD.get(), HangingShroomSporePodRenderer::new);
        }
    }
}
