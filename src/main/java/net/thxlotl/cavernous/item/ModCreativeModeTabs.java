package net.thxlotl.cavernous.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cavernous.MODID);

    public static final Supplier<CreativeModeTab> CAVERNOUS_TAB = CREATIVE_MODE_TAB.register("cavernous_tab", () -> CreativeModeTab.builder()
            .icon( () -> new ItemStack(ModBlocks.FUNGATITE.get()))
            .title(Component.translatable("creativetab.cavernous.cavernous_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.FUNGATITE);
                output.accept(ModBlocks.GROUND_FUNGATITE);
                output.accept(ModBlocks.FUNGATITE_COAL_ORE);
                output.accept(ModBlocks.FUNGATITE_COPPER_ORE);
                output.accept(ModBlocks.FUNGATITE_IRON_ORE);
                output.accept(ModBlocks.FUNGATITE_GOLD_ORE);
                output.accept(ModBlocks.FUNGATITE_REDSTONE_ORE);
                output.accept(ModBlocks.FUNGATITE_LAPIS_ORE);
                output.accept(ModBlocks.FUNGATITE_DIAMOND_ORE);
                output.accept(ModBlocks.FUNGATITE_EMERALD_ORE);
                output.accept(ModBlocks.FUNGATITE_STAIRS);
                output.accept(ModBlocks.FUNGATITE_SLAB);
                output.accept(ModBlocks.FUNGATITE_WALL);
                output.accept(ModBlocks.POLISHED_FUNGATITE);
                output.accept(ModBlocks.POLISHED_FUNGATITE_STAIRS);
                output.accept(ModBlocks.POLISHED_FUNGATITE_SLAB);
                output.accept(ModBlocks.POLISHED_FUNGATITE_WALL);
                output.accept(ModBlocks.CHISELED_FUNGATITE);
                output.accept(ModBlocks.FUNGATITE_BRICKS);;
                output.accept(ModBlocks.FUNGATITE_BRICK_STAIRS);
                output.accept(ModBlocks.FUNGATITE_BRICK_SLAB);
                output.accept(ModBlocks.FUNGATITE_BRICK_WALL);
                output.accept(ModBlocks.FEATHER_MOSS_BLOCK);
                output.accept(ModBlocks.FEATHER_MOSS_CARPET);
                output.accept(ModBlocks.FEATHER_MOSS_TUFTS);
                output.accept(ModBlocks.UNDERGROUND_MYCELIUM);
                output.accept(ModBlocks.MYCELIUM_SPROUTS);
                output.accept(ModBlocks.MYCELIUM_FERN);
                output.accept(ModBlocks.MYCELIUM_VINE);
                output.accept(ModBlocks.SHELFSHROOM);
                output.accept(ModBlocks.SHELFSHROOM_CAP_BLOCK);
                output.accept(ModBlocks.TOADSTOOL_CAP_BLOCK);
                output.accept(ModBlocks.TOADSTOOL_PATCH);
                output.accept(ModBlocks.TOADSTOOL_BUTTON);
                output.accept(ModBlocks.SHROOMWOOD_LOG);
                output.accept(ModBlocks.SHROOMWOOD);
                output.accept(ModBlocks.STRIPPED_SHROOMWOOD_LOG);
                output.accept(ModBlocks.STRIPPED_SHROOMWOOD);
                output.accept(ModBlocks.SHROOMWOOD_PLANKS);
                output.accept(ModBlocks.SHROOMWOOD_STAIRS);
                output.accept(ModBlocks.SHROOMWOOD_SLAB);
                output.accept(ModBlocks.SHROOMWOOD_FENCE);
                output.accept(ModBlocks.SHROOMWOOD_FENCE_GATE);
                output.accept(ModBlocks.SHROOMWOOD_DOOR);
                output.accept(ModBlocks.SHROOMWOOD_TRAPDOOR);
                output.accept(ModBlocks.SHROOMWOOD_PRESSURE_PLATE);
                output.accept(ModBlocks.SHROOMWOOD_BUTTON);
                output.accept(ModBlocks.SHROOMWOOD_SIGN);
                output.accept(ModBlocks.SHROOMWOOD_HANGING_SIGN);
                output.accept(ModBlocks.FLIPSHROOM_CAP_BLOCK);
                output.accept(ModBlocks.FLIPSHROOM);
                output.accept(ModBlocks.LAMPSHROOM_CAP_BLOCK);
                output.accept(ModBlocks.LAMPSHROOM);
                output.accept(ModBlocks.LAMPSHROOM_PATCH);
                output.accept(ModBlocks.GHOST_FUNGUS);
                ///output.accept(ModBlocks.LAMPSHROOM_TERRARIUM);
                output.accept(ModBlocks.BLEEDING_TOOTH_MUSHROOM);
                output.accept(ModBlocks.CORDYCEPS_PATCH);
                ///output.accept(ModBlocks.SPRINGSHROOM);
                ///output.accept(ModItems.ANT_SPAWN_EGG);
                ///output.accept(ModItems.INFECTED_ANT_SPAWN_EGG);
                ///output.accept(ModItems.FUNGAL_ZOMBIE_SPAWN_EGG);
                output.accept(ModBlocks.GEYSER_BLOCK);
                output.accept(ModBlocks.OBSIDIANSTONE);
                output.accept(ModBlocks.OBSIDIANSTONE_STAIRS);
                output.accept(ModBlocks.OBSIDIANSTONE_SLAB);
                output.accept(ModBlocks.OBSIDIANSTONE_WALL);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE_STAIRS);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE_SLAB);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE_WALL);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE_PRESSURE_PLATE);
                output.accept(ModBlocks.POLISHED_OBSIDIANSTONE_BUTTON);
                output.accept(ModBlocks.OBSIDIANSTONE_BRICKS);
                output.accept(ModBlocks.OBSIDIANSTONE_BRICK_STAIRS);
                output.accept(ModBlocks.OBSIDIANSTONE_BRICK_SLAB);
                output.accept(ModBlocks.OBSIDIANSTONE_BRICK_WALL);
                output.accept(ModBlocks.SCORIA);
                output.accept(ModBlocks.SOFT_MAGMA_BLOCK);
                output.accept(ModItems.ERUPTITE_INGOT);
            })
            .build());


    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
