package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.ChatMessageType;
import net.runelite.api.FriendsChatRank;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.plugins.bonnerrunnerai.ChatUtils;
import net.runelite.client.plugins.bonnerrunnerai.HostStats;
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

public class HostStatsCommand implements Command {

    @Inject
    CommandUtils commandUtils;

    @Inject
    ChatUtils chatUtils;

    @Override
    public void process(ChatMessage message) throws IOException {
        if (message.getType() == ChatMessageType.FRIENDSCHAT) {
            if (message.getMessage().equalsIgnoreCase("!host stats")) {
                FriendsChatRank rank = commandUtils.getFriendsChatRank(message);
                if (rank != FriendsChatRank.UNRANKED) {
                    getHostsStats(message.getName());
                }
            }
        }
    }

    public void getHostsStats(String name) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://localhost:8081/hosts/stats?name=" + name)
                .build();

        try {
            ResponseBody body = client.newCall(request).execute().body();

            InputStream in = body.byteStream();
            HostStats hostStats = RuneLiteAPI.GSON.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), HostStats.class);

            try {
                chatUtils.sendClanChatMessage("/" + name + " has hosted with Run4Less for a total of");
                printDuration(hostStats.getDuration());
            } catch (AWTException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void printDuration(Long duration) throws AWTException {

        //milliseconds
        long different = duration * 1000;

        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        chatUtils.sendClanChatMessage("/" + elapsedDays + " days "
                + elapsedHours + " hours "
                + elapsedMinutes + " minutes "
                + elapsedSeconds + " seconds ");


    }
}
