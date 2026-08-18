package net.thxlotl.cavernous.block.register;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModProperties {

    // Use this to save commonly used block behavior properties

    public static final BlockBehaviour.Properties obsidianstone() {
        return BlockBehaviour.Properties.of()
                .strength(3.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .mapColor(MapColor.COLOR_BLACK)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }

    public static final BlockBehaviour.Properties fungatite() {
        return BlockBehaviour.Properties.of()
                .strength(1.5F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .mapColor(MapColor.TERRACOTTA_BLUE)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }

    public static final BlockBehaviour.Properties refinedFungatite() {
        return BlockBehaviour.Properties.of()
                .strength(2.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .mapColor(MapColor.TERRACOTTA_BLUE)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }

    public static final BlockBehaviour.Properties scoria() {
        return BlockBehaviour.Properties.of()
                .strength(1.5F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .mapColor(MapColor.TERRACOTTA_GRAY)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }

    public static final BlockBehaviour.Properties scoriaBricks() {
        return BlockBehaviour.Properties.of()
                .strength(2.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .mapColor(MapColor.TERRACOTTA_GRAY)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }

    public static final BlockBehaviour.Properties eruptite() {
        return BlockBehaviour.Properties.of()
                .strength(3.0F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL)
                .mapColor(MapColor.COLOR_ORANGE)
                .instrument(NoteBlockInstrument.BASEDRUM);
    }


    // More fringe/not actually a definite block set

    public static final BlockBehaviour.Properties baseOre() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops();
    }

    public static final BlockBehaviour.Properties myceliumVine() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.CRIMSON_STEM)
                .noCollision()
                .instabreak()
                .sound(SoundType.SMALL_DRIPLEAF)
                .pushReaction(PushReaction.DESTROY);
    }

    public static final BlockBehaviour.Properties fungusCap() {
        return BlockBehaviour.Properties.of()
                .strength(0.5f)
                .sound(SoundType.WART_BLOCK);
    }

    public static final BlockBehaviour.Properties pottedPlant() {
        return BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY); /// From vanilla
    }
}

