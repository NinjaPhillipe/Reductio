package org.syntheos.reductio.checking;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.function.Predicate;
import java.util.function.Supplier;

class ValueUtilsTest {

    @Test
    void getOrDefault_ReturnsValue_WhenValueIsValid() {
        Predicate<String> isValid = value -> value.length() > 3;
        String result = ValueUtils.getOrDefault("valid", "default", isValid);
        assertEquals("valid", result);
    }

    @Test
    void getOrDefault_ReturnsDefaultValue_WhenValueIsInvalid() {
        Predicate<String> isValid = value -> value.length() > 3;
        String result = ValueUtils.getOrDefault("no", "default", isValid);
        assertEquals("default", result);
    }

    @Test
    void getOrDefault_ReturnsValue_WhenValueIsValid_WithSupplier() {
        Predicate<String> isValid = value -> value.length() > 3;
        Supplier<String> defaultValue = () -> "default";
        String result = ValueUtils.getOrDefault("valid", defaultValue, isValid);
        assertEquals("valid", result);
    }

    @Test
    void getOrDefault_ReturnsDefaultValue_WhenValueIsInvalid_WithSupplier() {
        Predicate<String> isValid = value -> value.length() > 3;
        Supplier<String> defaultValue = () -> "default";
        String result = ValueUtils.getOrDefault("no", defaultValue, isValid);
        assertEquals("default", result);
    }

    @Test
    void getOrDefault_ReturnsValue_WhenValueIsNotNull() {
        String result = ValueUtils.getOrDefault("value", "default");
        assertEquals("value", result);
    }

    @Test
    void getOrDefault_ReturnsDefaultValue_WhenValueIsNull() {
        String result = ValueUtils.getOrDefault(null, "default");
        assertEquals("default", result);
    }

    @Test
    void getOrDefault_ReturnsValue_WhenValueIsNotNull_WithSupplier() {
        Supplier<String> defaultValue = () -> "default";
        String result = ValueUtils.getOrDefault("value", defaultValue);
        assertEquals("value", result);
    }

    @Test
    void getOrDefault_ReturnsDefaultValue_WhenValueIsNull_WithSupplier() {
        Supplier<String> defaultValue = () -> "default";
        String result = ValueUtils.getOrDefault(null, defaultValue);
        assertEquals("default", result);
    }
}