package net.thxlotl.mixin;

import com.mojang.blaze3d.vertex.QuadInstance;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelBlockRenderer.class)
public interface ModelBlockRendererAccessor {

    @Accessor("quadInstance")
    QuadInstance getQuadInstance();

    @Invoker("getTintColor")
    int callGetTintColor(
            BlockAndTintGetter level,
            BlockState state,
            BlockPos pos,
            int tintIndex
    );

}
