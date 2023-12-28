package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Team implements IMenuData {
    private final String name;
    private final String code;
    @SerializedName("founded")
    private Integer foundationYear;
    private Stadium stadium;
    private Coach coach;
    private final ArrayList<Player> players;

    private Integer Attack;
    private Integer Defense;

    private Integer Aggressive;

    public Team(String name, String code, Integer foundationYear, ArrayList<Player> players) {
        this.name = name;
        this.code = code;
        this.foundationYear = foundationYear;
        this.players = players;
    }

    /**
     * Atualiza as Stats da Equipa em base dos valores de cada jogador
     */
    public void updateStats() {
        int newAttack = 0;
        int newDefense = 0;
        int newAggressivity = 0;
        int playerCounter = 0;
        for (Player player : players) {
            if (player.isInjured()) continue;
            playerCounter++;
            int playerAttack = player.getStats().attack();
            int playerDefense = player.getStats().defense();
            newAggressivity += player.getStats().aggressive();
            switch (player.getPosition()) {
                case GOALKEEPER:
                    newAttack += (int) (playerAttack * 0.5);
                    newDefense += playerDefense * 5;
                    break;
                case DEFENDER:
                    newAttack += playerAttack;
                    newDefense += playerDefense * 2;
                    break;
                case MIDFIELDER:
                    newAttack += playerAttack * 2;
                    newDefense += playerDefense;
                    break;
                case ATTACKER:
                    newAttack += (int) (playerAttack * 2.5);
                    newDefense += (int) (playerDefense * 0.5);
                    break;
            }
        }
        this.Attack = disperseValues(newAttack, true);
        this.Defense = disperseValues(newDefense, false);
        this.Aggressive = Utils.disperseValues(newAggressivity / playerCounter, 0, 34);
    }

    private int disperseValues(int value, boolean disperseAttack) {
        if (disperseAttack) {
            return Utils.disperseValues(value, 300, 1100);
        } else {
            return Utils.disperseValues(value, 600, 1200);
        }
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        if (Attack == null) {
            updateStats();
        }
        return Attack;
    }

    public int getDefense() {
        if (Defense == null) {
            updateStats();
        }
        return Defense;
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Adiciona uma lesao a um jogador na equipa
     * @param player Jogador a adicionar a lesao
     * @param match Jogo da Lesão
     */
    public void addInjure(Player player, MatchSimulator match) {
        Player p = getPlayer(player);
        if (p != null) {
            p.setInjured(true);
        }
        updateStats();
        match.updateStats();
    }

    public int getAggressive() {
        if (Aggressive == null) {
            updateStats();
        }
        return Aggressive;
    }

    public ArrayList<Player> getPlayersByPosition(Positions position) {
        return players.stream().filter(player -> player.getPosition() == position).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Player getPlayer(Player player) {
        return players.stream().filter(player1 -> player1.equals(player)).findFirst().orElse(null);
    }

    public ArrayList<Player> getInjuredPlayers() {
        return players.stream().filter(Player::isInjured).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public Integer getYearFounded() {
        return foundationYear;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return Objects.equals(name, team.name);
    }

    /**
     * Gera uma equipa com recurso a inputs do utilizador
     * @return Team
     */
    public static Team generateTeam() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escreva o nome da sua equipa:");
        String name = sc.nextLine();

        System.out.println("Escrevas as Siglas da sua equipa (por exemplo, BEN):");
        String code = sc.nextLine();

        System.out.println("Digite o ano de fundação:");
        int yearFounded = sc.nextInt();
        sc.nextLine();

        ArrayList<Player> players = new ArrayList<>();

        for (int i = 1; i < 12; i++) {
            System.out.println("Escreva o nome do jogador " + i + ":");
            players.add(Player.generatePlayer());
            // não verifica a tática
        }

        return new Team(name, code, yearFounded, players);
    }

    /**
     * Substitui um jogador por outro
     * @param previousPlayer Jogador a substituir
     * @param player Jogador a adicionar
     */
    public void replacePlayer(Player previousPlayer, Player player) {
        int index = players.indexOf(previousPlayer);
        players.set(index, player);
    }

    @Override
    public void showData() {
        System.out.println("Nome: " + name);
        System.out.println("Código: " + code);
        System.out.println("Ano de Fundação: " + foundationYear);
        System.out.println("Estádio: " + stadium.getName());
        System.out.println("Treinador: " + coach.getName());
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println(i + 1 + " - " + player.getName() + " - " + player.getPosition());
        }
    }
}