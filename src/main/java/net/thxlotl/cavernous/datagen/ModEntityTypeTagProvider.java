package net.thxlotl.cavernous.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Cavernous.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.EntityTypes.CAN_WALK_ON_SMOOTH_MAGMA)
                .add(EntityType.SHEEP);
    }
}
