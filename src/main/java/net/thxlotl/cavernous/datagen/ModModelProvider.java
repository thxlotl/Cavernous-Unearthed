package net.thxlotl.cavernous.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Holder;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.datagen.custom.ModBlockFamilies;
import net.thxlotl.cavernous.datagen.custom.ModBlockModelGenerators;
import net.thxlotl.cavernous.datagen.custom.ModItemModelGenerators;
import net.thxlotl.cavernous.item.ModItems;

import java.util.List;
import java.util.stream.Stream;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Cavernous.MODID);
    }

    private final List<Block> ignoredBlocks = List.of(
            ModBlocks.FEATHER_MOSS_CARPET.get(),
            ModBlocks.TOADSTOOL_PATCH.get(),
            ModBlocks.TOADSTOOL_BUTTON.get(),
            ModBlocks.POTTED_TOADSTOOL_BUTTON.get(),
            ///ModBlocks.MYCELIUM_FERN.get(),
            ModBlocks.LAMPSHROOM.get(),
            ModBlocks.LAMPSHROOM_STEM.get(),
            ///ModBlocks.HANGING_FEATHER_MOSS.get(),
            ModBlocks.BLEEDING_TOOTH_MUSHROOM.get(),
            ModBlocks.FLIPSHROOM_STEM.get(),
            ModBlocks.FLIPSHROOM.get(),
            ModBlocks.GHOST_FUNGUS.get(),
            ModBlocks.CORDYCEPS_PATCH.get(),
            ModBlocks.POTTED_LAMPSHROOM.get(),
            ModBlocks.LAMPSHROOM_TERRARIUM.get(),
            ModBlocks.SHELFSHROOM.get(),
            ///ModBlocks.SPRINGSHROOM.get(),
            ModBlocks.PUFFSHROOM.get(),
            ModBlocks.LAMPSHROOM_PATCH.get(),
            ModBlocks.BLUE_GHOST_FUNGUS.get(),
            ///ModBlocks.FEATHER_MOSS_TUFTS.get(),

            ModBlocks.OBSIDIANSTONE.get(),
            ModBlocks.OBSIDIANSTONE_STAIRS.get(),
            ModBlocks.OBSIDIANSTONE_SLAB.get(),
            ModBlocks.OBSIDIANSTONE_WALL.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE_BUTTON.get(),
            ModBlocks.POLISHED_OBSIDIANSTONE_PRESSURE_PLATE.get(),
            ModBlocks.OBSIDIANSTONE_BRICKS.get(),
            ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get(),
            ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get(),
            ModBlocks.OBSIDIANSTONE_BRICK_WALL.get(),
            ModBlocks.GEYSER_BLOCK.get(),
            ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get(),
            ModBlocks.OBSIDIANSTONE_IRON_ORE.get(),
            ModBlocks.OBSIDIANSTONE_GOLD_ORE.get(),
            ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get(),
            ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get(),
            ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get(),
            ModBlocks.ERUPTITE_GRATE.get(),
            ModBlocks.ERUPTITE_LAVA_LAMP.get()
    );
    private final List<Item> ignoredItems = List.of(
            ModItems.HANGING_SHROOM_SPORE_POD.get(),
            ModBlocks.OBSIDIANSTONE.asItem(),
            ModBlocks.OBSIDIANSTONE_STAIRS.asItem(),
            ModBlocks.OBSIDIANSTONE_SLAB.asItem(),
            ModBlocks.OBSIDIANSTONE_WALL.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE_WALL.asItem(),
            ModBlocks.OBSIDIANSTONE_BRICKS.asItem(),
            ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.asItem(),
            ModBlocks.OBSIDIANSTONE_BRICK_SLAB.asItem(),
            ModBlocks.OBSIDIANSTONE_BRICK_WALL.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE_PRESSURE_PLATE.asItem(),
            ModBlocks.POLISHED_OBSIDIANSTONE_BUTTON.asItem(),
            ModBlocks.GEYSER_BLOCK.asItem(),
            ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.asItem(),
            ModBlocks.OBSIDIANSTONE_IRON_ORE.asItem(),
            ModBlocks.OBSIDIANSTONE_GOLD_ORE.asItem(),
            ModBlocks.OBSIDIANSTONE_LAPIS_ORE.asItem(),
            ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.asItem(),
            ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.asItem()
    );


    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        ModBlockModelGenerators modBlockModels = new ModBlockModelGenerators(blockModels.blockStateOutput, blockModels.itemModelOutput, blockModels.modelOutput);
        ModItemModelGenerators modItemModels = new ModItemModelGenerators(itemModels.itemModelOutput, itemModels.modelOutput);

        // ITEMS
        itemModels.generateFlatItem(ModItems.ERUPTITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.ERUPTITE_CHAIN.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ERUPTITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ERUPTITE_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.TOADSTOOL_PATCH.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.MYCELIUM_FERN.asItem(), ModelTemplates.FLAT_ITEM);
        modItemModels.generateFlatItemWithBlockTexture(ModBlocks.BLUE_GHOST_FUNGUS.get(), ModelTemplates.FLAT_ITEM);
        ///modItemModels.generateFlatItemWithBlockTexture(ModBlocks.HANGING_FEATHER_MOSS.get(), ModelTemplates.FLAT_ITEM);
        ///modItemModels.generateFlatItemWithBlockTexture(ModBlocks.FEATHER_MOSS_TUFTS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ANT_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.INFECTED_ANT_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FUNGAL_ZOMBIE_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.CORDYCEPS_PATCH.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.LAMPSHROOM_PATCH.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.GHOST_FUNGUS.asItem(), ModelTemplates.FLAT_ITEM);
        modItemModels.generateFlatItemWithBlockTexture(ModBlocks.MYCELIUM_VINE.get(), ModelTemplates.FLAT_ITEM);

        // BLOCKS ------------------------------------------------------------------------------------------------------

        // FUNGATITE
        blockModels.createRotatedMirroredVariantBlock(ModBlocks.FUNGATITE.get());
        blockModels.familyWithExistingFullBlock(ModBlocks.FUNGATITE.get())
                .stairs(ModBlocks.FUNGATITE_STAIRS.get())
                .slab(ModBlocks.FUNGATITE_SLAB.get())
                .wall(ModBlocks.FUNGATITE_WALL.get());
        blockModels.createTrivialCube(ModBlocks.GROUND_FUNGATITE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_COAL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_IRON_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_COPPER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_GOLD_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_REDSTONE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_EMERALD_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_LAPIS_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FUNGATITE_DIAMOND_ORE.get());
        blockModels.family(ModBlocks.POLISHED_FUNGATITE.get())
                .stairs(ModBlocks.POLISHED_FUNGATITE_STAIRS.get())
                .slab(ModBlocks.POLISHED_FUNGATITE_SLAB.get())
                .wall(ModBlocks.POLISHED_FUNGATITE_WALL.get());
        blockModels.createTrivialCube(ModBlocks.CHISELED_FUNGATITE.get());
        blockModels.family(ModBlocks.FUNGATITE_BRICKS.get())
                .stairs(ModBlocks.FUNGATITE_BRICK_STAIRS.get())
                .slab(ModBlocks.FUNGATITE_BRICK_SLAB.get())
                .wall(ModBlocks.FUNGATITE_BRICK_WALL.get());

        // FEATHER MOSS
        blockModels.createTrivialCube(ModBlocks.FEATHER_MOSS_BLOCK.get());
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.FEATHER_MOSS_TUFTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        modBlockModels.createVine(ModBlocks.HANGING_FEATHER_MOSS.get(), false);


        // SHROOMWOOD
        blockModels.woodProvider(ModBlocks.SHROOMWOOD_LOG.get()).wood(ModBlocks.SHROOMWOOD.get()).logWithHorizontal(ModBlocks.SHROOMWOOD_LOG.get());

        blockModels.woodProvider(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get()).wood(ModBlocks.STRIPPED_SHROOMWOOD.get());

        blockModels.createHangingSign(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get(), ModBlocks.SHROOMWOOD_HANGING_SIGN.get(), ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get());



        // UNDERGROUND MYCELIUM
        TexturedModel.Provider undergroundMycelium = TexturedModel.CUBE_TOP_BOTTOM.updateTexture(mapping ->
                mapping.put(TextureSlot.SIDE, getBlockTexture(ModBlocks.UNDERGROUND_MYCELIUM.get(), "_side"))
                        .put(TextureSlot.BOTTOM, getBlockTexture(ModBlocks.FUNGATITE.get()))
                        .put(TextureSlot.TOP, getBlockTexture(ModBlocks.UNDERGROUND_MYCELIUM.get(), "_top"))
        );
        modBlockModels.createBlockWithRandomRotations(undergroundMycelium, ModBlocks.UNDERGROUND_MYCELIUM.get());

        blockModels.createCrossBlockWithDefaultItem(ModBlocks.MYCELIUM_SPROUTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.MYCELIUM_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createGrowingPlant(ModBlocks.MYCELIUM_VINE_PLANT.get(), ModBlocks.MYCELIUM_VINE.get(), BlockModelGenerators.PlantType.NOT_TINTED);



        blockModels.createTrivialCube(ModBlocks.LAMPSHROOM_CAP_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.TOADSTOOL_CAP_BLOCK.get());

        blockModels.createTrivialCube(ModBlocks.SHELFSHROOM_CAP_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.FLIPSHROOM_CAP_BLOCK.get());

        ///blockModels.createCrossBlockWithDefaultItem(ModBlocks.BLUE_GHOST_FUNGUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);



        blockModels.createCrossBlockWithDefaultItem(ModBlocks.INKY_CAP_PATCH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        //modBlockModels.createVine(ModBlocks.MUSHVINE.get(), false);

        //Volcanic Caves

        //region Obsidianstone

        /// what

//        blockModels.family(ModBlocks.OBSIDIANSTONE.get())
//                .stairs(ModBlocks.OBSIDIANSTONE_STAIRS.get())
//                .slab(ModBlocks.OBSIDIANSTONE_SLAB.get())
////                .wall(ModBlocks.OBSIDIANSTONE_WALL.get());
//        blockModels.family(ModBlocks.POLISHED_OBSIDIANSTONE.get())
//                .stairs(ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get())
//                .slab(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get())
//                .wall(ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get())
//                .pressurePlate(ModBlocks.POLISHED_OBSIDIANSTONE_PRESSURE_PLATE.get())
//                .button(ModBlocks.POLISHED_OBSIDIANSTONE_BUTTON.get());
//        blockModels.family(ModBlocks.OBSIDIANSTONE_BRICKS.get())
//                .stairs(ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get())
//                .slab(ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get())
//                .wall(ModBlocks.OBSIDIANSTONE_BRICK_WALL.get());


//        blockModels.createTrivialCube(ModBlocks.OBSIDIANSTONE_IRON_ORE.get());
//        blockModels.createTrivialCube(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get());
//        blockModels.createTrivialCube(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get());
//        blockModels.createTrivialCube(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get());
//        blockModels.createTrivialCube(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get());

        //endregion

        blockModels.createTrivialCube(ModBlocks.SCORIA.get());
        blockModels.createTrivialCube(ModBlocks.POLISHED_SCORIA.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.SOFT_MAGMA_BLOCK.get());
        blockModels.createCrossBlockWithDefaultItem(ModBlocks.MAGMA_FERN.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        //blockModels.createTrivialCube(ModBlocks.ERUPTITE_BLOCK.get()); /// Covered in the block family section
        //blockModels.createTrivialCube(ModBlocks.ERUPTITE_GRATE.get()); /// Custom model for backface
        blockModels.family(ModBlocks.CUT_ERUPTITE_BLOCK.get())
                .stairs(ModBlocks.CUT_ERUPTITE_STAIRS.get())
                .slab(ModBlocks.CUT_ERUPTITE_SLAB.get())
                .wall(ModBlocks.CUT_ERUPTITE_WALL.get());
        blockModels.createBarsAndItem(ModBlocks.ERUPTITE_BARS.get());
        blockModels.createAxisAlignedPillarBlockCustomModel(ModBlocks.ERUPTITE_CHAIN.get(), blockModels.plainVariant(TexturedModel.CHAIN.create(ModBlocks.ERUPTITE_CHAIN.get(), blockModels.modelOutput)));


        // Create all block family models
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(blockFamily -> blockModels.family(blockFamily.getBaseBlock()).generateFor(blockFamily));
    }



    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x -> !ignoredBlocks.contains(x.value()));
    }
    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(x -> !ignoredItems.contains(x.value()));
    }
}
