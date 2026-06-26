package net.thxlotl.cavernous.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.ModEntities;
import net.thxlotl.cavernous.item.custom.HangingShroomSporePodItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Cavernous.MODID);


    public static final DeferredItem<Item> ERUPTITE_INGOT = ITEMS.registerItem("eruptite_ingot", Item::new, Item.Properties::new);
    public static final DeferredItem<Item> ERUPTITE = ITEMS.registerItem("eruptite", Item::new, Item.Properties::new);


    public static final DeferredItem<HangingShroomSporePodItem> HANGING_SHROOM_SPORE_POD = ITEMS.registerItem("hanging_shroom_spore_pod", HangingShroomSporePodItem::new, () -> new Item.Properties().stacksTo(16));

    public static final DeferredItem<SpawnEggItem> ANT_SPAWN_EGG = ITEMS.registerItem(
            "ant_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.ANT.get())),
            Item.Properties::new
    );
    public static final DeferredItem<SpawnEggItem> INFECTED_ANT_SPAWN_EGG = ITEMS.registerItem(
            "infected_ant_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.INFECTED_ANT.get())),
            Item.Properties::new
    );
    public static final DeferredItem<SpawnEggItem> FUNGAL_ZOMBIE_SPAWN_EGG = ITEMS.registerItem(
            "fungal_zombie_spawn_egg",
            properties -> new SpawnEggItem(properties.spawnEgg(ModEntities.FUNGAL_ZOMBIE.get())),
            Item.Properties::new
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
