package net.runelite.client.plugins.bonetyperai;

import javax.inject.Inject;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class AiTyper {

    @Inject
    ChatUtils chatUtils;

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    private List<Action> actionList;

    private LocalDateTime lastActionTime;

    public synchronized void gameTick() throws AWTException {
        if (lastActionTime != null) {
            if (!canPerformNextAction(lastActionTime)) {
                return;
            }
        }

        if (executor.getQueue().size() > 0 || executor.getActiveCount() > 0) {
            System.out.println("queue size greatrer than 0");
            return;
        }
        System.out.println("made it here");
        // get random message
        String randomMessage = getRandomMessage();

        // should make any mistake in message
        // 75% chance of making a mistake
        if (getRandomNumber(1, 4) >= 3) {
            // make mistake
            System.out.println("Time to make mistake");
            setAction(createActionList(makeMistake(randomMessage)));
        } else {
            setAction(createActionList(randomMessage));
        }

        executor.execute(() -> {
            System.out.println("starting typing");
            while (!actionList.isEmpty()) {
                Action nextAction = getNextAction();
                if (nextAction.getaChar() == '|') {
                    chatUtils.robotTypeDelete(nextAction.getDelayOnPress());
                    try {
                        Thread.sleep(nextAction.getDelayAfterPress());
                    } catch (InterruptedException e) {
                        System.out.println("error");
                    }
                    continue;
                }
                chatUtils.robotType(nextAction.getaChar(), nextAction.getDelayOnPress());
                try {
                    Thread.sleep(nextAction.getDelayAfterPress());
                } catch (InterruptedException e) {
                    System.out.println("error");
                }
            }
            if (getRandomNumber(0, 10) >= 8) {
                try {
                    Thread.sleep(getRandomNumber(2000, 5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            chatUtils.robotTypeEnter(getRandomNumber(10, 50));
        });
        setLastActionTime();
    }

    private synchronized void setLastActionTime() {
        lastActionTime = LocalDateTime.now();
    }

    private List<Action> createActionList(String message) {
        List<Action> actions = new ArrayList<>();
        for (char c : message.toCharArray()) {
            actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 75), c));
            int randomNumber = getRandomNumber(0, 100);
            System.out.println("Number for adding char " + randomNumber);
            if (randomNumber >= 97) {
                System.out.println("Adding extra chars!!!!!!!!!!!");
                if (getRandomNumber(0, 2) >= 1) {
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), getRandomChar()));
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), getRandomChar()));
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), '|'));
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), '|'));
                } else {
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), getRandomChar()));
                    actions.add(new Action(getRandomNumber(3, 75), getRandomNumber(1, 2000), '|'));
                }
            }
        }
        return actions;
    }

    private synchronized Action getNextAction() {
        if (actionList == null || actionList.size() == 0) {
            return null;
        }
        Action action = actionList.get(0);
        actionList.remove(0);
        return action;
    }

    private synchronized void setAction(List<Action> actionListInternal) {
        actionList = actionListInternal;
    }

    private String getRandomMessage() {
        List<String> allMessages = getAllMessages();
        return allMessages.get(getRandomNumber(0, allMessages.size() - 1));
    }

    private List<String> getAllMessages() {
        List<String> messageList = new ArrayList<>();
        messageList.add("Hire a Bone Runner today, Join cc [Run4Less]");
        messageList.add("Run4Less is the best Bone Running Clan");
        messageList.add("Become a Bone Runner today, Join cc [Run4Less]");
        messageList.add("Run4Less is the Cheapest Bone Running Clan");
//        messageList.add("Do you like Bananas, Join cc Bananas");
//        messageList.add("Need a Banana, Join cc Bananas");
//        messageList.add("Become a Banana today, Join cc Bananas");
//        messageList.add("Banana cc for Bananas");
//        messageList.add("Bananas are life");
        return messageList;
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // todo refactor thisr
    private String makeMistake(String message) {
        String[] splitString = message.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : splitString) {
            if (s.contains("[Run4Less]") || s.contains("Run4Less")) {
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
        return getRandomNumber(0, 100) >= 95;
    }

    public synchronized boolean canPerformNextAction(LocalDateTime lastSendMessage) {
        if (lastSendMessage == null) {
            return true;
        }

        long between = ChronoUnit.SECONDS.between(lastSendMessage, LocalDateTime.now());
        if (between >= getRandomNumber(10, 25)) {
            return true;
        }
        return false;

    }
}
