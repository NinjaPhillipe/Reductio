package org.syntheos.reductio.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckedFunctionTest {
    @Test
    void apply_ReturnsResult_WhenNoExceptionThrown() throws Exception {
        CheckedFunction<String, Integer> function = String::length;
        assertEquals(5, function.apply("hello"));
    }

    @Test
    void apply_ThrowsException_WhenExceptionThrown() {
        CheckedFunction<String, Integer> function = s -> { throw new Exception("error"); };
        assertThrows(Exception.class, () -> function.apply("hello"));
    }
    
    @Test
    void apply_ThrowsException() {
        CheckedFunction<String, Integer> function = Integer::parseInt ;
        assertThrows(NumberFormatException.class, () -> function.apply(null));
    }
}
