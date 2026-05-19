package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.recipe.ItemFluidResultRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> REGISTRY = DeferredRegister.create(Registries.RECIPE_TYPE, TinkersConstructCommunityEdition.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemFluidResultRecipe>> MELTING =
            REGISTRY.register("melting", () -> RecipeType.simple(TinkersConstructCommunityEdition.location("melting")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<ItemFluidResultRecipe>> CASTING =
            REGISTRY.register("casting", () -> RecipeType.simple(TinkersConstructCommunityEdition.location("casting")));

    private TCRecipeTypes() {
    }
}
