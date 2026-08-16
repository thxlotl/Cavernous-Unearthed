package net.thxlotl.cavernous.util.worldgen.ore;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.util.worldgen.ore.entries.OreBlockEntry;
import net.thxlotl.cavernous.util.worldgen.ore.entries.OreConfigFeatureEntry;
import net.thxlotl.cavernous.util.worldgen.ore.entries.OrePlacedFeatureEntry;
import net.thxlotl.cavernous.util.worldgen.ore.enums.CustomStoneType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OrePlacedFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreResourceType;
import net.thxlotl.cavernous.worldgen.features.configured.FungalCavesConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.features.configured.VolcanicCavesConfiguredFeatures;
import net.thxlotl.cavernous.worldgen.features.placed.FungalCavesPlacedFeatures;
import net.thxlotl.cavernous.worldgen.features.placed.VolcanicCavesPlacedFeatures;

import java.util.EnumMap;

public class CustomStoneOreFamily {

    public static EnumMap<CustomStoneType, CustomStoneOreFamily> allFamilies = new EnumMap<>(CustomStoneType.class);

    public EnumMap<OreResourceType, Block> oreBlocks;
    public EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> configuredFeatures;
    public EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> placedFeatures;

    public CustomStoneOreFamily(EnumMap<OreResourceType, Block> oreBlocks,
                                EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> configuredFeatures,
                                EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> placedFeatures) {
        this.oreBlocks = oreBlocks;
        this.configuredFeatures = configuredFeatures;
        this.placedFeatures = placedFeatures;
    }

