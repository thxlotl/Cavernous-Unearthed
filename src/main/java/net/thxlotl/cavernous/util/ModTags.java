package net.thxlotl.cavernous.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
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
        public static final TagKey<Item> ANT_FOOD = createTag("ant_food");
        public static final TagKey<Item> SMOOTH_MAGMA_WALKABLE = createTag("smooth_magma_walkable");


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
        }
    }
    public static class EntityTypes {

        public static final TagKey<EntityType<?>> CAN_WALK_ON_SMOOTH_MAGMA = createTag("can_walk_on_smooth_magma");

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, name));
        }
    }

}
