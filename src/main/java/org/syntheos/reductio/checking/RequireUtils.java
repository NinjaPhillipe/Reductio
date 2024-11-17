package org.syntheos.reductio.checking;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class RequireUtils {
    private RequireUtils() {}

    public static <T extends  Throwable> void requireTrue(boolean value, Supplier<T> exception) throws  T {
        if (!value) {
            throw exception.get();
        }
    }

    public static <T extends  Throwable> void requireFalse(boolean value, Supplier<T> exception) throws  T {
        if (value) {
            throw exception.get();
        }
    }

    public static <V, T extends Throwable> void require(V value, Predicate<V> predicate, Supplier<T> exception) throws T {
        if (!predicate.test(value)) {
            throw exception.get();
        }
    }
}
