package net.runelite.client.plugins.bonerunner;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bone-runner")
public interface BoneRunnerConfig extends Config {

    @ConfigItem(
            keyName = "showHousePlayerCount",
            name = "Show player count",
            description = "Configures whether or not to show house player count"
    )
    default boolean showHousePlayerCount() {
        return true;
    }

    @ConfigItem(
            keyName = "burnerNotification",
            name = "Burner notification",
            description = "Configures whether or not to notify when burner is almost finished"
    )
    default boolean burnerNotification()
    {
        return false;
    }

}
