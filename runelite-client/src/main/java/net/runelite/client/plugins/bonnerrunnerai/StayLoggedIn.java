package net.runelite.client.plugins.bonnerrunnerai;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.api.VarClientInt;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.WorldService;
import net.runelite.client.util.WorldUtil;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// TODO refactor class
@Slf4j
public class StayLoggedIn {

    @Inject
    Client client;

    @Inject
    private WorldService worldService;

    int mouseMovementTotal = 0;
    int randomMouseTick = 0;

    int worldHopTotal = 0;
    int worldHopTick = 0;

    private Lock lock = new ReentrantLock();

    public void worldHopStayIn() {
        if (worldHopTick == 0) {
            worldHopTick = getRandomTick(28000, 33000);
        }
        worldHopTotal += 1;
        if (worldHopTotal > worldHopTick) {
            if (!lock.tryLock()) {
                return;
            }
            log.info("Switching worlds");
            if (hopToWorld(getWorld())) {
                worldHopTotal = 0;
                worldHopTick = getRandomTick(28000, 33000);
            }
            lock.unlock();
        }

    }

    public net.runelite.api.World getWorld() {
        WorldResult worldResult = worldService.getWorlds();
        // Don't try to hop if the world doesn't exist
        World world;
        if (client.getWorld() == 330) {
            world = worldResult.findWorld(331);
        } else {
            world = worldResult.findWorld(330);
        }

        final net.runelite.api.World rsWorld = client.createWorld();
        rsWorld.setActivity(world.getActivity());
        rsWorld.setAddress(world.getAddress());
        rsWorld.setId(world.getId());
        rsWorld.setPlayerCount(world.getPlayers());
        rsWorld.setLocation(world.getLocation());
        rsWorld.setTypes(WorldUtil.toWorldTypes(world.getTypes()));
        return rsWorld;
    }

    public boolean hopToWorld(net.runelite.api.World world) {
        if (client.getWidget(WidgetInfo.WORLD_SWITCHER_LIST) == null) {
            client.openWorldHopper();
            return false;
        } else {
            client.hopToWorld(world);
            return true;
        }
    }


    public void stayLoggedIn() {
        if (randomMouseTick == 0) {
            randomMouseTick = getRandomTick(250, 400);
        }
        mouseMovementTotal += 1;
        if (mouseMovementTotal > randomMouseTick) {
            if (!lock.tryLock()) {
                return;
            }
            if (!(isInventoryVisible())) {
                Rectangle bounds = client.getWidget(WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB).getBounds();
                Point point = determineClickLocation(bounds);
                Component component = convert(bounds);
                try {
                    mouseMoveTo(bounds);
                    client.getCanvas().dispatchEvent(new MouseEvent(component, MouseEvent.MOUSE_PRESSED, Instant.now().toEpochMilli(), SwingUtilities.LEFT, (int) point.getX(), (int) point.getY(), 1, true));
                    Thread.sleep(randomise(10, 50));
                    log.info("pressed");
                    client.getCanvas().dispatchEvent(new MouseEvent(component, MouseEvent.MOUSE_RELEASED, Instant.now().toEpochMilli(), SwingUtilities.LEFT, (int) point.getX(), (int) point.getY(), 1, true));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mouseMovementTotal += 10;
                lock.unlock();
                return;
            }

            WidgetItem widgetItem = client.getWidget(WidgetInfo.INVENTORY).getWidgetItem(0);
            Rectangle bounds = widgetItem.getCanvasBounds();
            Point point = determineClickLocation(bounds);
            Component component = convert(bounds);

            try {
                mouseMoveTo(bounds);
                client.getCanvas().dispatchEvent(new MouseEvent(component, MouseEvent.MOUSE_PRESSED, Instant.now().toEpochMilli(), SwingUtilities.LEFT, (int) point.getX(), (int) point.getY(), 1, true));
                Thread.sleep(randomise(10, 50));
                log.info("pressed");
                client.getCanvas().dispatchEvent(new MouseEvent(component, MouseEvent.MOUSE_RELEASED, Instant.now().toEpochMilli(), SwingUtilities.LEFT, (int) point.getX(), (int) point.getY(), 1, true));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                mouseMovementTotal = 0;
                randomMouseTick = getRandomTick(200, 350);
            }
            lock.unlock();
        }
    }

    public boolean isInventoryVisible() {
        return client.getVar(VarClientInt.INVENTORY_TAB) == 3;
    }


    public static int randomise(int min, int max) {
        return max >= min ? new Random().nextInt(max - min) + min : new Random().nextInt(min - max) + max;
    }


    protected Component convert(Rectangle location) {
        return client.getCanvas().getComponentAt((int) location.getX(), (int) location.getY());
    }

    private Point determineClickLocation(Rectangle clickBounds) {
        final int clickX = randomise(clickBounds.x, clickBounds.x + clickBounds.width);
        final int clickY = randomise(clickBounds.y, clickBounds.y + clickBounds.height);
        return new Point(clickX, clickY);
    }


    private int getRandomTick(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    protected void mouseMoveTo(Rectangle area) {
        determineMouseRouteTo(area).forEach((location) -> {
            Component componentAt = client.getCanvas().getComponentAt(location.getX(), location.getY());
            if (!Objects.isNull(componentAt)) {
                try {
                    MouseEvent ms = new MouseEvent(client.getCanvas().getComponentAt((int) location.getX(), (int) location.getY()), MouseEvent.MOUSE_MOVED, Instant.now().toEpochMilli(), Button.NONE.modifier(), (int) location.getX(), (int) location.getY(), 0, true);
                    client.getCanvas().dispatchEvent(ms);
                    Thread.sleep(randomise(randomise(1, 10), randomise(11, 21)));
                } catch (InterruptedException | IllegalArgumentException ex) {
                    log.error("Error with mouse", ex);
                }
            }
        });
    }

    private ArrayList<Point> determineMouseRouteTo(Rectangle area) {
        return quadraticBezierCurve(client.getMouseCanvasPosition(), determineClickLocation(area));
    }


    static ArrayList<Point> quadraticBezierCurve(Point departure, Point destination) {
        final int X = departure.getX() == destination.getX() ? (int) departure.getX() : (int) randomise((int) departure.getX(), (int) destination.getX());
        final int Y = departure.getY() == destination.getY() ? (int) departure.getY() : (int) randomise((int) departure.getY(), (int) destination.getY());
        Point bezierPoint = new Point(X, Y);
        ArrayList<Point> locations = new ArrayList<>();
        for (float t = 0f; t < 1f; t += 0.01) {
            double x = Math.round(Math.pow(1 - t, 2) * departure.getX() + (1 - t) * 2 * t * bezierPoint.getX() + t * t * destination.getX()),
                    y = Math.round(Math.pow(1 - t, 2) * departure.getY() + (1 - t) * 2 * t * bezierPoint.getY() + t * t * destination.getY());
            locations.add(new Point((int) x, (int) y));
        }
        return locations;
    }

    public static enum Button {
        NONE(MouseEvent.NOBUTTON), LEFT(MouseEvent.BUTTON1), MIDDLE(MouseEvent.BUTTON2), RIGHT(MouseEvent.BUTTON3);
        int button;

        Button(int button) {
            this.button = button;
        }

        int modifier() {
            return button;
        }
    }
}
