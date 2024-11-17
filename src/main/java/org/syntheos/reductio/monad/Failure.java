package org.syntheos.reductio.monad;

import org.syntheos.reductio.function.CheckedFunction;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public record Failure<T>(Exception e) implements Try<T> {
    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
        // can return this because it is a failure and failure is a record and records are immutable
        return (Try<U>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Try<U> mapC(CheckedFunction<? super T, ? extends U> mapper) {
        // can return this because it is a failure and failure is a record and records are immutable
        return (Try<U>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        // can return this because it is a failure and failure is a record and records are immutable
        return (Try<U>) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <U> Try<U> flatMapC(CheckedFunction<? super T, Try<U>> mapper) {
        // can return this because it is a failure and failure is a record and records are immutable
        return (Try<U>) this;
    }

    @Override
    public T getOrElse(T defaultValue) {
        return defaultValue;
    }

    @Override
    public Try<T> peek(Consumer<? super T> action) {
        return this;
    }

    @Override
    public Try<T> recover(Function<? super Exception, ? extends T> mapper) {
        try {
            return new Success<>(mapper.apply(e));
        } catch (Exception ex) {
            return new Failure<>(ex);
        }
    }

    @Override
    public Option<T> toOption() {
        return Option.empty();
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }
}
