package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.Scanner;

public enum Positions {
    @SerializedName("Goalkeeper")
    GOALKEEPER("Guarda-Redes"),
    @SerializedName("Defender")
    DEFENDER("Defesa"),
    @SerializedName("Midfielder")
    MIDFIELDER("Médio"),
    @SerializedName("Attacker")
    ATTACKER("Avançado");

    private final String name;

    Positions(String position) {
        this.name = position;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Gera uma posição com base nos dados inseridos pelo utilizador
     * @return Posição
     */
    public static Positions generetePosition() {
        Scanner sc = new Scanner(System.in);
        Positions playerPosition = null;

        while (playerPosition == null) {
            System.out.println("Escolha a posição do jogador (A para Avançado, M para Médio, D para Defesa, G para Guarda-Redes): ");
            String val = sc.next();
            char choice = val.toUpperCase().charAt(0);

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
