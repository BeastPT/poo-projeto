package uma.footballmanager;

import java.time.LocalDate;
import java.util.TreeMap;


public class Match {
    private Team homeTeam;
    private Team visitingTeam;
    private final LocalDate date;
    private final int matchHour;
    private final int matchMinute;

    private boolean finished;
    private TreeMap<Integer, String> goals;
    private TreeMap<Integer, String> sufferedGoals;

    private Referee referee;
    public Match(LocalDate date, int matchHour, int matchMinute, boolean finished, TreeMap<Integer, String> goals, TreeMap<Integer, String> sufferedGoals) {
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = finished;
        this.goals = goals;
        this.sufferedGoals = sufferedGoals;
    }

    public Match(LocalDate date, int matchHour, int matchMinute, boolean finished, TreeMap<Integer, String> goals, TreeMap<Integer, String> sufferedGoals, Referee referee) {
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = finished;
        this.goals = goals;
        this.sufferedGoals = sufferedGoals;
        this.referee = referee;
    }

    public void setHomeTeam(Team homeTeam) {
        if (this.homeTeam != null) {
            this.homeTeam = homeTeam;
        }
    }

    public void setVisitingTeam(Team visitingTeam) {
        if (this.visitingTeam != null) {
            this.visitingTeam = visitingTeam;
        }
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        if (this.referee != null) {
            this.referee = referee;
        }
    }

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

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute, boolean finished, TreeMap<Integer, String> goals, TreeMap<Integer, String> sufferedGoals) {
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

        MatchSimulator simulation = new MatchSimulator(homeTeam, visitingTeam, referee).simulate();

        goals = simulation.getGoals();
        sufferedGoals = simulation.getSufferedGoals();

        finished = true;

    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getVisitingTeam() {
        return visitingTeam;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getMatchHour() {
        return matchHour;
    }

    public int getMatchMinute() {
        return matchMinute;
    }

    public boolean isFinished() {
        return finished;
    }

    public TreeMap<Integer, String> getGoals() {
        return new TreeMap<>(goals);
    }

    public TreeMap<Integer, String> getSufferedGoals() {
        return new TreeMap<>(sufferedGoals);
    }
}
