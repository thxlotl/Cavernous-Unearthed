package net.thxlotl.cavernous.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.thxlotl.cavernous.Cavernous;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Cavernous.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event)
    {
        registerProviders(event);
    }

    @SubscribeEvent
    public static void gatherServerData(GatherDataEvent.Server event)
    {
        registerProviders(event);
    }

    private static void registerProviders(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();



        // Loot table
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        // Recipes
        generator.addProvider(true, new ModRecipeProvider.Runner(packOutput, lookupProvider));

        // Tags
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(packOutput, lookupProvider, Cavernous.MODID);
        generator.addProvider(true, blockTagsProvider);
        generator.addProvider(true, new ModItemTagProvider(packOutput, lookupProvider, Cavernous.MODID));

        // Models, Block states, items
        generator.addProvider(true, new ModModelProvider(packOutput));

        // Data maps
        generator.addProvider(true, new ModDataMapProvider(packOutput, lookupProvider));

        // Datapack
        generator.addProvider(true, new ModDatapackProvider(packOutput, lookupProvider));
    }
}
