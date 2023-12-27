package uma.footballmanager;

import com.google.gson.annotations.SerializedName;

import java.util.Scanner;

public enum Positions {
    @SerializedName("Goalkeeper")
    GOALKEEPER,
    @SerializedName("Defender")
    DEFENDER,
    @SerializedName("Midfielder")
    MIDFIELDER,
    @SerializedName("Attacker")
    ATTACKER;

    public static Positions generetePosition() {
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
