package net.runelite.client.plugins.bonestayloggedin;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.game.WorldService;
import net.runelite.client.util.WorldUtil;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;

import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class WorldHopper {

    @Inject
    Client client;

    @Inject
    private WorldService worldService;

    int worldHopTotal = 0;
    int worldHopTick = 0;

    private Lock lock = new ReentrantLock();

    private boolean shouldHop = false;

    public void worldHopStayIn(BoneStayLoggedInAiConfig config) {
        if(config.enableHopBackToSameWorld() && config.worldHopStartNumber() != client.getWorld() && !shouldHop) {
            // need to hop back to world
            shouldHop = true;
        }
        if (worldHopTick == 0) {
            worldHopTick = getRandomTick(28000, 33000);
        }
        worldHopTotal += 1;
        if (worldHopTotal > worldHopTick || shouldHop) {
            if (!lock.tryLock()) {
                return;
            }
            log.info("Switching worlds");
            if (hopToWorld(getWorld(config))) {
                worldHopTotal = 0;
                worldHopTick = getRandomTick(28000, 33000);
                shouldHop = false;
            }
            lock.unlock();
        }

    }

    private net.runelite.api.World getWorld(BoneStayLoggedInAiConfig config) {
        WorldResult worldResult = worldService.getWorlds();
        // Don't try to hop if the world doesn't exist
        World world;
        System.out.println(worldResult);
        System.out.println(config.worldHopStartNumber());
        System.out.println(config.worldHopEndNumber());
        if (client.getWorld() == config.worldHopStartNumber()) {
            System.out.println(worldResult.getWorlds());
            world = worldResult.findWorld(config.worldHopEndNumber());
        } else {
            world = worldResult.findWorld(config.worldHopStartNumber());
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

    private boolean hopToWorld(net.runelite.api.World world) {
        if (client.getWidget(WidgetInfo.WORLD_SWITCHER_LIST) == null) {
            client.openWorldHopper();
            return false;
        } else {
            client.hopToWorld(world);
            return true;
        }
    }

    private int getRandomTick(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }
}
