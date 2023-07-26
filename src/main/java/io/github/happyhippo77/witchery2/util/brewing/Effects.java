package io.github.happyhippo77.witchery2.util.brewing;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Effects {
    public static final Effect SNOW = new Effect("snow", "Snow", "Fire",
            (effect, world, pos) -> {
            BlockState blockState = effect.isInverted()?Blocks.FIRE.getDefaultState():Blocks.SNOW.getDefaultState();
            int radius = effect.getPower();

            BlockPos corner1 = new BlockPos(pos.getX() + 1 - radius, pos.getY(), pos.getZ() + 1 - radius);
            BlockPos corner2 = new BlockPos(pos.getX() - 1 + radius, pos.getY(), pos.getZ() - 1 + radius);

            for (int x = corner1.getX(); x <= corner2.getX(); x++) {
                for (int z = corner1.getZ(); z <= corner2.getZ(); z++) {
                    BlockPos p = new BlockPos(x, pos.getY(), z);
                    world.setBlockState(p, blockState);
                }
            }
        },
        (effect, world, entity) -> {
            if (!effect.isInverted()) {
                    entity.inPowderSnow = true;
                }
            else {
                System.out.println(100 * ((effect.getDuration() * effect.getDurationScaling() / 3600)));
                entity.setFireTicks((int) (100 * (((effect.getDuration() * effect.getDurationScaling()) / 3600))));
            }
        });

    public static final Effect SWIM_SPEED = new Effect("swim_speed", "Swim Speed", "Swim Speed",
            (effect, world, pos) -> {

            },
            (effect, world, entity) -> {
                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, (int) (effect.getDuration() * effect.getDurationScaling())));
                }
            });

    public static final Map<String, Effect> effects = Map.of(
            SNOW.getId(), SNOW,
            SWIM_SPEED.getId(), SWIM_SPEED
    );

    public static Effect getFromId(String id) {
        return effects.get(id);
    }

    public static Effect getFromNbt(NbtCompound nbt) {
        Effect effect = getFromId(nbt.getString("id")).copy();
        effect.setPower(nbt.getInt("power"));
        effect.setDuration(nbt.getInt("duration"));
        effect.setHasParticles(nbt.getBoolean("hasParticles"));
        effect.setInverted(nbt.getBoolean("inverted"));
        effect.setApplyBlock(nbt.getBoolean("applyBlock"));
        effect.setApplyEntity(nbt.getBoolean("applyEntity"));

        return effect;
    }
}
