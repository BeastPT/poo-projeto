package pt.uma.projeto.poo;

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

    public void addMatch(Match match) {
        this.matches.add(match);
    }

}
