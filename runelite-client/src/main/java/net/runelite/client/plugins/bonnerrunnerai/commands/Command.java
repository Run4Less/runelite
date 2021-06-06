package net.runelite.client.plugins.bonnerrunnerai.commands;

import net.runelite.api.events.ChatMessage;

import java.io.IOException;

public interface Command {

    void process(ChatMessage message) throws IOException;
}
