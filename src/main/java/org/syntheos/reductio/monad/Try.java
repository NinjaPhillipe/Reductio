package org.syntheos.reductio.monad;

import org.syntheos.reductio.function.CheckedFunction;
import org.syntheos.reductio.function.CheckedSupplier;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public sealed interface Try<T> permits Success, Failure {
    boolean isSuccess();
    boolean isFailure();
    
    <U> Try<U> map(Function<? super T, ? extends U> mapper);
    /**
     * Map a function that throws a checked exception
     * @param mapper
     * @param <U>
     * @return
     */
    <U> Try<U> mapC(CheckedFunction<? super T, ? extends U> mapper);
    
    <U> Try<U> flatMap(Function<? super T, Try<U>> mapper);
    
    <U> Try<U> flatMapC(CheckedFunction<? super T, Try<U>> mapper);
    
    T getOrElse(T defaultValue);
    
    Try<T> peek(Consumer<? super T> action);
    
    Try<T> recover(Function<? super Exception, ? extends T> mapper);
    
    Option<T> toOption();
    
    // for standard library interop
    Optional<T> toOptional();
    
    static <T> Try<T> of(CheckedSupplier<T> f) {
        requireNonNull(f);
        try {
            return new Success<>(f.get());
        } catch (Exception e) {
            return new Failure<>(e);
        }
    }
}
