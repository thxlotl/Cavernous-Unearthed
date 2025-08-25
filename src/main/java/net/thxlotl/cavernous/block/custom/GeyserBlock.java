package net.thxlotl.cavernous.block.custom;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class GeyserBlock extends Block {
    public GeyserBlock(Properties properties) {
        super(properties);
    }

    private Float timer;
    private Boolean isStoodOn;
    // Put this stuff in a block entity

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        // Add tag check
        isStoodOn = true;

        level.playSound(entity, pos, SoundEvents.MAGMA_CUBE_JUMP, SoundSource.BLOCKS, 1f, 1f);

        launchEntity(entity);
    }

    private void launchEntity(Entity entity)
    {
        entity.addDeltaMovement(new Vec3(0, 2, 0));
    }
}
