package uma.footballmanager;

import java.util.TreeMap;

import static uma.footballmanager.Utils.getRandomInt;

public class MatchSimulator {

    private final Team homeTeam;
    private final Team visitingTeam;

    private final TreeMap<Integer, Player> goals;
    private final TreeMap<Integer, Player> sufferedGoals;

    private int homeTeamAttack;
    private int homeTeamDefense;

    private int visitingTeamAttack;
    private int visitingTeamDefense;

    private int homeAttack;
    private int visitingAttack;

    private int homeAggressivity;
    private int visitingAggressivity;

    public MatchSimulator(Team homeTeam, Team visitingTeam) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.goals = new TreeMap<>();
        this.sufferedGoals = new TreeMap<>();
        this.homeTeamAttack = (int) (homeTeam.getAttack()*1.1);
        this.homeTeamDefense = (int) (homeTeam.getDefense()*1.1);

        this.visitingTeamAttack = visitingTeam.getAttack();
        this.visitingTeamDefense = visitingTeam.getDefense();

        this.homeAttack = homeTeamAttack - visitingTeamDefense;
        this.visitingAttack = visitingTeamAttack - homeTeamDefense;

        this.homeAggressivity = homeTeam.getAggressive();
        this.visitingAggressivity = visitingTeam.getAggressive();
    }

    private void fixAttacks() {
        int hAbs = Math.abs(homeAttack);
        int vAbs = Math.abs(visitingAttack);

        if (homeAttack < 0 && visitingAttack < 0) {
            int temp = homeAttack;
            homeAttack = vAbs;
            visitingAttack = Math.abs(temp);
        } else if (homeAttack < 0 || visitingAttack < 0) {
            if (hAbs > vAbs) {
                homeAttack = hAbs;
                visitingAttack = vAbs;
            } else if (vAbs > hAbs) {
                homeAttack = vAbs;
                visitingAttack = hAbs;
            } else {
                homeAttack = hAbs;
                visitingAttack = vAbs;
            }
        }

        System.out.println("Ataque da equipa da casa: " + homeAttack);
        System.out.println("Ataque da equipa visitante: " + visitingAttack);
    }

    private void buffStartingTeam(int chance){
        if (chance < 50) {
            System.out.println("A equipa da casa começa com a bola");
            homeAttack += 10;
        } else {
            System.out.println("A equipa visitante começa com a bola");
            visitingAttack += 10;
        }
    }

    private void debuffStartingTeam(int chance){
        if (chance < 50) {
            homeAttack -= 10;
        } else {
            visitingAttack -= 10;
        }
    }

    private boolean changeAggressivity(boolean buffAgressive) {
        if (goals.size() > sufferedGoals.size() && !buffAgressive) {
            visitingAggressivity = (int) (visitingAggressivity*1.1);
            return true;
        } else if (goals.size() < sufferedGoals.size() && !buffAgressive) {
            homeAggressivity = (int) (homeAggressivity*1.1);
            return true;
        } else if (goals.size() == sufferedGoals.size() && buffAgressive) {
            visitingAggressivity = visitingTeam.getAggressive();
            homeAggressivity = homeTeam.getAggressive();
            return false;
        }
        return buffAgressive;
    }

    private boolean simulateGoal(int i) {
        int random = getRandomInt();
        int attack;
        boolean homeTeamGoal = false;
        Team team;
        if (getRandomInt() < 50) {
            attack = homeAttack;
            team = homeTeam;
            homeTeamGoal = true;
        } else {
            attack = visitingAttack;
            team = visitingTeam;
        }

        if (random < attack) {
            Positions pos = getGoalPosition();
            var playersByPos = team.getPlayersByPosition(pos);
            int randomIndex = getRandomInt(0, playersByPos.size());
            Player player = playersByPos.get(randomIndex);
            addGoal(i, player, homeTeamGoal);
            return true;
        }
        return false;
    }

    public MatchSimulator simulate() {
        fixAttacks();

        int startChance = getRandomInt();
        buffStartingTeam(startChance);
        int buffStartingTeamTime = getRandomInt(10, 20);

        boolean debuffStarting = false;
        boolean buffAgressive = false;

        boolean debuffGoals = false;
        int debuffGoalsTime = 0;


        for (int i = 0; i < 90; i++) {
            if (i>buffStartingTeamTime && !debuffStarting) {
                debuffStartingTeam(startChance);
                debuffStarting = true;
            }

            buffAgressive = changeAggressivity(buffAgressive);

            if (!debuffGoals && (simulateGoal(i))) {
                debuffGoals = true;
                debuffGoalsTime = getRandomInt(1, 8);
            } else {
                debuffGoalsTime = (debuffGoalsTime == 0) ? 0 : debuffGoalsTime - 1;
            }

            // TODO: Simulate injuries

        }
        System.out.println("Resultado: " + goals.size() + " - " + sufferedGoals.size());
        return this;
    }

    private Positions getGoalPosition(){
        int random = getRandomInt();
        if (random < 2) {
            return Positions.GOALKEEPER;
        } else if (random < 13) {
            return Positions.DEFENDER;
        } else if (random < 42) {
            return Positions.MIDFIELDER;
        } else {
            return Positions.ATTACKER;
        }
    }

    private void addGoal(int minute, Player player, boolean homeTeam) {
        if (homeTeam) {
            this.goals.put(minute, player);
        } else {
            this.sufferedGoals.put(minute, player);
        }
    }
    public TreeMap<Integer, Player> getGoals() {
        return goals;
    }

    public TreeMap<Integer, Player> getSufferedGoals() {
        return sufferedGoals;
    }
}
