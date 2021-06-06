package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
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

public class RunnerDiscordCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().substring(0, 7).contains("!Runner")) {
                FriendsChatRank friendsChatRank = commandUtils.getFriendsChatRank(message);
                if (friendsChatRank != FriendsChatRank.UNRANKED) {
                    String messageInternalMessage = message.getMessage();
                    String substring = messageInternalMessage.substring(7);
                    sendRunnerNeeded(substring);
                    try {
                        chatUtils.sendClanChatMessage("/I've pinged the runners on discord for you");
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendRunnerNeeded(String message) {
        RequestBody reqbody = RequestBody.create(null, new byte[0]);
        Request request = new Request.Builder()
                .url("http://localhost:8081/runner" + "?message=" + message)
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
