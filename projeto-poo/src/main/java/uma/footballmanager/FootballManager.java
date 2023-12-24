package uma.footballmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class FootballManager {
    public static void main(String[] args) {
        Game game = SavesManager.loadData("singleJson");
        //game.getLeagues().get(2).getTeams().forEach(team -> System.out.println(team.getName() + " " + team.getAggressive()));
        debugMatch(game);

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
            match.simulateMatch();
            leg.addMatch(match);
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(League.class, new LeagueAdapter())
                .registerTypeAdapter(Match.class, new MatchAdapter())
                .create();

        System.out.println(gson.toJson(leg));
    }


}
