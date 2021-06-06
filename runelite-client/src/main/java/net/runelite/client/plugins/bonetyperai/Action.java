package net.runelite.client.plugins.bonetyperai;

public class Action {

    private int delayOnPress;

    private int delayAfterPress;

    private char aChar;

    public Action(int delayOnPress, int delayAfterPress, char aChar) {
        this.delayOnPress = delayOnPress;
        this.delayAfterPress = delayAfterPress;
        this.aChar = aChar;
    }

    public int getDelayOnPress() {
        return delayOnPress;
    }

    public void setDelayOnPress(int delayOnPress) {
        this.delayOnPress = delayOnPress;
    }

    public int getDelayAfterPress() {
        return delayAfterPress;
    }

    public void setDelayAfterPress(int delayAfterPress) {
        this.delayAfterPress = delayAfterPress;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Action{");
        sb.append("delayOnPress=").append(delayOnPress);
        sb.append(", delayAfterPress=").append(delayAfterPress);
        sb.append(", aChar=").append(aChar);
        sb.append('}');
        return sb.toString();
    }
}
