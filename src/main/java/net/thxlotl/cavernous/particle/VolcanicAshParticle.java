package net.thxlotl.cavernous.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.thxlotl.cavernous.util.MathUtil;

public class VolcanicAshParticle extends SingleQuadParticle {

    private static final double wobbleSpeed = 0.05;

    private double initialY;
    private float wobbleAngle;

    public VolcanicAshParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        this.initialY = Mth.randomBetween(random, 0.02f, 0.05f);
        this.wobbleAngle = Mth.randomBetween(random, 0f, 2 * Mth.PI);

        //Physics
        this.setParticleSpeed(0, initialY, 0);
        this.gravity = 0f;
        this.friction = 1f;
        this.hasPhysics = true;
        this.lifetime = Mth.randomBetweenInclusive(random, 60, 80);

        //Display
        float f = this.random.nextFloat() * 0.05f + 0.95f;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;

        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.1f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();

        double mag = Mth.sin(((float) this.age * 0.9f)) * wobbleSpeed;

        this.xd += mag * Mth.cos(this.wobbleAngle);
        this.zd  += mag * Mth.sin(this.wobbleAngle);
    }


    @Override
    protected Layer getLayer() {
        return Layer.OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
            return new VolcanicAshParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite.get(random));
        }
    }
}
