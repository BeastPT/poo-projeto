package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;

public class Team {
    private String name;
    private String code;
    @SerializedName("founded")
    private Integer foundationYear;
    private Stadium stadium;
    private Coach coach;
    private ArrayList<Player> players;

    private Integer Attack;
    private Integer Defense;

    private Integer Aggressive;

    public Team(String name, String code, Integer foundationYear, Stadium stadium, Coach coach, ArrayList<Player> players, Integer Attack, Integer Defense, Integer Aggressive) {
        this.name = name;
        this.code = code;
        this.foundationYear = foundationYear;
        this.stadium = stadium;
        this.coach = coach;
        this.players = players;
        this.Attack = Attack;
        this.Defense = Defense;
        this.Aggressive = Aggressive;
    }

    public void updateStats() {
        int newAttack = 0;
        int newDefense = 0;
        int newAggressivity = 0;
        int playerCounter = 0;
        for (Player player : players) {
            if (player.isInjured()) continue;
            playerCounter++;
            int playerAttack = player.getStats().attack();
            int playerDefense = player.getStats().defense();
            newAggressivity += player.getStats().aggressive();
            switch (player.getPosition()) {
                case GOALKEEPER:
                    newAttack += (int) (playerAttack*0.5);
                    newDefense += (int) (playerDefense*5);
                    break;
                case DEFENDER:
                    newAttack += (int) (playerAttack*1);
                    newDefense += (int) (playerDefense*2);
                    break;
                case MIDFIELDER:
                    newAttack += (int) (playerAttack*2);
                    newDefense += (int) (playerDefense*1);
                    break;
                case ATTACKER:
                    newAttack += (int) (playerAttack*2.5);
                    newDefense += (int) (playerDefense*0.5);
                    break;
            }
        }
        this.Attack = disperseValues(newAttack, true);
        this.Defense = disperseValues(newDefense, false);
        this.Aggressive = (int) (newAggressivity/playerCounter);
    }

    private int disperseValues(int value, boolean disperseAttack) {
        if (disperseAttack) {
            return Utils.disperseValues(value, 300, 1100);
        } else {
            return Utils.disperseValues(value, 600, 1200);
        }
    }
    public String getName() {
        return name;
    }

    public int getAttack() {
        if (Attack == null) {
            updateStats();
        }
        return Attack;
    }

    public int getDefense() {
        if (Attack == null) {
            updateStats();
        }
        return Defense;
    }

    public int getAggressive() {
        if (Aggressive == null) {
            updateStats();
        }
        return Aggressive;
    }

    public ArrayList<Player> getPlayersByPosition(Positions position) {
        return players.stream().filter(player -> player.getPosition() == position).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return Objects.equals(name, team.name);
    }
}
