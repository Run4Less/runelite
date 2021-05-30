package net.runelite.client.plugins.bonnerrunnerai;

import net.runelite.api.Client;
import net.runelite.api.ScriptID;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatUtils {

    @Inject
    Client client;

    private final static Lock lock = new ReentrantLock();

    public void sendClanChatMessage(String message) throws AWTException {
        if (!lock.tryLock()) {
            return;
        }
        client.runScript(ScriptID.CHATBOX_INPUT, 2, message);
        ChatUtils.lock.unlock();
    }

    public void sendPublicChatMessage(String message) throws AWTException {
        if (!lock.tryLock()) {
            return;
        }
        client.runScript(ScriptID.CHATBOX_INPUT, 1, message);
        ChatUtils.lock.unlock();
    }

    public void pressRobotBackSpace() throws AWTException {
        if (!lock.tryLock()) {
            return;
        }
        Robot robot = null;
        robot = new Robot();
        robot.setAutoDelay(0);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        ChatUtils.lock.unlock();
    }


    public boolean canSendMessage(LocalDateTime lastSendMessage) {
        if(lastSendMessage == null) {
            return true;
        }
        long between = ChronoUnit.SECONDS.between(lastSendMessage, LocalDateTime.now());
        if(between >= 10) {
            return true;
        }
        return false;

    }


}
