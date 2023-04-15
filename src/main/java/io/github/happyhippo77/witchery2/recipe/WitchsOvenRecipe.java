package io.github.happyhippo77.witchery2.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class WitchsOvenRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final ItemStack fume;
    private final float fumeChance;
    private final DefaultedList<Ingredient> recipeItems;

    public WitchsOvenRecipe(Identifier id, ItemStack output, ItemStack fume, float fumeChance, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.fume = fume;
        this.fumeChance = fumeChance;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return getOutput();
    }

    public ItemStack getFume() {
        return fume;
    }

    public float getFumeChance() {
        return fumeChance;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<WitchsOvenRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "witchs_oven";
    }

    public static class Serializer implements RecipeSerializer<WitchsOvenRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "witchs_oven";
        // this is the name given in the json file

        @Override
        public WitchsOvenRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            ItemStack fume = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "fume"));
            float fumeChance = JsonHelper.getFloat(json, "fumeChance");

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new WitchsOvenRecipe(id, output, fume, fumeChance, inputs);
        }

        @Override
        public WitchsOvenRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            ItemStack fume = buf.readItemStack();
            float fumeChance = buf.readFloat();
            return new WitchsOvenRecipe(id, output, fume, fumeChance, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, WitchsOvenRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
            buf.writeItemStack(recipe.getFume());
            buf.writeFloat(recipe.getFumeChance());
        }
    }
}
