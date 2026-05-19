package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.recipe.ApplyModifierRecipe;
import com.gfaraujosousa.tconstruct.recipe.ItemFluidResultRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TinkersConstructCommunityEdition.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ApplyModifierRecipe>> APPLY_MODIFIER =
            REGISTRY.register("apply_modifier", () -> new SimpleCraftingRecipeSerializer<>(ApplyModifierRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemFluidResultRecipe>> MELTING =
            REGISTRY.register("melting", () -> new ItemFluidResultRecipe.Serializer(TCRecipeTypes.MELTING.get(), TCRecipeSerializers.MELTING::get));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ItemFluidResultRecipe>> CASTING =
            REGISTRY.register("casting", () -> new ItemFluidResultRecipe.Serializer(TCRecipeTypes.CASTING.get(), TCRecipeSerializers.CASTING::get));

    private TCRecipeSerializers() {
    }
}
