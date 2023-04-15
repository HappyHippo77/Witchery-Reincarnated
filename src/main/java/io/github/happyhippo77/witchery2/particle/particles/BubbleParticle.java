package io.github.happyhippo77.witchery2.particle.particles;

import io.github.happyhippo77.witchery2.Witchery2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

import java.util.Random;

public class BubbleParticle extends SpriteBillboardParticle {

    private final SpriteProvider spriteProvider;
    private final boolean canMove;
    protected BubbleParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, SpriteProvider spriteProvider, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        //this.velocityMultiplier = 0.6F;
        this.scale = 0.04f;
        Random random = new Random();

        if(random.nextInt(4) == 0) {
            this.gravityStrength = -0.02f;
            this.canMove = true;
            this.maxAge = 15 + random.nextInt(10);
        } else {
            this.gravityStrength = 0.0F;
            this.canMove = false;
            this.maxAge = 10 + random.nextInt(10);
        }

        this.red = (float) Witchery2.bubbleParticleDataSetter.getRed() / 255;
        this.green = (float) Witchery2.bubbleParticleDataSetter.getGreen() / 255;
        this.blue = (float) Witchery2.bubbleParticleDataSetter.getBlue() / 255;
        this.alpha = (float) Witchery2.bubbleParticleDataSetter.getAlpha() / 255;

        this.velocityX = xd;
        this.velocityY = yd;
        this.velocityZ = zd;

        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.isAlive() && this.canMove) {
            this.velocityY -= 0.04D * this.gravityStrength;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if(super.onGround) {
                this.velocityX *= 0.699999988079071D;
                this.velocityZ *= 0.699999988079071D;
            }
        }
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.spriteProvider);
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new BubbleParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
