package com.gfaraujosousa.tconstruct.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record MaterialDefinition(ResourceLocation id, int tier, ToolStats stats, List<ResourceLocation> traits) {
    public static final Codec<MaterialDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(MaterialDefinition::id),
            Codec.INT.fieldOf("tier").forGetter(MaterialDefinition::tier),
            ToolStats.CODEC.fieldOf("stats").forGetter(MaterialDefinition::stats),
            ResourceLocation.CODEC.listOf().fieldOf("traits").orElse(List.of()).forGetter(MaterialDefinition::traits)
    ).apply(instance, MaterialDefinition::new));
}
