package org.syntheos.reductio.function;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    @SuppressWarnings("java:S112")
    R apply(T t) throws Exception;
}
