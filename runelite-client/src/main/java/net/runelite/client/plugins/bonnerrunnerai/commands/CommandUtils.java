package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.Client;
import net.runelite.api.FriendsChatManager;
import net.runelite.api.FriendsChatMember;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;

import javax.inject.Inject;
import java.util.Objects;

public class CommandUtils {

    @Inject
    Client client;

    public FriendsChatRank getFriendsChatRank(ChatMessage message) {
        FriendsChatManager manager = client.getFriendsChatManager();
        FriendsChatMember p = manager.findByName(message.getName());
        if (Objects.isNull(p)) {
            return null;
        }
        return p.getRank();
    }

    public FriendsChatMember getFriendsChatMamber(String name) {
        FriendsChatManager manager = client.getFriendsChatManager();
        FriendsChatMember p = manager.findByName(name);
        return p;
    }
}
