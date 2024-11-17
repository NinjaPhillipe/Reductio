package org.syntheos.reductio.function;

@FunctionalInterface
public interface CheckedSupplier<T> {
    @SuppressWarnings("java:S112")
    T get() throws Exception;
}
