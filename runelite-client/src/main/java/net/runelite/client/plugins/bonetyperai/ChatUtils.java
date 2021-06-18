package net.runelite.client.plugins.bonetyperai;

import net.runelite.api.Client;
import net.runelite.api.ScriptID;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatUtils {

    @Inject
    Client client;

    private final static Lock lock = new ReentrantLock();

    private static Robot robot = null;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void robotType(char c, int delay) {
        int code = KeyEvent.getExtendedKeyCodeForChar(c);
        if (Character.isUpperCase(c))
            robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(code);
        robot.delay(delay);
        robot.keyRelease(code);
        if (Character.isUpperCase(c))
            robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    public void robotTypeEnter(int delay) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void robotTypeDelete(int delay) {
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    public void sendPublicChatMessage(String message) throws AWTException {
        if (!lock.tryLock()) {
            return;
        }
        client.runScript(ScriptID.CHATBOX_INPUT, 0, message);
        ChatUtils.lock.unlock();
    }


}
