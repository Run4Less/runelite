package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import javax.inject.Inject;
import java.io.IOException;

public class HostCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!host")) {
                FriendsChatRank friendsChatRank = commandUtils.getFriendsChatRank(message);
                if (friendsChatRank == FriendsChatRank.FRIEND || friendsChatRank == FriendsChatRank.GENERAL) {
                    addActiveHost(message.getName());
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
