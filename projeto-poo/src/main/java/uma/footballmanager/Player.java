package uma.footballmanager;

import com.google.gson.annotations.SerializedName;
import java.util.Scanner;

public class Player extends Person implements MenuData {
    @SerializedName("statistics")
    private PlayerStats stats;
    private Positions position;

    private boolean injured;

    // TODO !WARNING!: É preciso ter cuidado com as verificações, existem jogadores que têm o mesmo nickname

    public Player(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, PlayerStats stats, Positions position) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.stats = stats;
        this.position = position;
        this.injured = false;
    }

    public Player(String name, String firstName, String lastName, Birth birth, String nationality, int height, int weight, PlayerStats stats, Positions position, boolean injured) {
        super(name, firstName, lastName, birth, nationality, height, weight);
        this.stats = stats;
        this.position = position;
        this.injured = injured;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public Positions getPosition() {
        return position;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }

    @Override
    public void showData() {
        System.out.println("Nome do jogador: " + getName());
        System.out.println("Posição: " + getPosition());
        System.out.println("Lesão: " + isInjured());
        System.out.println("Estatísticas do jogador: " + getStats());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getName().equals(player.getName());
    }

    private static Positions generatePlayerPosition() {
        try (Scanner sc = new Scanner(System.in)) {
            Positions playerPosition = null;

            while (playerPosition == null) {
                System.out.print("Escolha a posição do jogador (A para Avançado, M para Médio, D para Defesa, G para Guarda-Redes): ");
                char choice = sc.next().charAt(0);

                switch (choice) {
                    case 'A':
                        playerPosition = Positions.ATTACKER;
                        break;
                    case 'M':
                        playerPosition = Positions.MIDFIELDER;
                        break;
                    case 'D':
                        playerPosition = Positions.DEFENDER;
                        break;
                    case 'G':
                        playerPosition = Positions.GOALKEEPER;
                        break;
                    default:
                        System.out.println("Escolha inválida. Tente novamente.");
                }
            }
            return playerPosition;
        }
    }
}
