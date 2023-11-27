package pt.uma.projeto.poo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class League {
    private int id;
    private String name;
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Match> matches = new ArrayList<>();

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public League(int id, String name, ArrayList<Team> teams, ArrayList<Match> matches) {
        this.id = id;
        this.name = name;
        this.teams = teams;
        this.matches = matches;
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public boolean removeTeam(Team team) {
        return this.teams.remove(team);
    }
    public boolean removeTeam(int index) {
        try {
            this.teams.remove(index);
            return true;
        } catch (Exception e) {
            System.out.println("Team index out of bound");
            return false;
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam(int index) {
        return teams.get(index);
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Liga " + name + " com " + teams.size() + " equipas e " + matches.size() + " jogos"
                + "\nEquipas: " + teams;
    }
}
