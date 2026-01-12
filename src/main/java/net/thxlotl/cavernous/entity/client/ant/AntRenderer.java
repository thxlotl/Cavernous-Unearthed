package net.thxlotl.cavernous.entity.client.ant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.Identifier;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.custom.Ant;

public class AntRenderer extends MobRenderer<Ant, AntRenderState, AntModel> {

    private static final float SHADOW_RADIUS = 0.25f;
    private AntModel model;

    public AntRenderer(EntityRendererProvider.Context context) {
        super(context, new AntModel(context.bakeLayer(AntModel.LAYER_LOCATION)), SHADOW_RADIUS);
        this.model = new AntModel(context.bakeLayer(AntModel.LAYER_LOCATION));
    }

    @Override
    public void submit(AntRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        if(renderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
    }

    @Override
    public Identifier getTextureLocation(AntRenderState antRenderState) {
        return Identifier.fromNamespaceAndPath(Cavernous.MODID, "textures/entity/ant.png");
    }

    @Override
    public AntRenderState createRenderState() {
        return new AntRenderState();
    }

    @Override
    public void extractRenderState(Ant entity, AntRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        state.antEntity = entity;
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
    }
}
