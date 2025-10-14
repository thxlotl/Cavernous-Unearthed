package net.thxlotl.cavernous.entity.client.hangingshroomsporepod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.custom.projectile.HangingShroomSporePodProjectileEntity;

public class HangingShroomSporePodRenderer extends EntityRenderer<HangingShroomSporePodProjectileEntity, EntityRenderState> {
    private HangingShroomSporePodModel model;

    public HangingShroomSporePodRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new HangingShroomSporePodModel(context.bakeLayer(HangingShroomSporePodModel.LAYER_LOCATION));
    }

    @Override
    public HangingShroomSporePodRenderState createRenderState() {
        return new HangingShroomSporePodRenderState();
    }

    @Override
    public void extractRenderState(HangingShroomSporePodProjectileEntity entity, EntityRenderState reusedState, float partialTick) {
        super.extractRenderState(entity, reusedState, partialTick);

        HangingShroomSporePodRenderState state = (HangingShroomSporePodRenderState) reusedState;
        state.entity = entity;

        float age = reusedState.ageInTicks;
        float baseSpinPower = 10f;
        float spinXmagnitude = age * baseSpinPower * entity.spinXMultiplier;
        float spinYmagnitude = age * baseSpinPower * entity.spinYMultiplier;

        state.spinX = entity.spinXDirection ? spinXmagnitude : -spinXmagnitude;
        state.spinY = entity.spinYDirection ? spinYmagnitude : -spinYmagnitude;
    }

    @Override
    public void render(EntityRenderState renderState, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        HangingShroomSporePodRenderState state = (HangingShroomSporePodRenderState)renderState;
        Entity entity = state.entity;


        poseStack.pushPose();

        // Makes it face the right direction
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(renderState.partialTick, entity.yRotO, entity.getYRot()) + state.spinY));
        poseStack.mulPose(Axis.XP.rotationDegrees(state.spinX));

        poseStack.translate(0.0f, -1.0f, 0.0f);


        VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(
                bufferSource, this.model.renderType(this.getTextureLocation()),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "textures/entity/hanging_shroom_spore_pod.png");
    }
}
