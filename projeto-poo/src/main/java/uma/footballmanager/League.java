package uma.footballmanager;

import java.util.ArrayList;

public class League implements MenuData {
    private final String name;
    private final String country;
    private final ArrayList<Team> teams;
    private final ArrayList<Match> matches;

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = matches;
    }
    public League(String name, String country, ArrayList<Team> teams) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    public ArrayList<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    @Override
    public void showData() {
        System.out.println("Nome da Liga: " + name);
        System.out.println("Pais: " + country);
        System.out.println("Equipas: ");
        for (Team team : teams) {
            System.out.println(" - " + team.getName());
        }
    }

    public String getCountry() {
        return country;
    }

    public Team getTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
}