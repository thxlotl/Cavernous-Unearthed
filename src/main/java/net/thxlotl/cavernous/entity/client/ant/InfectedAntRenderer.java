package net.thxlotl.cavernous.entity.client.ant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.custom.InfectedAnt;

public class InfectedAntRenderer extends MobRenderer<InfectedAnt, AntRenderState, InfectedAntModel> {

    private static final float SHADOW_RADIUS = 0.25f;
    private InfectedAntModel model;

    public InfectedAntRenderer(EntityRendererProvider.Context context) {
        super(context, new InfectedAntModel(context.bakeLayer(InfectedAntModel.LAYER_LOCATION)), SHADOW_RADIUS);
        this.model = new InfectedAntModel(context.bakeLayer(InfectedAntModel.LAYER_LOCATION));
    }

    @Override
    public void render(AntRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(renderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(AntRenderState antRenderState) {
        return ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "textures/entity/infected_ant.png");
    }

    @Override
    public AntRenderState createRenderState() {
        return new AntRenderState();
    }

    @Override
    public void extractRenderState(InfectedAnt entity, AntRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        state.antEntity = entity;
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.tweakAnimationState.copyFrom(entity.tweakAnimationState);
    }
}
