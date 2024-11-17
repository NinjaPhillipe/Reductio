package org.syntheos.reductio.monad;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public record Option<T>(T value) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Option<?> EMPTY = new Option<>(null);

    @SuppressWarnings("unchecked")
    public static <T> Option<T> of(T value) {
        return value == null ? (Option<T>) EMPTY : new Option<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> empty() {
        return (Option<T>) EMPTY;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        if (value == null) {
            throw new IllegalStateException("No value present");
        }
        return value;
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<T> supplier) {
        return value != null ? value : supplier.get();
    }

    public Option<T> filter(Predicate<T> predicate) {
        if (value == null) {
            return this;
        } else {
            return predicate.test(value) ? this : empty();
        }
    }

    public <U> Option<U> map(Function<T, U> mapper) {
        if (value == null) {
            return empty();
        } else {
            return of(mapper.apply(value));
        }
    }

    public <U> Option<U> flatMap(Function<T, Option<U>> mapper) {
        if (value == null) {
            return empty();
        } else {
            return mapper.apply(value);
        }
    }

    public void ifPresent(Consumer<T> consumer) {
        if (value != null) {
            consumer.accept(value);
        }
    }

    public void ifPresentOrElse(Consumer<T> consumer, Runnable runnable) {
        if (value != null) {
            consumer.accept(value);
        } else {
            runnable.run();
        }
    }

    public <X extends Throwable> T orElseThrow(Supplier<X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }
    
    public Stream<T> stream() {
        return value != null ? Stream.of(value) : Stream.empty();
    }
    
    public static <T> Option<T> ofOptional(Optional<T> optional) {
        requireNonNull(optional, "optional is null");
        return optional.map(Option::of).orElseGet(Option::empty);
    }
}
