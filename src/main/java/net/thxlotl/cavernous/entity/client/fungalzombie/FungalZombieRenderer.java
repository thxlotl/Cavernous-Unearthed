package net.thxlotl.cavernous.entity.client.fungalzombie;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.custom.FungalZombie;

public class FungalZombieRenderer extends MobRenderer<FungalZombie, FungalZombieRenderState, FungalZombieModel>  {

    private static final float SHADOW_RADIUS = 0.5f;

    public FungalZombieRenderer(EntityRendererProvider.Context context) {

        super(context, new FungalZombieModel(context.bakeLayer(FungalZombieModel.LAYER_LOCATION)), SHADOW_RADIUS);
        this.model = new FungalZombieModel(context.bakeLayer(FungalZombieModel.LAYER_LOCATION));
    }

    @Override
    public Identifier getTextureLocation(FungalZombieRenderState fungalZombieRenderState) {
        return Identifier.fromNamespaceAndPath(Cavernous.MODID, "textures/entity/fungal_zombie.png");
    }

    @Override
    public FungalZombieRenderState createRenderState() {
        return new FungalZombieRenderState();
    }

    @Override
    public void extractRenderState(FungalZombie entity, FungalZombieRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);

        state.entity = entity;
        state.isAggressive = entity.isAggressive();
        state.tweakAnimationState.copyFrom(entity.tweakAnimationState);
        //state.attackAnimationState.copyFrom(entity.attackAnimationState);
    }

    @Override
    public void submit(FungalZombieRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {

        if(renderState.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(renderState, poseStack, nodeCollector, cameraRenderState);
    }
}
