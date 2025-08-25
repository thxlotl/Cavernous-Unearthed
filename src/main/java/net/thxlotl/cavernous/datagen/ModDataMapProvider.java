package net.thxlotl.cavernous.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.getId(), new Compostable(0.65f), true)
                .add(ModBlocks.FEATHER_MOSS_CARPET.getId(), new Compostable(0.30f), true)
                .add(ModBlocks.FEATHER_MOSS_TUFTS.getId(), new Compostable(0.30f), true)
                .add(ModBlocks.MYCELIUM_SPROUTS.getId(), new Compostable(0.50f), true)
                .add(ModBlocks.MYCELIUM_FERN.getId(), new Compostable(0.65f), true)
                .add(ModBlocks.TOADSTOOL_CAP_BLOCK.getId(), new Compostable(0.65f), true)
                .add(ModBlocks.TOADSTOOL_BUTTON.getId(), new Compostable(0.65f), true)
                .add(ModBlocks.TOADSTOOL_PATCH.getId(), new Compostable(0.50f), true);


        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(ModBlocks.SHROOMWOOD_LOG.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.STRIPPED_SHROOMWOOD.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_PLANKS.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_STAIRS.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_SLAB.getId(), new FurnaceFuel(150), true)
                .add(ModBlocks.SHROOMWOOD_FENCE.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_FENCE_GATE.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_DOOR.getId(), new FurnaceFuel(200), true)
                .add(ModBlocks.SHROOMWOOD_TRAPDOOR.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.getId(), new FurnaceFuel(300), true)
                .add(ModBlocks.SHROOMWOOD_BUTTON.getId(), new FurnaceFuel(100), true);
    }
}
