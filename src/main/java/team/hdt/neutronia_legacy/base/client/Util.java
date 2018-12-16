package team.hdt.neutronia_legacy.base.client;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class Util {
    private static LongSupplier nanoTimeSupplier = System::nanoTime;

    public static long milliTime() {
        return nanoTime() / 1000000L;
    }

    private static long nanoTime() {
        return nanoTimeSupplier.getAsLong();
    }

    public static <T> T get(Supplier<T> p_get_0_) {
        return p_get_0_.get();
    }

}