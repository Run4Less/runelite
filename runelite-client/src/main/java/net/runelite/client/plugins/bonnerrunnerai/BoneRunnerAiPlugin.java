package net.runelite.client.plugins.bonnerrunnerai;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.bonnerrunnerai.commands.Command;
import net.runelite.client.plugins.bonnerrunnerai.commands.Commands;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@PluginDescriptor(
        name = "Bone-runner-ai",
        description = "Enable bone runner ai",
        tags = {"bone-runner-ai", "overlay"}
)
@Slf4j
public class BoneRunnerAiPlugin extends Plugin {

    @Inject
    private BoneRunnerAiOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private Client client;

    @Inject
    Commands commands;

    @Inject
    ChatUtils chatUtils;

    @Inject
    StayLoggedIn stayLoggedIn;

    private Canvas canvas;

    private BoneRunnerAiConfig boneRunnerAiConfig;
    private List<Command> commandList = new ArrayList<>();

    @Provides
    BoneRunnerAiConfig getConfig(ConfigManager configManager) {
        boneRunnerAiConfig = configManager.getConfig(BoneRunnerAiConfig.class);
        return configManager.getConfig(BoneRunnerAiConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
        commandList = commands.getList();
        canvas = client.getCanvas();

    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
    }

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        stayLoggedIn.stayLoggedIn();
        stayLoggedIn.worldHopStayIn();
    }


    @Subscribe(priority = -2)
    public void onChatMessage(ChatMessage message) throws AWTException, IOException {
        if (!boneRunnerAiConfig.enableBoneRunnerAi()) {
            return;
        }
        for (Command c : commandList) {
            try {
                c.process(message);
            } catch (Exception e) {
                log.info("{}", e);
            }
        }
    }

}
