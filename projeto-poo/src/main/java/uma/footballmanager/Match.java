package uma.footballmanager;

import com.sun.source.tree.Tree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;


public class Match {
    private final Team homeTeam;
    private final Team visitingTeam;
    private final LocalDate date;
    private final int matchHour;
    private final int matchMinute;

    private boolean finished;
    private TreeMap<Integer, Player> goals;
    private TreeMap<Integer, Player> sufferedGoals;

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = false;
        this.goals = new TreeMap<>();
        this.sufferedGoals = new TreeMap<>();
    }

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute, boolean finished, TreeMap<Integer, Player> goals, TreeMap<Integer, Player> sufferedGoals) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = finished;
        this.goals = goals;
        this.sufferedGoals = sufferedGoals;
    }

    public void simulateMatch() {
        if (finished) return;

        MatchSimulator simulation = new MatchSimulator(homeTeam, visitingTeam).simulate();

        goals = simulation.getGoals();
        sufferedGoals = simulation.getSufferedGoals();

        finished = true;

    }
}
