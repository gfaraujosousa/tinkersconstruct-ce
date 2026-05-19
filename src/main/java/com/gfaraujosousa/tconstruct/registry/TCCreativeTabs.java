package com.gfaraujosousa.tconstruct.registry;

import com.gfaraujosousa.tconstruct.TinkersConstructCommunityEdition;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class TCCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinkersConstructCommunityEdition.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN = REGISTRY.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.tconstruct_ce.main"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TCItems.FLINT_PICKAXE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(TCItems.BLANK_PATTERN.get());
                output.accept(TCItems.FLINT_PICK_HEAD.get());
                output.accept(TCItems.FLINT_TOOL_HANDLE.get());
                output.accept(TCItems.FLINT_BINDING.get());
                output.accept(TCItems.FLINT_PICKAXE.get());
                output.accept(TCItems.REDSTONE_UPGRADE.get());
                output.accept(TCItems.GOLD_PICK_HEAD_CAST.get());
                output.accept(TCItems.MOLTEN_GOLD_INGOT.get());
                output.accept(TCItems.PART_BUILDER.get());
                output.accept(TCItems.TINKER_STATION.get());
                output.accept(TCItems.MODIFIER_WORKTABLE.get());
                output.accept(TCItems.MELTER.get());
                output.accept(TCItems.CASTING_TABLE.get());
                output.accept(TCItems.SEARED_BRICKS.get());
            }).build());

    private TCCreativeTabs() {
    }
}
