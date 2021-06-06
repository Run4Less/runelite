package net.runelite.client.plugins.bonestayloggedin;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = "bone-stay-logged-in-ai",
        description = "Enable bone stay logged ai",
        tags = {"bone-stay-logged-in-ai", "overlay"}
)
@Slf4j
public class BoneStayLoggedInAiPlugin extends Plugin {

    @Inject
    private BoneStayLoggedInAiOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private Client client;

    private Canvas canvas;

    @Inject
    MoveMouse moveMouse;

    @Inject
    WorldHopper worldHopper;

    private BoneStayLoggedInAiConfig config;

    @Provides
    BoneStayLoggedInAiConfig getConfig(ConfigManager configManager) {
        config = configManager.getConfig(BoneStayLoggedInAiConfig.class);
        return configManager.getConfig(BoneStayLoggedInAiConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        canvas = client.getCanvas();

    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
    }

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        if(config.enableMouseMovementAi()) {
            moveMouse.stayLoggedIn();
        }
        if(config.enableWorldHopping()) {
            worldHopper.worldHopStayIn(config);
        }
    }



}
