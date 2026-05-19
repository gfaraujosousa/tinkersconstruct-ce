package com.gfaraujosousa.tconstruct.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record ModifierEntry(ResourceLocation id, int level) {
    public static final Codec<ModifierEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(ModifierEntry::id),
            Codec.INT.fieldOf("level").orElse(1).forGetter(ModifierEntry::level)
    ).apply(instance, ModifierEntry::new));
}
