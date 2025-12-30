package net.thxlotl.cavernous.util.worldgen.ore.entries;

import net.minecraft.world.level.block.Block;
import net.thxlotl.cavernous.util.worldgen.ore.enums.OreResourceType;

public class OreBlockEntry {

    public OreResourceType resourceType;
    public Block block;

    public OreBlockEntry(OreResourceType resourceType, Block block) {
        this.resourceType = resourceType;
        this.block = block;
    }

}
