package net.thxlotl.cavernous.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.entity.GhostFungusBlockEntity;

public class GhostFungusBlockEntityRenderer implements BlockEntityRenderer<GhostFungusBlockEntity> {

    public GhostFungusBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GhostFungusBlockEntity ghostFungusBlockEntity, float v, PoseStack poseStack, MultiBufferSource buffer, int i, int i1, Vec3 vec3) {

    }
}
