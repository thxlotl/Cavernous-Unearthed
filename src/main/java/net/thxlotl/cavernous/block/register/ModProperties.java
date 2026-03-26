package net.thxlotl.cavernous.block.register;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModProperties {

    // Use this to save commonly used block behavior properties

    public static final BlockBehaviour.Properties OBSIDIANSTONE = BlockBehaviour.Properties.of()
            .strength(3.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.COLOR_BLACK)
            .instrument(NoteBlockInstrument.BASEDRUM);

    public static final BlockBehaviour.Properties FUNGATITE = BlockBehaviour.Properties.of()
            .strength(1.5F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.TERRACOTTA_BLUE)
            .instrument(NoteBlockInstrument.BASEDRUM);

    public static final BlockBehaviour.Properties REFINED_FUNGATITE = BlockBehaviour.Properties.of()
            .strength(2.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.TERRACOTTA_BLUE)
            .instrument(NoteBlockInstrument.BASEDRUM);

    public static final BlockBehaviour.Properties SCORIA = BlockBehaviour.Properties.of()
            .strength(1.5F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.TERRACOTTA_GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM);

    public static final BlockBehaviour.Properties SCORIA_BRICKS = BlockBehaviour.Properties.of()
            .strength(2.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE)
            .mapColor(MapColor.TERRACOTTA_GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM);

    // More fringe/not actually a definite block set

    public static final BlockBehaviour.Properties BASE_ORE = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops();

    public static final BlockBehaviour.Properties MYCELIUM_VINE = BlockBehaviour.Properties.of()
            .mapColor(MapColor.CRIMSON_STEM)
            .noCollision()
            .instabreak()
            .sound(SoundType.SMALL_DRIPLEAF)
            .pushReaction(PushReaction.DESTROY);

    public static final BlockBehaviour.Properties FUNGUS_CAP = BlockBehaviour.Properties.of()
            .strength(0.5f)
            .sound(SoundType.WART_BLOCK);

    public static final BlockBehaviour.Properties POTTED_PLANT = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY); /// From vanilla
}

