package net.thxlotl.cavernous.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.ClientInput;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.block.entity.ModBlockEntities;
import net.thxlotl.cavernous.effect.ModEffects;
import net.thxlotl.cavernous.rendering.GhostFungusBlockEntityRenderer;
import net.thxlotl.cavernous.rendering.RenderUtil;
import net.thxlotl.cavernous.worldgen.ModBiomeData;
import net.thxlotl.mixin.ClientInputAccessor;
import org.joml.Vector3f;

@EventBusSubscriber(modid = Cavernous.MODID)
public class ModEvents {

    private static final int MUSHY_MIND_QUOTIENT = 20;

    @SubscribeEvent
    public static void rendering(ViewportEvent.ComputeFogColor event)
    {
        Entity entity = event.getCamera().getEntity();

        if (entity.level().isClientSide && event.getCamera().getFluidInCamera() == FogType.NONE) {
            ClientLevel level = (ClientLevel) entity.level();
            Vector3f color =
                    RenderUtil.getBaseColor(
                            level,
                            event.getCamera(),
                            Minecraft.getInstance().options.getEffectiveRenderDistance(),
                            RenderUtil.getDarkenWorldAmount(Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaPartialTick(true)));

            event.setRed(color.x);
            event.setGreen(color.y);
            event.setBlue(color.z);
        }
    }

    @SubscribeEvent
    public static void rendering(ViewportEvent.RenderFog event)
    {
        Entity entity = event.getCamera().getEntity();

        if (entity.level().isClientSide && event.getCamera().getFluidInCamera() == FogType.NONE)
        {
            ClientLevel level = (ClientLevel) entity.level();
            BiomeManager biomemanager = level.getBiomeManager();
            FogData fogData = event.getFogData();
            Vec3 pos = entity.position().subtract((double)2.0F, (double)2.0F, (double)2.0F).scale((double)0.25F);

            float sampledNear = (float) CubicSampler.gaussianSampleVec3(
                    pos,
                    (x, y, z) -> {
                        Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                        ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                        float value = key != null ? ModBiomeData.get(ModBiomeData.BIOME_FOG_NEAR_OFFSET, key) : 1.0f;
                        return new Vec3(value, value, value); // replicate float into RGB
                    }
            ).x();
            float sampledFar = (float) CubicSampler.gaussianSampleVec3(
                    pos,
                    (x, y, z) -> {
                        Holder<Biome> biomeAtQuart = biomemanager.getNoiseBiomeAtQuart(x, y, z);
                        ResourceKey<Biome> key = biomeAtQuart.unwrapKey().orElse(null);
                        float value = key != null ? ModBiomeData.get(ModBiomeData.BIOME_FOG_FAR_MULTIPLIER, key) : 1.0f;
                        return new Vec3(value, value, value); // replicate float into RGB
                    }
            ).x();
            //System.out.println("Fog Near: " + event.getNearPlaneDistance() + ", Far: " + event.getFarPlaneDistance() + ", Near Sample: " + sampledNear + ", Far Sample: " + sampledFar);

            if (Minecraft.getInstance().player.hasEffect(MobEffects.BLINDNESS) || Minecraft.getInstance().player.hasEffect(MobEffects.DARKNESS)) {
                //fogData.environmentalStart = 0.0f;
            } else {
                fogData.environmentalStart += sampledNear;
                fogData.environmentalEnd *= sampledFar;
            }

        }

    }

//    @SubscribeEvent
//    public static void registerBlockColor(RegisterColorHandlersEvent.Block event)
//    {
//        event.register(GhostFungusBlock::getColor, ModBlocks.GHOST_FUNGUS.get());
//    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.GHOST_FUNGUS.get(), GhostFungusBlockEntityRenderer::new);
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

//            System.out.println("Shift value: " + shift);
//            System.out.println("Strafe: " + strafe + " Forward: " + forward);

            input.keyPresses = new Input(input.keyPresses.forward(), input.keyPresses.backward(), input.keyPresses.left(), input.keyPresses.right(), jump, shift, input.keyPresses.sprint());
            setInputVector(input, new Vec2(strafe, forward));
        }
    }

    private static void setInputVector(ClientInput input, Vec2 moveVector) {
        ((ClientInputAccessor) input).setMoveVector(moveVector);
    }

}
