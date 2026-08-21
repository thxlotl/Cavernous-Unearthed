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
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.thxlotl.cavernous.block.ModBlocks;
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

    private final List<Block> ignoredBlocks = List.of(
            ModBlocks.UNDERGROUND_MYCELIUM.get(),
            ModBlocks.FUNGATITE.get(),
            ModBlocks.GHOST_FUNGUS.get()
    );


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

        List<DeferredBlock<?>> simpleDropBlocks = List.of(
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
        for (DeferredBlock deferredBlock : simpleDropBlocks) {
            makeDropSelf(deferredBlock);
        }

        //dropSelf(ModBlocks.FUNGATITE.get());

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

        add(ModBlocks.FUNGATITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FUNGATITE_SLAB.get()));

        add(ModBlocks.POLISHED_FUNGATITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_FUNGATITE_SLAB.get()));

        add(ModBlocks.FUNGATITE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FUNGATITE_BRICK_SLAB.get()));


        add(ModBlocks.FEATHER_MOSS_TUFTS.get(),
                block -> createShearsOnlyDrop(ModBlocks.FEATHER_MOSS_TUFTS.get()));


        add(ModBlocks.MYCELIUM_SPROUTS.get(),
                block -> createShearsOnlyDrop(ModBlocks.MYCELIUM_SPROUTS.get()));
        add(ModBlocks.MYCELIUM_FERN.get(),
                block -> createShearsOnlyDrop(ModBlocks.MYCELIUM_FERN.get()));


        add(ModBlocks.TOADSTOOL_PATCH.get(),
                block -> createShearsOnlyDrop(ModBlocks.TOADSTOOL_CAP_BLOCK.get()));
        add(ModBlocks.POTTED_TOADSTOOL_BUTTON.get(),
                block -> createPotFlowerItemTable(ModBlocks.TOADSTOOL_BUTTON.asItem()));

        add(ModBlocks.FLIPSHROOM_STEM.get(), block -> createSingleItemTable(ModBlocks.FLIPSHROOM));
        add(ModBlocks.FLIPSHROOM.get(), block -> createSingleItemTable(ModBlocks.FLIPSHROOM));

        add(ModBlocks.SHROOMWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SHROOMWOOD_SLAB.get()));
        add(ModBlocks.SHROOMWOOD_DOOR.get(),
                block -> createDoorTable(ModBlocks.SHROOMWOOD_DOOR.get()));
        add(ModBlocks.SHROOMWOOD_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_SIGN));
        add(ModBlocks.SHROOMWOOD_WALL_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_SIGN));
        add(ModBlocks.SHROOMWOOD_HANGING_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_HANGING_SIGN));
        add(ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_HANGING_SIGN));


        add(ModBlocks.HANGING_FEATHER_MOSS.get(),
                block -> createShearsOnlyDrop(ModBlocks.HANGING_FEATHER_MOSS.get()));
        add(ModBlocks.LAMPSHROOM_STEM.get(), block -> createSingleItemTable(ModBlocks.LAMPSHROOM));
        add(ModBlocks.POTTED_LAMPSHROOM.get(),
                block -> createPotFlowerItemTable(ModBlocks.LAMPSHROOM.asItem()));


        add(ModBlocks.MYCELIUM_VINE_PLANT.get(), block -> createSingleItemTable(ModBlocks.MYCELIUM_VINE));




        //region Obsidianstone

        add(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get(),
                block -> createEruptiteOreDrop(ModBlocks.OBSIDIANSTONE_ERUPTITE_ORE.get()));
        add(ModBlocks.OBSIDIANSTONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OBSIDIANSTONE_SLAB.get()));
        add(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB.get()));
        add(ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OBSIDIANSTONE_BRICK_SLAB.get()));

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


        add(ModBlocks.CUT_ERUPTITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CUT_ERUPTITE_SLAB.get()));
        add(ModBlocks.ERUPTITE_DOOR.get(),
                block -> createDoorTable(ModBlocks.ERUPTITE_DOOR.get()));

        dropWhenSilkTouch(ModBlocks.SOFT_MAGMA_BLOCK.get());
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
