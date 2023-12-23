package uma.footballmanager;

import java.util.ArrayList;

public class League {
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
}
