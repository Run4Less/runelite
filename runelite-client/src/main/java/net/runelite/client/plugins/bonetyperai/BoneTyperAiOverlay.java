package net.runelite.client.plugins.bonetyperai;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class BoneTyperAiOverlay extends Overlay {


    private final Client client;
    private final BoneTyperAiConfig config;
    private final BoneTyperAiPlugin plugin;

    @Inject
    public BoneTyperAiOverlay(Client client, BoneTyperAiConfig config, BoneTyperAiPlugin plugin) {
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
