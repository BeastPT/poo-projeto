package uma.footballmanager;

import java.util.Random;

public class Utils {
    private static final Random random;

    static {
        random = new Random();
    }

    /**
     * @param min Valor mínimo
     * @param max Valor máximo
     * @return Valor aleatório entre min e max
     */
    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * @return Valor aleatório entre 0 e 100
     */
    public static int getRandomInt() {
        return getRandomInt(0, 100);
    }

    /**
     * @param value       Valor a dispersar
     * @param originalMin Valor mínimo original
     * @param originalMax Valor máximo original
     * @return Valor disperso entre 0 e 100
     */
    public static int disperseValues(int value, int originalMin, int originalMax) {
        return ((value - originalMin) * 100) / (originalMax - originalMin);
    }

    /**
     * @param length Tamanho da string
     * @return String aleatória com o tamanho especificado de a-z e 0-9
     */
    public static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
