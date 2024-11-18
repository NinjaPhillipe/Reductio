package org.syntheos.reductio.monad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EitherTest {
    
    @Test
    void left_mapLeft() {
        final Either<Integer, String> either = Either.left(12);
        final Either<String, String> eitherRes = either.mapLeft(s -> s + "!");
        
        switch (eitherRes) {
            case Left<String, String> left -> assertEquals("12!", left.value());
            case Right<String, String> right -> throw new IllegalStateException("Right value not expected");
        }
    }
    
    @Test
    void left_mapRight() {
        final Either<Integer, String> either = Either.left(12);
        final Either<Integer, Integer> eitherRes = either.mapRight(Integer::parseInt);

        switch (eitherRes) {
            case Left<Integer, Integer> left -> assertEquals(12, left.value());
            case Right<Integer, Integer> right -> throw new IllegalStateException("Right value not expected");
        }
    }
    
    @Test
    void right_mapLeft() {
        final Either<Integer, Integer> either = Either.right(42);
        final Either<String, Integer> eitherRes = either.mapLeft(s -> s + "!");
        
        switch (eitherRes) {
            case Left<String, Integer> left -> throw new IllegalStateException("Left value not expected");
            case Right<String, Integer> right -> assertEquals(42, right.value());
        }
    }
    
    @Test
    void right_mapRight() {
        final Either<String, Integer> either = Either.right(42);
        final Either<String, String> eitherRes = either.mapRight(i -> i + "!");

        switch (eitherRes) {
            case Left<String, String> left -> throw new IllegalStateException("Left value not expected");
            case Right<String, String> right -> assertEquals("42!", right.value());
        }
    }
    
    @Test
    void left_flatMapLeft() {
        final Either<Integer, String> either = Either.left(12);
        final Either<String, String> eitherRes = either.flatMapLeft(s -> Either.left(s + "!"));
        
        switch (eitherRes) {
            case Left<String, String> left -> assertEquals("12!", left.value());
            case Right<String, String> right -> throw new IllegalStateException("Right value not expected");
        }
    }
    
    @Test
    void left_flatMapRight() {
        final Either<Integer, String> either = Either.left(12);
        final Either<Integer, String> eitherRes = either.flatMapRight(s -> Either.right(s + "!"));

        switch (eitherRes) {
            case Left<Integer, String> left -> assertEquals(12, left.value());
            case Right<Integer, String> right -> throw new IllegalStateException("Right value not expected");
        }
    }
    
    @Test
    void right_flatMapLeft() {
        final Either<Integer, String> either = Either.right("42");
        final Either<String, String> eitherRes = either.flatMapLeft(s -> Either.left(s + "!"));
        
        switch (eitherRes) {
            case Left<String, String> left -> throw new IllegalStateException("Left value not expected");
            case Right<String, String> right -> assertEquals("42", right.value());
        }
    }
    
    @Test
    void right_flatMapRight() {
        final Either<Integer, String> either = Either.right("42");
        final Either<Integer, Integer> eitherRes = either.flatMapRight(s -> Either.right(Integer.parseInt(s)));

        switch (eitherRes) {
            case Left<Integer, Integer> left -> throw new IllegalStateException("Left value not expected");
            case Right<Integer, Integer> right -> assertEquals(42, right.value());
        }
    }
    
    @Test
    void left_fold() {
        final Either<Integer, String> either = Either.left(12);
        final String res = either.fold(
            i -> i.toString() + "L",
            i -> i + "R"
        );
        
        assertEquals("12L", res);
    }
    
    @Test
    void right_fold() {
        final Either<Integer, String> either = Either.right("12");
        final String res = either.fold(
            i -> i.toString() + "L",
            i -> i + "R"
        );
        
        assertEquals("12R", res);
    }
}
