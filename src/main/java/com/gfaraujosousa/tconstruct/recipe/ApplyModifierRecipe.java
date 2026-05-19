package com.gfaraujosousa.tconstruct.recipe;

import com.gfaraujosousa.tconstruct.item.ModifiableToolItem;
import com.gfaraujosousa.tconstruct.registry.TCComponents;
import com.gfaraujosousa.tconstruct.registry.TCItems;
import com.gfaraujosousa.tconstruct.registry.TCRecipeSerializers;
import com.gfaraujosousa.tconstruct.tool.ToolStackData;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class ApplyModifierRecipe extends CustomRecipe {
    public ApplyModifierRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        return findTool(input) >= 0 && count(input, Items.REDSTONE.getDefaultInstance()) == 1 && input.ingredientCount() == 2;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        int toolSlot = findTool(input);
        if (toolSlot < 0) {
            return ItemStack.EMPTY;
        }
        ItemStack result = input.getItem(toolSlot).copyWithCount(1);
        ToolStackData data = ModifiableToolItem.getToolData(result).addModifier(ToolStackData.HASTE);
        result.set(TCComponents.TOOL_STACK.get(), data);
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TCRecipeSerializers.APPLY_MODIFIER.get();
    }

    private static int findTool(CraftingInput input) {
        for (int i = 0; i < input.size(); i++) {
            if (input.getItem(i).is(TCItems.FLINT_PICKAXE.get())) {
                return i;
            }
        }
        return -1;
    }

    private static int count(CraftingInput input, ItemStack target) {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && stack.is(target.getItem())) {
                count++;
            }
        }
        return count;
    }
}
