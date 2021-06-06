package net.runelite.client.plugins.bonerunner;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class BoneRunnerOverlay extends Overlay {


    private final Client client;
    private final BoneRunnerConfig config;
    private final BoneRunnerPlugin plugin;

    @Inject
    public BoneRunnerOverlay(Client client, BoneRunnerConfig config, BoneRunnerPlugin plugin) {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        this.client = client;
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        return null;
    }
}
