package org.syntheos.reductio.monad;

import java.util.function.Function;

public record Left<L, R>(L value) implements Either<L, R> {
    @Override
    public <T> Either<T, R> mapLeft(Function<L, T> f) {
        return new Left<>(f.apply(value));
    }
    @SuppressWarnings("unchecked")
    @Override
    public <T> Either<L, T> mapRight(Function<R, T> f) {
        return (Either<L, T>) this; // can safely cast because the right value is not present and record is immutable
    }

    @Override
    public <T> Either<T, R> flatMapLeft(Function<L, Either<T, R>> f) {
        return f.apply(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Either<L, T> flatMapRight(Function<R, Either<L, T>> f) {
        return (Either<L, T>) this;
    }
}
