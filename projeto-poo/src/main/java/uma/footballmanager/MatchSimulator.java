package uma.footballmanager;

import java.util.TreeMap;

import static uma.footballmanager.Utils.disperseValues;
import static uma.footballmanager.Utils.getRandomInt;

public class MatchSimulator {

    private final Team homeTeam;
    private final Team visitingTeam;

    private final TreeMap<Integer, String> goals;
    private final TreeMap<Integer, String> sufferedGoals;

    private int homeTeamAttack;
    private int homeTeamDefense;

    private int visitingTeamAttack;
    private int visitingTeamDefense;

    private int homeAttack;
    private int visitingAttack;

    private int homeAggressivity;
    private int visitingAggressivity;
    private int gameAggressivity;

    private final Referee referee;

    public MatchSimulator(Team homeTeam, Team visitingTeam, Referee referee) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        this.goals = new TreeMap<>();
        this.sufferedGoals = new TreeMap<>();
        this.homeTeamAttack = (int) (homeTeam.getAttack() * 1.1);
        this.homeTeamDefense = (int) (homeTeam.getDefense() * 1.1);

        this.visitingTeamAttack = visitingTeam.getAttack();
        this.visitingTeamDefense = visitingTeam.getDefense();

        this.homeAttack = homeTeamAttack - visitingTeamDefense;
        this.visitingAttack = visitingTeamAttack - homeTeamDefense;

        this.homeAggressivity = homeTeam.getAggressive();
        this.visitingAggressivity = visitingTeam.getAggressive();

