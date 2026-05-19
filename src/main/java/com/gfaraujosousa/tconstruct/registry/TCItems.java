package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import com.gfaraujosousa.tconstruct.item.ModifiableToolItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCItems {
    public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(TinkersConstructCommunityEdition.MODID);

    public static final DeferredItem<BlockItem> PART_BUILDER = REGISTRY.registerSimpleBlockItem("part_builder", TCBlocks.PART_BUILDER);
    public static final DeferredItem<BlockItem> TINKER_STATION = REGISTRY.registerSimpleBlockItem("tinker_station", TCBlocks.TINKER_STATION);
    public static final DeferredItem<BlockItem> MODIFIER_WORKTABLE = REGISTRY.registerSimpleBlockItem("modifier_worktable", TCBlocks.MODIFIER_WORKTABLE);
    public static final DeferredItem<BlockItem> MELTER = REGISTRY.registerSimpleBlockItem("melter", TCBlocks.MELTER);
    public static final DeferredItem<BlockItem> CASTING_TABLE = REGISTRY.registerSimpleBlockItem("casting_table", TCBlocks.CASTING_TABLE);
    public static final DeferredItem<BlockItem> SEARED_BRICKS = REGISTRY.registerSimpleBlockItem("seared_bricks", TCBlocks.SEARED_BRICKS);

    public static final DeferredItem<Item> BLANK_PATTERN = REGISTRY.registerSimpleItem("blank_pattern", new Item.Properties());
    public static final DeferredItem<Item> FLINT_PICK_HEAD = REGISTRY.registerSimpleItem("flint_pick_head", new Item.Properties());
    public static final DeferredItem<Item> FLINT_TOOL_HANDLE = REGISTRY.registerSimpleItem("flint_tool_handle", new Item.Properties());
    public static final DeferredItem<Item> FLINT_BINDING = REGISTRY.registerSimpleItem("flint_binding", new Item.Properties());
    public static final DeferredItem<Item> GOLD_PICK_HEAD_CAST = REGISTRY.registerSimpleItem("gold_pick_head_cast", new Item.Properties());
    public static final DeferredItem<Item> MOLTEN_GOLD_INGOT = REGISTRY.registerSimpleItem("molten_gold_ingot", new Item.Properties());
    public static final DeferredItem<Item> REDSTONE_UPGRADE = REGISTRY.registerSimpleItem("redstone_upgrade", new Item.Properties());

    public static final DeferredItem<ModifiableToolItem> FLINT_PICKAXE = REGISTRY.register("flint_pickaxe", () -> new ModifiableToolItem(new Item.Properties().stacksTo(1)));

    private TCItems() {
    }
}
