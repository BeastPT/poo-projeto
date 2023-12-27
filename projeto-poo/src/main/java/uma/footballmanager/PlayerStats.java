package uma.footballmanager;

import java.util.Scanner;

public record PlayerStats(Integer defense, Integer attack, Integer aggressive) implements IMenuData {
    public static PlayerStats generetePlayerStats() {
        Scanner sc = new Scanner(System.in);
        int attackStats, defenseStats, aggression;

        do {
            System.out.print("Insira as estatísticas de ataque do jogador (0-100): ");
            while (!sc.hasNextInt()) {
                System.out.print("Inválido. Por favor insira um inteiro para o ataque de (0-100): ");
                sc.next();
            }
            attackStats = sc.nextInt();
        } while (isInvalidStats(attackStats));

        do {
            System.out.print("Insira as estatísticas de defesa do jogador (0-100): ");
            while (!sc.hasNextInt()) {
                System.out.print("Inválido. Por favor insira um inteiro para a defesa de (0-100): ");
                sc.next();
            }
            defenseStats = sc.nextInt();
        } while (isInvalidStats(defenseStats));

        do {
            System.out.print("Insira a agressividade do jogador (0-100): ");
            while (!sc.hasNextInt()) {
                System.out.print("Inválido. Por favor insira um inteiro para a agressividade de (0-100): ");
                sc.next();
            }
            aggression = sc.nextInt();
        } while (isInvalidStats(aggression));
        return new PlayerStats(attackStats, defenseStats, aggression);
    }

    private static boolean isInvalidStats(int stats) {
        return stats < 0 || stats > 100;
    }

    @Override
    public void showData() {
        System.out.println("Estatísticas do jogador: ");
        System.out.println(" - Ataque: " + attack());
        System.out.println(" - Defesa: " + defense());
        System.out.println(" - Agressividade: " + aggressive());
    }
}
