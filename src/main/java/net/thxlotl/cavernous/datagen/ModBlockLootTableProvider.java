package net.thxlotl.cavernous.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.datagen.custom.BlockItemDropPair;
import net.thxlotl.cavernous.item.ModItems;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }
    private ResourceKey<LootTable> lootKeyFromBlock(Block block)
    {
        return block.getLootTable().orElseThrow();
    }

    //region Static Lists
    private static final List<Block> ignoredBlocks = List.of(
            ModBlocks.UNDERGROUND_MYCELIUM.get(),
            ModBlocks.FUNGATITE.get(),
            ModBlocks.GHOST_FUNGUS.get()
    );
    private static final List<DeferredBlock<? extends Block>> simpleDropBlocks = List.of(
            ModBlocks.GROUND_FUNGATITE,
            ModBlocks.FUNGATITE_STAIRS,
            ModBlocks.FUNGATITE_WALL,
            ModBlocks.POLISHED_FUNGATITE,
            ModBlocks.POLISHED_FUNGATITE_STAIRS,
            ModBlocks.POLISHED_FUNGATITE_WALL,
            ModBlocks.CHISELED_FUNGATITE,
            ModBlocks.FUNGATITE_BRICKS,
            ModBlocks.FUNGATITE_BRICK_STAIRS,
            ModBlocks.FUNGATITE_BRICK_WALL,
            ModBlocks.FEATHER_MOSS_BLOCK,
            ModBlocks.FEATHER_MOSS_CARPET,
            ModBlocks.TOADSTOOL_CAP_BLOCK,
            ModBlocks.TOADSTOOL_BUTTON,
            ModBlocks.FLIPSHROOM_CAP_BLOCK,
            ModBlocks.SHROOMWOOD_LOG,
            ModBlocks.STRIPPED_SHROOMWOOD_LOG,
            ModBlocks.SHROOMWOOD,
            ModBlocks.STRIPPED_SHROOMWOOD,
            ModBlocks.STRIPPED_SHROOMWOOD,
            ModBlocks.SHROOMWOOD_PLANKS,
            ModBlocks.SHROOMWOOD_STAIRS,
            ModBlocks.SHROOMWOOD_FENCE,
            ModBlocks.SHROOMWOOD_FENCE_GATE,
            ModBlocks.SHROOMWOOD_TRAPDOOR,
            ModBlocks.SHROOMWOOD_PRESSURE_PLATE,
            ModBlocks.SHROOMWOOD_BUTTON,
            ModBlocks.LAMPSHROOM,
            ModBlocks.LAMPSHROOM_CAP_BLOCK,
            ModBlocks.LAMPSHROOM_PATCH,
            ModBlocks.MYCELIUM_VINE,
            ModBlocks.SHELFSHROOM_CAP_BLOCK,
            ModBlocks.SHELFSHROOM,
            ModBlocks.BLUE_GHOST_FUNGUS,
            ModBlocks.LAMPSHROOM_TERRARIUM,
            ModBlocks.PUFFSHROOM,
            ModBlocks.INKY_CAP_PATCH,
            ModBlocks.CORDYCEPS_PATCH,
            ModBlocks.BLEEDING_TOOTH_MUSHROOM,
            ModBlocks.OBSIDIANSTONE,
            ModBlocks.OBSIDIANSTONE_STAIRS,
            ModBlocks.OBSIDIANSTONE_WALL,
            ModBlocks.POLISHED_OBSIDIANSTONE,
            ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS,
            ModBlocks.POLISHED_OBSIDIANSTONE_WALL,
            ModBlocks.POLISHED_OBSIDIANSTONE_PRESSURE_PLATE,
            ModBlocks.POLISHED_OBSIDIANSTONE_BUTTON,
            ModBlocks.OBSIDIANSTONE_BRICKS,
            ModBlocks.OBSIDIANSTONE_BRICK_STAIRS,
            ModBlocks.OBSIDIANSTONE_BRICK_WALL,
            ModBlocks.GEYSER_BLOCK,
            ModBlocks.SCORIA,
            ModBlocks.POLISHED_SCORIA,
            ModBlocks.ERUPTITE_BLOCK,
            ModBlocks.CUT_ERUPTITE_BLOCK,
            ModBlocks.CUT_ERUPTITE_STAIRS,
            ModBlocks.CUT_ERUPTITE_WALL,
            ModBlocks.ERUPTITE_BARS,
            ModBlocks.MAGMA_FERN,
            ModBlocks.ERUPTITE_GRATE,
            ModBlocks.ERUPTITE_CHAIN,
            ModBlocks.ERUPTITE_TRAPDOOR,
            ModBlocks.ERUPTITE_LAVA_LAMP,
            ModBlocks.CHISELED_ERUPTITE_BLOCK,
            ModBlocks.ERUPTITE_LANTERN,
            ModBlocks.ERUPTITE_PRESSURE_PLATE,
            ModBlocks.ERUPTITE_BUTTON
    );
    private static final List<DeferredBlock<? extends Block>> slabs = List.of(
            ModBlocks.FUNGATITE_SLAB,
            ModBlocks.POLISHED_FUNGATITE_SLAB,
            ModBlocks.FUNGATITE_BRICK_SLAB,
            ModBlocks.SHROOMWOOD_SLAB,
            ModBlocks.OBSIDIANSTONE_SLAB,
            ModBlocks.POLISHED_OBSIDIANSTONE_SLAB,
            ModBlocks.OBSIDIANSTONE_BRICK_SLAB,
            ModBlocks.CUT_ERUPTITE_SLAB
    );
    private static final List<DeferredBlock<? extends Block>> shearsOnly = List.of(
            ModBlocks.FEATHER_MOSS_TUFTS,
            ModBlocks.MYCELIUM_SPROUTS,
            ModBlocks.MYCELIUM_FERN,
            ModBlocks.TOADSTOOL_PATCH,
            ModBlocks.HANGING_FEATHER_MOSS
    );
    private static final List<DeferredBlock<? extends Block>> doors = List.of(
            ModBlocks.SHROOMWOOD_DOOR,
            ModBlocks.ERUPTITE_DOOR
    );
    private static final List<DeferredBlock<? extends Block>> silkTouch = List.of(
            ModBlocks.SOFT_MAGMA_BLOCK
    );
    private static final List<BlockItemDropPair> dropPairs = List.of(
            BlockItemDropPair.of(ModBlocks.FLIPSHROOM_STEM, ModBlocks.FLIPSHROOM),
            BlockItemDropPair.of(ModBlocks.FLIPSHROOM, ModBlocks.FLIPSHROOM),
            BlockItemDropPair.of(ModBlocks.SHROOMWOOD_SIGN, ModBlocks.SHROOMWOOD_SIGN),
            BlockItemDropPair.of(ModBlocks.SHROOMWOOD_WALL_SIGN, ModBlocks.SHROOMWOOD_SIGN),
            BlockItemDropPair.of(ModBlocks.SHROOMWOOD_HANGING_SIGN, ModBlocks.SHROOMWOOD_HANGING_SIGN),
            BlockItemDropPair.of(ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN, ModBlocks.SHROOMWOOD_HANGING_SIGN),
            BlockItemDropPair.of(ModBlocks.LAMPSHROOM_STEM, ModBlocks.LAMPSHROOM),
            BlockItemDropPair.of(ModBlocks.MYCELIUM_VINE_PLANT, ModBlocks.MYCELIUM_VINE)
    );
    private static final List<BlockItemDropPair> flowerPots = List.of(
            BlockItemDropPair.of(ModBlocks.POTTED_LAMPSHROOM, ModBlocks.LAMPSHROOM),
            BlockItemDropPair.of(ModBlocks.POTTED_TOADSTOOL_BUTTON, ModBlocks.TOADSTOOL_BUTTON)
    );
    //endregion

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {

        super.generate(consumer);

        consumer.accept(lootKeyFromBlock(ModBlocks.UNDERGROUND_MYCELIUM.get()), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(hasSilkTouch())
                        .add(LootItem.lootTableItem(ModBlocks.UNDERGROUND_MYCELIUM))
                )
                .withPool(LootPool.lootPool()
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(doesNotHaveSilkTouch())
                        .add(LootItem.lootTableItem(ModBlocks.GROUND_FUNGATITE))
                ));
        consumer.accept(lootKeyFromBlock(ModBlocks.FUNGATITE.get()), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(hasSilkTouch())
                        .add(LootItem.lootTableItem(ModBlocks.FUNGATITE))
                )
                .withPool(LootPool.lootPool()
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(doesNotHaveSilkTouch())
                        .add(LootItem.lootTableItem(ModBlocks.GROUND_FUNGATITE))
                ));

    }

    @Override
    protected void generate() {

        // Generate tables for all lists
        for (DeferredBlock<? extends Block> deferredBlock : simpleDropBlocks) {
            makeDropSelf(deferredBlock);
        }
        for (DeferredBlock<? extends Block> deferredBlock : slabs) {
            add(deferredBlock.get(), block -> createSlabItemTable(deferredBlock.get()));
        }
        for (DeferredBlock<? extends Block> deferredBlock : shearsOnly) {
            add(deferredBlock.get(), block -> createShearsOnlyDrop(deferredBlock.get()));
        }
        for (DeferredBlock<? extends Block> deferredBlock : doors) {
            add(deferredBlock.get(), block -> createDoorTable(deferredBlock.get()));
        }
        for (DeferredBlock<? extends Block> deferredBlock : silkTouch) {
            dropWhenSilkTouch(deferredBlock.get());
        }
        for (BlockItemDropPair pair : dropPairs) {
            add(pair.block.get(), block -> createSingleItemTable(pair.itemLike));
        }
        for (BlockItemDropPair pair : flowerPots) {
            add(pair.block.get(), block -> createPotFlowerItemTable(pair.itemLike));
        }

        //region Ore

        add(ModBlocks.FUNGATITE_COAL_ORE.get(),
                block -> createOreDrop(ModBlocks.FUNGATITE_COAL_ORE.get(), Items.COAL));
        add(ModBlocks.FUNGATITE_COPPER_ORE.get(),
                block -> createCopperOreDrops(ModBlocks.FUNGATITE_COPPER_ORE.get()));
        add(ModBlocks.FUNGATITE_IRON_ORE.get(),
                block -> createOreDrop(ModBlocks.FUNGATITE_IRON_ORE.get(), Items.RAW_IRON));
        add(ModBlocks.FUNGATITE_GOLD_ORE.get(),
                block -> createOreDrop(ModBlocks.FUNGATITE_GOLD_ORE.get(), Items.RAW_GOLD));
        add(ModBlocks.FUNGATITE_REDSTONE_ORE.get(),
                block -> createRedstoneOreDrops(ModBlocks.FUNGATITE_REDSTONE_ORE.get()));
        add(ModBlocks.FUNGATITE_LAPIS_ORE.get(),
                block -> createLapisOreDrops(ModBlocks.FUNGATITE_LAPIS_ORE.get()));
        add(ModBlocks.FUNGATITE_DIAMOND_ORE.get(),
                block -> createOreDrop(ModBlocks.FUNGATITE_DIAMOND_ORE.get(), Items.DIAMOND));
        add(ModBlocks.FUNGATITE_EMERALD_ORE.get(),
                block -> createOreDrop(ModBlocks.FUNGATITE_EMERALD_ORE.get(), Items.EMERALD));

        add(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get(),
                block -> createEruptiteOreDrop(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get()));
        add(ModBlocks.OBSIDIANSTONE_IRON_ORE.get(),
                block -> createOreDrop(ModBlocks.OBSIDIANSTONE_IRON_ORE.get(), Items.RAW_IRON));
        add(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get(),
                block -> createOreDrop(ModBlocks.OBSIDIANSTONE_GOLD_ORE.get(), Items.RAW_GOLD));
        add(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get(),
                block -> createRedstoneOreDrops(ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get()));
        add(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get(),
                block -> createLapisOreDrops(ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get()));
        add(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get(),
                block -> createOreDrop(ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get(), Items.DIAMOND));

        //endregion
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x -> !ignoredBlocks.contains(x.value())).map(Holder::value)::iterator;
    }

    private void makeDropSelf(DeferredBlock<?> block) {
        dropSelf(block.get());
    }

    protected LootTable.Builder createEruptiteOreDrop(Block block) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(ModItems.ERUPTITE).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
