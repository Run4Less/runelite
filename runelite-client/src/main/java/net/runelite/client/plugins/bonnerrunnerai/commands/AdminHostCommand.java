package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatMember;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

public class AdminHostCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().substring(0, 5).contains("!Host")) {
                if (message.getMessage().equalsIgnoreCase("!Host")
                        || (message.getMessage().equalsIgnoreCase("!Host stats"))) {
                    return;
                }
                FriendsChatRank friendsChatRank = commandUtils.getFriendsChatRank(message);
                if (friendsChatRank == FriendsChatRank.GENERAL) {
                    String messageInternalMessage = message.getMessage();
                    String substring = messageInternalMessage.substring(5).trim();

                    FriendsChatMember member = commandUtils.getFriendsChatMamber(substring);
                    if (member == null) {
                        try {
                            chatUtils.sendClanChatMessage("/Unable to find user in clan chat");
                        } catch (AWTException e) {
                            e.printStackTrace();
                        }
                        throw new RuntimeException("error, Unable to find user in clan chat: " + substring);
                    }
                    addActiveHost(substring);
                }
            }
        }
    }

    public void addActiveHost(String name) {
        RequestBody reqbody = RequestBody.create(null, new byte[0]);
        Request request = new Request.Builder()
                .url("http://localhost:8081/hosts/active" + "?name=" + name)
                .post(reqbody)
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
