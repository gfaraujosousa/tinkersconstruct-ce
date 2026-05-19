package com.gfaraujosousa.tconstruct.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record ItemFluidResultRecipe(Ingredient ingredient, ResourceLocation fluid, int amount, ItemStack result, RecipeType<?> type, RecipeSerializer<?> serializer) implements Recipe<RecipeInput> {
    @Override
    public boolean matches(RecipeInput input, Level level) {
        return input.size() > 0 && ingredient.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return serializer;
    }

    @Override
    public RecipeType<?> getType() {
        return type;
    }

    public static class Serializer implements RecipeSerializer<ItemFluidResultRecipe> {
        private final RecipeType<?> type;
        private final java.util.function.Supplier<RecipeSerializer<?>> serializer;
        private final MapCodec<ItemFluidResultRecipe> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, ItemFluidResultRecipe> streamCodec;

        public Serializer(RecipeType<?> type, java.util.function.Supplier<RecipeSerializer<?>> serializer) {
            this.type = type;
            this.serializer = serializer;
            this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(ItemFluidResultRecipe::ingredient),
                    ResourceLocation.CODEC.fieldOf("fluid").forGetter(ItemFluidResultRecipe::fluid),
                    com.mojang.serialization.Codec.INT.fieldOf("amount").forGetter(ItemFluidResultRecipe::amount),
                    ItemStack.CODEC.fieldOf("result").forGetter(ItemFluidResultRecipe::result)
            ).apply(instance, (ingredient, fluid, amount, result) -> new ItemFluidResultRecipe(ingredient, fluid, amount, result, this.type, this.serializer.get())));
            this.streamCodec = StreamCodec.of(this::toNetwork, this::fromNetwork);
        }

        @Override
        public MapCodec<ItemFluidResultRecipe> codec() {
            return codec;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ItemFluidResultRecipe> streamCodec() {
            return streamCodec;
        }

        private ItemFluidResultRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            ResourceLocation fluid = ResourceLocation.STREAM_CODEC.decode(buffer);
            int amount = buffer.readVarInt();
            ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
            return new ItemFluidResultRecipe(ingredient, fluid, amount, result, type, serializer.get());
        }

        private void toNetwork(RegistryFriendlyByteBuf buffer, ItemFluidResultRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.ingredient());
            ResourceLocation.STREAM_CODEC.encode(buffer, recipe.fluid());
            buffer.writeVarInt(recipe.amount());
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result());
        }
    }
}
