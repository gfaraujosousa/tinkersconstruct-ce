package com.gfaraujosousa.tconstruct.data;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.tool.MaterialDefinition;
import com.gfaraujosousa.tconstruct.tool.ToolData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.LinkedHashMap;
import java.util.Map;

public class MaterialReloadListener extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();
    private static final String DIRECTORY = "tinkering/materials";

    public MaterialReloadListener() {
        super(GSON, DIRECTORY);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resources, net.minecraft.server.packs.resources.ResourceManager resourceManager, ProfilerFiller profiler) {
        Map<ResourceLocation, MaterialDefinition> loaded = new LinkedHashMap<>();
        resources.forEach((id, json) -> MaterialDefinition.CODEC.parse(JsonOps.INSTANCE, json)
                .resultOrPartial(message -> TinkersConstructCommunityEdition.LOGGER.error("Failed to load material {}: {}", id, message))
                .ifPresent(definition -> {
                    if (!definition.id().equals(id)) {
                        TinkersConstructCommunityEdition.LOGGER.error("Material {} declares mismatched id {}", id, definition.id());
                        return;
                    }
                    loaded.put(id, definition);
                }));
        ToolData.replaceMaterials(loaded);
        TinkersConstructCommunityEdition.LOGGER.info("Loaded {} TConstruct CE material definitions", ToolData.materials().size());
    }
}
