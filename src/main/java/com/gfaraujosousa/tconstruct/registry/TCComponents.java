package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.tool.ToolStackData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCComponents {
    public static final DeferredRegister.DataComponents REGISTRY = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TinkersConstructCommunityEdition.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ToolStackData>> TOOL_STACK =
            REGISTRY.registerComponentType("tool_stack", builder -> builder.persistent(ToolStackData.CODEC));

    private TCComponents() {
    }
}
