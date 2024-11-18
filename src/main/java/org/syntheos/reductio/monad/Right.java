package org.syntheos.reductio.monad;

import java.util.function.Function;

public record Right<L, R>(R value) implements Either<L, R> {
    @SuppressWarnings("unchecked")
    @Override
    public <T> Either<T, R> mapLeft(Function<L, T> f) {
        return (Either<T, R>) this; // can safely cast because the left value is not present and record is immutable
    }
    
    @Override
    public <T> Either<L, T> mapRight(Function<R, T> f) {
        return new Right<>(f.apply(value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Either<T, R> flatMapLeft(Function<L, Either<T, R>> f) {
        return (Either<T, R>) this;
    }

    @Override
    public <T> Either<L, T> flatMapRight(Function<R, Either<L, T>> f) {
        return f.apply(value);
    }

    @Override
    public <T> T fold(Function<L, T> left, Function<R, T> right) {
        return right.apply(value);
    }
}
