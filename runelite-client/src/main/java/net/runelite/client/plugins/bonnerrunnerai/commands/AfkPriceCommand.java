package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

public class AfkPriceCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().substring(0, 4).contains("!Afk")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    Float totalBones = Float.parseFloat(message.getMessage().substring(4).trim());
                    Double price = Math.floor((((totalBones / 26.0F) * 20000))/ 1000);
                    try {
                        chatUtils.sendClanChatMessage("/AFKing " +  totalBones.intValue() + " bones would be " + price.intValue() + "k");
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
