package org.syntheos.reductio.monad;

import java.util.function.Function;

public sealed interface Either<L, R> permits Left, Right {
    <T> Either<T, R> mapLeft(Function<L, T> f);
    <T> Either<L, T> mapRight(Function<R, T> f);
    
    <T> Either<T, R> flatMapLeft(Function<L, Either<T, R>> f);
    <T> Either<L, T> flatMapRight(Function<R, Either<L, T>> f);
    
    <T> T fold(Function<L, T> left, Function<R, T> right);
    
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }
    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}
