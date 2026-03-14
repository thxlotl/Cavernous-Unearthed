package net.thxlotl.cavernous.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.thxlotl.cavernous.util.MathUtil;

public class SporeParticle extends SingleQuadParticle {
    private float rotSpeed;
    private boolean lamp;

    public SporeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite, boolean lamp) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);
        RandomSource random = RandomSource.create();

        this.lifetime = Mth.randomBetweenInclusive(random, 300, 320);
        this.friction = 0.985f;
        this.gravity = 0f;
        this.hasPhysics = false;

        this.lamp = lamp;

        //this.setAlpha(0.75f);

        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.25f, 0.5f);

        this.rotSpeed = (float)Math.toRadians((double)MathUtil.scaleFloat((float)Math.random(), -15f, 15f));
//        this.rCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);
//        this.gCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);
//        this.bCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);

        //this.roll = MathUtil.scaleFloat((float)Math.random(), 0f, 0.7f);

        this.setAlpha(MathUtil.scaleFloat((float)Math.random(), 0.9f, 1f));
    }

    @Override
    protected int getLightColor(float partialTick) {
        if (this.lamp) {
            return 255;
        }
        else {
            return super.getLightColor(partialTick);
        }
    }

    @Override
    public void tick() {
        super.tick();

        this.oRoll = this.roll;
        this.roll += this.rotSpeed / 20.0F;

        if (this.alpha > 0.02f) {
            this.alpha -= 0.01f;
        }
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
            return new SporeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite.get(random), false);
        }
    }
    public static class LampshroomProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public LampshroomProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new SporeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite.get(random), true);
        }
    }
}
