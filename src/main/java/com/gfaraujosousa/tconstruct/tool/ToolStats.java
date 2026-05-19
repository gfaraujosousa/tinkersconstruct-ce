package com.gfaraujosousa.tconstruct.tool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ToolStats(int durability, float miningSpeed, float attackDamage, float attackSpeed) {
    public static final Codec<ToolStats> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("durability").forGetter(ToolStats::durability),
            Codec.FLOAT.fieldOf("mining_speed").forGetter(ToolStats::miningSpeed),
            Codec.FLOAT.fieldOf("attack_damage").forGetter(ToolStats::attackDamage),
            Codec.FLOAT.fieldOf("attack_speed").forGetter(ToolStats::attackSpeed)
    ).apply(instance, ToolStats::new));

    public ToolStats add(ToolStats other) {
        return new ToolStats(
                this.durability + other.durability,
                this.miningSpeed + other.miningSpeed,
                this.attackDamage + other.attackDamage,
                this.attackSpeed + other.attackSpeed
        );
    }
}
