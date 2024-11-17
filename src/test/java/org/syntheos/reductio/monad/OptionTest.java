package org.syntheos.reductio.monad;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Optional;

class OptionTest {

    @Test
    void of_ReturnsOptionWithValue_WhenValueIsNotNull() {
        Option<String> option = Option.of("value");
        assertTrue(option.isPresent());
        assertEquals("value", option.get());
    }

    @Test
    void of_ReturnsEmptyOption_WhenValueIsNull() {
        Option<String> option = Option.of(null);
        assertTrue(option.isEmpty());
    }

    @Test
    void empty_ReturnsEmptyOption() {
        Option<String> option = Option.empty();
        assertTrue(option.isEmpty());
    }

    @Test
    void get_ThrowsException_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        assertThrows(IllegalStateException.class, option::get);
    }

    @Test
    void orElse_ReturnsValue_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        assertEquals("value", option.orElse("default"));
    }

    @Test
    void orElse_ReturnsDefaultValue_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        assertEquals("default", option.orElse("default"));
    }

    @Test
    void orElseGet_ReturnsValue_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        assertEquals("value", option.orElseGet(() -> "default"));
    }

    @Test
    void orElseGet_ReturnsDefaultValue_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        assertEquals("default", option.orElseGet(() -> "default"));
    }

    @Test
    void filter_ReturnsOption_WhenPredicateMatches() {
        Option<String> option = Option.of("value");
        assertTrue(option.filter(v -> v.equals("value")).isPresent());
    }

    @Test
    void filter_ReturnsEmptyOption_WhenPredicateDoesNotMatch() {
        Option<String> option = Option.of("value");
        assertTrue(option.filter(v -> v.equals("other")).isEmpty());
    }

    @Test
    void map_ReturnsMappedOption_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        Option<Integer> mappedOption = option.map(String::length);
        assertTrue(mappedOption.isPresent());
        assertEquals(5, mappedOption.get());
    }

    @Test
    void map_ReturnsEmptyOption_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        Option<Integer> mappedOption = option.map(String::length);
        assertTrue(mappedOption.isEmpty());
    }

    @Test
    void flatMap_ReturnsMappedOption_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        Option<Integer> mappedOption = option.flatMap(v -> Option.of(v.length()));
        assertTrue(mappedOption.isPresent());
        assertEquals(5, mappedOption.get());
    }

    @Test
    void flatMap_ReturnsEmptyOption_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        Option<Integer> mappedOption = option.flatMap(v -> Option.of(v.length()));
        assertTrue(mappedOption.isEmpty());
    }

    @Test
    void ifPresent_ExecutesConsumer_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        StringBuilder result = new StringBuilder();
        option.ifPresent(result::append);
        assertEquals("value", result.toString());
    }

    @Test
    void ifPresentOrElse_ExecutesConsumer_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        StringBuilder result = new StringBuilder();
        option.ifPresentOrElse(result::append, () -> result.append("default"));
        assertEquals("value", result.toString());
    }

    @Test
    void ifPresentOrElse_ExecutesRunnable_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        StringBuilder result = new StringBuilder();
        option.ifPresentOrElse(result::append, () -> result.append("default"));
        assertEquals("default", result.toString());
    }

    @Test
    void orElseThrow_ThrowsException_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        assertThrows(IllegalStateException.class, () -> option.orElseThrow(IllegalStateException::new));
    }

    @Test
    void orElseThrow_ReturnsValue_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        assertEquals("value", option.orElseThrow(IllegalStateException::new));
    }

    @Test
    void stream_ReturnsStreamWithValue_WhenOptionIsPresent() {
        Option<String> option = Option.of("value");
        assertEquals(1, option.stream().count());
    }

    @Test
    void stream_ReturnsEmptyStream_WhenOptionIsEmpty() {
        Option<String> option = Option.empty();
        assertEquals(0, option.stream().count());
    }

    @Test
    void ofOptional_ReturnsOptionWithValue_WhenOptionalIsPresent() {
        Optional<String> optional = Optional.of("value");
        Option<String> option = Option.ofOptional(optional);
        assertTrue(option.isPresent());
        assertEquals("value", option.get());
    }

    @Test
    void ofOptional_ReturnsEmptyOption_WhenOptionalIsEmpty() {
        Optional<String> optional = Optional.empty();
        Option<String> option = Option.ofOptional(optional);
        assertTrue(option.isEmpty());
    }

    @Test
    void ofOptional_ThrowsException_WhenOptionalIsNull() {
        assertThrows(NullPointerException.class, () -> Option.ofOptional(null));
    }
}