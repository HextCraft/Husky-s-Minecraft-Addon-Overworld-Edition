package team.abnormal.neutronia.base.utils;

import java.util.function.Consumer;

public class Utils {

    public static <T> T consume(T object_1, Consumer<T> consumer_1) {
        consumer_1.accept(object_1);
        return object_1;
    }

}
