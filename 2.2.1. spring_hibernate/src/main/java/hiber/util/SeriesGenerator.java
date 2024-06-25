package hiber.util;

import java.util.Random;

public class SeriesGenerator {

    private static final int LIMIT_SERIES = 1_000_000_000;

    public static int generate() {
        return new Random().nextInt(LIMIT_SERIES);
    }
}
