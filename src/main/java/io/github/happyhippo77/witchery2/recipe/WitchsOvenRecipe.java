package io.github.happyhippo77.witchery2.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
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
    private final Ingredient input;


    public WitchsOvenRecipe(Identifier id, ItemStack output, ItemStack fume, Ingredient input) {
        this.id = id;
        this.output = output;
        this.fume = fume;
        this.input = input;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) {
            return false;
        }

        return input.test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    public Ingredient getInput() {
        return input;
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
            Ingredient input = Ingredient.fromJson(JsonHelper.getObject(json, "input"));
            //ItemStack input = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "input"));

            return new WitchsOvenRecipe(id, output, fume, input);
        }

        @Override
        public WitchsOvenRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            ItemStack fume = buf.readItemStack();
            return new WitchsOvenRecipe(id, output, fume, input);
        }

        @Override
        public void write(PacketByteBuf buf, WitchsOvenRecipe recipe) {
            recipe.getInput().write(buf);
            buf.writeItemStack(recipe.getOutput());
            buf.writeItemStack(recipe.getFume());
        }
    }
}
