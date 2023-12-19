package uma.footballmanager;

import java.util.ArrayList;

public class League {
    private String name;
    private String country;
    private ArrayList<Team> teams;

    public League(String name, String country, ArrayList<Team> teams) {
        this.name = name;
        this.country = country;
        this.teams = teams;
    }
}
