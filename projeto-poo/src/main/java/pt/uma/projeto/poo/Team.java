package pt.uma.projeto.poo;

import java.util.ArrayList;

public class Team {
    private int id;
    private String name;
    private String code;
    private String city;
    private int foundationYear;
    private Stadium stadium;
    private Coach coach;
    private ArrayList<Player> players;

    public Team(int id, String name, String code, String city, int foundationYear, Stadium stadium, Coach coach, Player[] players) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.city = city;
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
}
