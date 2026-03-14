package net.thxlotl.cavernous.particle.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.thxlotl.cavernous.Cavernous;

public class GeyserSplashModel extends EntityModel<GeyserModelRenderState> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Cavernous.MODID, "geyser_burst"), "main");
    private final ModelPart Inner;
    private final ModelPart outer;
    private final ModelPart bb_main;

    public GeyserSplashModel(ModelPart root) {
        super(root);
        this.Inner = root.getChild("Inner");
        this.outer = root.getChild("outer");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Inner = partdefinition.addOrReplaceChild("Inner", CubeListBuilder.create().texOffs(0, 167).addBox(-5.0F, -79.0F, -5.0F, 10.0F, 79.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition outer = partdefinition.addOrReplaceChild("outer", CubeListBuilder.create().texOffs(184, 194).addBox(-9.0F, -44.0F, -9.0F, 18.0F, 44.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(32, 53).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(GeyserModelRenderState renderState) {
        super.setupAnim(renderState);
    }
}