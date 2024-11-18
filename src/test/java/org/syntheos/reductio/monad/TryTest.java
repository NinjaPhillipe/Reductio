package org.syntheos.reductio.monad;

import org.junit.jupiter.api.Test;
import org.syntheos.reductio.function.CheckedFunction;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TryTest {
    
    @Test
    void try_Failure_map() {
        var t = Try.of(() -> Integer.parseInt("a"))
                .map(i -> i + 1);
        
        switch (t) {
            case Success<Integer> s -> throw new IllegalStateException("Success not expected");
            case Failure<Integer> f -> assertInstanceOf(NumberFormatException.class, f.exc());
        }
    }
    
    @Test
    void try_Failure_mapc() {
        var t = Try.of(() -> "a")
                .mapC(Integer::parseInt);
        
        switch (t) {
            case Success<Integer> s -> throw new IllegalStateException("Success not expected");
            case Failure<Integer> f -> assertInstanceOf(NumberFormatException.class, f.exc());
        }
    }
    
    @Test
    void try_flatmap() {
        var t = Try.of(() -> Integer.parseInt("a"))
                .flatMap(i -> Try.of(() -> i + 1));
        
        assertInstanceOf(Failure.class, t);
    }
    
    @Test
    void try_flatmapC() {
        final CheckedFunction<Integer, Try<Integer>> fun = i -> Try.of(() -> i + 1);
        
        var t = Try.of(() -> 3)
                .flatMapC(fun);
        
        switch (t) {
            case Success<Integer> s -> assertEquals(4, s.toOption().orElseThrow(() -> new IllegalStateException("Option is empty")));
            case Failure<Integer> f -> throw new IllegalStateException("Failure not expected");
        }
    }
    
    @Test
    void try_getOrElse() {
        var t = Try.of(() -> Integer.parseInt("a"))
                .getOrElse(12);
        
        assertEquals(12, t);
    }
    
    @Test
    void try_peek() {
        final int START_VALUE = 3;
        
        final AtomicInteger a = new AtomicInteger();
        final Consumer<Integer> c = i -> a.set(i+1);
        
        
        var t = Try.of(() -> START_VALUE)
                .peek(c);
        
        switch (t) {
            case Success<Integer> s -> assertEquals(START_VALUE, s.toOption().orElseThrow(() -> new IllegalStateException("Option is empty")));
            case Failure<Integer> f -> throw new IllegalStateException("Failure not expected");
        }
        
        assertEquals(START_VALUE + 1, a.get());
    }
    
    @Test
    void try_recovery_onSuccess() {
        final int START_VALUE = 3;
        final int RECOVERY_VALUE = 12;
        
        var t = Try.of(() -> START_VALUE)
                .recover(e -> RECOVERY_VALUE);
        
        switch (t) {
            case Success<Integer> s -> assertEquals(START_VALUE, s.toOption().orElseThrow(() -> new IllegalStateException("Option is empty")));
            case Failure<Integer> f -> throw new IllegalStateException("Failure not expected");
        }
    }
    
    @Test
    void try_recovery_onFailure() {
        final int RECOVERY_VALUE = 12;

        var t = Try.of(() -> Integer.parseInt("a"))
                .recover(e -> RECOVERY_VALUE);
        
        switch (t) {
            case Success<Integer> s -> assertEquals(RECOVERY_VALUE, s.toOption().orElseThrow(() -> new IllegalStateException("Option is empty")));
            case Failure<Integer> f -> throw new IllegalStateException("Failure not expected");
        }
    }
}
