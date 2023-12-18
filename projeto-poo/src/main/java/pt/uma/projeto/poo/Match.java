package pt.uma.projeto.poo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Match {
    private final Team homeTeam;
    private final Team visitingTeam;
    private final LocalDate date;
    private final int matchHour;
    private final int matchMinute;

    private boolean finished;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> sufferedGoals;


    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = false;
        this.goals = new ArrayList<>();
        this.sufferedGoals = new ArrayList<>();
    }

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute, ArrayList<Goal> goals, ArrayList<Goal> sufferedGoals) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.goals = goals;
        this.sufferedGoals = sufferedGoals;
        this.finished = false;
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

    public void setGoals(ArrayList<Goal> goals) {
        this.goals = goals;
    }

    public void addSufferedGoal(Goal goal) {
        sufferedGoals.add(goal);
    }

    public void setSufferedGoals(ArrayList<Goal> sufferedGoals) {
        this.sufferedGoals = sufferedGoals;
    }

    public boolean hasEnded() {
        return finished;
    }

    public void endMatch() {
        finished = true;
    }

    public void simulateMatch() {
        if (finished) return;
        int homeTeamGoals = 0;
        int visitingTeamGoals = 0;


        int homeTeamAttack = (int) (homeTeam.getAttackStat()*1.1);
        int homeTeamDefense = (int) (homeTeam.getDefenseStat()*1.1);

        int visitingTeamAttack = visitingTeam.getAttackStat();
        int visitingTeamDefense = visitingTeam.getDefenseStat();

        int homeAttack = homeTeamAttack-visitingTeamDefense;
        int visitingAttack = visitingTeamAttack-homeTeamDefense;


        int lastGoal = 0;


    }

}
