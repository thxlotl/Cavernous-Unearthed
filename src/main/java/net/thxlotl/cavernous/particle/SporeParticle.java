package net.thxlotl.cavernous.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thxlotl.cavernous.util.MathUtil;

public class SporeParticle extends SingleQuadParticle {
    private float rotSpeed;

    public SporeParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, TextureAtlasSprite sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, sprite);

        RandomSource random = RandomSource.create();
        this.lifetime = Mth.randomBetweenInclusive(random, 500, 1000);
        this.friction = 1f;
        this.gravity = 0f;
        this.hasPhysics = false;

        this.quadSize = MathUtil.scaleFloat((float)Math.random(), 0.25f, 0.5f);

        this.rotSpeed = (float)Math.toRadians((double)MathUtil.scaleFloat((float)Math.random(), -30f, 30f));
//        this.rCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);
//        this.gCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);
//        this.bCol += MathUtil.scaleFloat((float)Math.random(), 0f, 0.003f);

        //this.roll = MathUtil.scaleFloat((float)Math.random(), 0f, 0.7f);


    }

    @Override
    public void tick() {
        super.tick();

        this.oRoll = this.roll;
        this.roll += this.rotSpeed / 20.0F;
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
            return new SporeParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite.get(random));
        }
    }
}
