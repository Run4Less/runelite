package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

public class ExplainInsuranceCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!ex insurance")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    try {
                        chatUtils.sendClanChatMessage("/Insurance is a set price that a runner has to pay into the clan leader");
                        chatUtils.sendClanChatMessage("/the insurance is there to cover both the runner, as well as the client");
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
