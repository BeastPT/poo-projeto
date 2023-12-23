package uma.footballmanager;

import java.util.ArrayList;

public class Game {
    private ArrayList<League> leagues;

    public Game(ArrayList<League> leagues) {
        this.leagues = leagues;
    }

    public Game() {
        this.leagues = new ArrayList<>();
    }

    public void addLeague(League league) {
        this.leagues.add(league);
    }

    public void printLeagues() {
        for (League league : this.leagues) {
            System.out.println(league.getName());
        }
    }

    public ArrayList<League> getLeagues() {
        return leagues;
    }
}
