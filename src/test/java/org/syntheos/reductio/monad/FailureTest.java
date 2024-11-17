package org.syntheos.reductio.monad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FailureTest {

    @Test
    void failure_isFailure() {
        var f = new Failure<>(new Exception());
        assertTrue(f.isFailure());
    }
    
    @Test
    void failure_isNotSuccess() {
        var f = new Failure<>(new Exception());
        assertFalse(f.isSuccess());
    }
    
    @Test
    void failure_toOption_isEmpty() {
        var f = new Failure<>(new Exception());
        assertTrue(f.toOption().isEmpty());
    }
    
    @Test
    void failure_toOptional_isEmpty() {
        var f = new Failure<>(new Exception());
        assertTrue(f.toOptional().isEmpty());
    }
}
