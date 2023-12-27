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

    public Match(Team homeTeam, Team visitingTeam, LocalDate date, int matchHour, int matchMinute, Referee referee) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.date = date;
        this.matchHour = matchHour;
        this.matchMinute = matchMinute;
        this.finished = false;
        this.goals = new TreeMap<>();
        this.sufferedGoals = new TreeMap<>();
        this.referee = referee;
    }

    public void setHomeTeam(Team homeTeam) {
        if (this.homeTeam == null) {
            this.homeTeam = homeTeam;
        }
    }

    public void setVisitingTeam(Team visitingTeam) {
        if (this.visitingTeam == null) {
            this.visitingTeam = visitingTeam;
        }
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        if (this.referee == null) {
            this.referee = referee;
        }
    }

    public void simulateMatch() {
        if (finished) {
            System.out.println("Já foi simulado");
            return;
        }

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (finished) {
            result.append("Jogo realizado em ").append(date)
                    .append(" às ").append(matchHour).append(":").append(String.format("%02d", matchMinute));

            result.append(" (Finalizado)");
            result.append("\nResultado: ").append(goals.size()).append(" - ").append(sufferedGoals.size());

            result.append("\nEquipa da Casa: ").append(homeTeam.getName())
                    .append("\nEquipa visitante: ").append(visitingTeam.getName());

            if (referee != null) {
                result.append("\nÁrbitro: ").append(referee.getName());
            }

            result.append("\nGolos: ");
            appendGoals(result, goals);

            result.append("\nGolos Sofridos: ");
            appendGoals(result, sufferedGoals);
        } else {
            result.append("Jogo por realizar em ").append(date)
                    .append(" às ").append(matchHour).append(":").append(String.format("%02d", matchMinute));
            result.append("\nEquipa da Casa: ").append(homeTeam.getName())
                    .append("\nEquipa visitante: ").append(visitingTeam.getName());

            if (referee != null) {
                result.append("\nÁrbitro: ").append(referee.getName());
            }
        }

        return result.toString();
    }

    private void appendGoals(StringBuilder result, TreeMap<Integer, String> goalMap) {
        if (goalMap.isEmpty()) {
            result.append("Nenhum");
        } else {
            for (Integer minute : goalMap.keySet()) {
                result.append("\n").append(minute).append("' ").append(goalMap.get(minute));
            }
        }
    }
}
