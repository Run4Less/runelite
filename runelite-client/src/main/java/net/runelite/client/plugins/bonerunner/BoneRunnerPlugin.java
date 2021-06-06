package net.runelite.client.plugins.bonerunner;

import com.google.common.collect.Sets;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameTick;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@PluginDescriptor(
        name = "Bone-runner-plugin",
        description = "Show info about bone running",
        tags = {"bone-runner", "poh", "overlay"}
)
@Slf4j
public class BoneRunnerPlugin extends Plugin {

    static final Set<Integer> BURNER_UNLIT = Sets.newHashSet(ObjectID.INCENSE_BURNER, ObjectID.INCENSE_BURNER_13210, ObjectID.INCENSE_BURNER_13212);
    static final Set<Integer> BURNER_LIT = Sets.newHashSet(ObjectID.INCENSE_BURNER_13209, ObjectID.INCENSE_BURNER_13211, ObjectID.INCENSE_BURNER_13213);
    private final Map<Tile, IncenseBurner> incenseBurners = new HashMap<>();

    @Inject
    private BoneRunnerOverlay overlay;

    @Inject
    private Notifier notifier;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private Client client;

    @Inject
    private SpriteManager spriteManager;

    @Inject
    private InfoBoxManager infoBoxManager;

    @Inject
    private ClientThread clientThread;

    private BoneRunnerConfig boneRunnerConfig;
    private HouseMembersIndicator houseMembersIndicator = null;
    private volatile int housePlayerCount = 0;

    @Provides
    BoneRunnerConfig getConfig(ConfigManager configManager) {
        boneRunnerConfig = configManager.getConfig(BoneRunnerConfig.class);
        return configManager.getConfig(BoneRunnerConfig.class);
    }

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
        resetCounter();
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        if (event.getKey().equalsIgnoreCase("showHousePlayerCount")) {
            if (boneRunnerConfig.showHousePlayerCount()) {
                clientThread.invoke(this::addHousePlayerCounter);
            } else {
                resetCounter();
            }
        }
    }


    @Subscribe
    public void onGameTick(GameTick gameTick) {
        if (boneRunnerConfig.showHousePlayerCount()) {
            updateHousePlayerCount(client.getPlayers().size());
            addHousePlayerCounter();
        }

        if (boneRunnerConfig.burnerNotification()) {
            Collection<IncenseBurner> incenseBurnerList = incenseBurners.values();
            for (IncenseBurner burner : incenseBurnerList) {
                final Instant now = Instant.now();
                final long startCountdown = Duration.between(burner.getStart(), now).getSeconds();
                final double certainSec = burner.getCountdownTimer() - startCountdown;
                if (BURNER_LIT.contains(burner.getId()) &&
                        certainSec < 15 &&
                        !burner.getNotification()) {
                    burner.setNotification(true);
                    notifier.notify("Burner almost finished");
                }
            }
        }
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event) {
        final GameObject gameObject = event.getGameObject();
        if (!BURNER_LIT.contains(gameObject.getId()) && !BURNER_UNLIT.contains(gameObject.getId())) {

            return;
        }
        final double countdownTimer = 130.0; // Minimum amount of seconds a burner will light
        incenseBurners.put(event.getTile(), new IncenseBurner(gameObject.getId(), countdownTimer, false));
    }


    private void resetCounter() {
        infoBoxManager.removeInfoBox(houseMembersIndicator);
        houseMembersIndicator = null;
    }

    private void addHousePlayerCounter() {
        if (houseMembersIndicator == null) {
            final BufferedImage image = spriteManager.getSprite(SpriteID.TAB_FRIENDS_CHAT, 0);
            houseMembersIndicator = new HouseMembersIndicator(image, this);
            infoBoxManager.addInfoBox(houseMembersIndicator);
        }
    }

    public synchronized int getHousePlayerCount() {
        return housePlayerCount;
    }

    private synchronized void updateHousePlayerCount(int count) {
        housePlayerCount = count;
    }

}
