package com.gfaraujosousa.tconstruct.tool;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public final class ToolData {
    public static final Map<ResourceLocation, MaterialDefinition> MATERIALS = Map.of(
            ToolStackData.FLINT, new MaterialDefinition(
                    ToolStackData.FLINT,
                    1,
                    new ToolStats(64, 1.8F, 0.7F, 0.0F),
                    java.util.List.of(TinkersConstructCommunityEdition.location("crude"))
            )
    );

    public static final Map<ResourceLocation, ModifierDefinition> MODIFIERS = Map.of(
            ToolStackData.HASTE, new ModifierDefinition(
                    ToolStackData.HASTE,
                    "upgrade",
                    3,
                    new ToolStats(0, 1.0F, 0.0F, 0.0F)
            )
    );

    private ToolData() {
    }
}
