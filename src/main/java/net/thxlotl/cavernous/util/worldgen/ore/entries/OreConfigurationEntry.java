package net.thxlotl.cavernous.util.worldgen.ore.entries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreConfiguredFeatureType;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreResourceType;

import java.util.EnumMap;

public class OreConfigurationEntry {

    public static EnumMap<OreConfiguredFeatureType, OreConfigurationEntry> oreConfigs = new EnumMap<>(OreConfiguredFeatureType.class);

    public int size;
    public float discardChance;
    public OreResourceType resourceType;

    public OreConfigurationEntry(int size, float discardChance, OreResourceType resourceType) {
        this.size = size;
        this.discardChance = discardChance;
        this.resourceType = resourceType;
    }

    static {
        oreConfigs.put(OreConfiguredFeatureType.COAL,
                new OreConfigurationEntry(17, 0.0f, OreResourceType.COAL));

        oreConfigs.put(OreConfiguredFeatureType.COAL_BURIED,
                new OreConfigurationEntry(17, 0.5f, OreResourceType.COAL));

        oreConfigs.put(OreConfiguredFeatureType.COPPER_LARGE,
                new OreConfigurationEntry(20, 0.0f, OreResourceType.COPPER));

        oreConfigs.put(OreConfiguredFeatureType.COPPER_SMALL,
                new OreConfigurationEntry(10, 0.0f, OreResourceType.COPPER));

        oreConfigs.put(OreConfiguredFeatureType.IRON,
                new OreConfigurationEntry(9, 0.0f, OreResourceType.IRON));

        oreConfigs.put(OreConfiguredFeatureType.IRON_SMALL,
                new OreConfigurationEntry(4, 0.0f, OreResourceType.IRON));

        oreConfigs.put(OreConfiguredFeatureType.GOLD,
                new OreConfigurationEntry(9, 0.0f, OreResourceType.GOLD));

        oreConfigs.put(OreConfiguredFeatureType.GOLD_BURIED,
                new OreConfigurationEntry(9, 0.5f, OreResourceType.GOLD));

        oreConfigs.put(OreConfiguredFeatureType.REDSTONE,
                new OreConfigurationEntry(8, 0.0f, OreResourceType.REDSTONE));

        oreConfigs.put(OreConfiguredFeatureType.LAPIS,
                new OreConfigurationEntry(7, 0.0f, OreResourceType.LAPIS));

        oreConfigs.put(OreConfiguredFeatureType.LAPIS_BURIED,
                new OreConfigurationEntry(7, 1.0f, OreResourceType.LAPIS));

        oreConfigs.put(OreConfiguredFeatureType.DIAMOND_BURIED,
                new OreConfigurationEntry(8, 1.0f, OreResourceType.DIAMOND));

        oreConfigs.put(OreConfiguredFeatureType.DIAMOND_LARGE,
                new OreConfigurationEntry(12, 0.7f, OreResourceType.DIAMOND));

        oreConfigs.put(OreConfiguredFeatureType.DIAMOND_MEDIUM,
                new OreConfigurationEntry(8, 0.5f, OreResourceType.DIAMOND));

        oreConfigs.put(OreConfiguredFeatureType.DIAMOND_SMALL,
                new OreConfigurationEntry(4, 0.5f, OreResourceType.DIAMOND));

        oreConfigs.put(OreConfiguredFeatureType.EMERALD,
                new OreConfigurationEntry(3, 0.0f, OreResourceType.EMERALD));
    }
}