package net.thxlotl.cavernous.event;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
///import net.minecraft.util.CubicSampler;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.GaussianSampler;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.ModBlocks;
import net.thxlotl.cavernous.effect.ModEffects;
import net.thxlotl.cavernous.rendering.RenderUtil;
import net.thxlotl.cavernous.util.CubicSampler;
import net.thxlotl.cavernous.worldgen.biome.BiomeData;
///import net.thxlotl.mixin.ClientInputAccessor;
import net.thxlotl.mixin.ClientInputAccessor;
import org.joml.Vector3f;

@EventBusSubscriber(modid = Cavernous.MODID)
public class ModEvents {

    private static final int MUSHY_MIND_QUOTIENT = 20;

    @SubscribeEvent
    public static void rendering(ViewportEvent.ComputeFogColor event)
    {
        Camera cam = event.getCamera();
        Entity entity = cam.entity();

        // Smooth magma fog color
        if (cam.getBlockAtCamera() == ModBlocks.SOFT_MAGMA_BLOCK.get().defaultBlockState()) {
            setFogColors(event, 0.6f, 0.0941f, 0f);
        }
        else if (entity.level().isClientSide() && event.getCamera().getFluidInCamera() == FogType.NONE) {

            Vec3 vec3 = cam.position().subtract((double)2.0F, (double)2.0F, (double)2.0F).scale((double)0.25F);
            BiomeManager biomemanager = entity.level().getBiomeManager();

            float sampledOverride = (float) CubicSampler.gaussianSampleVec3(
                    vec3,
                    (x, y, z) -> {
                        Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                        ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                        float value = key != null ? BiomeData.get(BiomeData.BIOME_BRIGHTNESS_OVERRIDE, key) : 1.0f;
                        return new Vec3(value, value, value); // replicate float into RGB
                    }
            ).x();

            if (sampledOverride > 0) {
                ClientLevel level = (ClientLevel) entity.level();

                int caveColor = RenderUtil.getCaveFogColor(entity.level(), cam, vec3);
                int baseColor = RenderUtil.getBaseColor(
                        level,
                        event.getCamera(),
                        Minecraft.getInstance().options.getEffectiveRenderDistance(),
                        RenderUtil.getDarkenWorldAmount(Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(false)));

                int resolvedColor = ARGB.linearLerp(sampledOverride, baseColor, caveColor);

                setFogColors(event, ARGB.vector3fFromRGB24(resolvedColor));
            }
        }
    }

    private static void setFogColors(ViewportEvent.ComputeFogColor event, float red, float green, float blue) {
        event.setRed(red);
        event.setGreen(green);
        event.setBlue(blue);
    }
    private static void setFogColors(ViewportEvent.ComputeFogColor event, Vector3f color) {
        event.setRed(color.x);
        event.setGreen(color.y);
        event.setBlue(color.z);
    }

    @SubscribeEvent
    public static void rendering(ViewportEvent.RenderFog event)
    {
        Camera cam = event.getCamera();

        // Make fog close in soft magma
        // Maybe change this check to make give it a small range so there is less of a jump when entering magma
        if (cam.getBlockAtCamera() == ModBlocks.SOFT_MAGMA_BLOCK.get().defaultBlockState()) {

            Entity entity = cam.entity();
            FogData fogData = event.getFogData();

            float f = 16 * Minecraft.getInstance().options.getEffectiveRenderDistance();
            if (entity.isSpectator()) {
                fogData.environmentalStart = -8.0F;
                fogData.environmentalEnd = f * 0.5F;
            } else {
                label14: {
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingentity = (LivingEntity)entity;
                        if (livingentity.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                            fogData.environmentalStart = 0.0F;
                            fogData.environmentalEnd = 5.0F;
                            break label14;
                        }
                    }

                    fogData.environmentalStart = 0.25F;
                    fogData.environmentalEnd = 1.0F;
                }
            }

            fogData.skyEnd = fogData.environmentalEnd;
            fogData.cloudEnd = fogData.environmentalEnd;

        }
    }


    @SubscribeEvent
    public static void movementInputEvent(MovementInputUpdateEvent event) {

        Player player = event.getEntity();
        MobEffectInstance mushyInstance = player.getEffect(ModEffects.MUSHY_MIND_EFFECT);

        if (mushyInstance != null)
        {
            ClientInput input = event.getInput();
            Vec2 initialMovement = input.getMoveVector();

            int second = Math.round(Math.round((float) player.tickCount / (float) MUSHY_MIND_QUOTIENT));
            RandomSource random = RandomSource.create(second);
            random.nextInt();

            boolean jump = player.horizontalCollision || input.keyPresses.jump();
            boolean shift = random.nextBoolean() || input.keyPresses.shift();
            float forward = random.nextInt(3) - 1 + initialMovement.y;
            float strafe = random.nextInt(3) - 1 + initialMovement.x;

            input.keyPresses = new Input(input.keyPresses.forward(), input.keyPresses.backward(), input.keyPresses.left(), input.keyPresses.right(), jump, shift, input.keyPresses.sprint());
            setInputVector(input, new Vec2(strafe, forward));
        }
    }

    private static void setInputVector(ClientInput input, Vec2 moveVector) {
        ((ClientInputAccessor) input).setMoveVector(moveVector);
    }

}
