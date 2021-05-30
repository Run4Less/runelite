package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class PriceCommand implements Command {

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
            if (message.getMessage().equalsIgnoreCase("!Price")
                    || message.getMessage().equalsIgnoreCase("!Prices")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    try {
                        chatUtils.sendClanChatMessage("/Our bone running prices: 20k per inventory Afk, 18k per inventory Tick");
                        chatUtils.sendClanChatMessage("/Ess running prices: Lavas 7m/hr per runner, Astrals 5m/hr per runner");
                        lastSendMessage = LocalDateTime.now();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            } else if (message.getMessage().equalsIgnoreCase("!BonePrice")
                    || message.getMessage().equalsIgnoreCase("!BonePrices")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    try {
                        chatUtils.sendClanChatMessage("/Our bone running prices: 20k per inventory Afk, 18k per inventory Tick");
                        lastSendMessage = LocalDateTime.now();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            } else if (message.getMessage().equalsIgnoreCase("!EssPrice")
                    || message.getMessage().equalsIgnoreCase("!EssPrices")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    try {
                        chatUtils.sendClanChatMessage("/Ess running prices: Lavas 7m/hr per runner, Astrals 5m/hr per runner");
                        lastSendMessage = LocalDateTime.now();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
