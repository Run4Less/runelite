package net.runelite.client.plugins.bonnerrunnerai;

public class HostStats {

    private Long duration;

    public HostStats(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HostStatDTO{");
        sb.append("duration=").append(duration);
        sb.append('}');
        return sb.toString();
    }

}
