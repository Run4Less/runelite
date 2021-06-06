package net.runelite.client.plugins.bonnerrunnerai;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UnusedCode {

    private void robotPress(String keys) throws AWTException {
        Robot robot = null;
        robot = new Robot();
        robot.setAutoDelay(0);

        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            //Pressing shift if it's uppercase
            if (Character.isUpperCase(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
            if (c == '!') {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_1);
                continue;
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            //Releasing shift if it's uppercase
            if (Character.isUpperCase(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
