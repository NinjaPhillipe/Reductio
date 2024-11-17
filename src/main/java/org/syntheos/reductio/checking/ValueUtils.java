package org.syntheos.reductio.checking;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class ValueUtils {
    private ValueUtils() {}
    
    public static <T> T getOrDefault(T value, T defaultValue, Predicate<T> isValid) {
        return isValid.test(value) ? value : defaultValue;
    }
    
    public static <T> T getOrDefault(T value, Supplier<T> defaultValue, Predicate<T> isValid) {
        return isValid.test(value) ? value : defaultValue.get();
    }

    public static <T> T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
    
    public static <T> T getOrDefault(T value, Supplier<T> defaultValue) {
        return value != null ? value : defaultValue.get();
    }
}
