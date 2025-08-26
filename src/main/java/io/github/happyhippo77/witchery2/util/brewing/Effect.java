package io.github.happyhippo77.witchery2.util.brewing;

import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

interface ApplyBlockInterface {
    void apply(Effect effect, World world, BlockPos pos);
}

interface ApplyEntityInterface {
    void apply(Effect effect, World world, Entity entity);
}

public class Effect {
    private final String id;
    private final String name;
    private final String reversedName;
    private final ApplyBlockInterface blockEffects;
    private final ApplyEntityInterface entityEffects;
    private int duration;

    // Do not modify these unless via modifiers
    private boolean hasParticles = true;
    private boolean inverted = false;
    private boolean applyBlock = true;
    private boolean applyEntity = true;


    private int power = 1;
    private double powerScaling = 1;
    private double durationScaling = 1;
    private final boolean isCopy;

    public Effect(String id, String name, String reversedName, ApplyBlockInterface blockEffects, ApplyEntityInterface entityEffects, int duration) {
        this.id = id;
        this.name = name;
        this.reversedName = reversedName;
        this.blockEffects = blockEffects;
        this.entityEffects = entityEffects;
        this.isCopy = false;
        this.duration = duration;
    }
    private Effect(String id, String name, String reversedName, ApplyBlockInterface blockEffects, ApplyEntityInterface entityEffects, int duration, boolean hasParticles, boolean inverted, boolean applyBlock, boolean applyEntity) {
        this.id = id;
        this.name = name;
        this.reversedName = reversedName;
        this.blockEffects = blockEffects;
        this.entityEffects = entityEffects;
        this.duration = duration;
        this.hasParticles = hasParticles;
        this.inverted = inverted;
        this.applyBlock = applyBlock;
        this.applyEntity = applyEntity;
        this.isCopy = true;
    }

    public void setHasParticles(boolean hasParticles) {
        this.hasParticles = hasParticles;
    }
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public void setApplyBlock(boolean applyBlock) {
        this.applyBlock = applyBlock;
    }

    public void setApplyEntity(boolean applyEntity) {
        this.applyEntity = applyEntity;
    }

    public void setPower(int power) {
        if (this.isCopy) {
            this.power = power;
        }
        else {
            Witchery2.LOGGER.error("Effect was modified directly! Create a copy using Effect.copy() first.");
        }
    }
    public void setDuration(int duration) {
        if (this.isCopy) {
            this.duration = duration;
        }
        else {
            Witchery2.LOGGER.error("Effect was modified directly! Create a copy using Effect.copy() first.");
        }
    }

    public double getPowerScaling() {
        return powerScaling;
    }

    public void setPowerScaling(double powerScaling) {
        this.powerScaling = powerScaling;
    }

    public double getDurationScaling() {
        return durationScaling;
    }

    public void setDurationScaling(double durationScaling) {
        this.durationScaling = durationScaling;
    }

    public String getId() {
        return id;
    }

    public boolean hasParticles() {
        return hasParticles;
    }

    public String getName() {
        return this.isInverted()?this.reversedName:this.name;
    }

    public boolean isInverted() {
        return inverted;
    }

    public boolean shouldApplyBlock() {
        return applyBlock;
    }

    public boolean shouldApplyEntity() {
        return applyEntity;
    }

    public int getPower() {
        return power;
    }

    public int getDuration() {
        return duration;
    }

    public void applyModifiers(List<EffectModifier> modifiers) {
        if (this.isCopy) {
            for (EffectModifier modifier : modifiers) {
                modifier.apply(this);
            }
        }
        else {
            Witchery2.LOGGER.error("Effect was modified directly! Create a copy using Effect.copy() first.");
        }
    }

    public void applyBlock(World world, BlockPos pos) {
        if (this.shouldApplyBlock()) {
            this.blockEffects.apply(this, world, pos);
        }
    }

    public void applyEntity(World world, Entity entity) {
        if (this.shouldApplyEntity()) {
            this.entityEffects.apply(this, world, entity);
        }
    }

    public void apply(World world, BlockPos pos, @Nullable Entity entity) {
        if (this.shouldApplyBlock()) {
            this.blockEffects.apply(this, world, pos);
        }
        if (this.shouldApplyEntity()) {
            this.entityEffects.apply(this, world, entity);
        }
    }

    public Effect copy() {
        return new Effect(this.id, this.name, this.reversedName, this.blockEffects, this.entityEffects, this.duration, this.hasParticles, this.inverted, this.applyBlock, this.applyEntity);
    }

    public NbtCompound toNbt() {
        NbtCompound effect = new NbtCompound();
        effect.putString("id", this.id);
        effect.putInt("power", this.power);
        effect.putInt("duration", this.duration);
        effect.putBoolean("hasParticles", this.hasParticles);
        effect.putBoolean("inverted", this.inverted);
        effect.putBoolean("applyBlock", this.applyBlock);
        effect.putBoolean("applyEntity", this.applyEntity);
        return effect;
    }
}
