package net.thxlotl.cavernous.entity.client.ant;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.thxlotl.cavernous.Cavernous;

public class AntModel extends EntityModel<AntRenderState> {

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation attackAnimation;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(Cavernous.MODID, "ant"), "main");
    private final ModelPart thorax;
    private final ModelPart rightlegs;
    private final ModelPart r1;
    private final ModelPart r2;
    private final ModelPart r3;
    private final ModelPart leftlegs;
    private final ModelPart l1;
    private final ModelPart l2;
    private final ModelPart l3;
    private final ModelPart head;
    private final ModelPart antennae;
    private final ModelPart leftpinsir;
    private final ModelPart rightpinsir;
    private final ModelPart abdomen;


    public AntModel(ModelPart root) {
        super(root);
        this.thorax = root.getChild("thorax");
        this.rightlegs = this.thorax.getChild("rightlegs");
        this.r1 = this.rightlegs.getChild("r1");
        this.r2 = this.rightlegs.getChild("r2");
        this.r3 = this.rightlegs.getChild("r3");
        this.leftlegs = this.thorax.getChild("leftlegs");
        this.l1 = this.leftlegs.getChild("l1");
        this.l2 = this.leftlegs.getChild("l2");
        this.l3 = this.leftlegs.getChild("l3");
        this.head = root.getChild("head");
        this.antennae = this.head.getChild("antennae");
        this.abdomen = root.getChild("abdomen");
        this.leftpinsir = this.head.getChild("leftpinsir");
        this.rightpinsir = this.head.getChild("rightpinsir");

        this.walkingAnimation = AntAnimations.WALK.bake(root);
        this.attackAnimation = AntAnimations.ATTACK.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition thorax = partdefinition.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(0, 25).addBox(0.0F, -2.0F, -4.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 22.0F, 2.5F));

        PartDefinition rightlegs = thorax.addOrReplaceChild("rightlegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition r1 = rightlegs.addOrReplaceChild("r1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

        PartDefinition r1_r1 = r1.addOrReplaceChild("r1_r1", CubeListBuilder.create().texOffs(17, 6).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3998F));

        PartDefinition r2 = rightlegs.addOrReplaceChild("r2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

        PartDefinition r2_r1 = r2.addOrReplaceChild("r2_r1", CubeListBuilder.create().texOffs(17, 7).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3998F));

        PartDefinition r3 = rightlegs.addOrReplaceChild("r3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition r3_r1 = r3.addOrReplaceChild("r3_r1", CubeListBuilder.create().texOffs(17, 5).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3998F));

        PartDefinition leftlegs = thorax.addOrReplaceChild("leftlegs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition l1 = leftlegs.addOrReplaceChild("l1", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, -4.0F));

        PartDefinition l1_r1 = l1.addOrReplaceChild("l1_r1", CubeListBuilder.create().texOffs(17, 4).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

        PartDefinition l2 = leftlegs.addOrReplaceChild("l2", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, -2.0F));

        PartDefinition l2_r1 = l2.addOrReplaceChild("l2_r1", CubeListBuilder.create().texOffs(17, 2).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

        PartDefinition l3 = leftlegs.addOrReplaceChild("l3", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition l3_r1 = l3.addOrReplaceChild("l3_r1", CubeListBuilder.create().texOffs(17, 3).addBox(-1.0F, 0.0F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7418F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -2.1F, -4.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.35F, -1.0F));

        PartDefinition antennae = head.addOrReplaceChild("antennae", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -4.0F));

        PartDefinition leftantenna_r1 = antennae.addOrReplaceChild("leftantenna_r1", CubeListBuilder.create().texOffs(12, 16).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.1F, 0.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition rightantenna_r1 = antennae.addOrReplaceChild("rightantenna_r1", CubeListBuilder.create().texOffs(16, 9).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.1F, 0.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition leftpinsir = head.addOrReplaceChild("leftpinsir", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.9F, -4.0F));

        PartDefinition rightpinsir = head.addOrReplaceChild("rightpinsir", CubeListBuilder.create().texOffs(16, 14).addBox(-0.5F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.9F, -4.0F));

        PartDefinition abdomen = partdefinition.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.75F, -0.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(AntRenderState renderState) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(renderState.yRot, renderState.xRot);

        this.walkingAnimation.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 10f, 2.5f);
        this.attackAnimation.apply(renderState.attackAnimationState, renderState.ageInTicks);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -45f, 45f);
        headPitch = Mth.clamp(headPitch, -30f, 30f);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch * ((float)Math.PI / 180f);
    }
}
