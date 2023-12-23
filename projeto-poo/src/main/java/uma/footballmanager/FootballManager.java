package uma.footballmanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FootballManager {
    public static void main(String[] args) {
        Game game = SavesManager.loadData("singleJson");
        //game.printLeagues();


    }

    private void debugMatch(Game game) {
        var a1 = game.getLeagues().get(2).getTeams();
        for (int i = 0; i < 10; i++) {
            var team1 = a1.get((int) (Math.random()*a1.size()));
            System.out.println(team1.getName());
            var team2 = a1.get((int) (Math.random()*a1.size()));
            System.out.println(team2.getName());
            Match match = new Match(team1, team2, LocalDate.now(), 20, 0);
            match.simulateMatch();
        }
    }
}
