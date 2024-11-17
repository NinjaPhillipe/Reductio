package org.syntheos.reductio.monad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuccessTest {
    @Test
    void success_isSuccess() {
        var s = new Success<>(1);
        assertTrue(s.isSuccess());
    }
    
    @Test
    void success_isNotFailure() {
        var s = new Success<>(1);
        assertFalse(s.isFailure());
    }
    
    @Test
    void success_toOption_isPresent() {
        var s = new Success<>(1);
        assertTrue(s.toOption().isPresent());
    }
    
    @Test
    void success_toOptional_isPresent() {
        var s = new Success<>(1);
        assertTrue(s.toOptional().isPresent());
    }
}
