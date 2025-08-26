package io.github.happyhippo77.witchery2.block.blocks;

import io.github.happyhippo77.witchery2.block.ModBlocks;
import io.github.happyhippo77.witchery2.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class RowanTrapdoor extends TrapdoorBlock {
    public RowanTrapdoor(Settings settings) {
        super(settings, ModBlocks.ROWAN_BLOCK_SET);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        NbtCompound subCompound = new NbtCompound();
        subCompound.put("dimension", NbtString.of(world.getDimensionKey().getValue().toString()));
        NbtList posList = new NbtList();
        posList.add(NbtInt.of(pos.getX()));
        posList.add(NbtInt.of(pos.getY()));
        posList.add(NbtInt.of(pos.getZ()));
        subCompound.put("pos", posList);

        NbtCompound finalCompound = new NbtCompound();
        finalCompound.put("door", subCompound);

        ItemStack necessaryKey = new ItemStack(ModItems.ROWAN_DOOR_KEY);
        necessaryKey.setNbt(finalCompound);

        boolean hasKey = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().getStack(i).hasNbt()) {
                if (Objects.equals(player.getInventory().getStack(i).getNbt().get("door"), subCompound)) {
                    hasKey = true;
                }
            }
        }

        if (hasKey) {
            return super.onUse(state, world, pos, player, hand, hit);
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);

        NbtCompound subCompound = new NbtCompound();
        subCompound.put("dimension", NbtString.of(world.getDimensionKey().getValue().toString()));
        NbtList posList = new NbtList();
        posList.add(NbtInt.of(pos.getX()));
        posList.add(NbtInt.of(pos.getY()));
        posList.add(NbtInt.of(pos.getZ()));
        subCompound.put("pos", posList);

        NbtCompound finalCompound = new NbtCompound();
        finalCompound.put("door", subCompound);

        NbtCompound displayCompound = new NbtCompound();
        NbtList lore = new NbtList();
        lore.add(NbtString.of("[{\"text\":\"" + StringUtils.capitalize(world.getDimensionKey().getValue().getPath()) + ": " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "\",\"italic\":false,\"color\":\"gray\"}]"));
        displayCompound.put("Lore", lore);
        finalCompound.put("display", displayCompound);

        ItemStack necessaryKey = new ItemStack(ModItems.ROWAN_DOOR_KEY);
        necessaryKey.setNbt(finalCompound);

        Text tip = Text.literal(world.getDimensionKey().getValue().toString() + ": " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());

        if (placer instanceof PlayerEntity player) {
            player.giveItemStack(necessaryKey);
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        NbtCompound subCompound = new NbtCompound();
        subCompound.put("dimension", NbtString.of(world.getDimensionKey().getValue().toString()));
        NbtList posList = new NbtList();
        posList.add(NbtInt.of(pos.getX()));
        posList.add(NbtInt.of(pos.getY()));
        posList.add(NbtInt.of(pos.getZ()));
        subCompound.put("pos", posList);

        NbtCompound finalCompound = new NbtCompound();
        finalCompound.put("door", subCompound);

        ItemStack necessaryKey = new ItemStack(ModItems.ROWAN_DOOR_KEY);
        necessaryKey.setNbt(finalCompound);

        boolean hasKey = false;
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().getStack(i).hasNbt()) {
                if (Objects.equals(player.getInventory().getStack(i).getNbt().get("door"), subCompound)) {
                    hasKey = true;
                }
            }
        }

        ItemStack stack;
        if (hasKey) {
            stack = new ItemStack(ModBlocks.ROWAN_TRAPDOOR);
        } else {
            stack = new ItemStack(Items.STICK, 12);
        }

        float f = 0.7F;
        double d0 = (double)(world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
        double d1 = (double)(world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
        double d2 = (double)(world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
        ItemEntity entityitem = new ItemEntity(world, pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, stack);
        entityitem.setPickupDelay(10);

        if (!world.isClient) {
            if (!player.isCreative()) {
                world.spawnEntity(entityitem);
            }
        }
    }
}
