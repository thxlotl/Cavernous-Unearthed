package net.thxlotl.cavernous.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, Cavernous.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE_COAL_ORE.get())
                .add(ModBlocks.FUNGATITE_COPPER_ORE.get())
                .add(ModBlocks.FUNGATITE_IRON_ORE.get())
                .add(ModBlocks.FUNGATITE_REDSTONE_ORE.get())
                .add(ModBlocks.FUNGATITE_GOLD_ORE.get())
                .add(ModBlocks.FUNGATITE_LAPIS_ORE.get())
                .add(ModBlocks.FUNGATITE_DIAMOND_ORE.get())
                .add(ModBlocks.FUNGATITE_EMERALD_ORE.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get())
                .add(ModBlocks.FUNGATITE_STAIRS.get())
                .add(ModBlocks.FUNGATITE_SLAB.get())
                .add(ModBlocks.FUNGATITE_WALL.get())
                .add(ModBlocks.POLISHED_FUNGATITE.get())
                .add(ModBlocks.POLISHED_FUNGATITE_STAIRS.get())
                .add(ModBlocks.POLISHED_FUNGATITE_SLAB.get())
                .add(ModBlocks.POLISHED_FUNGATITE_WALL.get())
                .add(ModBlocks.CHISELED_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE_BRICKS.get())
                .add(ModBlocks.FUNGATITE_BRICK_STAIRS.get())
                .add(ModBlocks.FUNGATITE_BRICK_SLAB.get())
                .add(ModBlocks.FUNGATITE_BRICK_WALL.get())
                .add(ModBlocks.GEYSER_BLOCK.get())
                .add(ModBlocks.SOFT_MAGMA_BLOCK.get())
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.OBSIDIANSTONE_STAIRS.get())
                .add(ModBlocks.OBSIDIANSTONE_SLAB.get())
                .add(ModBlocks.OBSIDIANSTONE_WALL.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICKS.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_WALL.get())
                .add(ModBlocks.SCORIA.get())
                .add(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_IRON_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get())
                .add(ModBlocks.ERUPTITE_BLOCK.get())
                .add(ModBlocks.CUT_ERUPTITE_BLOCK.get())
                .add(ModBlocks.CUT_ERUPTITE_STAIRS.get())
                .add(ModBlocks.CUT_ERUPTITE_SLAB.get())
                .add(ModBlocks.CUT_ERUPTITE_WALL.get())
                .add(ModBlocks.ERUPTITE_BARS.get())
                .add(ModBlocks.ERUPTITE_GRATE.get())
                .add(ModBlocks.ERUPTITE_CHAIN.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get())
                .add(ModBlocks.FEATHER_MOSS_CARPET.get())
                .add(ModBlocks.TOADSTOOL_CAP_BLOCK.get())
                .add(ModBlocks.LAMPSHROOM_CAP_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SHROOMWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get())
                .add(ModBlocks.SHROOMWOOD.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.get())
                .add(ModBlocks.SHROOMWOOD_PLANKS.get())
                .add(ModBlocks.SHROOMWOOD_STAIRS.get())
                .add(ModBlocks.SHROOMWOOD_SLAB.get())
                .add(ModBlocks.SHROOMWOOD_FENCE.get())
                .add(ModBlocks.SHROOMWOOD_FENCE_GATE.get())
                .add(ModBlocks.SHROOMWOOD_DOOR.get())
                .add(ModBlocks.SHROOMWOOD_TRAPDOOR.get())
                .add(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get())
                .add(ModBlocks.SHROOMWOOD_BUTTON.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.FUNGATITE_IRON_ORE.get())
                .add(ModBlocks.FUNGATITE_LAPIS_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_IRON_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FUNGATITE_GOLD_ORE.get())
                .add(ModBlocks.FUNGATITE_REDSTONE_ORE.get())
                .add(ModBlocks.FUNGATITE_DIAMOND_ORE.get())
                .add(ModBlocks.FUNGATITE_EMERALD_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get());


        tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get())
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.SCORIA.get());
        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.SCORIA.get());
        
        tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.FLIPSHROOM.get())
                .add(ModBlocks.FLIPSHROOM_STEM.get())
                .add(ModBlocks.HANGING_FEATHER_MOSS.get())
                .add(ModBlocks.MYCELIUM_VINE_PLANT.get())
                .add(ModBlocks.MYCELIUM_VINE.get());

        tag(BlockTags.COAL_ORES)
                .add(ModBlocks.FUNGATITE_COAL_ORE.get());tag(BlockTags.COAL_ORES)
                .add(ModBlocks.FUNGATITE_COAL_ORE.get());
        tag(BlockTags.IRON_ORES)
                .add(ModBlocks.FUNGATITE_IRON_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_IRON_ORE.get());
        tag(BlockTags.COPPER_ORES)
                .add(ModBlocks.FUNGATITE_COPPER_ORE.get());
        tag(BlockTags.GOLD_ORES)
                .add(ModBlocks.FUNGATITE_GOLD_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get());
        tag(BlockTags.REDSTONE_ORES)
                .add(ModBlocks.FUNGATITE_REDSTONE_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get());
        tag(BlockTags.EMERALD_ORES)
                .add(ModBlocks.FUNGATITE_EMERALD_ORE.get());
        tag(BlockTags.LAPIS_ORES)
                .add(ModBlocks.FUNGATITE_LAPIS_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get());
        tag(BlockTags.DIAMOND_ORES)
                .add(ModBlocks.FUNGATITE_DIAMOND_ORE.get())
                .add(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get());


        tag(BlockTags.STAIRS)
                .add(ModBlocks.FUNGATITE_STAIRS.get())
                .add(ModBlocks.POLISHED_FUNGATITE_STAIRS.get())
                .add(ModBlocks.FUNGATITE_BRICK_STAIRS.get())
                .add(ModBlocks.SHROOMWOOD_STAIRS.get())
                .add(ModBlocks.OBSIDIANSTONE_STAIRS.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_STAIRS.get())
                .add(ModBlocks.CUT_ERUPTITE_STAIRS.get());
        tag(BlockTags.SLABS)
                .add(ModBlocks.FUNGATITE_SLAB.get())
                .add(ModBlocks.POLISHED_FUNGATITE_SLAB.get())
                .add(ModBlocks.FUNGATITE_BRICK_SLAB.get())
                .add(ModBlocks.SHROOMWOOD_SLAB.get())
                .add(ModBlocks.OBSIDIANSTONE_SLAB.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get())
                .add(ModBlocks.CUT_ERUPTITE_SLAB.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.FUNGATITE_WALL.get())
                .add(ModBlocks.POLISHED_FUNGATITE_WALL.get())
                .add(ModBlocks.FUNGATITE_BRICK_WALL.get())
                .add(ModBlocks.OBSIDIANSTONE_WALL.get())
                .add(ModBlocks.POLISHED_OBSIDIANSTONE_WALL.get())
                .add(ModBlocks.OBSIDIANSTONE_BRICK_WALL.get())
                .add(ModBlocks.CUT_ERUPTITE_WALL.get());
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.SHROOMWOOD_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.SHROOMWOOD_FENCE_GATE.get());
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.SHROOMWOOD_DOOR.get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.SHROOMWOOD_TRAPDOOR.get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.SHROOMWOOD_BUTTON.get());
        tag(BlockTags.BARS)
                .add(ModBlocks.ERUPTITE_BARS.get());

        tag(BlockTags.REPLACEABLE)
                .add(ModBlocks.FEATHER_MOSS_TUFTS.get())
                .add(ModBlocks.MYCELIUM_SPROUTS.get());
        tag(BlockTags.ENCHANTMENT_POWER_TRANSMITTER)
                .add(ModBlocks.FEATHER_MOSS_TUFTS.get())
                .add(ModBlocks.TOADSTOOL_PATCH.get())
                .add(ModBlocks.MYCELIUM_SPROUTS.get());

        tag(BlockTags.DIRT)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get());
        tag(BlockTags.LUSH_GROUND_REPLACEABLE)
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get());
        tag(BlockTags.MOSS_REPLACEABLE)
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get());
        tag(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT)
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get())
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE_COAL_ORE.get())
                .add(ModBlocks.FUNGATITE_COPPER_ORE.get())
                .add(ModBlocks.FUNGATITE_IRON_ORE.get())
                .add(ModBlocks.FUNGATITE_GOLD_ORE.get())
                .add(ModBlocks.FUNGATITE_REDSTONE_ORE.get())
                .add(ModBlocks.FUNGATITE_LAPIS_ORE.get())
                .add(ModBlocks.FUNGATITE_EMERALD_ORE.get())
                .add(ModBlocks.FUNGATITE_DIAMOND_ORE.get())
                .add(ModBlocks.FLIPSHROOM_CAP_BLOCK.get())
                .add(ModBlocks.SHELFSHROOM_CAP_BLOCK.get())
                .add(ModBlocks.TOADSTOOL_CAP_BLOCK.get())
                .add(ModBlocks.LAMPSHROOM_CAP_BLOCK.get())
                .addTag(ModTags.Blocks.SHROOMWOOD_LOGS);
        tag(BlockTags.LOGS)
                .add(ModBlocks.SHROOMWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get())
                .add(ModBlocks.SHROOMWOOD.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.get());
        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SHROOMWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get())
                .add(ModBlocks.SHROOMWOOD.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.get());
        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.TOADSTOOL_BUTTON.get());

        tag(BlockTags.SUPPORTS_BIG_DRIPLEAF)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get());

        tag(BlockTags.SUPPORTS_SMALL_DRIPLEAF)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get());

        tag(BlockTags.SNIFFER_DIGGABLE_BLOCK)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get());

        tag(BlockTags.SNIFFER_EGG_HATCH_BOOST)
                .add(ModBlocks.FEATHER_MOSS_BLOCK.get());

        tag(BlockTags.COMBINATION_STEP_SOUND_BLOCKS)
                .add(ModBlocks.FEATHER_MOSS_CARPET.get());

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_TOADSTOOL_BUTTON.get());

        tag(BlockTags.SIGNS)
                .add(ModBlocks.SHROOMWOOD_SIGN.get())
                .add(ModBlocks.SHROOMWOOD_WALL_SIGN.get());
        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.SHROOMWOOD_WALL_SIGN.get());
        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.SHROOMWOOD_SIGN.get());
        tag(BlockTags.WALL_POST_OVERRIDE)
                .add(ModBlocks.SHROOMWOOD_SIGN.get());
        tag(BlockTags.ALL_HANGING_SIGNS)
                .add(ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get())
                .add(ModBlocks.SHROOMWOOD_HANGING_SIGN.get());
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.SHROOMWOOD_HANGING_SIGN.get());
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get());


        // Mod Tags

        tag(ModTags.Blocks.SHROOMWOOD_LOGS)
                .add(ModBlocks.SHROOMWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get())
                .add(ModBlocks.SHROOMWOOD.get())
                .add(ModBlocks.STRIPPED_SHROOMWOOD.get());

        tag(ModTags.Blocks.UNDERGROUND_MYCELIUM_REPLACEABLE)
                .add(ModBlocks.FUNGATITE.get());

        tag(ModTags.Blocks.FUNGATITE_ORE_REPLACEABLE)
                .add(ModBlocks.GROUND_FUNGATITE.get())
                .add(ModBlocks.FUNGATITE.get())
                .add(ModBlocks.UNDERGROUND_MYCELIUM.get());

        tag(ModTags.Blocks.MYCELIUM_SPROUTS_PLACEABLE)
                .addTag(BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT);

        tag(ModTags.Blocks.HOT_BLOCKS)
                .add(Blocks.LAVA)
                .add(Blocks.MAGMA_BLOCK)
                .add(ModBlocks.SOFT_MAGMA_BLOCK.get());

        tag(ModTags.Blocks.MAGMA_PLANT_PLACEABLE)
                .add(Blocks.MAGMA_BLOCK)
                .addTag(BlockTags.DIRT)
                .add(ModBlocks.SOFT_MAGMA_BLOCK.get())
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.SCORIA.get());

        tag(ModTags.Blocks.GEYSER_PATCH_REPLACEABLE)
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.SCORIA.get());


        tag(ModTags.Blocks.OBSIDIANSTONE_ORE_REPLACEABLE)
                .add(ModBlocks.OBSIDIANSTONE.get())
                .add(ModBlocks.SCORIA.get());

    }
}
