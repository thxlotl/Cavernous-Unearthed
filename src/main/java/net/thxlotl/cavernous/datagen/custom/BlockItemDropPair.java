package net.thxlotl.cavernous.datagen.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class BlockItemDropPair {

    public DeferredBlock<? extends Block> block;
    public ItemLike itemLike;

    public BlockItemDropPair(DeferredBlock<? extends Block> block, ItemLike itemLike) {
        this.block = block;
        this.itemLike = itemLike;
    }

    public static BlockItemDropPair of(DeferredBlock<? extends Block> block, ItemLike itemLike) {
        return new BlockItemDropPair(block, itemLike);
    }
}
