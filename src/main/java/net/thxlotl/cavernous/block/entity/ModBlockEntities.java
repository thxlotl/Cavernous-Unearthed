package net.thxlotl.cavernous.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Cavernous.MODID);

    // Signs
    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register(
                    "mod_sign",
                    () -> new BlockEntityType<>(ModSignBlockEntity::new, false, ModBlocks.SHROOMWOOD_SIGN.get(), ModBlocks.SHROOMWOOD_WALL_SIGN.get()));
    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register(
                    "mod_hanging_sign",
                    () -> new BlockEntityType<>(
                            ModHangingSignBlockEntity::new,  // BlockEntity factory
                            ModBlocks.SHROOMWOOD_HANGING_SIGN.get(), // Blocks this BE applies to
                            ModBlocks.SHROOMWOOD_WALL_HANGING_SIGN.get()
                    )
            );

    // Ghost fungus
    public static final Supplier<BlockEntityType<GhostFungusBlockEntity>> GHOST_FUNGUS =
            BLOCK_ENTITIES.register(
                    "ghost_fungus",
                    () -> new BlockEntityType<>(
                            GhostFungusBlockEntity::new,
                            ModBlocks.GHOST_FUNGUS.get()
                    )
            );



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
