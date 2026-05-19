package com.gfaraujosousa.tconstruct.tool;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ToolData {
    private static final Map<ResourceLocation, MaterialDefinition> BUILTIN_MATERIALS = Map.of(
            ToolStackData.FLINT, new MaterialDefinition(
                    ToolStackData.FLINT,
                    1,
                    new ToolStats(64, 1.8F, 0.7F, 0.0F),
                    List.of(TinkersConstructCommunityEdition.location("crude"))
            )
    );

    private static final Map<ResourceLocation, ModifierDefinition> BUILTIN_MODIFIERS = Map.of(
            ToolStackData.HASTE, new ModifierDefinition(
                    ToolStackData.HASTE,
                    "upgrade",
                    3,
                    new ToolStats(0, 1.0F, 0.0F, 0.0F)
            )
    );

    private static volatile Map<ResourceLocation, MaterialDefinition> materials = BUILTIN_MATERIALS;
    private static volatile Map<ResourceLocation, ModifierDefinition> modifiers = BUILTIN_MODIFIERS;

    private ToolData() {
    }

    public static MaterialDefinition material(ResourceLocation id) {
        return materials.get(id);
    }

    public static ModifierDefinition modifier(ResourceLocation id) {
        return modifiers.get(id);
    }

    public static Map<ResourceLocation, MaterialDefinition> materials() {
        return materials;
    }

    public static Map<ResourceLocation, ModifierDefinition> modifiers() {
        return modifiers;
    }

    public static void replaceMaterials(Map<ResourceLocation, MaterialDefinition> loaded) {
        materials = mergeWithFallbacks(BUILTIN_MATERIALS, loaded);
    }

    public static void replaceModifiers(Map<ResourceLocation, ModifierDefinition> loaded) {
        modifiers = mergeWithFallbacks(BUILTIN_MODIFIERS, loaded);
    }

    private static <T> Map<ResourceLocation, T> mergeWithFallbacks(Map<ResourceLocation, T> fallbacks, Map<ResourceLocation, T> loaded) {
        LinkedHashMap<ResourceLocation, T> merged = new LinkedHashMap<>(fallbacks);
        merged.putAll(loaded);
        return Map.copyOf(merged);
    }
}
