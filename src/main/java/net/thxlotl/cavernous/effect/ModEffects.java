package net.thxlotl.cavernous.effect;


import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thxlotl.cavernous.Cavernous;

public class ModEffects {


    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Cavernous.MODID);


    public static final Holder<MobEffect> MUSHY_MIND_EFFECT = MOB_EFFECTS.register(
            "mushy_mind",
            () -> new MushyMindEffect(MobEffectCategory.NEUTRAL, 0x36ebab)
    );


    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }

}
