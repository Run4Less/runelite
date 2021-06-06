package net.runelite.client.plugins.bonnerrunnerai;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("bone-runner-ai")
public interface BoneRunnerAiConfig extends Config {

    @ConfigItem(
            keyName = "enableBoneRunnerAi",
            name = "Enable bone runner AI",
            description = "Configures whether the bone runner AI is enabled"
    )
    default boolean enableBoneRunnerAi() {
        return true;
    }

}
