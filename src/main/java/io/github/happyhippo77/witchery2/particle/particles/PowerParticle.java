package io.github.happyhippo77.witchery2.particle.particles;

import io.github.happyhippo77.witchery2.Witchery2;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class PowerParticle extends SpriteBillboardParticle {

    private final SpriteProvider spriteProvider;
    private final boolean canMove;
    private final boolean circling;
    protected PowerParticle(ClientWorld level, double xCoord, double yCoord, double zCoord, SpriteProvider spriteProvider, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);

        this.scale = 0.06F;
        Random random = new Random();

        this.maxAge = 25 + random.nextInt(10);

        this.canMove = true;
        this.gravityStrength = 0.25F;

        this.red = (float) Witchery2.powerParticleDataSetter.getRed() / 255;
        this.green = (float) Witchery2.powerParticleDataSetter.getGreen() / 255;
        this.blue = (float) Witchery2.powerParticleDataSetter.getBlue() / 255;
        this.alpha = (float) Witchery2.powerParticleDataSetter.getAlpha() / 255;
        this.circling = Witchery2.powerParticleDataSetter.isCircling();

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
            if(this.circling) {
                Vec3d motion = new Vec3d(this.velocityX, this.velocityY, this.velocityZ);
                motion = motion.rotateY(0.5f);
                this.velocityX = motion.x * 1.08D;
                this.velocityY = motion.y * 0.85D;
                this.velocityZ = motion.z * 1.08D;
            } else {
                this.velocityY -= 0.04D * this.gravityStrength;
            }

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
            return new PowerParticle(level, x, y, z, this.sprites, dx, dy, dz);
        }
    }
}
