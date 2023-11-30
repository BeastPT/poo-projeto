package pt.uma.projeto.poo;

import java.util.ArrayList;

public class Team {
    private final int id;
    private String name;
    private String code;
    private final int foundationYear;
    private Stadium stadium;
    private Coach coach;
    private final ArrayList<Player> players;

    private int attackStat;
    private int defenseStat;

    public Team(int id, String name, String code, int foundationYear, Stadium stadium, Coach coach, ArrayList<Player> players) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.foundationYear = foundationYear;
        this.stadium = stadium;
        this.coach = coach;
        this.players = players;
    }

    public Team(int id, String name, String code, int foundationYear, Stadium stadium, Coach coach) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.foundationYear = foundationYear;
        this.stadium = stadium;
        this.coach = coach;
        this.players = new ArrayList<>();
    }

    private boolean canAddPlayer() {
        return players.size() < 11;
    }
    public boolean addPlayer(Player player) {
        if (canAddPlayer()) {
            return players.add(player);
        }
        return false;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public boolean removePlayer(int index) {
        try {
            this.players.remove(index);
            return true;
        } catch (Exception e) {
            System.out.println("Players index out of bound");
            return false;
        }
    }

    public void changeName(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public int getAttackStat() {
        return attackStat;
    }

    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    public int getDefenseStat() {
        return defenseStat;
    }

    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    @Override
    public String toString() {
        return "Equipa " + name + " com " + players.size() + " jogadores"
                + "\nJogadores: " + players
                + "\nTreinador: " + coach;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
