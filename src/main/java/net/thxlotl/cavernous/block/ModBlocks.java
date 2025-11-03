package net.thxlotl.cavernous.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.custom.*;
import net.thxlotl.cavernous.block.custom.sign.ModHangingSignBlock;
import net.thxlotl.cavernous.block.custom.sign.ModStandingSignBlock;
import net.thxlotl.cavernous.block.custom.sign.ModWallHangingSignBlock;
import net.thxlotl.cavernous.block.custom.sign.ModWallSignBlock;
import net.thxlotl.cavernous.item.ModItems;
import net.thxlotl.cavernous.util.GhostFungus;
import net.thxlotl.cavernous.util.ModWoodTypes;
import net.thxlotl.cavernous.worldgen.datagen.configuredfeatures.FungalCavesConfiguredFeatures;

import java.util.function.Function;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cavernous.MODID);


    // region Fungatite Blocks
    public static final DeferredBlock<Block> FUNGATITE = registerBlock(
            "fungatite",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(1.5f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<Block> GROUND_FUNGATITE = registerBlock(
            "ground_fungatite",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(2.0f, 6.0f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.GRAVEL));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_COAL_ORE = registerBlock(
            "fungatite_coal_ore",
            properties -> new DropExperienceBlock(UniformInt.of(0, 2), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_COPPER_ORE = registerBlock(
            "fungatite_copper_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_IRON_ORE = registerBlock(
            "fungatite_iron_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_GOLD_ORE = registerBlock(
            "fungatite_gold_ore",
            properties -> new DropExperienceBlock(ConstantInt.of(0), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE));
    public static final DeferredBlock<RedStoneOreBlock> FUNGATITE_REDSTONE_ORE = registerBlock(
            "fungatite_redstone_ore",
            properties -> new RedStoneOreBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_LAPIS_ORE = registerBlock(
            "fungatite_lapis_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_DIAMOND_ORE = registerBlock(
            "fungatite_diamond_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_EMERALD_ORE = registerBlock(
            "fungatite_emerald_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE));
    public static final DeferredBlock<StairBlock> FUNGATITE_STAIRS = registerBlock(
            "fungatite_stairs",
            properties -> new StairBlock(ModBlocks.FUNGATITE.get().defaultBlockState(), properties),
            BlockBehaviour.Properties.of().
                    strength(1.5f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<SlabBlock> FUNGATITE_SLAB = registerBlock(
            "fungatite_slab",
            properties -> new SlabBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(1.5f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<WallBlock> FUNGATITE_WALL = registerBlock(
            "fungatite_wall",
            properties -> new WallBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(1.5f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    //endregion

    //region Polished Fungatite Blocks
    public static final DeferredBlock<Block> POLISHED_FUNGATITE = registerBlock(
            "polished_fungatite",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<StairBlock> POLISHED_FUNGATITE_STAIRS = registerBlock(
            "polished_fungatite_stairs",
            properties -> new StairBlock(ModBlocks.POLISHED_FUNGATITE.get().defaultBlockState(), properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<SlabBlock> POLISHED_FUNGATITE_SLAB = registerBlock(
            "polished_fungatite_slab",
            properties -> new SlabBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<WallBlock> POLISHED_FUNGATITE_WALL = registerBlock(
            "polished_fungatite_wall",
            properties -> new WallBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<Block> CHISELED_FUNGATITE = registerBlock(
            "chiseled_fungatite",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    //endregion

    //region Fungatite Brick Blocks
    public static final DeferredBlock<Block> FUNGATITE_BRICKS = registerBlock(
            "fungatite_bricks",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<StairBlock> FUNGATITE_BRICK_STAIRS = registerBlock(
            "fungatite_brick_stairs",
            properties -> new StairBlock(ModBlocks.FUNGATITE.get().defaultBlockState(), properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<SlabBlock> FUNGATITE_BRICK_SLAB = registerBlock(
            "fungatite_brick_slab",
            properties -> new SlabBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    public static final DeferredBlock<WallBlock> FUNGATITE_BRICK_WALL = registerBlock(
            "fungatite_brick_wall",
            properties -> new WallBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE));
    //endregion

    //region Feather Moss Blocks
    public static final DeferredBlock<BonemealableFeaturePlacerBlock> FEATHER_MOSS_BLOCK = registerBlock(
            "feather_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(FungalCavesConfiguredFeatures.FEATHER_MOSS_PATCH_BONEMEAL, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)
            );
    public static final DeferredBlock<CarpetBlock> FEATHER_MOSS_CARPET = registerBlock(
            "feather_moss_carpet",
            properties -> new CarpetBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)
            );
    public static final DeferredBlock<VineBlock> HANGING_FEATHER_MOSS = registerBlock(
            "hanging_feather_moss",
            properties -> new VineBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.MOSS)
    );
    public static final DeferredBlock<WaterLoggablePlantBlock> FEATHER_MOSS_TUFTS = registerBlock(
            "feather_moss_tufts",
            properties -> new WaterLoggablePlantBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.PLANT)
            );
    //endregion

    //region Underground Mycelium Blocks
    public static final DeferredBlock<UndergroundMyceliumBlock> UNDERGROUND_MYCELIUM = registerBlock(
            "underground_mycelium",
            properties -> new UndergroundMyceliumBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.NYLIUM)
                    .mapColor(MapColor.COLOR_PURPLE)
                    .randomTicks());
    public static final DeferredBlock<WaterLoggablePlantBlock> MYCELIUM_SPROUTS = registerBlock(
            "mycelium_sprouts",
            properties -> new WaterLoggablePlantBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<MyceliumFernBlock> MYCELIUM_FERN = registerBlock(
            "mycelium_fern",
            properties -> new MyceliumFernBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    //endregion

    //region Toadstool Blocks
    public static final DeferredBlock<MushroomCapBlock> TOADSTOOL_CAP_BLOCK = registerBlock(
            "toadstool_cap_block",
            properties -> new MushroomCapBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(0.5f).
                    sound(SoundType.MOSS));
    public static final DeferredBlock<ToadstoolPatchBlock> TOADSTOOL_PATCH = registerBlock(
            "toadstool_patch",
            properties -> new ToadstoolPatchBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.NONE)
    );
    public static final DeferredBlock<ToadstoolButtonBlock> TOADSTOOL_BUTTON = registerBlock(
            "toadstool_button",
            properties -> new ToadstoolButtonBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA)
    );
    public static final DeferredBlock<FlowerPotBlock> POTTED_TOADSTOOL_BUTTON = registerBlock(
            "potted_toadstool_button",
            properties -> new FlowerPotBlock(ModBlocks.TOADSTOOL_BUTTON.get(), properties),
            BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)
    );
    //endregion

    //region Shroomwood Blocks
    public static final DeferredBlock<ShroomwoodLogBlock> SHROOMWOOD_LOG = registerBlock(
            "shroomwood_log",
            properties -> new ShroomwoodLogBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM)
    );
    public static final DeferredBlock<ShroomwoodBlock> SHROOMWOOD = registerBlock(
            "shroomwood",
            properties -> new ShroomwoodBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HYPHAE)
    );
    public static final DeferredBlock<ModFlammableRotatedPillarBlock> STRIPPED_SHROOMWOOD_LOG = registerBlock(
            "stripped_shroomwood_log",
            properties -> new ModFlammableRotatedPillarBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM)
    );
    public static final DeferredBlock<ModFlammableRotatedPillarBlock> STRIPPED_SHROOMWOOD = registerBlock(
            "stripped_shroomwood",
            properties -> new ModFlammableRotatedPillarBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_HYPHAE)
    );
    public static final DeferredBlock<Block> SHROOMWOOD_PLANKS = registerBlock(
            "shroomwood_planks",
            properties -> new Block(properties) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS))
            ;
    public static final DeferredBlock<StairBlock> SHROOMWOOD_STAIRS = registerBlock(
            "shroomwood_stairs",
            properties -> new StairBlock(ModBlocks.SHROOMWOOD_PLANKS.get().defaultBlockState(), properties){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STAIRS)
    );
    public static final DeferredBlock<SlabBlock> SHROOMWOOD_SLAB = registerBlock(
            "shroomwood_slab",
            properties -> new SlabBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<FenceBlock> SHROOMWOOD_FENCE = registerBlock(
            "shroomwood_fence",
            properties -> new FenceBlock(properties) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<FenceGateBlock> SHROOMWOOD_FENCE_GATE = registerBlock(
            "shroomwood_fence_gate",
            properties -> new FenceGateBlock(ModWoodTypes.SHROOMWOOD, properties) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            },
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<DoorBlock> SHROOMWOOD_DOOR = registerBlock(
            "shroomwood_door",
            properties -> new DoorBlock(BlockSetType.MANGROVE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<TrapDoorBlock> SHROOMWOOD_TRAPDOOR = registerBlock(
            "shroomwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.MANGROVE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<PressurePlateBlock> SHROOMWOOD_PRESSURE_PLATE = registerBlock(
            "shroomwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.MANGROVE, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<ButtonBlock> SHROOMWOOD_BUTTON = registerBlock(
            "shroomwood_button",
            properties -> new ButtonBlock(BlockSetType.MANGROVE, 30, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );
    public static final DeferredBlock<ModWallSignBlock> SHROOMWOOD_WALL_SIGN = registerBlock(
            "shroomwood_wall_sign",
            properties -> new ModWallSignBlock(ModWoodTypes.SHROOMWOOD, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_SIGN)
    );
    public static final DeferredBlock<ModStandingSignBlock> SHROOMWOOD_SIGN = registerSignBlock(
            "shroomwood_sign",
            properties -> new ModStandingSignBlock(ModWoodTypes.SHROOMWOOD, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_SIGN),
            SHROOMWOOD_WALL_SIGN
    );
    public static final DeferredBlock<ModWallHangingSignBlock> SHROOMWOOD_WALL_HANGING_SIGN = registerBlock(
            "shroomwood_hanging_wall_sign",
            properties -> new ModWallHangingSignBlock(ModWoodTypes.SHROOMWOOD, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_WALL_HANGING_SIGN)
    );
    public static final DeferredBlock<ModHangingSignBlock> SHROOMWOOD_HANGING_SIGN = registerHangingSignBlock(
            "shroomwood_hanging_sign",
            properties -> new ModHangingSignBlock(ModWoodTypes.SHROOMWOOD, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HANGING_SIGN),
            SHROOMWOOD_WALL_HANGING_SIGN
    );
    //endregion

    //region Hanging Shroom Blocks
    public static final DeferredBlock<HangingShroomStemBlock> HANGING_SHROOM_STEM = registerBlock(
            "hanging_shroom_stem",
            properties -> new HangingShroomStemBlock(properties),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.SMALL_DRIPLEAF).pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<HangingShroomCapBlock> HANGING_SHROOM_CAP = registerBlock(
            "hanging_shroom_cap",
            properties -> new HangingShroomCapBlock(properties),
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).randomTicks().instabreak().sound(SoundType.SMALL_DRIPLEAF).pushReaction(PushReaction.DESTROY).forceSolidOff()
    );
    //endregion

    //region Lampshroom Blocks
    public static final DeferredBlock<Block> LAMPSHROOM_CAP_BLOCK = registerBlock(
            "lampshroom_cap_block",
            properties -> new Block(properties),
            BlockBehaviour.Properties.of().
                    strength(0.5f).
                    sound(SoundType.MOSS)
                    .lightLevel((p) -> 9));
    public static final DeferredBlock<LampshroomBlock> LAMPSHROOM = registerBlock(
            "lampshroom",
            properties -> new LampshroomBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).lightLevel((p) -> 7)
    );
    public static final DeferredBlock<LampshroomStemBlock> LAMPSHROOM_STEM = registerBlock(
            "lampshroom_stem",
            properties -> new LampshroomStemBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).noCollission());
    public static final DeferredBlock<FlowerPotBlock> POTTED_LAMPSHROOM = registerBlock(
            "potted_lampshroom",
            properties -> new FlowerPotBlock(ModBlocks.LAMPSHROOM.get(), properties),
            BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p) -> 6)
    );
    public static final DeferredBlock<LampshroomTerrarium> LAMPSHROOM_TERRARIUM = registerBlock(
            "lampshroom_terrarium",
            properties -> new LampshroomTerrarium(properties),
            BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p) -> 6)
    );
    //endregion

    //region Shelfshroom Blocks
    public static final DeferredBlock<MushroomCapBlock> SHELFSHROOM_CAP_BLOCK = registerBlock(
            "shelfshroom_cap_block",
            properties -> new MushroomCapBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(0.5f).
                    sound(SoundType.MOSS));
    public static final DeferredBlock<ShelfshroomBlock> SHELFSHROOM = registerBlock(
            "shelfshroom",
            properties -> new ShelfshroomBlock(properties),
            BlockBehaviour.Properties.of()
                    .instabreak().
                    sound(SoundType.MOSS));
    //endregion

    //region Misc. Mushroom Blocks
    public static final DeferredBlock<BleedingToothMushroomBlock> BLEEDING_TOOTH_MUSHROOM = registerBlock(
            "bleeding_tooth_mushroom",
            properties -> new BleedingToothMushroomBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA));
    public static final DeferredBlock<GhostFungusBlock> GHOST_FUNGUS = registerBlock(
            "ghost_fungus",
            properties -> new GhostFungusBlock(properties),
            BlockBehaviour.Properties.of().replaceable().noCollission().lightLevel(GhostFungus.GHOST_FUNGUS_LIGHT).sound(SoundType.NETHER_SPROUTS)
    );
    public static final DeferredBlock<CordycepsPatchBlock> CORDYCEPS_PATCH = registerBlock(
            "cordyceps_patch",
            properties -> new CordycepsPatchBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
    );
    public static final DeferredBlock<SpringshroomBlock> SPRINGSHROOM = registerBlock(
            "springshroom",
            properties -> new SpringshroomBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA)
    );
    //endregion








    //region Scrapped/Oh-hold Blocks
    public static final DeferredBlock<LampShroomButtonBlock> LAMPSHROOM_BUTTON = registerBlock(
            "lampshroom_button",
            properties -> new LampShroomButtonBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).lightLevel((p) -> 3));
    public static final DeferredBlock<ToadstoolButtonBlock> GILLED_MUSHROOM = registerBlock(
            "gilled_mushroom",
            properties -> new ToadstoolButtonBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA)
    );
    public static final DeferredBlock<WaterLoggablePlantBlock> CLUSTER_SHROOM = registerBlock(
            "cluster_shroom",
            properties -> new WaterLoggablePlantBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<ToadstoolPatchBlock> BLACK_TRUMPET_PATCH = registerBlock(
            "black_trumpet_patch",
            properties -> new ToadstoolPatchBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<WaterLoggablePlantBlock> ANCIENT_FERN = registerBlock(
            "ancient_fern",
            properties -> new WaterLoggablePlantBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).noLootTable()
    );
    public static final DeferredBlock<MushroomGillBlock> MUSHROOM_GILL_BLOCK = registerBlock(
            "mushroom_gill_block",
            properties -> new MushroomGillBlock(properties),
            BlockBehaviour.Properties.of().noOcclusion()
    );
    public static final DeferredBlock<Block> TEST_BLOCK_ORE = registerBlock(
            "test_block_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE).
                    noLootTable());
    public static final DeferredBlock<Block> GEYSER_BLOCK = registerBlock(
            "geyser_block",
            properties -> new GeyserBlock(properties),
            BlockBehaviour.Properties.of().
                    strength(2f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.BASALT));
    //endregion

    // Each block is basically adding its name, function to use, and properties to a list which neoforge will then use all together to actually make a block

    // Returns deferred block of type T with inputs
    // Name
    // Block factory is taking in a function that has properties as an input and an output of ? extends T or any block subclass
    // Block behaviour properties is just a class that describes the properties
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, blockProperties); // Registers the block in the Deferred register
        registerBlockItem(name, block); // Registers the item for the block
        return block;
    }

    //region Sign Registration
    private static <T extends Block> DeferredBlock<T> registerSignBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties, DeferredBlock<ModWallSignBlock> wallSign)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, blockProperties); // Registers the block in the Deferred register
        ModItems.ITEMS.registerItem(name, (properties) -> new SignItem(block.get(), wallSign.get(), properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name))).stacksTo(16))); // Registers the item for the block
        return block;
    }
    private static <T extends Block> DeferredBlock<T> registerHangingSignBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties, DeferredBlock<ModWallHangingSignBlock> wallSign)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, blockProperties); // Registers the block in the Deferred register
        ModItems.ITEMS.registerItem(name, (properties) -> new HangingSignItem(block.get(), wallSign.get(), properties.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name))).stacksTo(16))); // Registers the item for the block
        return block;
    }
    //endregion

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name)))));
        //ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