        this.gameAggressivity = (homeAggressivity + visitingAggressivity) / 2;
        fixAttacks();
        this.referee = referee;
    }

    /**
     * Atualiza as Stats da Equipa em base dos valores de cada jogador
     */
    public void updateStats() {
        this.homeTeamAttack = (int) (homeTeam.getAttack() * 1.1);
        this.homeTeamDefense = (int) (homeTeam.getDefense() * 1.1);

        this.visitingTeamAttack = visitingTeam.getAttack();
        this.visitingTeamDefense = visitingTeam.getDefense();

        this.homeAttack = homeTeamAttack - visitingTeamDefense;
        this.visitingAttack = visitingTeamAttack - homeTeamDefense;

        this.homeAggressivity = homeTeam.getAggressive();
        this.visitingAggressivity = visitingTeam.getAggressive();

        this.gameAggressivity = (homeAggressivity + visitingAggressivity) / 2;
        fixAttacks();
    }

    /**
     * Verifica se o árbitro esta a favorecer a equipa da casa
     * @return true se o árbitro estiver a favorecer a equipa da casa
     */
    private boolean riggedMatch() {
        return Utils.getRandomInt() < referee.getExperience() && referee.getBirth().place().equals(homeTeam.getStadium().getCity());
    }

    private int disperseAttacks(int attack) {
        return disperseValues(attack, -90, 70);
    }

    /**
     * Corrige os valores de ataque para que não sejam negativos e sejam entre 0 e 100
     * Valores não verificados para todas as equipas, suscetando a erros
     */
    private void fixAttacks() {
        homeAttack = disperseAttacks(homeAttack);
        visitingAttack = disperseAttacks(visitingAttack);
        if (homeAttack < 0 || visitingAttack < 0) {
            // TODO: SIMULATE PARA AS 3 LIGAS DEFAULT
            throw new RuntimeException("NEGATIVE VALUES ATTACKS");
        }
    }

    /**
     * Da um bonus a equipa que começa com a bola
     * @param chance chance de a equipa da casa começar com a bola
     */
    private void buffStartingTeam(int chance) {
        if (chance < 50) {
            System.out.println("A equipa da casa começa com a bola");
            homeAttack = (int) (homeAttack * 1.15);
        } else {
            System.out.println("A equipa visitante começa com a bola");
            visitingAttack = (int) (visitingAttack * 1.15);
        }
    }

    /**
     * Retira o bonus a equipa que começa com a bola
     * @param chance chance de a equipa da casa começar com a bola
     */
    private void debuffStartingTeam(int chance) {
        if (chance < 50) {
            homeAttack = (int) (homeAttack * 0.85);
        } else {
            visitingAttack = (int) (visitingAttack * 0.85);
        }
    }

    /**
     * Altera a agressividade das equipas
     * @param buffAgressive se a agressividade esta a ser aumentada
     * @return se a agressividade esta a ser aumentada
     */
    private boolean changeAggressivity(boolean buffAgressive) {
        if (goals.size() > sufferedGoals.size() && !buffAgressive) {
            visitingAggressivity = (int) (visitingAggressivity * 1.1);
            return true;
        } else if (goals.size() < sufferedGoals.size() && !buffAgressive) {
            homeAggressivity = (int) (homeAggressivity * 1.1);
            return true;
        } else if (goals.size() == sufferedGoals.size() && buffAgressive) {
            visitingAggressivity = visitingTeam.getAggressive();
            homeAggressivity = homeTeam.getAggressive();
            return false;
        }
        return buffAgressive;
    }

    /**
     * Simula um golo
     * @param i minuto do jogo
     * @return se houve golo
     */
    private boolean simulateGoal(int i) {
        int attack;
        boolean homeTeamGoal = false;
        Team team;
        if (getRandomInt() < 30) return false; // Limitar um pouco o número de golos na partida
        if (getRandomInt() < 50) {
            attack = homeAttack;
            team = homeTeam;
            homeTeamGoal = true;
        } else {
            attack = visitingAttack;
            team = visitingTeam;
        }
        if (riggedMatch()) {
            attack += Utils.getRandomInt(1, 10);
        }

        if (getRandomInt() < attack) {
            Positions pos = getGoalPosition();
            var playersByPos = team.getPlayersByPosition(pos);
            int randomIndex = getRandomInt(0, playersByPos.size() - 1);
            Player player = playersByPos.get(randomIndex);
            addGoal(i, player, homeTeamGoal);
            return true;
        }
        return false;
    }

    /**
     * Simula uma lesão
     */
    private void simulateInjure() {
        Positions pos = getInjurePosition();
        Team team = (getRandomInt() < 50) ? homeTeam : visitingTeam;
        var playersByPos = team.getPlayersByPosition(pos);
        int randomIndex = getRandomInt(0, playersByPos.size() - 1);
        Player player = playersByPos.get(randomIndex);
        System.out.println("Jogador " + player.getName() + " da equipa " + team.getName() + " lesionado");
        team.addInjure(player, this);
    }

    /**
     * Simula o jogo por completo
     * @return MatchSimulator
     */
    public MatchSimulator simulate() {
        int startChance = getRandomInt();
        buffStartingTeam(startChance);
        int buffStartingTeamTime = getRandomInt(10, 20);

        boolean debuffStarting = false;
        boolean buffAgressive = false;

        boolean debuffGoals = false;
        int debuffGoalsTime = 0;
        int debuffInjures = 0;

        for (int i = 0; i < 90; i++) {
            if (i > buffStartingTeamTime && !debuffStarting) {
                debuffStartingTeam(startChance);
                debuffStarting = true;
            }

            buffAgressive = changeAggressivity(buffAgressive);

            if (!debuffGoals && (simulateGoal(i))) {
                debuffGoals = true;
                debuffGoalsTime = getRandomInt(1, 8);
            } else {
                if (debuffGoalsTime == 0) {
                    debuffGoals = false;
                } else
                    debuffGoalsTime--;
            }

            double prob = (double) gameAggressivity / getRandomInt(50, 100);
            if (Math.random() * 100 < prob && debuffInjures == 0) {
                simulateInjure();
                debuffInjures = getRandomInt(4, 12);
            }

        }
        System.out.println("Resultado: " + goals.size() + " - " + sufferedGoals.size());
        return this;
    }

    /**
     * Seleciona a posição do jogador que sofreu a lesão
     * @return posição do jogador que sofreu a lesão
     */
    private Positions getInjurePosition() {
        int random = getRandomInt();
        if (random < 1) {
            return Positions.GOALKEEPER;
        } else if (random < 30) {
            return Positions.DEFENDER;
        } else if (random < 60) {
            return Positions.MIDFIELDER;
        } else {
            return Positions.ATTACKER;
        }
    }

    /**
     * Seleciona a posição do jogador que marcou o golo
     * @return posição do jogador que marcou o golo
     */
    private Positions getGoalPosition() {
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

    /**
     * Adiciona um golo ao jogo
     * @param minute minuto do jogo
     * @param player jogador que marcou o golo
     * @param isHomeTeam se o golo foi marcado pela equipa da casa
     */
    private void addGoal(int minute, Player player, boolean isHomeTeam) {
        System.out.println("Golo do jogador " + player.getName() + " da equipa " + ((isHomeTeam) ? homeTeam.getName() : visitingTeam.getName()) + " no minuto " + minute);
        if (isHomeTeam) {
            this.goals.put(minute, player.getName());
        } else {
            this.sufferedGoals.put(minute, player.getName());
        }
    }

    public TreeMap<Integer, String> getGoals() {
        return goals;
    }

    public TreeMap<Integer, String> getSufferedGoals() {
        return sufferedGoals;
    }
}
