package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class FootballManager {
    public static void main(String[] args) {
        //Game game = SavesManager.createGame();
        //debugMatch(game);
        //SavesManager.saveGame(game);
        Menu.mainMenu();
    }

    private static void debugMatch(Game game) {
        League leg = game.getLeagues().get(2);
        var a1 = leg.getTeams();
        for (int i = 0; i < 10; i++) {
            var team1 = a1.get((int) (Math.random()*a1.size()));
            System.out.println(team1.getName());
            var team2 = a1.get((int) (Math.random()*a1.size()));
            System.out.println(team2.getName());
            Match match = new Match(team1, team2, LocalDate.now(), 20, 0);
            var ref = leg.getReferee();
            match.setReferee(ref);
            match.simulateMatch();
            leg.addMatch(match);
        }
    }


}
