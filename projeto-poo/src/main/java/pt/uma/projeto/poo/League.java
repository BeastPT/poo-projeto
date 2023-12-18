package pt.uma.projeto.poo;

import java.util.ArrayList;

public class League {
    private final String name;
    private final String country;
    private final ArrayList<Team> teams;
    private final ArrayList<Match> matches;

    public League(String name, String country) {
        this.name = name;
        this.country = country;
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches) {
        this.name = name;
        this.country = country;
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
        return new ArrayList<>(teams);
    }

    public Team getTeamByIndex(int index) {
        return teams.get(index);
    }

    public Team getTeamByName(String name) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
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
