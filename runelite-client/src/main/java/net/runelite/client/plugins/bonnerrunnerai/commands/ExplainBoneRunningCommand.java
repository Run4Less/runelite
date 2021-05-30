package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExplainBoneRunningCommand implements Command {

    private LocalDateTime lastSendMessage = null;

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if(!chatUtils.canSendMessage(lastSendMessage)) {
            return;
        }
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!ex")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    try {
                        chatUtils.sendClanChatMessage("/When you find a runner, trade your runner once, give them bones + gp 1st trade");
                        chatUtils.sendClanChatMessage("/They will then un-note the bones, and run them to you, to get your max xp/h");
                        chatUtils.sendClanChatMessage("/Only use runners with a rank, these runners are insured.");
                        chatUtils.sendClanChatMessage("/If you would like insurance explained, please let your runner know.");
                        lastSendMessage = LocalDateTime.now();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
