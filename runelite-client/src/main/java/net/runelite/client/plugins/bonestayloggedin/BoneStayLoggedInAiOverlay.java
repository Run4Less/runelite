package net.runelite.client.plugins.bonestayloggedin;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;

public class BoneStayLoggedInAiOverlay extends Overlay {

    private final Client client;
    private final BoneStayLoggedInAiConfig config;
    private final BoneStayLoggedInAiPlugin plugin;

    @Inject
    public BoneStayLoggedInAiOverlay(Client client, BoneStayLoggedInAiConfig config, BoneStayLoggedInAiPlugin plugin) {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        this.client = client;
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        Point point =
                new Point(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY());

        int[] xPoints = new int[]{(int) (point.getX() - 5), (int) (point.getX() + 5)};
        int[] yPoints = new int[]{(int) (point.getY() - 5), (int) (point.getY() + 5)};
        Polygon polygon = new Polygon(xPoints, yPoints, 2);
        OverlayUtil.renderPolygon(graphics, polygon, Color.green);
        xPoints = new int[]{(int) (point.getX() + 5), (int) (point.getX() - 5)};
        yPoints = new int[]{(int) (point.getY() - 5), (int) (point.getY() + 5)};
        polygon = new Polygon(xPoints, yPoints, 2);
        OverlayUtil.renderPolygon(graphics, polygon, Color.green);

        return null;
    }
}
