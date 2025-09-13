package net.thxlotl.cavernous.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.WeatherCheck;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlockStateProperties;
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
            ModBlocks.GHOST_FUNGUS.get()
    );

//
//    @Override
//    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
//
//        consumer.accept(lootKeyFromBlock(ModBlocks.UNDERGROUND_MYCELIUM.get()), LootTable.lootTable()
//                .withPool(LootPool.lootPool()
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
//                        .when(hasSilkTouch())
//                        .add(LootItem.lootTableItem(ModBlocks.UNDERGROUND_MYCELIUM))
//                )
//                .withPool(LootPool.lootPool()
//                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
//                        .when(doesNotHaveSilkTouch())
//                        .add(LootItem.lootTableItem(ModBlocks.FUNGATITE))
//        ));
//
//
//
//    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.FUNGATITE.get());
        dropSelf(ModBlocks.GROUND_FUNGATITE.get());

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

        dropSelf(ModBlocks.FUNGATITE_STAIRS.get());
        add(ModBlocks.FUNGATITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FUNGATITE_SLAB.get()));
        dropSelf(ModBlocks.FUNGATITE_WALL.get());

        dropSelf(ModBlocks.POLISHED_FUNGATITE.get());
        dropSelf(ModBlocks.POLISHED_FUNGATITE_STAIRS.get());
        add(ModBlocks.POLISHED_FUNGATITE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_FUNGATITE_SLAB.get()));
        dropSelf(ModBlocks.POLISHED_FUNGATITE_WALL.get());
        dropSelf(ModBlocks.CHISELED_FUNGATITE.get());

        dropSelf(ModBlocks.FUNGATITE_BRICKS.get());
        dropSelf(ModBlocks.FUNGATITE_BRICK_STAIRS.get());
        add(ModBlocks.FUNGATITE_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.FUNGATITE_BRICK_SLAB.get()));
        dropSelf(ModBlocks.FUNGATITE_BRICK_WALL.get());


        dropSelf(ModBlocks.FEATHER_MOSS_BLOCK.get());
        dropSelf(ModBlocks.FEATHER_MOSS_CARPET.get());
        add(ModBlocks.FEATHER_MOSS_TUFTS.get(),
                block -> createShearsOnlyDrop(ModBlocks.FEATHER_MOSS_TUFTS.get()));


        add(ModBlocks.MYCELIUM_SPROUTS.get(),
                block -> createShearsOnlyDrop(ModBlocks.MYCELIUM_SPROUTS.get()));
        add(ModBlocks.MYCELIUM_FERN.get(),
                block -> createShearsOnlyDrop(ModBlocks.MYCELIUM_FERN.get()));


        dropSelf(ModBlocks.TOADSTOOL_CAP_BLOCK.get());
        add(ModBlocks.TOADSTOOL_PATCH.get(),
                block -> createShearsOnlyDrop(ModBlocks.TOADSTOOL_CAP_BLOCK.get()));
        dropSelf(ModBlocks.TOADSTOOL_BUTTON.get());
        add(ModBlocks.POTTED_TOADSTOOL_BUTTON.get(),
                block -> createPotFlowerItemTable(ModBlocks.TOADSTOOL_BUTTON.asItem()));


        dropSelf(ModBlocks.SHROOMWOOD_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SHROOMWOOD_LOG.get());
        dropSelf(ModBlocks.SHROOMWOOD.get());
        dropSelf(ModBlocks.STRIPPED_SHROOMWOOD.get());
        dropSelf(ModBlocks.SHROOMWOOD_PLANKS.get());
        dropSelf(ModBlocks.SHROOMWOOD_STAIRS.get());
        add(ModBlocks.SHROOMWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SHROOMWOOD_SLAB.get()));
        dropSelf(ModBlocks.SHROOMWOOD_FENCE.get());
        dropSelf(ModBlocks.SHROOMWOOD_FENCE_GATE.get());
        add(ModBlocks.SHROOMWOOD_DOOR.get(),
                block -> createDoorTable(ModBlocks.SHROOMWOOD_DOOR.get()));
        dropSelf(ModBlocks.SHROOMWOOD_TRAPDOOR.get());
        dropSelf(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.SHROOMWOOD_BUTTON.get());
        add(ModBlocks.SHROOMWOOD_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_SIGN));
        add(ModBlocks.SHROOMWOOD_WALL_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_SIGN));
        add(ModBlocks.SHROOMWOOD_HANGING_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_HANGING_SIGN));
        add(ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get(), block -> createSingleItemTable(ModBlocks.SHROOMWOOD_HANGING_SIGN));


        add(ModBlocks.MUSHVINE.get(),
                block -> createShearsOnlyDrop(ModBlocks.MUSHVINE.get()));
        add(ModBlocks.MUSHROOM_GILL_BLOCK.get(),
                block -> createShearsOnlyDrop(ModBlocks.MUSHROOM_GILL_BLOCK.get()));
        dropSelf(ModBlocks.LAMPSHROOM.get());
        add(ModBlocks.LAMPSHROOM_STEM.get(), block -> createSingleItemTable(ModBlocks.LAMPSHROOM));
        dropSelf(ModBlocks.LAMPSHROOM_CAP_BLOCK.get());

        add(ModBlocks.HANGING_SHROOM_STEM.get(), block -> createSingleItemTable(ModBlocks.HANGING_SHROOM_CAP));
        add(ModBlocks.HANGING_SHROOM_CAP.get(), block -> createSingleItemTable(ModBlocks.HANGING_SHROOM_CAP));

        dropSelf(ModBlocks.LAMPSHROOM_BUTTON.get());
        dropSelf(ModBlocks.BLEEDING_TOOTH_MUSHROOM.get());
        dropSelf(ModBlocks.GILLED_MUSHROOM.get());
        dropSelf(ModBlocks.GEYSER_BLOCK.get());
        dropSelf(ModBlocks.CLUSTER_SHROOM.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x -> !ignoredBlocks.contains(x.value())).map(Holder::value)::iterator;
    }
}