    public static void init() {
        allFamilies.put(CustomStoneType.FUNGATITE, new CustomStoneOreFamily(
                makeOreBlockMap(
                        new OreBlockEntry(OreResourceType.COAL, ModBlocks.FUNGATITE_COAL_ORE.get()),
                        new OreBlockEntry(OreResourceType.COPPER, ModBlocks.FUNGATITE_COPPER_ORE.get()),
                        new OreBlockEntry(OreResourceType.IRON, ModBlocks.FUNGATITE_IRON_ORE.get()),
                        new OreBlockEntry(OreResourceType.GOLD, ModBlocks.FUNGATITE_GOLD_ORE.get()),
                        new OreBlockEntry(OreResourceType.REDSTONE, ModBlocks.FUNGATITE_REDSTONE_ORE.get()),
                        new OreBlockEntry(OreResourceType.LAPIS, ModBlocks.FUNGATITE_LAPIS_ORE.get()),
                        new OreBlockEntry(OreResourceType.DIAMOND, ModBlocks.FUNGATITE_DIAMOND_ORE.get()),
                        new OreBlockEntry(OreResourceType.EMERALD, ModBlocks.FUNGATITE_EMERALD_ORE.get())
                ),
                makeConfigFeatureMap(
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.COAL, FungalCavesConfiguredFeatures.FUNGATITE_ORE_COAL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.COAL_BURIED, FungalCavesConfiguredFeatures.FUNGATITE_ORE_COAL_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.COPPER_LARGE, FungalCavesConfiguredFeatures.FUNGATITE_ORE_COPPER_LARGE),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.COPPER_SMALL, FungalCavesConfiguredFeatures.FUNGATITE_ORE_COPPER_SMALL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.IRON, FungalCavesConfiguredFeatures.FUNGATITE_ORE_IRON),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.IRON_SMALL, FungalCavesConfiguredFeatures.FUNGATITE_ORE_IRON_SMALL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.GOLD, FungalCavesConfiguredFeatures.FUNGATITE_ORE_GOLD),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.GOLD_BURIED, FungalCavesConfiguredFeatures.FUNGATITE_ORE_GOLD_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.REDSTONE, FungalCavesConfiguredFeatures.FUNGATITE_ORE_REDSTONE),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.LAPIS, FungalCavesConfiguredFeatures.FUNGATITE_ORE_LAPIS),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.LAPIS_BURIED, FungalCavesConfiguredFeatures.FUNGATITE_ORE_LAPIS_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_SMALL, FungalCavesConfiguredFeatures.FUNGATITE_ORE_DIAMOND_SMALL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_MEDIUM, FungalCavesConfiguredFeatures.FUNGATITE_ORE_DIAMOND_MEDIUM),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_LARGE, FungalCavesConfiguredFeatures.FUNGATITE_ORE_DIAMOND_LARGE),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_BURIED, FungalCavesConfiguredFeatures.FUNGATITE_ORE_DIAMOND_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.EMERALD, FungalCavesConfiguredFeatures.FUNGATITE_ORE_EMERALD)
                ),
                makePlacedFeatureMap(
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.COAL_LOWER, FungalCavesPlacedFeatures.FUNGATITE_ORE_COAL_LOWER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.COAL_UPPER, FungalCavesPlacedFeatures.FUNGATITE_ORE_COAL_UPPER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.COPPER, FungalCavesPlacedFeatures.FUNGATITE_ORE_COPPER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.COPPER_LARGE, FungalCavesPlacedFeatures.FUNGATITE_ORE_COPPER_LARGE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_MIDDLE, FungalCavesPlacedFeatures.FUNGATITE_ORE_IRON_MIDDLE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_SMALL, FungalCavesPlacedFeatures.FUNGATITE_ORE_IRON_SMALL),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_UPPER, FungalCavesPlacedFeatures.FUNGATITE_ORE_IRON_UPPER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.GOLD, FungalCavesPlacedFeatures.FUNGATITE_ORE_GOLD),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.GOLD_LOWER, FungalCavesPlacedFeatures.FUNGATITE_ORE_GOLD_LOWER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.REDSTONE, FungalCavesPlacedFeatures.FUNGATITE_ORE_REDSTONE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.REDSTONE_LOWER, FungalCavesPlacedFeatures.FUNGATITE_ORE_REDSTONE_LOWER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.LAPIS, FungalCavesPlacedFeatures.FUNGATITE_ORE_LAPIS),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.LAPIS_BURIED, FungalCavesPlacedFeatures.FUNGATITE_ORE_LAPIS_BURIED),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND, FungalCavesPlacedFeatures.FUNGATITE_ORE_DIAMOND),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_BURIED, FungalCavesPlacedFeatures.FUNGATITE_ORE_DIAMOND_BURIED),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_LARGE, FungalCavesPlacedFeatures.FUNGATITE_ORE_DIAMOND_LARGE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_MEDIUM, FungalCavesPlacedFeatures.FUNGATITE_ORE_DIAMOND_MEDIUM),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.EMERALD, FungalCavesPlacedFeatures.FUNGATITE_ORE_EMERALD)
                )
        ));

        allFamilies.put(CustomStoneType.OBSIDIANSTONE, new CustomStoneOreFamily(
                makeOreBlockMap(
                        new OreBlockEntry(OreResourceType.IRON, ModBlocks.OBSIDIANSTONE_IRON_ORE.get()),
                        new OreBlockEntry(OreResourceType.GOLD, ModBlocks.OBSIDIANSTONE_GOLD_ORE.get()),
                        new OreBlockEntry(OreResourceType.REDSTONE, ModBlocks.OBSIDIANSTONE_REDSTONE_ORE.get()),
                        new OreBlockEntry(OreResourceType.LAPIS, ModBlocks.OBSIDIANSTONE_LAPIS_ORE.get()),
                        new OreBlockEntry(OreResourceType.DIAMOND, ModBlocks.OBSIDIANSTONE_DIAMOND_ORE.get())
                ),
                makeConfigFeatureMap(
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.IRON, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_IRON),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.IRON_SMALL, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_IRON_SMALL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.GOLD, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_GOLD),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.GOLD_BURIED, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_GOLD_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.REDSTONE, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_REDSTONE),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.LAPIS, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_LAPIS),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.LAPIS_BURIED, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_LAPIS_BURIED),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_SMALL, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_DIAMOND_SMALL),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_MEDIUM, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_DIAMOND_MEDIUM),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_LARGE, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_DIAMOND_LARGE),
                        new OreConfigFeatureEntry(OreConfiguredFeatureType.DIAMOND_BURIED, VolcanicCavesConfiguredFeatures.OBSIDIANSTONE_ORE_DIAMOND_BURIED)
                ),
                makePlacedFeatureMap(
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_MIDDLE, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_IRON_MIDDLE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_SMALL, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_IRON_SMALL),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.IRON_UPPER, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_IRON_UPPER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.GOLD, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_GOLD),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.GOLD_LOWER, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_GOLD_LOWER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.REDSTONE, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_REDSTONE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.REDSTONE_LOWER, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_REDSTONE_LOWER),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.LAPIS, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_LAPIS),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.LAPIS_BURIED, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_LAPIS_BURIED),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_DIAMOND),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_BURIED, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_DIAMOND_BURIED),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_LARGE, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_DIAMOND_LARGE),
                        new OrePlacedFeatureEntry(OrePlacedFeatureType.DIAMOND_MEDIUM, VolcanicCavesPlacedFeatures.OBSIDIANSTONE_ORE_DIAMOND_MEDIUM)
                )
        ));
    }

    //region Helper methods
    private static EnumMap<OreResourceType, Block> makeOreBlockMap(OreBlockEntry... entries) {

        EnumMap<OreResourceType, Block> map = new EnumMap<>(OreResourceType.class);
        for (OreBlockEntry entry : entries) {
            map.put(entry.resourceType, entry.block);
        }
        return map;
    }
    private static EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> makeConfigFeatureMap(OreConfigFeatureEntry... entries) {

        EnumMap<OreConfiguredFeatureType, ResourceKey<ConfiguredFeature<?,?>>> map = new EnumMap<>(OreConfiguredFeatureType.class);
        for (OreConfigFeatureEntry entry : entries) {
            map.put(entry.featureType, entry.feature);
        }
        return map;
    }
    private static EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> makePlacedFeatureMap(OrePlacedFeatureEntry... entries) {

        EnumMap<OrePlacedFeatureType, ResourceKey<PlacedFeature>> map = new EnumMap<>(OrePlacedFeatureType.class);
        for (OrePlacedFeatureEntry entry : entries) {
            map.put(entry.featureType, entry.feature);
        }
        return map;
    }
    //endregion

}
