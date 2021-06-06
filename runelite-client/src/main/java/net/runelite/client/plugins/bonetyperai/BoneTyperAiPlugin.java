package net.runelite.client.plugins.bonetyperai;

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
        name = "bone-typer-ai",
        description = "Enable bone typer ai",
        tags = {"bone-typer-ai", "overlay"}
)
@Slf4j
public class BoneTyperAiPlugin extends Plugin {

    @Inject
    private BoneTyperAiOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private Client client;

    @Inject
    AiTyper aiTyper;

    private Canvas canvas;

    private BoneTyperAiConfig boneTyperAiConfig;

    @Provides
    BoneTyperAiConfig getConfig(ConfigManager configManager) {
        boneTyperAiConfig = configManager.getConfig(BoneTyperAiConfig.class);
        return configManager.getConfig(BoneTyperAiConfig.class);
    }


    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        canvas = client.getCanvas();

    }


    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
        x = 0;
        aiTyper.setActionToNull();
    }


    int x = 0;
    @Subscribe
    public void onGameTick(GameTick gameTick) {
        if (boneTyperAiConfig.enableTyper()) {
            if (x > 20) {
                try {
                    aiTyper.gameTick();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            } else {
                x++;
            }
        }

    }


}
