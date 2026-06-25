package net.thxlotl.cavernous.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
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
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
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
import net.thxlotl.cavernous.block.register.ModProperties;
import net.thxlotl.cavernous.block.register.ModBlockSetTypes;
import net.thxlotl.cavernous.item.ModItems;
import net.thxlotl.cavernous.util.GhostFungus;
import net.thxlotl.cavernous.datagen.tag.ModTags;
import net.thxlotl.cavernous.util.ModWoodTypes;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Cavernous.MODID);

    /*

    So I've cleaned up a lot in this file but as I decide on more final properties, colors, etc, I should be able to
    simplify things a little further

     */

    // FUNGAL ----------------------------------------------------------------------------------------------------------

    // region Fungatite Blocks

    public static final DeferredBlock<Block> FUNGATITE = registerTrivialBlock("fungatite", ModProperties.FUNGATITE);
    public static final DeferredBlock<StairBlock> FUNGATITE_STAIRS = registerStairBlock("fungatite", ModProperties.FUNGATITE, () -> ModBlocks.FUNGATITE.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> FUNGATITE_SLAB = registerSlabBlock("fungatite", ModProperties.FUNGATITE);
    public static final DeferredBlock<WallBlock> FUNGATITE_WALL = registerWallBlock("fungatite", ModProperties.FUNGATITE);

    public static final DeferredBlock<Block> GROUND_FUNGATITE = registerBlock(
            "ground_fungatite",
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(1.0F, 4.0F)
                    .sound(SoundType.STONE)
                    .mapColor(MapColor.TERRACOTTA_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
    );

    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_COAL_ORE = registerOreBlock("fungatite", "coal", UniformInt.of(0, 2), 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_COPPER_ORE = registerOreBlock("fungatite", "copper", ConstantInt.of(0), 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_IRON_ORE = registerOreBlock("fungatite", "iron", ConstantInt.of(0), 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_GOLD_ORE = registerOreBlock("fungatite", "gold", ConstantInt.of(0), 3.0f);
    public static final DeferredBlock<RedStoneOreBlock> FUNGATITE_REDSTONE_ORE = registerRedstoneOreBlock("fungatite", 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_LAPIS_ORE = registerOreBlock("fungatite", "lapis", UniformInt.of(2, 5), 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_DIAMOND_ORE = registerOreBlock("fungatite", "diamond", UniformInt.of(3, 7), 3.0f);
    public static final DeferredBlock<DropExperienceBlock> FUNGATITE_EMERALD_ORE = registerOreBlock("fungatite", "emerald", UniformInt.of(3, 7), 3.0f);

    public static final DeferredBlock<Block> POLISHED_FUNGATITE = registerTrivialBlock("polished_fungatite", ModProperties.REFINED_FUNGATITE);
    public static final DeferredBlock<StairBlock> POLISHED_FUNGATITE_STAIRS = registerStairBlock("polished_fungatite", ModProperties.REFINED_FUNGATITE, () -> ModBlocks.POLISHED_FUNGATITE.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> POLISHED_FUNGATITE_SLAB = registerSlabBlock("polished_fungatite", ModProperties.REFINED_FUNGATITE);
    public static final DeferredBlock<WallBlock> POLISHED_FUNGATITE_WALL = registerWallBlock("polished_fungatite", ModProperties.REFINED_FUNGATITE);
    public static final DeferredBlock<Block> CHISELED_FUNGATITE = registerTrivialBlock("chiseled_fungatite", ModProperties.REFINED_FUNGATITE);

    public static final DeferredBlock<Block> FUNGATITE_BRICKS = registerTrivialBlock("fungatite_bricks", ModProperties.REFINED_FUNGATITE);
    public static final DeferredBlock<StairBlock> FUNGATITE_BRICK_STAIRS = registerStairBlock("fungatite_brick", ModProperties.REFINED_FUNGATITE, () -> ModBlocks.FUNGATITE_BRICKS.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> FUNGATITE_BRICK_SLAB = registerSlabBlock("fungatite_bricks", ModProperties.REFINED_FUNGATITE);
    public static final DeferredBlock<WallBlock> FUNGATITE_BRICK_WALL = registerWallBlock("fungatite_bricks", ModProperties.REFINED_FUNGATITE);

    //endregion

    //region Underground Mycelium Blocks
    public static final DeferredBlock<UndergroundMyceliumBlock> UNDERGROUND_MYCELIUM = registerBlock(
            "underground_mycelium",
            UndergroundMyceliumBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(2.0f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NYLIUM)
                    .mapColor(MapColor.TERRACOTTA_PURPLE)
                    .randomTicks()
    );
    public static final DeferredBlock<MyceliumSproutsBlock> MYCELIUM_SPROUTS = registerBlock(
            "mycelium_sprouts",
            properties -> new MyceliumSproutsBlock(properties, ModTags.Blocks.MYCELIUM_SPROUTS_PLACEABLE),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<MyceliumFernBlock> MYCELIUM_FERN = registerBlock(
            "mycelium_fern",
            MyceliumFernBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<MyceliumVineBlock> MYCELIUM_VINE = registerBlock("mycelium_vine", MyceliumVineBlock::new, ModProperties.MYCELIUM_VINE); /// TIP BLOCK
    public static final DeferredBlock<MyceliumVinePlantBlock> MYCELIUM_VINE_PLANT = registerBlock("mycelium_vine_plant",MyceliumVinePlantBlock::new,ModProperties.MYCELIUM_VINE); /// BODY BLOCK
    //endregion

    //region Feather Moss Blocks
    public static final DeferredBlock<BonemealableFeaturePlacerBlock> FEATHER_MOSS_BLOCK = registerBlock(
            "feather_moss_block",
            properties -> new BonemealableFeaturePlacerBlock(FungalCavesConfiguredFeatures.FEATHER_MOSS_PATCH_BONEMEAL, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK)
            );
    public static final DeferredBlock<CarpetBlock> FEATHER_MOSS_CARPET = registerBlock(
            "feather_moss_carpet",
            CarpetBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_CARPET)
            );
    public static final DeferredBlock<WaterLoggablePlantBlock> FEATHER_MOSS_TUFTS = registerBlock(
            "feather_moss_tufts",
            WaterLoggablePlantBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.PLANT)
    );

    public static final DeferredBlock<VineBlock> HANGING_FEATHER_MOSS = registerBlock(
            "hanging_feather_moss",
            VineBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.MOSS)
    );
    //endregion

    //region Toadstool Blocks

    public static final DeferredBlock<Block> TOADSTOOL_CAP_BLOCK = registerTrivialBlock("toadstool_cap_block", ModProperties.FUNGUS_CAP);
    public static final DeferredBlock<ToadstoolPatchBlock> TOADSTOOL_PATCH = registerBlock(
            "toadstool_patch",
            ToadstoolPatchBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.NONE)
    );
    public static final DeferredBlock<ToadstoolButtonBlock> TOADSTOOL_BUTTON = registerBlock(
            "toadstool_button",
            ToadstoolButtonBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).sound(SoundType.FUNGUS)
    );
    public static final DeferredBlock<FlowerPotBlock> POTTED_TOADSTOOL_BUTTON = registerBlock(
            "potted_toadstool_button",
            (p) -> new FlowerPotBlock(TOADSTOOL_BUTTON.get(), p),
            ModProperties.POTTED_PLANT);

    //endregion

    //region Shroomwood Blocks

    /*

        Make generic stuff for things in here, its sooooooo messy

     */

    public static final DeferredBlock<ShroomwoodLogBlock> SHROOMWOOD_LOG = registerBlock(
            "shroomwood_log",
            ShroomwoodLogBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM)
    );
    public static final DeferredBlock<ShroomwoodBlock> SHROOMWOOD = registerBlock(
            "shroomwood",
            ShroomwoodBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_HYPHAE)
    );
    public static final DeferredBlock<ModFlammableRotatedPillarBlock> STRIPPED_SHROOMWOOD_LOG = registerBlock(
            "stripped_shroomwood_log",
            ModFlammableRotatedPillarBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM)
    );
    public static final DeferredBlock<ModFlammableRotatedPillarBlock> STRIPPED_SHROOMWOOD = registerBlock(
            "stripped_shroomwood",
            ModFlammableRotatedPillarBlock::new,
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

    //region Lampshroom Blocks

    public static final DeferredBlock<LampshroomBlock> LAMPSHROOM = registerBlock(
            "lampshroom",
            LampshroomBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).lightLevel((p) -> 7).sound(SoundType.FUNGUS)
    );
    public static final DeferredBlock<LampshroomStemBlock> LAMPSHROOM_STEM = registerBlock(
            "lampshroom_stem",
            LampshroomStemBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).noCollision().sound(SoundType.FUNGUS)
    );

    public static final DeferredBlock<Block> LAMPSHROOM_CAP_BLOCK = registerBlock("lampshroom_cap_block", Block::new, ModProperties.FUNGUS_CAP.lightLevel((p) -> 9).emissiveRendering(ModBlocks::always));
    public static final DeferredBlock<FlowerPotBlock> POTTED_LAMPSHROOM = registerBlock(
            "potted_lampshroom",
            (p) -> new FlowerPotBlock(LAMPSHROOM.get(), p),
            ModProperties.POTTED_PLANT.lightLevel((p) -> 9).emissiveRendering(ModBlocks::always)
    );

    public static final DeferredBlock<LampshroomPatchBlock> LAMPSHROOM_PATCH = registerBlock(
            "lampshroom_patch",
            LampshroomPatchBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p) -> 2)
    );
    public static final DeferredBlock<LampshroomTerrarium> LAMPSHROOM_TERRARIUM = registerBlock(
            "lampshroom_terrarium",
            LampshroomTerrarium::new,
            BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p) -> 6)
    );

    //endregion

    //region Shelfshroom Blocks

    public static final DeferredBlock<Block> SHELFSHROOM_CAP_BLOCK = registerTrivialBlock("shelfshroom_cap_block", ModProperties.FUNGUS_CAP);

    ///  Need to figure out if Im doing smth with this
    public static final DeferredBlock<ShelfshroomBlock> SHELFSHROOM = registerBlock("shelfshroom", ShelfshroomBlock::new, BlockBehaviour.Properties.of().instabreak().sound(SoundType.FUNGUS));

    //endregion

    //region Flipshroom

    public static final DeferredBlock<Block> FLIPSHROOM_CAP_BLOCK = registerTrivialBlock("flipshroom_cap_block", ModProperties.FUNGUS_CAP.lightLevel((p) -> 4));

    public static final DeferredBlock<HangingShroomStemBlock> FLIPSHROOM_STEM = registerBlock(
            "flipshroom_stem",
            HangingShroomStemBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollision().instabreak().sound(SoundType.SMALL_DRIPLEAF).pushReaction(PushReaction.DESTROY)
    );
    public static final DeferredBlock<HangingShroomCapBlock> FLIPSHROOM = registerBlock(
            "flipshroom",
            HangingShroomCapBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).randomTicks().instabreak().sound(SoundType.SMALL_DRIPLEAF).pushReaction(PushReaction.DESTROY).forceSolidOff() /// ??? deprecated to what
    );

    //endregion

    //region Misc. Mushroom Blocks

    public static final DeferredBlock<GhostFungusBlock> GHOST_FUNGUS = registerBlock(
            "ghost_fungus",
            GhostFungusBlock::new,
            BlockBehaviour.Properties.of().replaceable().noCollision().lightLevel(GhostFungus.GHOST_FUNGUS_LIGHT).sound(SoundType.FUNGUS)
    );
    public static final DeferredBlock<CordycepsPatchBlock> CORDYCEPS_PATCH = registerBlock(
            "cordyceps_patch",
            CordycepsPatchBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
    );

    /// Keep these but fix them
    public static final DeferredBlock<SurfaceCoverPlant> BLUE_GHOST_FUNGUS = registerBlock(
            "blue_ghost_fungus",
            properties -> new SurfaceCoverPlant(properties, BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT),
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((p) -> 2)
    );
    public static final DeferredBlock<BleedingToothMushroomBlock> BLEEDING_TOOTH_MUSHROOM = registerBlock(
            "bleeding_tooth_mushroom",
            BleedingToothMushroomBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA).sound(SoundType.FUNGUS)
    );

    /// Don't keep these but replace it
    public static final DeferredBlock<WaterLoggablePlantBlock> INKY_CAP_PATCH = registerBlock(
            "inky_cap_patch",
            WaterLoggablePlantBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS)
    );
    public static final DeferredBlock<PuffshroomBlock> PUFFSHROOM = registerBlock(
            "puffshroom",
            PuffshroomBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.AZALEA)
    );

    //endregion

    // VOLCANIC --------------------------------------------------------------------------------------------------------

    //region Scoria

    public static final DeferredBlock<Block> SCORIA = registerTrivialBlock("scoria", ModProperties.SCORIA);

    public static final DeferredBlock<Block> POLISHED_SCORIA = registerTrivialBlock("polished_scoria", ModProperties.SCORIA_BRICKS);

    //endregion

    //region Obsidianstone

    public static final DeferredBlock<Block> OBSIDIANSTONE = registerTrivialBlock("obsidianstone", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<StairBlock> OBSIDIANSTONE_STAIRS = registerStairBlock("obsidianstone", ModProperties.OBSIDIANSTONE, () -> ModBlocks.OBSIDIANSTONE.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> OBSIDIANSTONE_SLAB = registerSlabBlock("obsidianstone", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<WallBlock> OBSIDIANSTONE_WALL = registerWallBlock("obsidianstone", ModProperties.OBSIDIANSTONE);

    public static final DeferredBlock<Block> POLISHED_OBSIDIANSTONE = registerTrivialBlock("polished_obsidianstone", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<StairBlock> POLISHED_OBSIDIANSTONE_STAIRS = registerStairBlock("polished_obsidianstone", ModProperties.OBSIDIANSTONE, () -> ModBlocks.OBSIDIANSTONE.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> POLISHED_OBSIDIANSTONE_SLAB = registerSlabBlock("polished_obsidianstone", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<WallBlock> POLISHED_OBSIDIANSTONE_WALL = registerWallBlock("polished_obsidianstone", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<PressurePlateBlock> POLISHED_OBSIDIANSTONE_PRESSURE_PLATE = registerStonePressurePlate("polished_obsidianstone", ModBlockSetTypes.POLISHED_OBSIDIANTSTONE);
    public static final DeferredBlock<ButtonBlock> POLISHED_OBSIDIANSTONE_BUTTON = registerStoneButton("polished_obsidianstone", ModBlockSetTypes.POLISHED_OBSIDIANTSTONE);

    public static final DeferredBlock<Block> OBSIDIANSTONE_BRICKS = registerTrivialBlock("obsidianstone_bricks", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<StairBlock> OBSIDIANSTONE_BRICK_STAIRS = registerStairBlock("obsidianstone_brick", ModProperties.OBSIDIANSTONE, () -> ModBlocks.OBSIDIANSTONE_BRICKS.get().defaultBlockState());
    public static final DeferredBlock<SlabBlock> OBSIDIANSTONE_BRICK_SLAB = registerSlabBlock("obsidianstone_brick", ModProperties.OBSIDIANSTONE);
    public static final DeferredBlock<WallBlock> OBSIDIANSTONE_BRICK_WALL = registerWallBlock("obsidianstone_brick", ModProperties.OBSIDIANSTONE);

    //endregion

    //region Volcanic Cave Misc

    public static final DeferredBlock<Block> GEYSER_BLOCK = registerBlock(
            "geyser_block",
            GeyserBlock::new,
            ModProperties.OBSIDIANSTONE.sound(SoundType.BASALT)
    );
    public static final DeferredBlock<SoftMagmaBlock> SOFT_MAGMA_BLOCK = registerBlock(
            "soft_magma_block",
            SoftMagmaBlock::new,
            BlockBehaviour.Properties.of().
                    strength(1.0f).
                    requiresCorrectToolForDrops().
                    sound(SoundType.STONE).
                    lightLevel((p) -> 9).
                    noOcclusion()
                    .forceSolidOn()
                    .emissiveRendering(ModBlocks::always)
    );
    public static final DeferredBlock<MagmaFern> MAGMA_FERN = registerBlock(
            "magma_fern",
            MagmaFern::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_SPROUTS).mapColor(MapColor.COLOR_ORANGE)
    );

    //endregion

    //region Eruptite

    ///public static final DeferredBlock<DropExperienceBlock> OBSIDIANSTONE_ERUPTITE_ORE = registerOreBlock("obsidianstone", "eruptite", ConstantInt.of(0), 4.5f);
    public static final DeferredBlock<Block> ERUPTITE_BLOCK = registerBlock("eruptite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));
    public static final DeferredBlock<Block> CUT_ERUPTITE_BLOCK = registerBlock("cut_eruptite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));
    public static final DeferredBlock<IronBarsBlock> ERUPTITE_BARS = registerBlock("eruptite_bars", IronBarsBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS));

    //endregion

    // HELPER METHODS --------------------------------------------------------------------------------------------------

    //region Helper Methods
    private static DeferredBlock<Block> registerTrivialBlock(String name, BlockBehaviour.Properties blockProperties)
    {
        DeferredBlock<Block> block = BLOCKS.registerBlock(name, Block::new, () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(name, block); // Registers the item for the block
        return block;
    }
    private static DeferredBlock<StairBlock> registerStairBlock(String familyName, BlockBehaviour.Properties blockProperties, Supplier<BlockState> state)
    {
        familyName += "_stairs";
        DeferredBlock<StairBlock> stairBlock = BLOCKS.registerBlock(familyName, (p) -> new StairBlock(state.get(), p), () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(familyName, stairBlock); // Registers the item for the block
        return stairBlock;
    }
    private static DeferredBlock<SlabBlock> registerSlabBlock(String familyName, BlockBehaviour.Properties blockProperties)
    {
        familyName += "_slab";
        DeferredBlock<SlabBlock> slabBlock = BLOCKS.registerBlock(familyName, SlabBlock::new, () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(familyName, slabBlock); // Registers the item for the block
        return slabBlock;
    }
    private static DeferredBlock<WallBlock> registerWallBlock(String familyName, BlockBehaviour.Properties blockProperties)
    {
        familyName += "_wall";
        DeferredBlock<WallBlock> wallBlock = BLOCKS.registerBlock(familyName, WallBlock::new, () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(familyName, wallBlock); // Registers the item for the block
        return wallBlock;
    }
    private static DeferredBlock<PressurePlateBlock> registerPressurePlateBlock(String familyName, BlockBehaviour.Properties blockProperties, BlockSetType blockSetType)
    {
        familyName += "_pressure_plate";
        DeferredBlock<PressurePlateBlock> pressurePlateBlock = BLOCKS.registerBlock(familyName, (p) -> new PressurePlateBlock(blockSetType, p), () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(familyName, pressurePlateBlock); // Registers the item for the block
        return pressurePlateBlock;
    }
    private static DeferredBlock<PressurePlateBlock> registerStonePressurePlate(String familyName, BlockSetType blockSetType)
    {
        return registerPressurePlateBlock(familyName, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_PRESSURE_PLATE), blockSetType);
    }
    private static BlockBehaviour.Properties stoneButtonProperties() {return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON);}
    private static DeferredBlock<ButtonBlock> registerStoneButton(String familyName, BlockSetType blockSetType)
    {
        familyName += "_button";
        DeferredBlock<ButtonBlock> buttonBlock = BLOCKS.registerBlock(familyName, (p) -> new ButtonBlock(blockSetType, 20, p), ModBlocks::stoneButtonProperties); // Registers the block in the Deferred register
        registerBlockItem(familyName, buttonBlock); // Registers the item for the block
        return buttonBlock;
    }
    private static DeferredBlock<DropExperienceBlock> registerOreBlock(String familyName, String oreName, IntProvider expAmount, float destroyTime)
    {
        return registerBlock(
                familyName + "_" + oreName + "_ore",
                properties -> new DropExperienceBlock(expAmount, properties),
                ModProperties.BASE_ORE.strength(destroyTime, 3.0f)
        );
    }
    private static DeferredBlock<RedStoneOreBlock> registerRedstoneOreBlock(String familyName, float destroyTime)
    {
        return registerBlock(
                familyName + "_redstone_ore",
                RedStoneOreBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_BLOCK).strength(destroyTime, 3.0f)
        );
    }
    //endregion

    //region Sign Registration

    private static <T extends Block> DeferredBlock<T> registerSignBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties, DeferredBlock<ModWallSignBlock> wallSign)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, () -> blockProperties); // Registers the block in the Deferred register
        ModItems.ITEMS.registerItem(name, (properties) -> new SignItem(block.get(), wallSign.get(), properties.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Cavernous.MODID, name))).stacksTo(16))); // Registers the item for the block
        return block;
    }
    private static <T extends Block> DeferredBlock<T> registerHangingSignBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties, DeferredBlock<ModWallHangingSignBlock> wallSign)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, () -> blockProperties); // Registers the block in the Deferred register
        ModItems.ITEMS.registerItem(name, (properties) -> new HangingSignItem(block.get(), wallSign.get(), properties.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Cavernous.MODID, name))).stacksTo(16))); // Registers the item for the block
        return block;
    }

    //endregion

    // Each block is basically adding its name, function to use, and properties to a list which neoforge will then use all together to actually make a block

    // Returns deferred block of type T with inputs
    // Name
    // Block factory is taking in a function that has properties as an input and an output of ? extends T or any block subclass
    // Block behaviour properties is just a class that describes the properties
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends T> blockFactory, BlockBehaviour.Properties blockProperties)
    {
        DeferredBlock<T> block = BLOCKS.registerBlock(name, blockFactory, () -> blockProperties); // Registers the block in the Deferred register
        registerBlockItem(name, block); // Registers the item for the block
        return block;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Cavernous.MODID, name)))));
        //ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
