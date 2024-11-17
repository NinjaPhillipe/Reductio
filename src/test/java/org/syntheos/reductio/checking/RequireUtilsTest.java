package org.syntheos.reductio.checking;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RequireUtilsTest {

    @Test
    void requireTrue_DoesNotThrow_WhenValueIsTrue() {
        assertDoesNotThrow(() -> RequireUtils.requireTrue(true, IllegalArgumentException::new));
    }

    @Test
    void requireTrue_ThrowsException_WhenValueIsFalse() {
        assertThrows(IllegalArgumentException.class, () -> RequireUtils.requireTrue(false, IllegalArgumentException::new));
    }

    @Test
    void requireFalse_DoesNotThrow_WhenValueIsFalse() {
        assertDoesNotThrow(() -> RequireUtils.requireFalse(false, IllegalArgumentException::new));
    }

    @Test
    void requireFalse_ThrowsException_WhenValueIsTrue() {
        assertThrows(IllegalArgumentException.class, () -> RequireUtils.requireFalse(true, IllegalArgumentException::new));
    }

    @Test
    void require_DoesNotThrow_WhenPredicateIsTrue() {
        Predicate<String> isNotEmpty = value -> !value.isEmpty();
        assertDoesNotThrow(() -> RequireUtils.require("value", isNotEmpty, IllegalArgumentException::new));
    }

    @Test
    void require_ThrowsException_WhenPredicateIsFalse() {
        Predicate<String> isNotEmpty = value -> !value.isEmpty();
        assertThrows(IllegalArgumentException.class, () -> RequireUtils.require("", isNotEmpty, IllegalArgumentException::new));
    }
}