package net.thxlotl.cavernous.datagen.custom;

import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.thxlotl.cavernous.block.ModBlocks;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    private static final Map<Block, BlockFamily> MAP = new HashMap<>();

    public static final BlockFamily SHROOMWOOD_PLANKS = familyBuilder(ModBlocks.SHROOMWOOD_PLANKS.get())
            .button(ModBlocks.SHROOMWOOD_BUTTON.get())
            .fence(ModBlocks.SHROOMWOOD_FENCE.get())
            .fenceGate(ModBlocks.SHROOMWOOD_FENCE_GATE.get())
            .pressurePlate(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get())
            .sign(ModBlocks.SHROOMWOOD_SIGN.get(), ModBlocks.SHROOMWOOD_WALL_SIGN.get())
            .slab(ModBlocks.SHROOMWOOD_SLAB.get())
            .stairs(ModBlocks.SHROOMWOOD_STAIRS.get())
            .door(ModBlocks.SHROOMWOOD_DOOR.get())
            .trapdoor(ModBlocks.SHROOMWOOD_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final BlockFamily ERUPTITE = familyBuilder(ModBlocks.ERUPTITE_BLOCK.get())
            ///.button(ModBlocks.SHROOMWOOD_BUTTON.get())
            ///.pressurePlate(ModBlocks.SHROOMWOOD_PRESSURE_PLATE.get())
            .door(ModBlocks.ERUPTITE_DOOR.get())
            .trapdoor(ModBlocks.ERUPTITE_TRAPDOOR.get())
            .recipeGroupPrefix("eruptite")
            .recipeUnlockedBy("has_eruptite")
            .getFamily();

    private static BlockFamily.Builder familyBuilder(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily existing = MAP.put(baseBlock, builder.getFamily());
        if (existing!=null) {
            throw new IllegalStateException("Duplicate BlockFamily for block: " + baseBlock);
        }
        return builder;
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
