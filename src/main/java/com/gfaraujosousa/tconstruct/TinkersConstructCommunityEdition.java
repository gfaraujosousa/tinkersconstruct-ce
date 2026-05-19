package com.gfaraujosousa.tconstruct;

import com.gfaraujosousa.tconstruct.registry.TCBlocks;
import com.gfaraujosousa.tconstruct.registry.TCComponents;
import com.gfaraujosousa.tconstruct.registry.TCCreativeTabs;
import com.gfaraujosousa.tconstruct.registry.TCItems;
import com.gfaraujosousa.tconstruct.registry.TCRecipeSerializers;
import com.gfaraujosousa.tconstruct.registry.TCRecipeTypes;
import com.gfaraujosousa.tconstruct.test.TConstructCEGameTests;
import com.gfaraujosousa.tconstruct.data.MaterialReloadListener;
import com.gfaraujosousa.tconstruct.data.ModifierReloadListener;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;
import org.slf4j.Logger;

@Mod(TinkersConstructCommunityEdition.MODID)
public class TinkersConstructCommunityEdition {
    public static final String MODID = "tconstruct_ce";
    public static final String DISPLAY_NAME = "Tinkers' Construct Community Edition";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TinkersConstructCommunityEdition(IEventBus modEventBus, ModContainer modContainer) {
        TCComponents.REGISTRY.register(modEventBus);
        TCBlocks.REGISTRY.register(modEventBus);
        TCItems.REGISTRY.register(modEventBus);
        TCCreativeTabs.REGISTRY.register(modEventBus);
        TCRecipeTypes.REGISTRY.register(modEventBus);
        TCRecipeSerializers.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::registerGameTests);
        NeoForge.EVENT_BUS.addListener(this::addReloadListeners);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void registerGameTests(RegisterGameTestsEvent event) {
        event.register(TConstructCEGameTests.class);
    }

    private void addReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new MaterialReloadListener());
        event.addListener(new ModifierReloadListener());
    }

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
