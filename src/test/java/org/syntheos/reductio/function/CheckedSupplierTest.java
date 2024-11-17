package org.syntheos.reductio.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckedSupplierTest {
    
    @Test
    void get() throws Exception {
        CheckedSupplier<Integer> supplier = () -> 1;
        assertEquals(1, supplier.get());
    }
    
    @Test
    void get_ThrowsException() {
        CheckedSupplier<Integer> supplier = () -> {
            throw new Exception();
        };
        try {
            supplier.get();
        } catch (Exception e) {
            assertEquals(Exception.class, e.getClass());
        }
    }
}
