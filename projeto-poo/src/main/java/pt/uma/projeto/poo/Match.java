package pt.uma.projeto.poo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Match {
    private final Team homeTeam;
    private final Team visitingTeam;
    private final LocalDate date;
    private final int matchHour;
    private final int matchMinute;
    private ArrayList<Goal> goals = new ArrayList<>();
    private ArrayList<Goal> sufferedGoals = new ArrayList<>();

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
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

    public String getMatchTime() {
        return matchHour + ":" + matchMinute;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public ArrayList<Goal> getSufferedGoals() {
        return sufferedGoals;
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }

    public void addSufferedGoal(Goal goal) {
        sufferedGoals.add(goal);
    }

}
