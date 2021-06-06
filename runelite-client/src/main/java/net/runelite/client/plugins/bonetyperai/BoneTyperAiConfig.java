package net.runelite.client.plugins.bonetyperai;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bone-typer-ai")
public interface BoneTyperAiConfig extends Config {

    @ConfigItem(
            keyName = "enableTyper",
            name = "Enable typer",
            description = "Configures whether or not the typer is enabled"
    )
    default boolean enableTyper() {
        return true;
    }



}
