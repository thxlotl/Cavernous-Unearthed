package net.thxlotl.cavernous.entity.client.fungal_zombie;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.entity.client.ant.AntRenderState;
import net.thxlotl.cavernous.entity.custom.FungalZombie;

public class FungalZombieModel extends EntityModel<FungalZombieRenderState> {

    private final KeyframeAnimation walkingAnimation;
    //private final KeyframeAnimation attackAnimation;
    private final KeyframeAnimation armRaiseAnimation;
    private final KeyframeAnimation tweakAnimation;

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Cavernous.MODID, "fungal_zombie"), "main");
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;
    private final ModelPart left_leg;
    private final ModelPart right_leg;

    public FungalZombieModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.left_leg = root.getChild("left_leg");
        this.right_leg = root.getChild("right_leg");

        this.walkingAnimation = FungalZombieAnimations.WALK.bake(root);
        this.armRaiseAnimation = FungalZombieAnimations.ARM_RAISE.bake(root);
        this.tweakAnimation = FungalZombieAnimations.TWEAK.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition growth_r1 = head.addOrReplaceChild("growth_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -5.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -2.0F, -3.111F, 0.1719F, -2.9644F));

        PartDefinition growth_r2 = head.addOrReplaceChild("growth_r2", CubeListBuilder.create().texOffs(16, 32).addBox(0.0F, -5.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.5F, -2.0F, -3.111F, -0.1719F, 2.9644F));

        PartDefinition growth_r3 = head.addOrReplaceChild("growth_r3", CubeListBuilder.create().texOffs(16, 32).addBox(0.0F, -5.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, 0.0F, 0.1745F, 0.0F));

        PartDefinition growth_r4 = head.addOrReplaceChild("growth_r4", CubeListBuilder.create().texOffs(0, 32).addBox(-8.0F, -5.0F, 0.0F, 8.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, 0.0F, -0.1745F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -6.5F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.5F, 0.0F));

        PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 2.0F, 0.0F));

        PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 2.0F, 0.0F));

        PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(FungalZombieRenderState renderState) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(renderState.yRot, renderState.xRot);

        this.walkingAnimation.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 3.5f, 4f);
        //this.attackAnimation.apply(renderState.attackAnimationState, renderState.ageInTicks);
        
        if (renderState.isAggressive) {
            this.armRaiseAnimation.apply(((long) renderState.ageInTicks), 1f);
        }

        float f = renderState.attackTime;
        //AnimationUtils.animateZombieArms(this.left_arm, this.right_arm, renderState.isAggressive, f, renderState.ageInTicks);

        this.tweakAnimation.apply(renderState.tweakAnimationState, renderState.ageInTicks);

    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -45f, 45f);
        headPitch = Mth.clamp(headPitch, -45f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch * ((float)Math.PI / 180f);
    }
}
