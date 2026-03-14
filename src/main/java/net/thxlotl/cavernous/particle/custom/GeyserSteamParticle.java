package net.thxlotl.cavernous.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.thxlotl.cavernous.util.MathUtil;

public class GeyserSteamParticle extends SingleQuadParticle {

    private static final float horizontalSpeed = 0.035f;
    private static final float verticalSpeed = 0.040f;
    private float steamAlpha = 0.65f;

    public GeyserSteamParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, 0, 0, 0, sprite);

        RandomSource random = RandomSource.create();

        // speed
        float angle = Mth.PI / 4 + random.nextFloat() * 0.4f - 0.2f;
        this.setParticleSpeed(horizontalSpeed * Mth.cos(angle), verticalSpeed * MathUtil.scaleFloat(random.nextFloat(), 1f, 1.5f), horizontalSpeed * Mth.sin(angle));

        // size
        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.6f, 0.9f);
        this.setSize(quadSize, quadSize);

        // alpha + lifetime
        this.setAlpha(0f);
        this.setLifetime(200);

        // physics
        this.friction = 1f;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.age > this.getLifetime() / 2f && this.steamAlpha > 0.01f) {
            this.steamAlpha -= 0.01f;
        }
        this.setAlpha(Math.min(steamAlpha, this.age / 10f));

        this.yd *= 0.993f;
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new GeyserSteamParticle(level, x, y, z, this.sprite.get(random));
        }
    }
}
