package net.runelite.client.plugins.bonestayloggedin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bone-stay-logged-in-ai")
public interface BoneStayLoggedInAiConfig extends Config {

    @ConfigItem(
            keyName = "enableMouseMovements",
            name = "Enable mouse movement ai",
            description = "Configures whether the mouse movements is enabled"
    )
    default boolean enableMouseMovementAi() {
        return true;
    }

    @ConfigItem(
            keyName = "enableWorldHopping",
            name = "Enable world hopping",
            description = "Configures whether world hopping is enabled"
    )
    default boolean enableWorldHopping() {
        return true;
    }

    @ConfigItem(
            keyName = "enableHopBacktoSameWorld",
            name = "Enable hopping back to same world",
            description = "Configures whether to hopping back to same world"
    )
    default boolean enableHopBackToSameWorld() {
        return true;
    }


    @ConfigItem(
            keyName = "worldHopStartNumber",
            name = "World hop start number",
            description = "Configures world hop start number"
    )
    default int worldHopStartNumber() {
        return 301;
    }

    @ConfigItem(
            keyName = "worldHopEnsNumber",
            name = "World hop end number",
            description = "Configures world hop end number"
    )
    default int worldHopEndNumber() {
        return 302;
    }


}
