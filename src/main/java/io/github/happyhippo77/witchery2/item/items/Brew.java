package io.github.happyhippo77.witchery2.item.items;

import io.github.happyhippo77.witchery2.util.brewing.Effect;
import io.github.happyhippo77.witchery2.util.brewing.Effects;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Brew extends Item {
    public Brew(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack != null && stack.getNbt() != null) {
            List<Text> arr = new ArrayList<>();

            NbtList effectsNbt = stack.getNbt().getList("effects", NbtElement.COMPOUND_TYPE);
            List<Effect> effects = new ArrayList<>();

            for (NbtElement e : effectsNbt) {
                effects.add(Effects.getFromNbt((NbtCompound) e));
            }

            if (effects.isEmpty()) {
                arr.add(Text.literal("Colored Water").formatted(Formatting.GRAY));
            }
            else {
                for (int i = 0; i < effectsNbt.size(); i++) {
                    Effect effect = Effects.getFromNbt((NbtCompound) effectsNbt.get(i));
                    String tip = effect.getName() + " [" + String.format("%01d:%02d", effect.getDuration() / 20 / 60, (int) (effect.getDurationScaling() / 20) % 60) + "]";
                    //String tip = effect.getName() + "[" + (int) Math.floor(effect.getDuration() / 20d / 60) + ":" + (int) (effect.getDurationScaling() / 20) % 60 + "]";
                    arr.add(Text.literal(tip).formatted(Formatting.GRAY));
                }
            }
            int drinkSpeed = stack.getNbt().getInt("drinkSpeed");
            String speed = null;
            if(drinkSpeed != 32) {
                if(drinkSpeed > 48) {
                    speed = "Very Slow";
                } else if(drinkSpeed > 32) {
                    speed = "Slow";
                } else if(drinkSpeed < 16) {
                    speed = "Very Fast";
                } else {
                    speed = "Fast";
                }
            }
            if (speed != null) {
                arr.add(Text.literal("Quaffing: " + speed).formatted(Formatting.BLUE));
            }

            tooltip.addAll(arr);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        NbtList effects = stack.getNbt().getList("effects", NbtElement.COMPOUND_TYPE);
        for (NbtElement compound : effects) {
            Effect effect = Effects.getFromNbt((NbtCompound) compound);
            effect.applyEntity(world, user);
        }

        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }
        if (playerEntity != null) {
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        if (playerEntity == null || !playerEntity.getAbilities().creativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }
            if (playerEntity != null) {
                playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }
        user.emitGameEvent(GameEvent.DRINK);

        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        int i = 32;
        if (stack.getNbt() != null) {
            i = stack.getNbt().getInt("drinkSpeed");
        }
        return i;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
