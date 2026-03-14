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

public class GeyserAmbientParticle extends SingleQuadParticle{
    private float rotSpeed;

    public GeyserAmbientParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        RandomSource random = RandomSource.create();
        this.lifetime = Mth.randomBetweenInclusive(random, 20, 40);
        this.friction = 1f;
        this.gravity = 1f;
        this.hasPhysics = true;

        float f = this.random.nextFloat() * 0.15f;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;

        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.2f, 0.3f);

        this.rotSpeed = (float)Math.toRadians((double)MathUtil.scaleFloat((float)Math.random(), -10f, 10f));

    }

    @Override
    public void tick() {
        super.tick();

        this.oRoll = this.roll;
        this.roll += this.rotSpeed / 20.0F;
    }

    @Override
    protected SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new GeyserAmbientParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite.get(random));
        }
    }
}