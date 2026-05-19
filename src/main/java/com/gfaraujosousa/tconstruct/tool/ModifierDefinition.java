package com.gfaraujosousa.tconstruct.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record ModifierDefinition(ResourceLocation id, String slotType, int maxLevel, ToolStats statBonus) {
    public static final Codec<ModifierDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(ModifierDefinition::id),
            Codec.STRING.fieldOf("slot_type").orElse("upgrade").forGetter(ModifierDefinition::slotType),
            Codec.INT.fieldOf("max_level").orElse(1).forGetter(ModifierDefinition::maxLevel),
            ToolStats.CODEC.fieldOf("stat_bonus").forGetter(ModifierDefinition::statBonus)
    ).apply(instance, ModifierDefinition::new));
}
