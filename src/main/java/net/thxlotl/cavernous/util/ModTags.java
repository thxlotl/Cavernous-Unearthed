package net.thxlotl.cavernous.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.thxlotl.cavernous.Cavernous;

public class ModTags {

    public static class Blocks {


        public static final TagKey<Block> SHROOMWOOD_LOGS = createTag("shroomwood_logs");
        public static final TagKey<Block> UNDERGROUND_MYCELIUM_REPLACEABLE = createTag("underground_mycelium_replaceable");
        public static final TagKey<Block> FUNGATITE_ORE_REPLACEABLE = createTag("fungatite_ore_replaceable");


        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
        }
    }
    public static class Items {

        public static final TagKey<Item> SHROOMWOOD_LOGS = createTag("shroomwood_logs");


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
        }
    }

}
