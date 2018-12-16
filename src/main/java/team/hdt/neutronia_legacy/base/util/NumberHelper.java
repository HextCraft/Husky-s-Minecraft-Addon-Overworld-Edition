package team.hdt.neutronia_legacy.base.util;

import java.util.Random;

public class NumberHelper {
    private static final Random RAND = new Random();

    public static int getNumberInRange(int min, int max, Random rand) {
        return rand.nextInt(max - min + 1) + min;
    }

    public static Random getRand() {
        return RAND;
    }
}