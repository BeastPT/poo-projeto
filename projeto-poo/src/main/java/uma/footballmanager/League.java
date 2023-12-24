package uma.footballmanager;

import java.util.ArrayList;

public class League implements MenuData {
    private final String name;
    private final String country;
    private final ArrayList<Team> teams;

    public League(String name, String country, ArrayList<Team> teams) {
        this.name = name;
        this.country = country;
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
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
}