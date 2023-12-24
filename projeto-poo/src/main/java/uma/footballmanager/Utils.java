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
     * @param value Valor a dispersar
     * @param originalMin Valor mínimo original
     * @param originalMax Valor máximo original
     * @return Valor disperso entre 0 e 100
     */
    public static int disperseValues(int value, int originalMin, int originalMax) {
        return (int) (((value - originalMin) * 100) / (originalMax - originalMin));
    }

}
