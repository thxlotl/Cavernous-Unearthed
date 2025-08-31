package net.thxlotl.cavernous.event;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.CubicSampler;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.thxlotl.cavernous.Cavernous;
import net.thxlotl.cavernous.rendering.RenderUtil;
import net.thxlotl.cavernous.worldgen.ModBiomeData;
import org.joml.Vector3f;

@EventBusSubscriber(modid = Cavernous.MODID)
public class ModEvents {

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

            fogData.environmentalStart += sampledNear;
            fogData.environmentalEnd *= sampledFar;

        }

    }

}
