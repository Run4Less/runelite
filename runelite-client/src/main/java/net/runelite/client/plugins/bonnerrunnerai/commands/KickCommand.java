package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

public class KickCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!kick")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank == FriendsChatRank.GENERAL) {
                    try {
                        chatUtils.sendClanChatMessage("/Attempting to kick player from chat-channel…");
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
