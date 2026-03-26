package net.thxlotl.cavernous.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, Cavernous.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.FUNGATITE.asItem());

        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.FUNGATITE.asItem());

        tag(ItemTags.LOGS)
                .add(ModBlocks.SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.SHROOMWOOD.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.SHROOMWOOD.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.asItem());
        tag(ItemTags.PLANKS)
                .add(ModBlocks.SHROOMWOOD_PLANKS.asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.SHROOMWOOD_STAIRS.asItem());
        tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.SHROOMWOOD_SLAB.asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.SHROOMWOOD_FENCE.asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.SHROOMWOOD_DOOR.asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.SHROOMWOOD_TRAPDOOR.asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.SHROOMWOOD_BUTTON.asItem());

        tag(ItemTags.SIGNS)
                .add(ModBlocks.SHROOMWOOD_SIGN.asItem());
        tag(ItemTags.HANGING_SIGNS)
                .add(ModBlocks.SHROOMWOOD_HANGING_SIGN.asItem());

        tag(ItemTags.SAPLINGS)
                .add(ModBlocks.TOADSTOOL_BUTTON.asItem());


        // CAVERNOUS TAGS
        tag(ModTags.Items.SHROOMWOOD_LOGS)
                .add(ModBlocks.SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.asItem())
                .add(ModBlocks.SHROOMWOOD.asItem())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.asItem());

        tag(ModTags.Items.ANT_FOOD)
                .add(ModBlocks.LAMPSHROOM.asItem())
                .add(ModBlocks.BLEEDING_TOOTH_MUSHROOM.asItem());

        tag(ModTags.Items.SMOOTH_MAGMA_WALKABLE)
                .add(Items.NETHERITE_BOOTS);

    }
}
