package pt.uma.projeto.poo;

public class PlayerStats {
    private int goals;
    private int assists;
    private int yellowCards;
    private int redCards;
    private int fouls;

    public PlayerStats() {
        this.goals = 0;
        this.assists = 0;
        this.yellowCards = 0;
        this.redCards = 0;
        this.fouls = 0;
    }

    public PlayerStats(int goals, int assists, int yellowCards, int redCards, int fouls) {
        this.goals = goals;
        this.assists = assists;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.fouls = fouls;
    }

    public int getGoals() {
        return goals;
    }

    public void addGoal() {
        this.goals++;
    }

    public int getAssists() {
        return assists;
    }

    public void addAssist() {
        this.assists++;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void addYellowCard() {
        this.yellowCards++;
    }

    public int getRedCards() {
        return redCards;
    }

    public void addRedCard() {
        this.redCards++;
    }

    public int getFouls() {
        return fouls;
    }

    public void addFoul() {
        this.fouls++;
    }
}
