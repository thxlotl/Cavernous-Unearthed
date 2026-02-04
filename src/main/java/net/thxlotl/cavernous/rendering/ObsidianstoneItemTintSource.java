package net.thxlotl.cavernous.rendering;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.thxlotl.cavernous.block.custom.ObsidianstoneBlock;
import org.jspecify.annotations.Nullable;

public class ObsidianstoneItemTintSource implements ItemTintSource {
    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity) {
        return ObsidianstoneBlock.unheatedColor;
    }

    @Override
    public MapCodec<? extends ItemTintSource> type() {
        return null;
    }
}
