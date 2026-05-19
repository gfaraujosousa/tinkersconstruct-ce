package com.gfaraujosousa.tconstruct.tool;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public record ToolStackData(ResourceLocation definition, List<ResourceLocation> materials, List<ModifierEntry> modifiers, int damage, boolean broken) {
    public static final ResourceLocation FLINT = TinkersConstructCommunityEdition.location("flint");
    public static final ResourceLocation PICKAXE = TinkersConstructCommunityEdition.location("pickaxe");
    public static final ResourceLocation HASTE = TinkersConstructCommunityEdition.location("haste");

    public static final Codec<ToolStackData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("definition").forGetter(ToolStackData::definition),
            ResourceLocation.CODEC.listOf().fieldOf("materials").forGetter(ToolStackData::materials),
            ModifierEntry.CODEC.listOf().fieldOf("modifiers").orElse(List.of()).forGetter(ToolStackData::modifiers),
            Codec.INT.fieldOf("damage").orElse(0).forGetter(ToolStackData::damage),
            Codec.BOOL.fieldOf("broken").orElse(false).forGetter(ToolStackData::broken)
    ).apply(instance, ToolStackData::new));

    public static ToolStackData flintPickaxe() {
        return new ToolStackData(PICKAXE, List.of(FLINT, FLINT, FLINT), List.of(), 0, false);
    }

    public ToolStackData withDamage(int amount) {
        ToolStats stats = resolvedStats();
        int clamped = Math.max(0, Math.min(amount, stats.durability()));
        return new ToolStackData(definition, materials, modifiers, clamped, clamped >= stats.durability());
    }

    public ToolStackData damage(int amount) {
        if (broken || amount <= 0) {
            return this;
        }
        return withDamage(damage + amount);
    }

    public ToolStackData repair(int amount) {
        if (amount <= 0) {
            return this;
        }
        int repaired = Math.max(0, damage - amount);
        return new ToolStackData(definition, materials, modifiers, repaired, false);
    }

    public ToolStackData addModifier(ResourceLocation modifierId) {
        List<ModifierEntry> updated = new ArrayList<>(modifiers);
        ModifierDefinition modifier = ToolData.modifier(modifierId);
        int maxLevel = modifier == null ? 1 : modifier.maxLevel();
        for (int i = 0; i < updated.size(); i++) {
            ModifierEntry existing = updated.get(i);
            if (existing.id().equals(modifierId)) {
                updated.set(i, new ModifierEntry(modifierId, Math.min(maxLevel, existing.level() + 1)));
                return new ToolStackData(definition, materials, List.copyOf(updated), damage, broken);
            }
        }
        updated.add(new ModifierEntry(modifierId, 1));
        return new ToolStackData(definition, materials, List.copyOf(updated), damage, broken);
    }

    public ToolStats resolvedStats() {
        ToolStats result = new ToolStats(1, 1.0F, 1.0F, 1.0F);
        for (ResourceLocation materialId : materials) {
            MaterialDefinition material = ToolData.material(materialId);
            if (material != null) {
                result = result.add(material.stats());
            }
        }
        for (ModifierEntry entry : modifiers) {
            ModifierDefinition modifier = ToolData.modifier(entry.id());
            if (modifier != null) {
                for (int i = 0; i < entry.level(); i++) {
                    result = result.add(modifier.statBonus());
                }
            }
        }
        return result;
    }
}
