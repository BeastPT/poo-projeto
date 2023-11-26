package pt.uma.projeto.poo;

public class Goal {
    private final int minute;
    private final Player player;

    public Goal(int minute, Player player) {
        this.minute = minute;
        this.player = player;
    }

    public int getMinute() {
        return minute;
    }

    public Player getPlayer() {
        return player;
    }
}
