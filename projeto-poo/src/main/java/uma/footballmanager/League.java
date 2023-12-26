package uma.footballmanager;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class League implements MenuData {
    private final String name;
    private final String country;
    private final ArrayList<Team> teams;
    private final ArrayList<Match> matches;
    private final ArrayList<Referee> referees;

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches, ArrayList<Referee> referees) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = matches;
        this.referees = referees;
    }

    public League(String name, String country, ArrayList<Team> teams, ArrayList<Match> matches) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = matches;
        this.referees = new ArrayList<>();
    }
    public League(String name, ArrayList<Team> teams, String country, ArrayList<Referee> referees) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = new ArrayList<>();
        this.referees = referees;
    }

    public League(String name, String country, ArrayList<Team> teams) {
        this.name = name;
        this.country = country;
        this.teams = teams;
        this.matches = new ArrayList<>();
        this.referees = new ArrayList<>();
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

    public ArrayList<Referee> getReferees() {
        return new ArrayList<>(referees);
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

    public Referee getReferee(String refereeName) {
        for (Referee referee : referees) {
            if (referee.getName().equals(refereeName)) {
                return referee;
            }
        }
        return null;
    }

    public Referee getReferee() {
        return referees.get(Utils.getRandomInt(0, referees.size()-1));
    }
}