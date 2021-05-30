package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

public class InsuranceCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!insurance")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                int insuranceFor;
                if (rank == FriendsChatRank.RECRUIT) {
                    insuranceFor = 1;
                } else if (rank == FriendsChatRank.CORPORAL) {
                    insuranceFor = 5;
                } else if (rank == FriendsChatRank.SERGEANT) {
                    insuranceFor = 10;
                } else if (rank == FriendsChatRank.LIEUTENANT) {
                    insuranceFor = 50;
                } else if (rank == FriendsChatRank.CAPTAIN) {
                    insuranceFor = 100;
                } else {
                    return;
                }
                try {
                    chatUtils.sendClanChatMessage("/" + message.getName() + " is insured for " + insuranceFor + "m");
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
