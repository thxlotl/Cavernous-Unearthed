package net.thxlotl.mixin;

import com.mojang.blaze3d.vertex.QuadInstance;
import net.minecraft.util.ARGB;
import net.thxlotl.cavernous.rendering.SaturatableQuad;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(QuadInstance.class)
public abstract class QuadInstanceMixin implements SaturatableQuad, QuadInstanceAccessor {

    @Shadow private int color0;
    @Shadow private int color1;
    @Shadow private int color2;
    @Shadow private int color3;


    @Unique
    public QuadInstance saturateColor(float saturationMultiplier) {

        QuadInstanceAccessor qia = (QuadInstanceAccessor) this;

        color0 = (saturate(color0, saturationMultiplier));
        color1 = (saturate(color1, saturationMultiplier));
        color2 = (saturate(color2, saturationMultiplier));
        color3 = (saturate(color3, saturationMultiplier));

        return (QuadInstance)(Object)this;

    }

    @Override
    public QuadInstance getSelf() {
        return (QuadInstance)(Object)this;
    }

    private static int saturate(int color, float saturationMultiplier) {
        int a = ARGB.alpha(color);
        float r = ARGB.red(color) / 255f;
        float g = ARGB.green(color) / 255f;
        float b = ARGB.blue(color) / 255f;

        float max = Math.max(r, Math.max(g, b));
        float min = Math.min(r, Math.min(g, b));
        float delta = max - min;

        float h = 0f;
        float s = (max == 0f) ? 0f : delta / max;
        float v = max;

        if (delta != 0f) {
            if (max == r) {
                h = ((g - b) / delta) % 6f;
            } else if (max == g) {
                h = ((b - r) / delta) + 2f;
            } else {
                h = ((r - g) / delta) + 4f;
            }
            h /= 6f;
            if (h < 0) h += 1f;
        }

        s *= saturationMultiplier;
        s = Math.max(0f, Math.min(1f, s));

        float c = v * s;
        float x = c * (1 - Math.abs((h * 6f) % 2 - 1));
        float m = v - c;

        float r1 = 0, g1 = 0, b1 = 0;

        int sector = (int)(h * 6f);
        switch (sector) {
            case 0 -> { r1 = c; g1 = x; b1 = 0; }
            case 1 -> { r1 = x; g1 = c; b1 = 0; }
            case 2 -> { r1 = 0; g1 = c; b1 = x; }
            case 3 -> { r1 = 0; g1 = x; b1 = c; }
            case 4 -> { r1 = x; g1 = 0; b1 = c; }
            case 5 -> { r1 = c; g1 = 0; b1 = x; }
        }

        int newR = clamp((int)((r1 + m) * 255));
        int newG = clamp((int)((g1 + m) * 255));
        int newB = clamp((int)((b1 + m) * 255));

        return ARGB.color(a, newR, newG, newB);
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
}
