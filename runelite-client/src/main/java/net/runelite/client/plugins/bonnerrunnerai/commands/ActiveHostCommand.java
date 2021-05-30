package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;
import net.runelite.client.plugins.bonnerrunnerai.Host;
import net.runelite.http.api.RuneLiteAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ActiveHostCommand implements Command {

    @Inject
    private Client client;

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("@hosts")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    getActiveHosts();
                }
            }
        }
    }

    public void getActiveHosts() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8081/hosts")
                .build();

        try {
            ResponseBody body = client.newCall(request).execute().body();

            InputStream in = body.byteStream();
            Host[] hosts = RuneLiteAPI.GSON.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), Host[].class);
            StringBuilder allHost = new StringBuilder();
            for (int i = 0; i < hosts.length; i++) {
                allHost.append(hosts[i].getName());
                if (!(i == hosts.length - 1)) allHost.append(",");
                allHost.append(" ");
            }
            System.out.println("All active hosts " + allHost);
            try {
                chatUtils.sendClanChatMessage("/[Active hosts] " + allHost);
            } catch (AWTException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
