package org.syntheos.reductio.monad;

import org.syntheos.reductio.function.CheckedFunction;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public record Success<T>(T value) implements Try<T> {
    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
        try {
            return new Success<>(mapper.apply(value));
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }
    
    @Override
    public <U> Try<U> mapC(CheckedFunction<? super T, ? extends U> mapper) {
        try {
            return new Success<>(mapper.apply(value));
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }

    @Override
    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
        try {
            return mapper.apply(value);
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }

    @Override
    public <U> Try<U> flatMapC(CheckedFunction<? super T, Try<U>> mapper) {
        try {
            return mapper.apply(value);
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }

    @Override
    public T getOrElse(T defaultValue) {
        return value == null ? defaultValue : value;
    }

    @Override
    public Try<T> peek(Consumer<? super T> action) {
        try {
            action.accept(value);
            return this;
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }

    @Override
    public Try<T> recover(Function<? super Exception, ? extends T> mapper) {
        return this; // no need to recover from success
    }

    @Override
    public Option<T> toOption() {
        return Option.of(value);
    }

    @Override
    public Optional<T> toOptional() {
        return Optional.ofNullable(value);
    }
}
