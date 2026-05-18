package net.thxlotl.cavernous.rendering;

import com.mojang.blaze3d.vertex.QuadInstance;

public interface SaturatableQuad {

    QuadInstance saturateColor(float saturation);

    QuadInstance getSelf();
}
