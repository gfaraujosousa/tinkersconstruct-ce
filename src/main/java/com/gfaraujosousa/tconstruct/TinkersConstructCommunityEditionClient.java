package com.gfaraujosousa.tconstruct;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = TinkersConstructCommunityEdition.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TinkersConstructCommunityEdition.MODID, value = Dist.CLIENT)
public class TinkersConstructCommunityEditionClient {
    public TinkersConstructCommunityEditionClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        TinkersConstructCommunityEdition.LOGGER.debug("Client setup for {} as {}", TinkersConstructCommunityEdition.DISPLAY_NAME, Minecraft.getInstance().getUser().getName());
    }
}
