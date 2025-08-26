package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.entity.entities.BrewEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ProjectileBrew extends Brew {
    public ProjectileBrew(Settings settings) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5f, 0.4f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
            BrewEntity brewEntity = new BrewEntity(world, user);
            brewEntity.setItem(itemStack);
            brewEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20f, 0.75f, 1.0f);
            world.spawnEntity(brewEntity);
        }
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
