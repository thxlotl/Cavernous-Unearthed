package net.thxlotl.cavernous.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.thxlotl.cavernous.util.MathUtil;

public class GeyserBurstParticle extends SingleQuadParticle{

    private static final double maxPower = 0.65;

    private float rotSpeed;
    protected double theta;
    protected double horizontalPower;
    protected double yInitial;

    public GeyserBurstParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite, double theta, double horizontalPower) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        this.theta = theta;
        this.horizontalPower = horizontalPower;
        this.yInitial = ySpeed;

        this.rotSpeed = (float)Math.toRadians((double)MathUtil.scaleFloat((float)Math.random(), -10f, 10f));

        RandomSource random = RandomSource.create();
        this.lifetime = Mth.randomBetweenInclusive(random, 25, 45);
        this.friction = 1f;
        this.gravity = 1f;
        this.hasPhysics = true;

        float f = this.random.nextFloat() * 0.07f + 0.93f;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;

        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.45f, 0.5f);
        this.setSize(0.2F, 0.2F);

        this.setParticleSpeed(xSpeed, ySpeed, zSpeed);

        this.setAlpha(0.6f);

    }

    @Override
    public void tick() {
        super.tick();

        double vx = this.xd;
        double vy = this.yd;
        double vz = this.zd;

        double c = (Math.pow(this.yInitial, 2) / maxPower) * this.horizontalPower / 10f;

        vx += c * Math.cos(this.theta);
        vz += c * Math.sin(this.theta);

        this.setParticleSpeed(vx, vy, vz);

        if (this.alpha > 0.02f) {
            this.alpha -= 0.01f;
        }

        this.oRoll = this.roll;
        this.roll += this.rotSpeed / 20.0F;
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class BurstProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public BurstProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {

            double theta = MathUtil.scaleFloat(random.nextDouble(), 0, 2 * Math.PI);
            double horizontalPower = MathUtil.scaleFloat(random.nextDouble(), 0.05, 0.1);
            double initialSpeed = MathUtil.scaleFloat(random.nextDouble(), 0.01, 0.05);

            double vx = Math.cos(theta) * initialSpeed;
            double vy = random.nextDouble() * 0.55f + 0.3f;
            double vz = Math.sin(theta) * initialSpeed;

            return new GeyserBurstParticle(level, x, y, z, vx, vy, vz, this.sprite.get(random), theta, horizontalPower);
        }
    }
    public static class BubbleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public BubbleProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {

            double theta = MathUtil.scaleFloat(random.nextDouble(), 0, 2 * Math.PI);
            double horizontalPower = MathUtil.scaleFloat(random.nextDouble(), 0.02, 0.1);
            double initialSpeed = MathUtil.scaleFloat(random.nextDouble(), 0.04, 0.06);

            double vx = Math.cos(theta) * initialSpeed;
            double vy = random.nextDouble() * 0.1f + 0.1f;
            double vz = Math.sin(theta) * initialSpeed;

            return new GeyserBurstParticle(level, x, y, z, vx, vy, vz, this.sprite.get(random), theta, horizontalPower);
        }
    }
}