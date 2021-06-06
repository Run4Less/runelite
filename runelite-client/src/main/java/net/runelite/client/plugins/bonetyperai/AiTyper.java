package net.runelite.client.plugins.bonetyperai;

import javax.inject.Inject;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AiTyper {

    @Inject
    ChatUtils chatUtils;

    private static List<Action> actionList;

    private static LocalDateTime lastActionTime;

    public synchronized void gameTick() throws AWTException {
        if (lastActionTime != null) {
            if (!canPerformNextAction(lastActionTime)) {
                return;
            }
        }
        chatUtils.sendPublicChatMessage(getRandomMessage());
        setLastActionTime();
    }

    private synchronized void setLastActionTime() {
        lastActionTime = LocalDateTime.now();
    }

    public synchronized void setActionToNull() {
        actionList = null;
    }

    private synchronized Action getNextAction() {
        if (actionList == null || actionList.size() == 0) {
            return null;
        }
        Action action = actionList.get(0);
        actionList.remove(0);
        return action;
    }

    private synchronized boolean setAction() {
        if (actionList == null || actionList.isEmpty()) {
            actionList = createActionList(makeMistake(getRandomMessage()));
            System.out.println(actionList.size());
            return true;
        }
        return false;
    }

    private List<Action> createActionList(String message) {
        List<Action> actions = new ArrayList<>();
        for (char c : message.toCharArray()) {
            actions.add(new Action(getRandomNumber(3, 50), getRandomNumber(1, 50), c));
        }
        return actions;
    }

    private String getRandomMessage() {
        List<String> allMessages = getAllMessages();
        return allMessages.get(getRandomNumber(0, allMessages.size() - 1));
    }

    private List<String> getAllMessages() {
        List<String> messageList = new ArrayList<>();
        messageList.add("Do you like Bananas, Join cc Bananas");
        messageList.add("Need a Banana, Join cc Banana");
        messageList.add("Bananas are the number 1 fruit in the game");
        messageList.add("Become a Banana today, Join cc Bananas");
        messageList.add("Banana cc for Bananas");
        messageList.add("Bananas are life");
        return messageList;
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // todo refactor this
    private String makeMistake(String message) {
        String[] splitString = message.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : splitString) {
            if (s.contains("Run4less")) {
                sb.append(s);
                sb.append(" ");
                continue;
            }
            if (shouldMakeMistake()) {
                int c = getRandomNumber(0, s.length() - 1);
                char[] chars = s.toCharArray();
                chars[c] = getRandomChar();
                sb.append(chars);
                sb.append(" ");
            } else {
                sb.append(s);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private char getRandomChar() {
        Random random = new Random();
        return (char) (random.nextInt(26) + 'a');
    }

    private boolean shouldMakeMistake() {
        return getRandomNumber(0, 1000) > 950;
    }

    public synchronized boolean canPerformNextAction(LocalDateTime lastSendMessage) {
        if (lastSendMessage == null) {
            return true;
        }
        long between = ChronoUnit.SECONDS.between(lastSendMessage, LocalDateTime.now());
        if (between >= getRandomNumber(10, 12)) {
            return true;
        }
        return false;
    }

        /*

         if (setAction()) {
            System.out.println("Starting new thread");
            threadPoolExecutor.execute(new Thread() {
                public void run() {
                    while (true) {
                        Action action = getNextAction();
                        if (action == null) {
                            ChatUtils.robotTypeEnter(getRandomNumber(3, 15));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            setLastActionTime();
                            setActionToNull();
                            return;
                        }
                        if (getRandomNumber(0, 1000) > 950) {
                            try {
                                Thread.sleep(getRandomNumber(0, 3) * 1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        ChatUtils.robotType(action.getaChar(), action.getDelayAfterPress());
                        try {
                            Thread.sleep(action.getDelayAfterPress());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
         */


}
