package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class FootballManager {
    public static void main(String[] args) {
<<<<<<< Updated upstream
        //Game game = SavesManager.createGame();
       // game.getLeagues().get(0).generateMatches();
        //debugMatch(game);
       // SavesManager.saveGame(game);
        Menu.mainMenu();
        
=======
        Menu.saveMenu();
//        Game game = SavesManager.createGame();
//        League l = game.getLeagues().get(0);
//        l.generateMatches();
//        l.getMatches();
//        for (Match match : l.getMatches()) {
//            match.simulateMatch();
//        }
//        debugMatch(game);
>>>>>>> Stashed changes
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
