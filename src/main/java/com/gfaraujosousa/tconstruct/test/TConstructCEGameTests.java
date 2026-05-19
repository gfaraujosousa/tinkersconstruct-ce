package com.gfaraujosousa.tconstruct.test;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.item.ModifiableToolItem;
import com.gfaraujosousa.tconstruct.registry.TCComponents;
import com.gfaraujosousa.tconstruct.registry.TCItems;
import com.gfaraujosousa.tconstruct.tool.ToolData;
import com.gfaraujosousa.tconstruct.tool.ToolStackData;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(TinkersConstructCommunityEdition.MODID)
@PrefixGameTestTemplate(false)
public final class TConstructCEGameTests {
    private TConstructCEGameTests() {
    }

    @GameTest(template = "empty", timeoutTicks = 20)
    public static void datapackToolDataLoaded(GameTestHelper helper) {
        helper.assertTrue(ToolData.material(ToolStackData.FLINT) != null, "Expected flint material to load from datapack JSON");
        helper.assertTrue(ToolData.modifier(ToolStackData.HASTE) != null, "Expected haste modifier to load from datapack JSON");
        helper.assertTrue(ToolData.material(ToolStackData.FLINT).stats().durability() == 64, "Expected flint material durability from JSON");
        helper.assertTrue(ToolData.modifier(ToolStackData.HASTE).maxLevel() == 3, "Expected haste modifier max level from JSON");
        helper.succeed();
    }

    @GameTest(template = "empty", timeoutTicks = 20)
    public static void toolReceivesModifier(GameTestHelper helper) {
        ItemStack stack = new ItemStack(TCItems.FLINT_PICKAXE.get());
        ToolStackData before = ModifiableToolItem.getToolData(stack);
        stack.set(TCComponents.TOOL_STACK.get(), before.addModifier(ToolStackData.HASTE));
        ToolStackData after = ModifiableToolItem.getToolData(stack);
        helper.assertTrue(after.modifiers().size() == 1, "Expected one modifier on the tool");
        helper.assertTrue(after.resolvedStats().miningSpeed() > before.resolvedStats().miningSpeed(), "Expected haste to increase mining speed");
        helper.succeed();
    }

    @GameTest(template = "empty", timeoutTicks = 20)
    public static void brokenToolKeepsStack(GameTestHelper helper) {
        ItemStack stack = new ItemStack(TCItems.FLINT_PICKAXE.get());
        ToolStackData data = ModifiableToolItem.getToolData(stack);
        stack.set(TCComponents.TOOL_STACK.get(), data.withDamage(data.resolvedStats().durability()));
        ToolStackData broken = ModifiableToolItem.getToolData(stack);
        helper.assertFalse(stack.isEmpty(), "Broken tools must stay in the stack");
        helper.assertTrue(broken.broken(), "Expected tool component to enter broken state");
        helper.succeed();
    }
}
