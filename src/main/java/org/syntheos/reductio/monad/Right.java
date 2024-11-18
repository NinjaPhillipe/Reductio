package org.syntheos.reductio.monad;

import java.util.function.Function;

public record Right<L, R>(R value) implements Either<L, R> {
    @Override
    public L left() {
        throw new IllegalStateException("Left value not present");
    }

    @Override
    public R right() {
        return value;
    }

    @Override
    public <T> Either<L, T> mapRight(Function<R, T> f) {
        return new Right<>(f.apply(value));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Either<T, R> mapLeft(Function<L, T> f) {
        return (Either<T, R>) this; // can safely cast because the left value is not present and record is immutable
    }
}
