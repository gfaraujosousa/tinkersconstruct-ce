package com.gfaraujosousa.tconstruct.item;

import com.gfaraujosousa.tconstruct.registry.TCComponents;
import com.gfaraujosousa.tconstruct.tool.ToolStackData;
import com.gfaraujosousa.tconstruct.tool.ToolStats;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ModifiableToolItem extends Item {
    public ModifiableToolItem(Properties properties) {
        super(properties.component(TCComponents.TOOL_STACK.get(), ToolStackData.flintPickaxe()));
    }

    public static ToolStackData getToolData(ItemStack stack) {
        ToolStackData data = stack.get(TCComponents.TOOL_STACK.get());
        if (data == null) {
            data = ToolStackData.flintPickaxe();
            stack.set(TCComponents.TOOL_STACK.get(), data);
        }
        return data;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        ToolStackData data = getToolData(stack);
        if (data.broken()) {
            return 0.2F;
        }
        return Math.max(1.0F, data.resolvedStats().miningSpeed());
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if (!level.isClientSide && state.getDestroySpeed(level, pos) > 0.0F) {
            stack.set(TCComponents.TOOL_STACK.get(), getToolData(stack).damage(1));
        }
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide) {
            stack.set(TCComponents.TOOL_STACK.get(), getToolData(stack).damage(2));
        }
        return true;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getToolData(stack).damage() > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        ToolStackData data = getToolData(stack);
        ToolStats stats = data.resolvedStats();
        return Math.round(13.0F - (float) data.damage() * 13.0F / Math.max(1, stats.durability()));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return getToolData(stack).broken() ? 0x7a1f1f : 0x4ca64c;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ToolStackData data = getToolData(stack);
        ToolStats stats = data.resolvedStats();
        tooltipComponents.add(Component.translatable("tooltip.tconstruct_ce.materials", data.materials().size()).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("tooltip.tconstruct_ce.modifiers", data.modifiers().size()).withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("tooltip.tconstruct_ce.durability", Math.max(0, stats.durability() - data.damage()), stats.durability()).withStyle(data.broken() ? ChatFormatting.RED : ChatFormatting.DARK_GREEN));
        if (data.broken()) {
            tooltipComponents.add(Component.translatable("tooltip.tconstruct_ce.broken").withStyle(ChatFormatting.RED));
        }
    }
}
