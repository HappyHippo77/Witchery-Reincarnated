package io.github.happyhippo77.witchery2.block.entity.entities;

import io.github.happyhippo77.witchery2.Witchery2;
import io.github.happyhippo77.witchery2.block.entity.ModBlockEntities;
import io.github.happyhippo77.witchery2.item.ModItems;
import io.github.happyhippo77.witchery2.util.PoweredBlockEntity;
import io.github.happyhippo77.witchery2.util.brewing.*;
import io.github.happyhippo77.witchery2.util.brewing.crafting.CauldronRecipeRegistry;
import io.github.happyhippo77.witchery2.util.brewing.crafting.RecipeCheck;
import io.github.happyhippo77.witchery2.util.brewing.ingredients.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.*;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WitchsCauldronEntity extends PoweredBlockEntity {
    // Store the current value of the level
    private CauldronLevel level = CauldronLevel.EMPTY;
    private Color color = new Color(52, 95, 218);
    private final Color defaultColor = new Color(52, 95, 218);
    private int ticksHeated;
    private int ritualTicks;
    private final ArrayList<Item> ingredients = new ArrayList<>();
    private boolean colorOverride = false;
    private int altarCachedRangeScale = 0;

    public WitchsCauldronEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WITCHS_CAULDRON_ENTITY, pos, state);
    }

    public void setColorOverride(boolean colorOverride) {
        this.colorOverride = colorOverride;
    }

    public void setColor(Color color) {
        this.color = color;
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
    }

    public CauldronLevel getLevel() {
        return level;
    }

    public Color getColor() {
        return color;
    }

    public int getTicksHeated() {
        return ticksHeated;
    }

    public boolean isRitualInProgress() {
        return ritualTicks > 0;
    }

    public int getRitualTicks() {
        return ritualTicks;
    }
    public int getRitualSeconds() {
        return ritualTicks / 20;
    }

    public boolean isBoiling() {
        return ticksHeated == 100;
    }

    public int requiredPower = 0;

    public ArrayList<Item> getIngredients() {
        return ingredients;
    }

    public boolean isPowered() {
        return !this.ingredients.isEmpty() && (this.requiredPower == 0 || (this.altar != null && this.altar.getPower() > this.requiredPower));
    }

    public void addIngredient(Item ingredient) {
        ingredients.add(ingredient);

        for (Item item : ingredients) {
            if (IngredientRegistry.isIngredientType(item, IngredientUse.BREW_MODIFIER)) {
                ((BrewModifierIngredient)IngredientRegistry.fromItem(item)).getModifier().applyCauldron(this);
            }
        }

        if (!this.colorOverride) {
            Color tempColor = new Color(17);
            for (Item item : this.ingredients) {
                tempColor = new Color(37 * tempColor.getRGB() + item.getTranslationKey().hashCode());
            }
            this.color = tempColor;
        }
        markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
        this.requiredPower += IngredientRegistry.fromItem(ingredient).getRequiredPower();
    }

    public void clear() {
        ingredients.clear();
        this.level = CauldronLevel.EMPTY;
        this.colorOverride = false;
        this.requiredPower = 0;
        markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
    }

    private void collectBrew(PlayerEntity player) {
        List<List<Item>> ingredientsForEffects = new ArrayList<>();

        int firstIndex = 0;
        int currentIndex = 0;
        for (Item ingredient : this.ingredients) {
            if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.EFFECT)) {
                List<Item> effectIngredients = new ArrayList<>();
                for (int i = firstIndex; i <= currentIndex; i++) {
                    effectIngredients.add(this.ingredients.get(i));
                }
                ingredientsForEffects.add(effectIngredients);
                firstIndex = currentIndex + 1;
            }
            currentIndex++;
        }
        ingredientsForEffects.add(this.ingredients.subList(firstIndex, this.ingredients.size()));

        int extent = 1;
        int linger = 1;
        List<BrewModifier> brewModifiers = new ArrayList<>();
        DispersalType dispersalType = DispersalType.BASIC;
        List<Effect> effects = new ArrayList<>();

        int powerForEffect = 1;
        int durationMultiplierForEffect = 0;
        List<EffectModifier> effectModifiers = new ArrayList<>();

        for (List<Item> ingredients : ingredientsForEffects){
            for (Item ingredient : ingredients) {
                if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.POWER)) {
                    PowerIngredient powerIngredient = (PowerIngredient) IngredientRegistry.fromItem(ingredient);
                    if (powerForEffect < powerIngredient.getCeiling()) {
                        powerForEffect += 1;
                    }
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.EXTENT)) {
                    ExtentIngredient extentIngredient = (ExtentIngredient) IngredientRegistry.fromItem(ingredient);
                    if (extent < extentIngredient.getCeiling()) {
                        extent += 1;
                    }
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.LINGER)) {
                    LingerIngredient lingerIngredient = (LingerIngredient) IngredientRegistry.fromItem(ingredient);
                    if (linger < lingerIngredient.getCeiling()) {
                        linger += 1;
                    }
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.DISPERSAL)) {
                    dispersalType = ((DispersalIngredient) IngredientRegistry.fromItem(ingredient)).getDispersalType();
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.BREW_MODIFIER)) {
                    brewModifiers.add(((BrewModifierIngredient) IngredientRegistry.fromItem(ingredient)).getModifier());
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.DURATION)) {
                    DurationIngredient durationIngredient = (DurationIngredient) IngredientRegistry.fromItem(ingredient);
                    if (durationMultiplierForEffect < durationIngredient.getCeiling()) {
                        durationMultiplierForEffect += 2;
                    }
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.EFFECT_MODIFIER)) {
                    effectModifiers.add(((EffectModifierIngredient) IngredientRegistry.fromItem(ingredient)).getModifier());
                }
                else if (IngredientRegistry.isIngredientType(ingredient, IngredientUse.EFFECT)) {
                    Effect effect = ((EffectIngredient)IngredientRegistry.fromItem(ingredient)).getEffect().copy();
                    effect.setPower(powerForEffect);
                    if (durationMultiplierForEffect == 0) {
                        durationMultiplierForEffect = 1;
                    }
                    effect.setDuration(effect.getDuration() * durationMultiplierForEffect);
                    effect.applyModifiers(effectModifiers);
                    effects.add(effect);
                    powerForEffect = 1;
                    durationMultiplierForEffect = 1;
                    effectModifiers.clear();
                }
            }
        }

        if (this.isPowered()) {
            NbtCompound brewNbt = new NbtCompound();

            brewNbt.putString("dispersal", dispersalType.toString());
            brewNbt.putInt("extent", extent);
            brewNbt.putInt("linger", linger);

            NbtList effectsNbt = new NbtList();
            for (Effect effect : effects) {
                effectsNbt.add(effect.toNbt());
            }

            brewNbt.put("effects", effectsNbt);

            brewNbt.put("color", NbtInt.of(this.color.getRGB()));

            brewNbt.putInt("drinkSpeed", 32);

            ItemStack brewItem = dispersalType == DispersalType.BASIC?new ItemStack(ModItems.BREW):new ItemStack(ModItems.PROJECTILE_BREW);
            brewItem.setNbt(brewNbt);

            for (BrewModifier modifier : brewModifiers) {
                modifier.applyBrew(brewItem);
            }

            StringBuilder name = new StringBuilder();

            name.append(switch (dispersalType) {
                case INSTANT -> "Splash ";
                case GAS -> "Gas ";
                case LIQUID -> "Liquid ";
                case TRIGGER -> "Triggered ";
                case BASIC -> "";
            });
            name.append("Brew of ");
            if (effects.isEmpty()) {
                name.append("Colored Water");
            }
            else {
                for (int i = 0; i < effects.size(); i++) {
                    name.append(effects.get(i).getName());

                    if (i != effects.size() - 1) {
                        name.append(" and ");
                    }
                }
            }

            brewItem.setCustomName(Text.literal(name.toString()).setStyle(Style.EMPTY.withItalic(false)));

            // TODO: Allow for multiple brews to be obtained.
            if (!player.isCreative()) {
                player.getMainHandStack().decrement(1);
            }
            if (player.getMainHandStack().isEmpty()) {
                player.setStackInHand(Hand.MAIN_HAND, brewItem);
            }
            else {
                player.giveItemStack(brewItem);
            }

            if (!world.isClient) {
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS);
            }

            this.clear();
        }

    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (heldItem.getItem() == Items.WATER_BUCKET || PotionUtil.getPotion(heldItem) == Potions.WATER) {
            if (!level.isFull() && ingredients.isEmpty()) {
                if (!player.isCreative()) {
                    if (heldItem.getItem() == Items.WATER_BUCKET) {
                        player.setStackInHand(hand, new ItemStack(Items.BUCKET));
                    }
                    if (PotionUtil.getPotion(heldItem) == Potions.WATER) {
                        player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
                    }
                }
                if (heldItem.getItem() == Items.WATER_BUCKET) {
                    world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                if (PotionUtil.getPotion(heldItem) == Potions.WATER) {
                    world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
                world.emitGameEvent(null, GameEvent.FLUID_PLACE, pos);
                level = level.increase();
                markDirty();
                this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
                return ActionResult.SUCCESS;
            }
        }
        if (heldItem.getItem() == Items.BUCKET || heldItem.getItem() == Items.GLASS_BOTTLE) {
            if (!level.isEmpty()) {
                if (ingredients.isEmpty()){
                    if (!player.isCreative()) {
                        if (heldItem.getItem() == Items.BUCKET) {
                            player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
                        }
                        if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                            player.setStackInHand(hand, PotionUtil.setPotion(new ItemStack(Items.POTION), Potions.WATER));
                        }
                    }
                    if (heldItem.getItem() == Items.BUCKET) {
                        world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                    if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                        world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                    world.emitGameEvent(null, GameEvent.FLUID_PICKUP, pos);

                    level = level.decrease();
                    markDirty();
                    this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 0);
                }
                else {
                    collectBrew(player);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.SUCCESS;

    }

    int ticks = 0;
    public static void tick(World world, BlockPos pos, BlockState state, WitchsCauldronEntity entity) {
        if (world.isClient) {
            return;
        }

        if (entity.level.isEmpty() || entity.ingredients.isEmpty()) {
            entity.color = entity.defaultColor;
            entity.markDirty();
            world.updateListeners(pos, state, state, 0);
        }

        // Calculate Ticks Heated
        if ((world.getBlockState(pos.down(1)).getBlock() == Blocks.FIRE) && (!entity.level.isEmpty())) {
            if (entity.ticksHeated < 100) {
                entity.ticksHeated += 1;
                entity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }
        else {
            if (entity.ticksHeated > 0) {
                entity.ticksHeated = 0;
                entity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }

        List<Item> recipe = new ArrayList<>(entity.ingredients);

        if (entity.isPowered()) {
            RecipeCheck check = CauldronRecipeRegistry.checkRecipe(recipe);
            if (check.valid()) {
                entity.ritualTicks = 1;
            }
        }
        else {
            entity.ritualTicks = 0;
        }

        if (entity.isRitualInProgress()) {
            if (entity.ritualTicks < 200) {
                entity.ritualTicks++;
                entity.markDirty();
            }

            if (entity.ritualTicks == 200) {
                RecipeCheck check = CauldronRecipeRegistry.checkRecipe(recipe);

                ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, check.output(), 0, 0.2 ,0);
                world.spawnEntity(itemEntity);
                ((ServerWorld)world).spawnParticles(ParticleTypes.EFFECT, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5, 16, 0.25, 0.25, 0.25, 0);
                world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 0.5f);
                entity.clear();
                entity.ritualTicks = 0;
                entity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }
        // TODO: Do we really need this?
        else {
            if (entity.ritualTicks > 0) {
                entity.ritualTicks = 0;
                entity.markDirty();
            }
        }
    }

    // Serialize the BlockEntity
    @Override
    public void writeNbt(NbtCompound tag) {
        NbtList nbtIngredients = new NbtList();
        for (Item ingredient : ingredients) {
            nbtIngredients.add(NbtString.of(Registries.ITEM.getId(ingredient).toString()));
        }
        
        // Save the current value of the number to the tag
        tag.putInt("level", level.toInt());
        tag.putInt("colorR", color.getRed());
        tag.putInt("colorG", color.getGreen());
        tag.putInt("colorB", color.getBlue());
        tag.putInt("ticksHeated", ticksHeated);
        tag.putInt("ritualTicks", ritualTicks);
        tag.put("ingredients", nbtIngredients);

        super.writeNbt(tag);
    }

    // Deserialize the BlockEntity
    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);

        level = level.fromInt(tag.getInt("level"));
        color = new Color(tag.getInt("colorR"), tag.getInt("colorG"), tag.getInt("colorB"));
        ticksHeated = tag.getInt("ticksHeated");
        ritualTicks = tag.getInt("ritualTicks");

        ingredients.clear();
        NbtList nbtIngredients = tag.getList("ingredients", NbtList.STRING_TYPE);
        for (NbtElement ingredient: nbtIngredients) {
            Item item = Registries.ITEM.get(new Identifier(ingredient.toString().replace("\"", "")));

            ingredients.add(item);
            this.requiredPower += IngredientRegistry.fromItem(item).getRequiredPower();
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}