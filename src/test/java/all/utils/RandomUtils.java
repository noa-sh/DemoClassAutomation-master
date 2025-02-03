package all.utils;

import java.util.Random;

public class RandomUtils {
    /**
     * Generates a random integer with the specified number of digits.
     *
     * @param length the number of digits for the random integer
     * @return a random integer with the specified number of digits
     */
    public static int getRandomInt(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        Random random = new Random();
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        return random.nextInt((max - min) + 1) + min;
    }
}
