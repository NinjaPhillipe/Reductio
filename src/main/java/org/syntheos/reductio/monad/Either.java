package org.syntheos.reductio.monad;

import java.util.function.Function;

public sealed interface Either<L, R> permits Left, Right {
    L left();
    R right();

    <T> Either<T, R> mapLeft(Function<L, T> f);
    <T> Either<L, T> mapRight(Function<R, T> f);
    
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }
    
    static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }
}
